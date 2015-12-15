//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube, Christian Aymon, 1376 Goumoens-la-Ville, Switzerland
//          infos@icube.ch
//
// (C)Copyright 2004-2012
//**********************************************************************
#include "stdafx.h"
#include "Exception.h"
#include "string.h"
//t
/************************/
/* Exception::Exception */
/************************/

Exception::Exception(const char * Message)
{
	int i,j;
	FMessage = Message;
	i = strlen(Message);
	j = FMessage.length();
}


/************************/
/* Exception::Exception */
/************************/

Exception::Exception(const string & Message)
{
	FMessage = Message;
}


/**********************/
/* Exception::Message */
/**********************/

string  Exception::Message()
{
	return FMessage;
}


