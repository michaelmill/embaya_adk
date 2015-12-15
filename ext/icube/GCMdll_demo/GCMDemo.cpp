//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube, Christian Aymon, 1376 Goumoens-la-Ville, Switzerland
//          infos@icube.ch
//
// (C)Copyright 2004-2012
//**********************************************************************
/*
Demo project for the DLMS security package. 
The program performs a ''high level security'' association and then 
reads several attributes from a capable meter.

The communication profile is HDLC over a serial-line. 
The HDLC addressed must be set according to the meter (see in the code).
WRAPPER can also be used, please see the other i-cube demo projects.

The demo version of GCMDll can only use the default encryption and 
authentication keys.

The following parameters can be changed (see here below):
- the system-title of this system, by default it is 'ABCDEFGH'
- if the meters needs an IEC MODE_E opening (usually with optical probe)
- if the optical probe ''echoes''
*/

#include "stdafx.h"
#include "interface\EZHdlcInterface.h"
#include "interface\XmlPduInterface.h"
#include "interface\GCMInterface.h"
#include "Exception.h"
#include "Misc.h"
#include <string>
#include <iostream>
using namespace std;

// Our system title (hex), 8 octets
string OwnSystemTitleHex = "4142434445464748"; // 'ABCDEFGH'

// Our HLS challenge (hex) 8 to 64 octets
string OwnHLSChallengeHex = "6162636465666768";

// Default keys (hex), 16 octets
string EncryptionKeyHex = "000102030405060708090A0B0C0D0E0F";
string AuthenticationKeyHex = "D0D1D2D3D4D5D6D7D8D9DADBDCDDDEDF";

// Set to true if our com port (optical probe) is echoing
static bool ComIsEchoing = false;

// Set to true if a MODE E opening is needed
static bool DoModeEOpening = true;

static THDLCParameters HDLCInstance;
static THDLCStatus HDLCStatus;
static TXmlPduStatus XmlPduStatus;
static HANDLE hCom;
static int RecCount = 0;
static int ResponseTimeout;

string OwnSystemTitleBin = HexStrToByteStr(OwnSystemTitleHex);
string OwnHLSChallengeBin = HexStrToByteStr(OwnHLSChallengeHex);
string EncryptionKeyBin = HexStrToByteStr(EncryptionKeyHex);
string AuthenticationKeyBin = HexStrToByteStr(AuthenticationKeyHex);
string DeviceSystemTitleBin;


// Misc routines
// *************

void DisplayOnNewLine()
{
	if (RecCount > 0)
	{
		cout << endl;
		RecCount = 0;
	}
}


void Writeln(const string s)
{
	DisplayOnNewLine();
	cout << s << endl;
}


void DisplayReceivedByte(unsigned const char c)
{
	if (RecCount == 0)
		printf("R:");
	printf("%02hX", c);
	RecCount++;
}


void DisplaySentString(unsigned const char * s, int Size)
{
	int i;
	DisplayOnNewLine();
	printf("S:");
	for (i = 0; i < Size; i++)
		printf("%02hX", unsigned char (s[i]));
	printf("\n");
}


void Throw(const string &s)
{
	throw Exception(s);
}


// HDLC Callback routines
// **********************

// HDLC calls ComGetBytes when it needs a (received) byte
BOOL __stdcall ComGetByteProc(unsigned char * pData)
{
	const int TimeoutValue = 2000;
	COMMTIMEOUTS CommTimeouts;
	DWORD ReadBytesQty;
	memset(&CommTimeouts, 0, sizeof(CommTimeouts));
	CommTimeouts.ReadIntervalTimeout = MAXDWORD;
	CommTimeouts.ReadTotalTimeoutMultiplier = MAXDWORD;
	CommTimeouts.ReadTotalTimeoutConstant = ResponseTimeout;
	SetCommTimeouts(hCom, &CommTimeouts);
	ReadFile(hCom, pData, 1, &ReadBytesQty, NULL);
	if (ReadBytesQty != 0)
	{
		// Show the received byte
		DisplayReceivedByte(*pData);
		return TRUE;
	} else
		return FALSE;
}


