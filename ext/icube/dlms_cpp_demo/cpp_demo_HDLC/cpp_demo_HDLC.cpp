
//**********************************************************************
//  XML DLMS components
//  www.icube.ch, 1040 Echallens, Switzerland, (C)Copyright 2004-2012
//**********************************************************************

#include "..\interface\EZHdlcInterface.h"
#include "..\interface\XmlPduInterface.h"
#include "..\common\Misc.h"
#include <stdio.h>
#include <tchar.h>
#include <string>
using namespace std;


// This program does the following:
// - Setup an HDLC connection
// - Sends some the xml-elements 
// - Displays the server responses and the trafic.
// - Releases the connection
// The parameters of the connection are directly specified in ''main''

// 
static THDLCParameters HDLCInstance;
static THDLCStatus HDLCStatus;
static TXmlPduStatus XmlPduStatus;
static HANDLE hCom;
static int RecCount = 0;
static int ResponseTimeout;
static bool ComIsEchoing;



// Misc Log routines
// *****************

void DisplayOnNewLine()
{
	if (RecCount > 0)
	{
		printf("\n");
		RecCount = 0;
	}
}

void Writeln(const string s)
{
	DisplayOnNewLine();
	printf(s.c_str());
	printf("\n");
}

void __stdcall DisplayReceivedByte(unsigned const char c)
{
	if (RecCount == 0)
		printf("R:");
	printf("%02hX", c);
	RecCount++;
}

void __stdcall DisplaySentString(unsigned const char * s, int Size)
{
	int i;
	DisplayOnNewLine();
	printf("S:");
	for (i = 0; i < Size; i++)
		printf("%02hX", unsigned char (s[i]));
	printf("\n");
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
//*************
bool ComCreate(char * pComPortName)
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
		printf ("COM CreateFile failed with error %d.\n", GetLastError());
		return FALSE;
	}
	return TRUE;
}


