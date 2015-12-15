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

#include <time.h> 
#include <string>
#include "c_dlms_enums.h"

using namespace std;

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

// DataType enumerates skipped fields from date time.
enum DATETIME_SKIPS
{    
    // Nothing is skipped from date time.    
    DATETIME_SKIPS_NONE = 0x0,    
    // Year part of date time is skipped.
    DATETIME_SKIPS_YEAR = 0x1,
    // Month part of date time is skipped.
    DATETIME_SKIPS_MONTH = 0x2,
    // Day part is skipped.
    DATETIME_SKIPS_DAY = 0x4,
    // Day of week part of date time is skipped.
    DATETIME_SKIPS_DAYOFWEEK = 0x8,
    // Hours part of date time is skipped.
    DATETIME_SKIPS_HOUR = 0x10,
    // Minute part of date time is skipped.
    DATETIME_SKIPS_MINUTE = 0x20,
    // Seconds part of date time is skipped.
    DATETIME_SKIPS_SECOND = 0x40,
    // Hundreds of seconds part of date time is skipped.
    DATETIME_SKIPS_MS = 0x80,
	//Devitation is skipped on write.
	DATETIME_SKIPS_DEVITATION = 0x100
};

// This class is used because in COSEM object model some fields from date time can be ignored.
// Default behavior of DateTime do not allow this.
class c_dlms_date_time {

	DATETIME_SKIPS      m_Skip;
	struct              tm m_Value;
	bool                m_DaylightSavingsBegin;
	bool                m_DaylightSavingsEnd;
	GXDLMS_CLOCK_STATUS m_Status;

        void Init (int year, int month, int day, int hour, int minute, int second, int millisecond, int devitation);

   public :

      c_dlms_date_time    ();
      c_dlms_date_time    (struct tm value); 
      c_dlms_date_time    (int year, int month, int day, int hour, int minute, int second, int millisecond);   
      c_dlms_date_time    (int year, int month, int day, int hour, int minute, int second, int millisecond, int devitation);

      struct tm& GetValue ();
      void       SetValue (struct tm& value);
    
    // skip selected date time fields.
      DATETIME_SKIPS GetSkip();
      void SetSkip(DATETIME_SKIPS value);
      basic_string<char> ToString();

   // get currect time.
      static c_dlms_date_time Now();

   // daylight savings begin.
      bool GetDaylightSavingsBegin();
      void SetDaylightSavingsBegin(bool value);

   // daylight savings end.
      bool GetDaylightSavingsEnd();
      void SetDaylightSavingsEnd(bool value);

   // status of the clock.
      GXDLMS_CLOCK_STATUS GetStatus();
      void SetStatus(GXDLMS_CLOCK_STATUS value);
      unsigned char DaysInMonth(int year, short month);
};