// HDLC calls ComPutBytesProc when it needs to send a string
void __stdcall ComPutBytesProc(const unsigned char * pData, int iSize)
{
	DWORD Dmy;
	PurgeComm(hCom, PURGE_RXCLEAR);
	WriteFile(hCom, pData, iSize, &Dmy, NULL);
	// Show the bytes sent
	DisplaySentString(pData, iSize);
	if (ComIsEchoing)
	{
		int EchoCount = 0;
		bool Timeout = FALSE;
		unsigned char b;
		FlushFileBuffers(hCom);
		while ((!Timeout) && (EchoCount < iSize))
		{
			Timeout = !ComGetByteProc(&b);
			EchoCount++;
		}
	}
}


// HDLC calls HDLCStateChangeProc when its internal state changes
void __stdcall HDLCStateChangeProc(void)
{
	switch (HDLCState(&HDLCInstance))
	{
	case HDLC_NDM:
		Writeln("HDLC in NDM");
		break;
	case HDLC_NRM:
		Writeln("HDLC in NRM");
		break;
	default:
		Writeln("HDLC state unexpected");
	}
}



// COM routines
// ************
void ComCreate(char * pComPortName)
{
	// Create the Com handle
	hCom = CreateFileA(pComPortName,
		GENERIC_READ | GENERIC_WRITE,
		0, // comm devices must be opened w/exclusive-access
		NULL, // no security attributes
		OPEN_EXISTING, // comm devices must use OPEN_EXISTING
		0, // not overlapped I/O
		NULL // hTemplate must be NULL for comm devices
		);

	if (hCom == INVALID_HANDLE_VALUE)
	{
		Throw("COM CreateFile failed with error " + IntToStr(GetLastError()));
	}
}


void ComConfigure(int ByteSize, int StopBits, int Parity, int Baud)
{
	DCB Dcb;
	DWORD Dmy;
	ClearCommError(hCom, &Dmy, NULL);
	GetCommState(hCom, &Dcb);
	Dcb.BaudRate = Baud;
	Dcb.ByteSize = ByteSize;
	Dcb.fBinary = true;
	Dcb.fOutxCtsFlow = false;
	Dcb.fOutxDsrFlow = false;
	Dcb.fDtrControl = DTR_CONTROL_ENABLE;
	Dcb.fDsrSensitivity = false;
	Dcb.fOutX = false;
	Dcb.fInX = false;
	Dcb.StopBits = StopBits;
	Dcb.Parity = Parity;
	if (!SetCommState(hCom, &Dcb))
	{
		Throw("COM SetComState failed with error " + IntToStr(GetLastError()));
	}
}


void ComPurge()
{
	DWORD ErrorCode;
	ClearCommError(hCom, &ErrorCode, NULL);
	PurgeComm(hCom, PURGE_TXABORT | PURGE_RXABORT | PURGE_TXCLEAR | PURGE_RXCLEAR);
}


void ComDoModeEOpening()
{
	const char CR = char(13);
	const char LF = char(10);
	const char ACK = char(6);
	const int MaxBaud = 115200;
	const int CharToBaud[] = {300, 600, 1200, 2400, 4800, 9600, 19200, 38400, 57600, 115200};
	char AckString [] = {ACK, '2', 0, '2', CR, LF, 0};
	bool Timeout, Done;
	string Response;
	unsigned char b, BaudChar;
	int Baud;
	string s;

	Writeln("Start mode E opening");
	// Configure port
	ComPurge();
	ComConfigure(7, ONESTOPBIT, EVENPARITY, 300);

	// Send identification string
	s = "/?!\r\n";
	ComPutBytesProc((const unsigned char*)s.c_str(), s.size());

	// Get and check response
	Timeout = FALSE;
	Done = FALSE;
	Response = "";
	while (!Done)
	{
		Timeout = !ComGetByteProc(&b);
		Response += b;
		Done = Timeout || (b == '\n');
	}

	if (Timeout)
	{
		Writeln("No response to mode E opening");
		return;
	}

	if ((Response.size() < 6) || (Response[0] != '/') ||
		!((Response[4] >= '0') && (Response[4] <= '9')))
	{
		Writeln("Mode E illegal response");
		return;
	}

	BaudChar = Response[4];
	// Determine requested baud
	do
	{
		Baud = CharToBaud[BaudChar-'0'];
		if ((BaudChar == '0') || (Baud <= MaxBaud))
			break;
		BaudChar--;
	} while (true);

	// Acknowledge
	AckString[2] = BaudChar;
	s = AckString;
	ComPutBytesProc((const unsigned char*)s.c_str(), s.size());
	FlushFileBuffers(hCom);

	// Instead of waiting for a response and (sometimes) having a collision
	// between the response and the baud changeover, we simply wait a while,
	// purge the comm channel and then set the new channel parameters.
	Sleep(2000);
	ComPurge();
	ComConfigure(8, ONESTOPBIT, NOPARITY, Baud);
	Writeln("--- baud changeover ---");
	Sleep(20);
	Writeln("Mode E opening done");
}


