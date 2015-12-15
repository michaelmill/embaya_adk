//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2012
//**********************************************************************
#ifndef TCPChannel_H
#define TCPChannel_H

#include "winsock2.h"
#include "ws2tcpip.h"
#include "windows.h"
#include <string>
using namespace std;


const int MAX_HDLC_FRAME_SIZE = 1000; // A value that is certainly larger than the longest possible hdlc frame

class TTCPChannel
{
private:
	SOCKET TheSocket;
    void ThrowTCPException(const string &Message);
public:

	// Send the specified string to the channel
	void PutString(const char * pData, int iSize);

	// Expect a char from the channel with timeout
	unsigned char GetByte(int TimeoutValue, bool & Timeout);

	// Indicates if the channel can be used
	bool IsOpen();

	TTCPChannel();

	~TTCPChannel();

	void Connect(char * Host, int Port);

	void Disconnect();
};

typedef TTCPChannel * PTCPChannel;


class ETCPException
{
public:
	string Message;
	ETCPException(const string & Message);
};

#endif