bool ComConfigure(int ByteSize, int StopBits, int Parity, int Baud)
{
	DCB Dcb;
	DWORD Dmy;
	ClearCommError(hCom, &Dmy, NULL);
	GetCommState(hCom, &Dcb);
	Dcb.StopBits = StopBits;
	Dcb.BaudRate = Baud;
	Dcb.ByteSize = ByteSize;
	Dcb.Parity = Parity;
	if (!SetCommState(hCom, &Dcb))
	{
		printf ("COM SetComState failed with error %d.\n", GetLastError());
		return FALSE;
	}
	return TRUE;
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




// *********************

BOOL SendReceiveXml(char * FileName)
{
	char *XmlRef;
	HANDLE FileHandle, MapHandle;
	string FullPath;
	TXmlPduBuffer RequestBuffer, ResponseBuffer;
	THDLCBuffer HDLCBuffer;

	FullPath = FileInExeDirectory(FileName);

	Writeln("Processing: " + FullPath);

	// We create a mapping of the required file
	FileHandle = CreateFileA(FullPath.c_str(), GENERIC_READ, FILE_SHARE_READ,
		NULL, OPEN_EXISTING, FILE_FLAG_RANDOM_ACCESS, 0);
	if (FileHandle == INVALID_HANDLE_VALUE)
	{
		Writeln("Cannot open file mapping to file " + FullPath);
		return FALSE;
	}

	MapHandle = CreateFileMapping(FileHandle, NULL, PAGE_READONLY, 0,
		GetFileSize(FileHandle, NULL), NULL);
	if (MapHandle == NULL)
	{
		Writeln("Cannot open file mapping to file " + FullPath);
		CloseHandle(FileHandle);
		return FALSE;
	}

	// Finally, we get *char to the Xml 
	XmlRef = (char*)MapViewOfFile(MapHandle, FILE_MAP_READ, 0, 0, 0);
	if (XmlRef == NULL)
	{
		CloseHandle(MapHandle);
		CloseHandle(FileHandle);
		Writeln("Cannot map view to file " + FullPath);
		return FALSE;
	}


	DisplayOnNewLine();
	Writeln("Sending:");
	Writeln(XmlRef);

	// Translate Xml to Pdu
	XmlToPdu(XmlRef, RequestBuffer, XmlPduStatus);

	if (XmlPduStatus.ErrorKind == XMLPDU_NO_ERROR)
	{
		// Send the Pdu to the HDLC channel and get the response
		HDLCSendReceive(&HDLCInstance, XmlPduBufferData(RequestBuffer), XmlPduBufferSize(RequestBuffer), 
			&HDLCBuffer, &HDLCStatus);
		if (HDLCStatus.ErrorKind == HDLC_NO_ERROR)
		{
			XmlPduReleaseBuffer(RequestBuffer);
			// Translate the response Pdu to Xml
			PduToXml(HDLCBufferData(HDLCBuffer), HDLCBufferSize(HDLCBuffer), ResponseBuffer, XmlPduStatus);
			if (XmlPduStatus.ErrorKind == XMLPDU_NO_ERROR)
			{
				HDLCReleaseBuffer(&HDLCBuffer);
				DisplayOnNewLine();
				Writeln("Received:");
				Writeln((const char *)XmlPduBufferData(ResponseBuffer));
				XmlPduReleaseBuffer(ResponseBuffer);
			}
			else
			{
				Writeln(XmlPduStatus.ErrorMessage);
			}
		}
		else
		{
			Writeln(HDLCStatus.ErrorMessage);
		}
	}
	else
	{
		Writeln(XmlPduStatus.ErrorMessage);
	}

	UnmapViewOfFile(XmlRef);
	CloseHandle(MapHandle);
	CloseHandle(FileHandle);
	return TRUE;
}


int _tmain(int argc, _TCHAR* argv[])
{
	const int MaxFilesToProcess = 2;
	int i;
	BOOL Continue, DoModeEOpening;
	char * FileToProcess[MaxFilesToProcess]; 

	Writeln("xmlpdu & ezhdlc demo");
	Writeln("HDLC version: " + string(HDLCVersion()));
	Writeln("XmlPduVersion: " + string(XmlPduVersion()));

	Sleep(3000);

	// We will use the HDLC protocol on a serial line, we setup the
	// communication port
	ComCreate("COM1:\n");

	// We set the response timeout (in ms), the meter has to answer in this delay
	ResponseTimeout = 2000;

	// An optical probe may echo (or not) the characters
	// WARNING, if Echoing is TRUE then the console logging will also show the echo!
	ComIsEchoing = FALSE;

	// A mode E opening may be needed, if yes then the opening is done in the 
	// client application, before using the HDLC protocol
	DoModeEOpening = FALSE;
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

	// Now the supporting ''layer''is connected, we setup the HDLC instance parameters

	/*
	// For example for an ACTARIS SL7000 meter
	HDLCInstance.ClientMACAddress = 0x3; 
	HDLCInstance.ServerAddressSize = 4;
	HDLCInstance.ServerLowerMACAddressValue = 0x11;
	HDLCInstance.ServerUpperMACAddressValue = 1; // Logical device management
	// Set the xml (request) files that we will ''send'' to the meter
	FileToProcess[0] = "AssociationRequest_LN_PW.xml"; // Open an association with the default PW
	FileToProcess[1] = "GetRequestNormal(Clock).xml"; // read the clock
	*/

	// For a L+G meter we could use this:
	/**/
	HDLCInstance.ClientMACAddress = 0x10;  // public client
	HDLCInstance.ServerAddressSize = 1; // one byte addressing
	HDLCInstance.ServerLowerMACAddressValue = 0; // not specified
	HDLCInstance.ServerUpperMACAddressValue = 1; // Logical device management
	FileToProcess[0] = "AssociationRequest_SN_NoPW.xml"; // Open a public association
	FileToProcess[1] = "ReadRequest(ObjectsList).xml"; // read the object list
	/**/

	HDLCInstance.StateChangeCallBack = HDLCStateChangeProc;
	// As we will use HDLC on the Com port, we set the appropriate call back
	HDLCInstance.GetByteCallBack = ComGetByteProc;
	HDLCInstance.PutBytesCallback = ComPutBytesProc;

	// Create the Instance
	HDLCCreateInstance(&HDLCInstance);

	// Connect HDLC
	HDLCConnect(&HDLCInstance, &HDLCStatus);
	if (HDLCStatus.ErrorKind != HDLC_NO_ERROR)
	{
		Writeln(HDLCStatus.ErrorMessage);
	}


	// Poll HDLC just to see what happens
	for (i = 0; i < 3; i++)
	{
		Writeln("Poll and wait for 2 seconds");
		HDLCPoll(&HDLCInstance, &HDLCStatus);
		if (HDLCStatus.ErrorKind != HDLC_NO_ERROR)
		{
			Writeln(HDLCStatus.ErrorMessage);
		}
		Sleep(2000);
	}

	// Process all the files passed on the commande line
	// Currently these files are AssociationRequestSN.xml and ReadRequest(ObjectsList).xml
	if (HDLCStatus.ErrorKind == HDLC_NO_ERROR)
	{
		i = 0;
		Continue = TRUE;
		while ((i < MaxFilesToProcess) && Continue)
		{
			Continue = SendReceiveXml(FileToProcess[i++]);
		}
	}

	// Finally, disconnect HDLC...
	HDLCDisconnect(&HDLCInstance, &HDLCStatus);
	if (HDLCStatus.ErrorKind != HDLC_NO_ERROR)
	{
		Writeln(HDLCStatus.ErrorMessage);
	}

	// ...and destroy the instance
	HDLCDestroyInstance(&HDLCInstance);

	// ans close the supporting serial line
	if (hCom != NULL)
		CloseHandle(hCom);

	Writeln("Type enter to finish");
	getchar();

	return 0;
}

