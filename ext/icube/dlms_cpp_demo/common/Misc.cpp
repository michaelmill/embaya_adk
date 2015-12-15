//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube software, 1376 Goumoens-la-Ville, Switzerland
//  infos@icube.ch
//
// © Copyright 2004-2012
//**********************************************************************
#include "Misc.h"
#include "windows.h"

char H(int n)
{
	if (n <= 9)
		return '0' + n;
	else
		return 'A' + n - 10;
}


/*******************/
/* ByteStrToHexStr */
/*******************/

string ByteStrToHexStr(const string &s)
{
	unsigned int i;
	string Result;
	for (i = 0; i < s.length(); i++)
	{
		Result += H((s[i] >> 4) & 0xF);
		Result += H(s[i] & 0xF);
	}
	return Result;
}


/*******************/
/* HexStrToByteStr */
/*******************/

string HexStrToByteStr(const string &s)
{
	string Result;
	unsigned int i, j;
	unsigned char h;
	Result = "";
	j = 0;
	h = 0;
	i = 0;
	while (i < s.length())
	{
		if ((s[i] >= '0') && (s[i] <= '9'))
		{
			h = (h << 4) + s[i] - '0';
			j++;
		} else if ((s[i] >= 'A') && (s[i] <= 'F'))
		{
			h = (h << 4) + s[i] - 'A' + 10;
			j++;
		} else if (s[i] == ' ')
		{
		} else
		{
			throw EStrException("HexStrToByteStr: Illegal character");
		}
		if (j == 2) 
		{
			Result += h;
			h = 0;
			j = 0;
		}
		i++;
	}
	return Result;
}


/************/
/* IntToHex */
/************/

string IntToHex(int n, int Digits)
{
	int d;
	string Result = "";
	while (Digits > 0)
	{
		d = n & 0xF;
		if (d <= 9)
			Result = char(d + '0') + Result;
		else
			Result = char(d - 10 + 'A') + Result;
		Digits--;
		n = n >> 4;
	}
	return Result;
}


/************/
/* IntToStr */
/************/

string IntToStr(int n)
{
	bool Neg;
	string Result = "";
	if (n < 0)
	{
		Neg = true;
		n = -n;
	}
	else
		Neg = false;
	do
	{
		Result = char((n % 10) + '0') + Result;
		n /= 10;
	} while (n > 0);
	if (Neg)
		Result = '-' + Result;
	return Result;
}


/********************************/
/* EStrException::EStrException */
/********************************/

EStrException::EStrException(const string & Message)
{
	this->Message = Message;
}

/**********************/
/* FileInExeDirectory */
/**********************/

string FileInExeDirectory(const string FileName)
{
	string ExePath;
	char F[1024];
	GetModuleFileNameA(NULL, F, sizeof(F));
	ExePath = string(F);
	return ExePath.substr(0, ExePath.find_last_of('\\') + 1) + FileName;
}