// Helper function working with strings
// ************************************

// Returns a string containing the Pdu (bin) of the passed Xml
string XMLPDU(const string Xml)
{
	TXmlPduBuffer Buffer;
	TXmlPduStatus Status;
	XmlToPdu(Xml.c_str(), Buffer, Status);
	if (Status.ErrorKind != XMLPDU_NO_ERROR)
		Throw(Status.ErrorMessage);
	string Result = string((const char *)XmlPduBufferData(Buffer), XmlPduBufferSize(Buffer));
	XmlPduReleaseBuffer(Buffer);
	return Result;
}

// Returns a string containing the Xml of the passed bin pdu.
string PDUXML(const string Pdu)
{
	string Result;
	TXmlPduBuffer Buffer;
	TXmlPduStatus Status;
	PduToXml((const unsigned char *)Pdu.c_str(), Pdu.length(), Buffer, Status);
	if (Status.ErrorKind != XMLPDU_NO_ERROR)
		Throw(Status.ErrorMessage);
	Result = string((const char *)XmlPduBufferData(Buffer), XmlPduBufferSize(Buffer));
	XmlPduReleaseBuffer(Buffer);
	return Result;
}

// Returns a string containing the bin ciphering of the bin plain pdu
string CIPHER(unsigned int SecurityControl, const string &PlainAPDU, const string &SystemTitleBin)
{
	const int MaxCipheredFrameSize = 500;
	unsigned int InOutCipheredFrameSize;
	TGCMStatus GCMStatus;
	char CipheredFrame [MaxCipheredFrameSize];
	InOutCipheredFrameSize = MaxCipheredFrameSize;
	Cipher(SecurityControl, (void*)PlainAPDU.c_str(), PlainAPDU.length(), (void*)SystemTitleBin.c_str(), 
		(void*)EncryptionKeyBin.c_str(),  (void*)AuthenticationKeyBin.c_str(),
		(void*)CipheredFrame, &InOutCipheredFrameSize, &GCMStatus);
	if (!GCMStatus.Ok)
		Throw (string(GCMStatus.Message));
	else
		return string(CipheredFrame, InOutCipheredFrameSize);
}


// Returns a string containing the plain bin pdu of a bin ciphered pdu
string DECIPHER(const string &CipheredPdu, const string &SystemTitleBin)
{
	const int MaxPlainFrameSize = 500;
	unsigned int InOutPlainFrameSize;
	TGCMStatus GCMStatus;
	char PlainFrame [MaxPlainFrameSize];
	InOutPlainFrameSize = MaxPlainFrameSize;
	DeCipher((void*)CipheredPdu.c_str(), CipheredPdu.length(), (void*)SystemTitleBin.c_str(), 
		(void*)EncryptionKeyBin.c_str(),  (void*)AuthenticationKeyBin.c_str(),
		(void*)PlainFrame, &InOutPlainFrameSize, &GCMStatus);
	if (!GCMStatus.Ok)
		Throw (string(GCMStatus.Message));
	else
		return string(PlainFrame, InOutPlainFrameSize);
}


// Performs an HDLC change, send the bin ToSend pdu and returs the bin response
string HDLC_SEND_RECEIVE(const string &ToSend)
{
	THDLCStatus Status;
	THDLCBuffer Buffer;
	HDLCSendReceive(&HDLCInstance, (const unsigned char*)ToSend.c_str(), ToSend.length(), &Buffer, &Status); 
	if (HDLCStatus.ErrorKind != HDLC_NO_ERROR)
		Throw(Status.ErrorMessage);
	string Result = string((const char *)HDLCBufferData(Buffer), HDLCBufferSize(Buffer));
	HDLCReleaseBuffer(&Buffer);
	return Result;
}


