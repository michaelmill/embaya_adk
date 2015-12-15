//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2010
//**********************************************************************
#ifndef HDLCCallBack_H
#define HDLCCallBack_H

#include "windows.h"
// Callback functions
typedef void  (__stdcall *THDLCPutBytesProc)(const unsigned char * pData, int iSize);

typedef BOOL  (__stdcall *THDLCGetByteProc)(unsigned char * pData);

typedef void  (__stdcall *THDLCStateChangeProc)(void);

#endif
