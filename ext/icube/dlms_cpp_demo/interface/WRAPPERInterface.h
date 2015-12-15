//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2010
//**********************************************************************
#ifndef WRAPPERInterface_H
#define WRAPPERInterface_H

#ifdef WRAPPER_EXPORTS
#define WRAPPER_API __declspec(dllexport)
#else
#define WRAPPER_API
#endif

#include "WRAPPERCallBack.h"

// WRAPPER parameters
struct TWRAPPERParameters
{
  int ClientAddress;
  int ServerAddress;
  TWRAPPERPutBytesProc PutBytesCallBack;
  TWRAPPERGetByteProc GetByteCallBack;
	void * WRAPPERObject;
};



// WRAPPER status and error codes
struct TWRAPPERStatus
{
  int ErrorKind;
  char ErrorMessage[256];
} ;


const int WRAPPER_NO_ERROR = 0x0;
const int WRAPPER_ERROR = 0x1;

// WRAPPER buffer and related functions
typedef void *TWRAPPERBuffer;


// Buffer function
extern "C" WRAPPER_API int __cdecl WRAPPERBufferSize(TWRAPPERBuffer Buffer);

extern "C" WRAPPER_API const unsigned char * __cdecl WRAPPERBufferData(TWRAPPERBuffer Buffer);

extern "C" WRAPPER_API void __cdecl WRAPPERReleaseBuffer(TWRAPPERBuffer *pBuffer);

// Instance creation / destruction
extern "C" WRAPPER_API void __cdecl WRAPPERCreateInstance(TWRAPPERParameters *pInstance);

extern "C" WRAPPER_API void __cdecl WRAPPERDestroyInstance(TWRAPPERParameters *pInstance);

// Transfer functions
extern "C" WRAPPER_API void __cdecl WRAPPERSendReceive(TWRAPPERParameters *pInstance, const unsigned char *pOutData, int iOutDataSize,
	TWRAPPERBuffer *InData, TWRAPPERStatus *pWRAPPERStatus);

// Misc functions

extern "C" WRAPPER_API const char * __cdecl WRAPPERVersion();
#endif