// In the passed Xml, locates the hex value of the Attribute and returns it
string ValueOf(const string &Xml, const string &Attribute)
{
	unsigned int m, n, i;
	bool Found = false;
	n = Xml.find(Attribute, 0);
	if (n != string::npos)
	{
		n += Attribute.length();
		m = Xml.find("=\"", n);
		if (m != string::npos)
		{
			m += 2;
			i = m;
			while ((m < Xml.length()) && (Xml[m] != '"'))
				m++;
			Found = true;
			return Xml.substr(i, m-i);
		}
	}
	if (!Found)
		Throw("Attribute " + Attribute + " not found");
}

// Returns the HLS bin response to the passed bin challenge
string ResponseToHLSChallenge(const string &Challenge, const string &SystemTitle)
{
	// Cipher the challenge using the provided parameters and using authentication
	string Result = CIPHER(AUTHENTICATION_ONLY, Challenge, SystemTitle);
	// Strip the unciphered challenge from the Result. We keep the ''header'' and the ''authentication tag''
	return Result.erase(5, Challenge.length());
}


// Returns true if the bin Response and bin Challenge match
bool CheckResponseToChallenge(const string &Challenge, const string &Response, const string &SystemTitle)
{
	string CipheredFrame = Response;
	// Insert the Challenge after the security control and FC in the received response
	CipheredFrame.insert(5, Challenge);
	string Challenge1 = DECIPHER(CipheredFrame, SystemTitle);
	return Challenge1.compare(Challenge) == 0;
}


// Returns the ServiceName (GetRequest, ActionRequest, etc..) of the passed Xml
string ServiceName(const string &Xml)
{
	int b, e;
	b = Xml.find("<", 0);
	b++;
	e = b+1;
	while ((e < Xml.length()) && (Xml[e] != '>') && (Xml[e] != ' '))
		e++;
	return Xml.substr(b, e-b);
}

// Returns the glo service of the passed Xml
string GetGloXml(unsigned int SecurityControl, const string &Xml)
{
	string PlainPduBin, CipheredPduBin;
	PlainPduBin = XMLPDU(Xml);
	CipheredPduBin = CIPHER(SecurityControl, PlainPduBin, OwnSystemTitleBin);
	return "<glo_" + ServiceName(Xml) + " Value='" + ByteStrToHexStr(CipheredPduBin) + "' />\n";
}

// Returns the service of the passed glo service
string GetUnGloXml(const string &Xml)
{
	string CipheredResponsePduHex, ResponsePduBin;
	CipheredResponsePduHex = ValueOf(Xml, ServiceName(Xml));
	ResponsePduBin = DECIPHER(HexStrToByteStr(CipheredResponsePduHex), DeviceSystemTitleBin);
	return PDUXML(ResponsePduBin);
}


