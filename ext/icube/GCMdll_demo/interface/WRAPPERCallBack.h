//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2010
//**********************************************************************
#ifndef WRAPPERCallBack_H
#define WRAPPERCallBack_H

#include "windows.h"
// Callback functions
typedef void  (__stdcall *TWRAPPERPutBytesProc)(const unsigned char * pData, int iSize);

typedef BOOL  (__stdcall *TWRAPPERGetByteProc)(unsigned char * pData);

#endif
