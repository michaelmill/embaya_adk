
//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2012
//**********************************************************************
//

//#include "stdafx.h"
#include "TCPChannel.h"
#include "..\common\Misc.h"
#include "..\interface\WRAPPERInterface.h"
#include "..\interface\XmlPduInterface.h"
#include <stdio.h>
#include <string>

// The TCP channel
static TTCPChannel TCPChannel;
static int ResponseTimeout;
static int RecCount = 0;
static TWRAPPERParameters WRAPPERInstance;
static TWRAPPERStatus WRAPPERStatus;
static TXmlPduStatus XmlPduStatus;

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



// WRAPPER Callback routines
// *************************

// WRAPPER calls TCPGetByte when it needs a (received) byte
BOOL __stdcall TCPGetByteProc(unsigned char * pData)
{
	bool Timeout;
	*pData = TCPChannel.GetByte(ResponseTimeout, Timeout);
	if (!Timeout)
		// Show the received byte
		DisplayReceivedByte(*pData);
	return !Timeout;
}

// WRAPPER calls TCPPutBytesProc when it needs to send a string
void __stdcall TCPPutBytesProc(const unsigned char * pData, int iSize)
{
	// Show the bytes sent
	DisplaySentString(pData, iSize);
	TCPChannel.PutString((const char *)pData, iSize);
}


// *********************

bool SendReceiveXml(char * FileName)
{
	char *XmlRef;
	HANDLE FileHandle, MapHandle;
	string FullPath;
	TXmlPduBuffer RequestBuffer, ResponseBuffer;
	TWRAPPERBuffer WRAPPERBuffer;

	FullPath = FileInExeDirectory(FileName);

	Writeln("Processing: " + FullPath);

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
		WRAPPERSendReceive(&WRAPPERInstance, XmlPduBufferData(RequestBuffer), XmlPduBufferSize(RequestBuffer), 
			&WRAPPERBuffer, &WRAPPERStatus);
		if (WRAPPERStatus.ErrorKind == WRAPPER_NO_ERROR)
		{
			XmlPduReleaseBuffer(RequestBuffer);
			// Translate the response Pdu to Xml
			PduToXml(WRAPPERBufferData(WRAPPERBuffer), WRAPPERBufferSize(WRAPPERBuffer), ResponseBuffer, XmlPduStatus);
			if (XmlPduStatus.ErrorKind == XMLPDU_NO_ERROR)
			{
				WRAPPERReleaseBuffer(&WRAPPERBuffer);
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
			Writeln(WRAPPERStatus.ErrorMessage);
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

int main()
{
	const int MaxFilesToProcess = 2;
	int i;
	bool Continue;
	char * FileToProcess[MaxFilesToProcess]; 

	Writeln("WRAPPER demo");
	Writeln("WRAPPER version: " + string(WRAPPERVersion()));
	Writeln("XmlPduVersion: " + string(XmlPduVersion()));

	Sleep(3000);

	// We set the response timeout (in ms), the meter has to answer in this delay
	ResponseTimeout = 2000;

	// Connect the TCPChannel to the target, replace the ''local host'' address by a real address
	// 4059 is the standard COSEM port
	try
	{
		TCPChannel.Connect("127.0.0.1", 4059); 


		// Now the supporting ''layer''is connected, we setup the WRAPPER instance parameters
		WRAPPERInstance.ClientAddress = 0x10; // Public client
		WRAPPERInstance.ServerAddress = 1; // Management logical device
		// Setup the coll back routines
		WRAPPERInstance.GetByteCallBack = TCPGetByteProc;
		WRAPPERInstance.PutBytesCallBack = TCPPutBytesProc;
		FileToProcess[0] = "AssociationRequest_SN_NoPW.xml"; // Open an association without PW (public)
		FileToProcess[1] = "ReadRequest(ObjectsList).xml"; // read the object list

		// Create the Instance
		WRAPPERCreateInstance(&WRAPPERInstance);

		// Process all the files passed on the commande line
		// Currently these files are AssociationRequestSN.xml and ReadRequest(ObjectsList).xml
		i = 0;
		Continue = TRUE;
		while ((i < MaxFilesToProcess) && Continue)
		{
			Continue = SendReceiveXml(FileToProcess[i++]);
		}


		// Finally destroy the instance...
		WRAPPERDestroyInstance(&WRAPPERInstance);

		// and, disconnect TCP
		TCPChannel.Disconnect();

	} catch (EStrException param) 
	{
		Writeln(param.Message);
	}
	catch (...)
	{
		Writeln("Exception occured");
	}
	Writeln("Type enter to finish");
	getchar();

	return 0;
}



int WINAPI WinMain(HINSTANCE hInst,HINSTANCE hPrev,LPSTR lpszCmd,int nCmd)
{
	AllocConsole( );  // Create Console Window
	freopen("CONIN$","rb",stdin);   // reopen stdin handle as console window input
	freopen("CONOUT$","wb",stdout);  // reopen stout handle as console window output
	freopen("CONOUT$","wb",stderr); // reopen stderr handle as console window output
	main( );   // Call a regular C main function
	FreeConsole( );  // Free Console Window
	return 0;
}