// Performs an association of LN_WITH_CIPHERING context and HIGH_SECURITY_GMAC mechanism
void Associate()
{
	string InitiateRequestXml, GloInitiateRequestXml, AssociationRequestXml, AssociationRequestPduBin,
		AssociationResponsePduBin, AssociationResponseXml, Value, DeviceChallengeHex, ResponseToDeviceChallengeBin,
		ActionRequestXml, GloActionRequestXml, GloActionRequestPduBin,
		GloActionResponsePduBin, GloActionResponseXml, ActionResponseXml,
		DeviceResponseToChallengeHex;
		Writeln("");
		Writeln("*** ASSOCIATION ***");
	// In order to establish a ciphered assocition we first need a ciphered
	// InitiateRequest, we start with a plain InitiateRequest
	InitiateRequestXml = "\
		<InitiateRequest> \
          <ProposedDlmsVersionNumber Value='06' /> \
          <ProposedConformance> \n\
            <ConformanceBit Name='Action' /> \
            <ConformanceBit Name='SelectiveAccess' /> \
            <ConformanceBit Name='Set' /> \
            <ConformanceBit Name='Get' /> \
            <ConformanceBit Name='MultipleReferences' /> \
            <ConformanceBit Name='BlockTransferWithSetOrWrite' /> \
            <ConformanceBit Name='BlockTransferWithGetOrRead' /> \
          </ProposedConformance> \
          <ProposedMaxPduSize Value='00EF' /> \
        </InitiateRequest>";

	// Get the glo of this xml
	GloInitiateRequestXml = GetGloXml(AUTHENTICATION_AND_ENCRYPTION, InitiateRequestXml);

	// The Glo_InitiateRequest allows us to build the AssociationRequest. We have to provide our system title
	// as well as the glo_InitateRequest that we have just ciphered
	AssociationRequestXml = string("\n\
        <AssociationRequest> \n\
           <ApplicationContextName Value='LN_WITH_CIPHERING' /> \n\
           <CallingAPTitle Value='") + OwnSystemTitleHex + string("'/> \n\
           <SenderACSERequirements Value='1' /> \n\
           <MechanismName Value='HIGH_SECURITY_GMAC' /> \n\
           <CallingAuthenticationValue Value='") + OwnHLSChallengeHex + string("' /> \n") + 
           GloInitiateRequestXml + string("\
         </AssociationRequest>");
    Writeln(AssociationRequestXml);

	// Get the Pdu the association request
	AssociationRequestPduBin = XMLPDU(AssociationRequestXml);
	// Transfer it and get the response
	AssociationResponsePduBin = HDLC_SEND_RECEIVE(AssociationRequestPduBin);
	// Have the response xml
	AssociationResponseXml = PDUXML(AssociationResponsePduBin);
	Writeln(AssociationResponseXml);
	
	// Check that the AssociationResponse "Result" indicates success
	Value = ValueOf(AssociationResponseXml, "AssociationResult");
	if (HexToInt(Value) != 0)
		Throw("AssociationResponse.Result is not zero");

	// Extract the device's system title, save it globally
	DeviceSystemTitleBin = HexStrToByteStr(ValueOf(AssociationResponseXml, "RespondingAPTitle "));

	// We have to perform the two final passes of the HLS
	// We send an action request to the method 1 of the assocition object,
	// passing the processing of the challenge received from the device
	// First, get the meter's challenge
	DeviceChallengeHex = ValueOf(AssociationResponseXml, "RespondingAuthenticationValue");
	// Create the response to the challenge
	ResponseToDeviceChallengeBin = ResponseToHLSChallenge(HexStrToByteStr(DeviceChallengeHex), OwnSystemTitleBin);
	// Create the ActionRequest with the our response to the challenge
	ActionRequestXml = string("\
        <ActionRequest> \n\
          <ActionRequestNormal> \n\
            <InvokeIdAndPriority Value='C1' /> \n\
            <MethodDescriptor> \n\
              <ClassId Value='000F' /> \n\
              <InstanceId Value='0000280000FF' /> \n\
              <MethodId Value='01' /> \n\
            </MethodDescriptor> \n\
            <MethodInvocationParameters> \n\
              <OctetString Value='") + ByteStrToHexStr(ResponseToDeviceChallengeBin) + string("' /> \n\
            </MethodInvocationParameters> \n\
          </ActionRequestNormal> \n\
        </ActionRequest>\n");
    Writeln(ActionRequestXml);

	// Get the glo action request
	GloActionRequestXml = GetGloXml(AUTHENTICATION_AND_ENCRYPTION, ActionRequestXml);
	Writeln(GloActionRequestXml);

	// Make the ciphered request pdu and send it
	GloActionRequestPduBin = XMLPDU(GloActionRequestXml);
	GloActionResponsePduBin = HDLC_SEND_RECEIVE(GloActionRequestPduBin);

	// Decipher the response
	GloActionResponseXml = PDUXML(GloActionResponsePduBin);
	Writeln(GloActionResponseXml);

	// Get the ActionResponse
	ActionResponseXml = GetUnGloXml(GloActionResponseXml);
	Writeln(ActionResponseXml);

	// Do we have success
	Value = ValueOf(ActionResponseXml, "Result");
	if (Value.compare("Success") != 0)
		Throw("Action result is not success");

	// If success then get the remote response to our challenge
	DeviceResponseToChallengeHex = ValueOf(ActionResponseXml, "OctetString");
	// Check it
	if (!CheckResponseToChallenge(OwnHLSChallengeBin, HexStrToByteStr(DeviceResponseToChallengeHex), DeviceSystemTitleBin))
		Throw("Illegal device response to our challenge");
}


// Perform the passed request (Xml) using required Security
void DoRequest(unsigned int SecurityControl, const string &Xml)
{
	string GloRequestXml, GloRequestPduBin, GloResponsePduBin, GloResponseXml, CipheredRequestPduHex, ResponseXml;
	Writeln("");
	Writeln("*** " + ServiceName(Xml) + " ***");
	Writeln(Xml);
	GloRequestXml = GetGloXml(SecurityControl, Xml);
	Writeln("");
	Writeln(GloRequestXml);
	GloRequestPduBin = XMLPDU(GloRequestXml);
	GloResponsePduBin = HDLC_SEND_RECEIVE(GloRequestPduBin);
	GloResponseXml = PDUXML(GloResponsePduBin);
	Writeln("");
	Writeln(GloResponseXml);
	ResponseXml = GetUnGloXml(GloResponseXml);
	Writeln("");
	Writeln(ResponseXml);
}



