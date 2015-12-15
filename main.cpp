/* -----------------------------------------------------------------;
 * $source   : / $
 * $revision : 1.0 $
 * $author   : wyr@null.co.za $
 * $date     : 2015/12/03 10:30:00 SAST $
 * $desc     : $
 * $log      : $
 *
 * -----------------------------------------------------------------;
 */

#include "main.h"
#include "ext-gurux-clean/GXDLMSVariant.h"

int c_dlms_adk_register_protocol (struct c_dlms_adk_interface *c);
int c_dlms_adk_connect           (struct c_dlms_adk_interface *c);

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

static inline void m_change_byte_order (std::vector<unsigned char>& target,
                                        void*                       source, 
                                        int                         size) {
#ifdef _BIG_ENDIAN
   for(int pos = 0; pos != size; pos++) {
      target.push_back(source[pos]);
   }	
#else
   for(int pos = 0; pos != size; pos++) {			
      target.push_back(((char*) source)[size - pos - 1]);
   }
#endif
}

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

static inline basic_string<char> m_bytes_to_hex (unsigned char* bytes, 
                                                 int            count) {

   const char hexArray[] = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

   basic_string<char> hexChars(3 * count, 0);		
   int tmp;
   for (int pos = 0; pos != count; pos++) {
      tmp = bytes[pos] & 0xFF;
      hexChars[pos * 3]     = hexArray[tmp >> 4];
      hexChars[pos * 3 + 1] = hexArray[tmp & 0x0F];
      hexChars[pos * 3 + 2] = ' ';
   }
   return hexChars;
}

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

int CheckInit (CGXDLMSVariant ClientID,
               CGXDLMSVariant ServerID) {

   if (ClientID.vt == DLMS_DATA_TYPE_NONE) {
      return ERROR_CODES_INVALID_CLIENT_ADDRESS;
   }
   if (ServerID.vt == DLMS_DATA_TYPE_NONE) {
      return ERROR_CODES_INVALID_SERVER_ADDRESS;
   }
   return ERROR_CODES_OK;
}

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

int main (int    argc, 
          char **argv, 
          char **envp) {
/**
   std::vector<unsigned char> data;

   int index = 0, count = 0;

   CGXDLMSVariant ClientID = (unsigned short) 100;
   CGXDLMSVariant ServerID = (unsigned short) 1;

   printf ("<%i>\n", ClientID.bVal);

   int clientSize = ClientID.GetSize();
   int serverSize = ServerID.GetSize();

   std::cout << clientSize << std::endl;
   std::cout << serverSize << std::endl;

 //int ret = CheckInit(ClientID, ServerID);

   int len        = 7 + clientSize + serverSize;
   std::vector<unsigned char> buff(0, len);

   // add version 0x0001
   unsigned short version = 1;
   m_change_byte_order (buff, &version, 2);
 
   //Add Source (Client)
   m_change_byte_order (buff, &ClientID.bVal, clientSize);
	
   //Add Destination (Server)		
   m_change_byte_order (buff, &ServerID.bVal, serverSize);

   //Add data length. 2 bytes.
   buff.push_back((unsigned char) (count >> 8) & 0xFF);
   buff.push_back((count & 0xFF));

   for(int pos = 0; pos != count; pos++) {
      buff.push_back(data[index + pos]);			
   }

   string tmp;

   tmp += "\t" + m_bytes_to_hex (&buff[0], buff.size());

   std::cout << tmp.c_str() << std::endl;
**/

   struct c_dlms_adk_interface c;
   struct c_dlms_adk_interface l_connect;

   c.j = 4;

   c_dlms_adk_register_protocol (&c);

   //
   // - Connect, with parameters.
   //

   l_connect.host = (char*)malloc (sizeof(l_connect.host));
   l_connect.msg  = (char*)malloc (sizeof(l_connect.msg));
   l_connect.ret  = (char*)malloc (sizeof(l_connect.ret));

   l_connect.port = 4059;

   sprintf (l_connect.host, "%s", "localhost");
   sprintf (l_connect.msg,  "%s", "message");

   c_dlms_adk_connect (&l_connect);

   free (l_connect.host);
   free (l_connect.msg);
   free (l_connect.ret);

   return 1;
}
