//**********************************************************************
//  DLMS/COSEM components
//
//  i-cube, Christian Aymon, 1376 Goumoens-la-Ville, Switzerland
//          infos@icube.ch
//
// (C)Copyright 2004-2012
//**********************************************************************
#ifndef Exception_h
#define Exception_h
#include <string>
using namespace std;


class Exception
{
public:
	string FMessage;
	Exception(const string & Message);
	Exception(const char * Message);
	string  Message(); 
};

#endif
