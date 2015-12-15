//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2012
//**********************************************************************
#ifndef Misc_h
#define Misc_h
#include <string>
using namespace std;

class EStrException
{
public:
	string Message;
	EStrException(const string & Message);
};

string ByteStrToHexStr(const string &s);
string HexStrToByteStr(const string &s);
string IntToHex(int n, int Digits);
string IntToStr(int n);
string AXDRBitStringToBinHexStr(const string &Bytes, int BitQty);
void BinHexStrToAXDRBitString(const string & BinHexStr, int & BitQty, string & Bytes);
unsigned int HexToInt(string & s);
string PackBERBitString(const string & Src, int ExpectedBits);
string FileInExeDirectory(const string FileName);


#endif
