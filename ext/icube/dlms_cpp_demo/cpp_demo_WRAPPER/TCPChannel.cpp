//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2012
//**********************************************************************
// #include "stdafx.h"
#include "TCPChannel.h"
#include "..\common\Misc.h"

/****************************/
/* TTCPChannel::TTCPChannel */
/****************************/

TTCPChannel::TTCPChannel()
{
	WSADATA info;
	if (WSAStartup(MAKEWORD(2,0), &info))
	{
      ThrowTCPException("Could not start WSA");
    }
}

/*****************************/
/* TTCPChannel::~TTCPChannel */
/*****************************/

TTCPChannel::~TTCPChannel()
{
	closesocket(TheSocket);
	TheSocket = INVALID_SOCKET;
	WSACleanup();
}



/************************/
/* TTCPChannel::Connect */
/************************/

void TTCPChannel::Connect(char * Host, int Port)
{
	struct addrinfo *result = NULL, *ptr = NULL, hints;
	int iResult;
	char PortStr [10];

	_itoa_s(Port, PortStr, sizeof(PortStr), 10);
	ZeroMemory( &hints, sizeof(hints) );
	hints.ai_family = AF_UNSPEC;
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_protocol = IPPROTO_TCP;

	iResult = getaddrinfo(Host, PortStr, &hints, &result);
	if (iResult != 0)
	{
		ThrowTCPException("Cannot resolve host:" + string(Host) + " Port:" + string(PortStr));
	}

	// Create a SOCKET for connecting to server
	ptr = result;
	TheSocket = INVALID_SOCKET;
	TheSocket = socket(ptr->ai_family, ptr->ai_socktype, ptr->ai_protocol);
	if (TheSocket == INVALID_SOCKET)
	{
		ThrowTCPException ("INVALID_SOCKET");
	}

	// Connect to server.
	iResult = connect(TheSocket, ptr->ai_addr, (int)ptr->ai_addrlen);
	if (iResult == SOCKET_ERROR) {
		closesocket(TheSocket);
		TheSocket = INVALID_SOCKET;
	}

	if (TheSocket == INVALID_SOCKET) {
		ThrowTCPException("Unable to connect to server " + string(Host) +":" + string(PortStr));
	}

	freeaddrinfo(result);
}

void TTCPChannel::Disconnect()
{
	closesocket(TheSocket);
}

/**************************/
/* TTCPChannel::PutString */
/**************************/

void TTCPChannel::PutString(const char * pData, int iSize)
{
	send(TheSocket, pData, iSize, 0);
}


/************************/
/* TTCPChannel::GetByte */
/************************/

unsigned char TTCPChannel::GetByte(int TimeoutValue, bool & Timeout)
{
	fd_set fds;
	int n, ReceivedByteQty;
	struct timeval tv;
	DWORD dwError, t1, t2;
	unsigned char Result;

	// Set up the file descriptor set.
	FD_ZERO(&fds);
	FD_SET(TheSocket, &fds);
	// Set up the struct timeval for the timeout.
	tv.tv_sec = TimeoutValue / 1000;
	tv.tv_usec = (TimeoutValue % 1000) * 1000;

	// Wait until timeout or data received.
	n = select ( TheSocket+1, &fds, NULL, NULL, &tv );
	if (n == 0 )
	{
		Timeout = TRUE;
		return 0;
	}
	
	if (n == -1)
	{
		dwError = WSAGetLastError();
		ThrowTCPException("select failed with error: " + IntToStr(dwError));
	}

	t1 = GetTickCount();
	ReceivedByteQty = recv(TheSocket, (char*)&Result, 1, 0 );
	t2 = GetTickCount();
	if (ReceivedByteQty == 0)
	{
		ThrowTCPException("TCP socket has been disconnected");
	} 
	else if (ReceivedByteQty == SOCKET_ERROR)
	{
		dwError = WSAGetLastError();
		ThrowTCPException("ReadFrame failed with error: " + IntToStr(dwError));
	}
	else
	{
		Timeout = FALSE;
		return Result;
	}
}


/**********************************/
/* TTCPChannel::ThrowTCPException */
/**********************************/

void TTCPChannel::ThrowTCPException(const string &Message)
{
	throw EStrException(Message);
}


/***********************/
/* TTCPChannel::IsOpen */
/***********************/

bool TTCPChannel::IsOpen()
{
	return TRUE;
}


