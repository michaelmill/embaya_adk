//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2010
//**********************************************************************
#ifndef EZHDLCInterface_H
#define EZHDLCInterface_H

#ifdef EZHDLC_EXPORTS
#define EZHDLC_API __declspec(dllexport)
#else
#define EZHDLC_API
#endif

#include "HDLCCallBack.h"

// HDLC parameters
struct THDLCParameters
{
  int ClientMACAddress;
  int ServerAddressSize;
  int ServerUpperMACAddressValue;
  int ServerLowerMACAddressValue;
  THDLCPutBytesProc PutBytesCallback;
  THDLCGetByteProc GetByteCallBack;
  THDLCStateChangeProc StateChangeCallBack;
  void * HDLCObject;
};



// HDLC error codes
struct THDLCStatus
{
  int ErrorKind;
  char ErrorMessage[256];
} ;

const int HDLC_NO_ERROR = 0x0;
const int HDLC_ERROR = 0x1;


// HDLC status
const int HDLC_NDM = 0x0;
const int HDLC_NRM = 0x1;

// HDLC buffer and related functions
typedef void *THDLCBuffer;

// Buffer function
extern "C" EZHDLC_API int __cdecl HDLCBufferSize(THDLCBuffer Buffer);

extern "C" EZHDLC_API const unsigned char * __cdecl HDLCBufferData(THDLCBuffer Buffer);

extern "C" EZHDLC_API void __cdecl HDLCReleaseBuffer(THDLCBuffer *pBuffer);


// Instance creation / destruction
extern "C" EZHDLC_API void __cdecl HDLCCreateInstance(THDLCParameters *pInstance);

extern "C" EZHDLC_API void __cdecl HDLCDestroyInstance(THDLCParameters *pInstance);


// Transfer functions
extern "C" EZHDLC_API void __cdecl HDLCConnect(THDLCParameters *pInstance, THDLCStatus  *pHDLCStatus);

extern "C" EZHDLC_API void __cdecl HDLCDisconnect(THDLCParameters *pInstance, THDLCStatus *HDLCStatus);

extern "C" EZHDLC_API void __cdecl HDLCSendReceive(THDLCParameters *pInstance, const unsigned char *pOutData, int iOutDataSize,
	THDLCBuffer *InData, THDLCStatus *pHDLCStatus);

extern "C" EZHDLC_API void __cdecl HDLCPoll(THDLCParameters *pInstance, THDLCStatus  *pHDLCStatus);


// Misc functions
extern "C" EZHDLC_API int __cdecl HDLCState(THDLCParameters *pInstance);

extern "C" EZHDLC_API const char * __cdecl HDLCVersion();

#endif
