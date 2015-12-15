//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube, Christian Aymon, 1376 Goumoens-la-Ville, Switzerland
//          infos@icube.ch
//
// (C)Copyright 2004-2012
//**********************************************************************
#include "stdafx.h"
#include "Misc.h"
#include "Exception.h"
//t

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


/*****/
/* H */
/*****/

char H(int n)
{
	if (n <= 9)
		return '0' + n;
	else
		return 'A' + n - 10;
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
			throw Exception("HexStrToByteStr: Illegal character");
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
/* HexToInt */
/************/

unsigned int HexToInt(string & s)
{
	unsigned int i;
	char c;
	unsigned int Result = 0;
	for (i = 0; i < s.size(); i++)
	{
		Result = (Result << 4);
		c = s[i];
		if ((c >= '0') && (c <= '9'))
			Result += (c - '0');
		else if ((c >= 'A') && (c <= 'F'))
			Result += (c - 'A' + 10);
		else
			throw Exception("HexToInt: illegal character");
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
// Is there any STANDARD way to do this in C++?
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


