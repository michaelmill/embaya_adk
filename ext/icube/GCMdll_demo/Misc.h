//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube, Christian Aymon, 1376 Goumoens-la-Ville, Switzerland
//          infos@icube.ch
//
// (C)Copyright 2004-2012
//**********************************************************************
#ifndef Misc_h
#define Misc_h
#include <string>
using namespace std;

string ByteStrToHexStr(const string &s);
string HexStrToByteStr(const string &s);
string IntToHex(int n, int Digits);
string IntToStr(int n);
unsigned int HexToInt(string & s);
char H(int n);


#endif
