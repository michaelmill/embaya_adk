//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2012
//**********************************************************************
#include <string>
using namespace std;

#ifndef GCMDllInterface_H
#define GCMDllInterface_H

#ifdef GCMDLL_EXPORTS
#define GCMDLL_API __declspec(dllexport)
#else
#define GCMDLL_API
#endif

// Security control
// Ciphering variants
int const AUTHENTICATION_ONLY = 0x10;
int const ENCRYPTION_ONLY = 0x20;
int const AUTHENTICATION_AND_ENCRYPTION = 0x30;
// Key-set
int const UNICAST_KEYS = 0;
int const BROADCAST_KEYS = 0x40;

// Error message
int const MaxMessageSize = 256;
struct TGCMStatus
{
	bool Ok;
	char Message[MaxMessageSize];
};

// Cipher, according to ''SecurityControl'', the PlainAPDU, using the SystemTitle and the Keys. Returns CipheredFrame. 
// On input InOutCipheredFrameSize indicates the maximal size of the available buffer, on output it indicates
// the size of the ciphered frame. The frame counter is automatically generated.
extern "C" GCMDLL_API void __cdecl Cipher(unsigned int SecurityControl, void *pPlainAPDU, int nPlainAPDUSize, void *pSystemTitle, 
	void *pEncryptionKey, void *pAuthenticationKey, 
	void *pCipheredFrame, unsigned int *pInOutCipheredFrameSize,
	TGCMStatus *pGCMStatus
	);

// Same as ''Cipher'' except that the FrameCounter is passed.
extern "C" GCMDLL_API void __cdecl Cipher0(unsigned int SecurityControl, void *pPlainAPDU, int nPlainAPDUSize, void *pSystemTitle, 
	void *pEncryptionKey, void *pAuthenticationKey, 
	void *pCipheredFrame, unsigned int *pInOutCipheredFrameSize,
	TGCMStatus *pGCMStatus, unsigned int FrameCounter
	);

// Decipher the CiperedFrame using the SystemTitle and the Keys. Returns PlainAPDU
// On input pInOutPlainAPDUSize indicates the maximal size of the available buffer, on output it indicates
// the size of the plain APDU.
extern "C" GCMDLL_API void __cdecl DeCipher(void *pCipheredFrame, int nCipheredFrameSize, void *pSystemTitle, 
	void *pEncryptionKey, void *pAuthenticationKey, 
	void *pPlainAPDU, unsigned int *pInOutPlainAPDUSize, TGCMStatus *pGCMStatus);

// Return a version string
extern "C" GCMDLL_API const char * __cdecl GCMDllVersion();

#endif
