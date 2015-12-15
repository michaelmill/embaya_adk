/* -----------------------------------------------------------------;
 * $source   : / $
 * $revision : 1.0 $
 * $author   : wyr@null.co.za $
 * $date     : 2015/10/26 15:07:00 SAST $
 * $desc     : $
 * $log      : $
 *
 * -----------------------------------------------------------------;
 */

#pragma once
#include <string>
#include <vector>

using namespace std;

#include "c_dlms_enums.h"
#include "c_dlms_error_codes.h"
#include "c_dlms_date_time.h"

#define __tagVARIANT
#define __VARIANT_NAME_1
#define __VARIANT_NAME_2
#define __VARIANT_NAME_3

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

class c_dlms_variant;

struct c_struct_dlms_variant {

   union 
       {
       struct __tagVARIANT
            {
            DLMS_DATA_TYPE vt;
            union 
                {					                
                	long               lVal;
                	unsigned char      bVal;
                	char               cVal;
			short              iVal;
			int                intVal;
                	float              fltVal;
                	double             dblVal;
			bool 		   boolVal;	
			unsigned short     uiVal;
			unsigned int   	   uintVal;
			unsigned long      ulVal;
			long long          llVal;
			unsigned long long ullVal;				
                } __VARIANT_NAME_3;
            } __VARIANT_NAME_2;        
        } __VARIANT_NAME_1;
	
	c_dlms_date_time             dateTime;

        basic_string<char>           trVal;
	vector<unsigned char>        byteArr;
	vector<c_dlms_variant> Arr;
    } ;

class c_dlms_variant : public c_struct_dlms_variant
{
	static int Convert(c_dlms_variant* item, DLMS_DATA_TYPE type);
public:
	void Clear();
	c_dlms_variant ();
	
	c_dlms_variant (float value);
	c_dlms_variant (double value);

	c_dlms_variant (unsigned long long value);
	c_dlms_variant (long long value);
	c_dlms_variant (bool value);
	c_dlms_variant (char value);
	c_dlms_variant (short value);
	c_dlms_variant (int value);
	c_dlms_variant (long value);
	c_dlms_variant (struct tm value);
	c_dlms_variant (vector<unsigned char> value);
	c_dlms_variant (c_dlms_date_time value);	
	
	c_dlms_variant (c_dlms_variant* value);
	c_dlms_variant (unsigned char* pValue, int size, DLMS_DATA_TYPE type);
	c_dlms_variant (unsigned char value);
	c_dlms_variant (unsigned short value);
	c_dlms_variant (unsigned int value);
	c_dlms_variant (unsigned long value);
	c_dlms_variant (basic_string<char> value);
	c_dlms_variant (const char* value);
	c_dlms_variant& operator=(basic_string<char> value);
	c_dlms_variant& operator=(const char* value);
	c_dlms_variant& operator=(float value);
	c_dlms_variant& operator=(double value);
	c_dlms_variant& operator=(unsigned long long value);
	c_dlms_variant& operator=(long long value);
	c_dlms_variant& operator=(bool value);
	c_dlms_variant& operator=(char value);
	c_dlms_variant& operator=(short value);
	c_dlms_variant& operator=(int value);
	c_dlms_variant& operator=(long value);
	c_dlms_variant& operator=(unsigned char value);
	c_dlms_variant& operator=(unsigned short value);
	c_dlms_variant& operator=(unsigned int value);
	c_dlms_variant& operator=(unsigned long value);
	c_dlms_variant& operator=(struct tm value);	
	c_dlms_variant& operator=(vector<unsigned char> value);		
	c_dlms_variant& operator=(c_dlms_date_time value);
		
	void Add(const unsigned char*, int count);
	void Add(const char*, int count);
	void Add(basic_string<char> value);
	bool Equals(c_dlms_variant& item);
	int ChangeType(DLMS_DATA_TYPE newType);
	//Returns bytes as Big Endian byteorder.
	void GetBytes(std::vector<unsigned char>& buff);
	//Get size in bytes.
	int GetSize();
	//Get size in bytes.
	static int GetSize(DLMS_DATA_TYPE vt);	
	basic_string<char> ToString();
	basic_string<char> ToDateTime();
	basic_string<char> ToDate();
	basic_string<char> ToTime();
	int ToInteger();
	double ToDouble();
};