// ENTRY POINT
// ***********
int _tmain(int argc, _TCHAR* argv[])
{
	Writeln("XmlPduVersion " + string(XmlPduVersion()));
	Writeln("EzHdlcVersion " + string(HDLCVersion()));
	Writeln("GCM Version " + string(GCMDllVersion()));
	bool ComOpened = false;
	bool HDLCConnected = false;
	try
	{
		// Open the com port
		ComCreate("COM1:\n");
		ComOpened = true;
		
		// Connect to device using HDLC
		// We set the response timeout (in ms). The meter has to answer in this delay.
		ResponseTimeout = 2000;

		// An optical probe may echo (or not) the characters
		// WARNING, if Echoing is TRUE then the console logging will also show the echo!
		// ComIsEchoing = true;

		// A mode E opening may be needed, if yes then the opening is done in the 
		// client application, before using the HDLC protocol
		DoModeEOpening = true;
		if (DoModeEOpening)
			// When using Mode E opening, the final baud is negotiated during the opening
			ComDoModeEOpening();
		else
			// We configure the com port and set the baud
			ComConfigure(
			8, //ByteSize
			ONESTOPBIT, // StopBits
			NOPARITY, // Parity
			9600 // Baud
			);

		// The supporting ''layer''is now connected, we setup the HDLC instance parameters
		// These parameters may vary according to your meter

		HDLCInstance.ClientMACAddress = 1; // We are client ''1''
		HDLCInstance.ServerAddressSize = 2; // Meter needs ''two bytes addressing''
		HDLCInstance.ServerLowerMACAddressValue = 0x11; // Meter's physical address
		HDLCInstance.ServerUpperMACAddressValue = 1; // We access the default logical device (management = 1)

		// Set our call backs
		HDLCInstance.StateChangeCallBack = HDLCStateChangeProc;
		HDLCInstance.GetByteCallBack = ComGetByteProc;
		HDLCInstance.PutBytesCallback = ComPutBytesProc;

		// Create the Instance
		HDLCCreateInstance(&HDLCInstance);

		// Connect HDLC
		HDLCConnect(&HDLCInstance, &HDLCStatus);
		if (HDLCStatus.ErrorKind != HDLC_NO_ERROR)
		{
			Throw(HDLCStatus.ErrorMessage);
		}
		HDLCConnected = true;


		// Poll HDLC just to show that it works
		for (int i = 0; i < 2; i++)
		{
			Writeln("Poll and wait for 2 seconds");
			HDLCPoll(&HDLCInstance, &HDLCStatus);
			if (HDLCStatus.ErrorKind != HDLC_NO_ERROR)
			{
				Throw(HDLCStatus.ErrorMessage);
			}
			Sleep(2000);
		}

		// Establish a ciphered association using HLS
		Associate();

		// Read the clock value using several ciphering variant
		string ReadTheClockXml = "\
            <GetRequest> \n\
              <GetRequestNormal> \n\
                <InvokeIdAndPriority Value='C1' /> \n\
                <AttributeDescriptor> \n\
                  <ClassId Value='0008' /> \n\
                  <InstanceId Value='0000010000FF' /> \n\
                  <AttributeId Value='02' /> \n\
                </AttributeDescriptor> \n\
              </GetRequestNormal> \n\
            </GetRequest> \n";
        DoRequest(AUTHENTICATION_ONLY, ReadTheClockXml);
		DoRequest(ENCRYPTION_ONLY, ReadTheClockXml);
		DoRequest(AUTHENTICATION_AND_ENCRYPTION, ReadTheClockXml);
	}
	catch (Exception e)
	{
		Writeln("*** " + e.Message());
	}
	catch (...)
	{
		Writeln("");
	}

	if (HDLCConnected)
	{
		// Disconnect HDLC...
		HDLCDisconnect(&HDLCInstance, &HDLCStatus);
		// ...and destroy the instance
		HDLCDestroyInstance(&HDLCInstance);
	}
	// Clean up
	if (ComOpened)
		CloseHandle(hCom);

	cout << endl << "Type enter" << endl;
	getchar();
	return 0;
}

