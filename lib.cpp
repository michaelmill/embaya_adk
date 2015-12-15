/* -----------------------------------------------------------------;
 * $source   : / $
 * $revision : 1.0 $
 * $author   : wyr@null.co.za $
 * $date     : 2015/12/02 10:43:00 SAST $
 * $desc     : $
 * $log      : $
 *
 * -----------------------------------------------------------------;
 */

/**
 - To define methods which are used externally. Fundamentally, I need to
   identify which methods will be used by the HD/system. A connection
   stack is useful. I need to clarify what the basic handshake is.

 - 00 01 00 64 00 01 00 38 60 36 A1 09 06 07 60 85 74 05 08 01 01 8A 02 07 80 8B 07 60 85 74 05 08 02 01 AC 0A 80 08 31 32 33 34 35 36 37 38 BE 10 04 0E 01 00 00 00 06 5F 1F 04 00 00 7E 1F FF FF
**/

#include "lib.h"

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

void error (const char *msg) {

   perror(msg);
   exit(0);
}

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

int c_dlms_adk_connect (struct c_dlms_adk_interface *c) {

   int                sockfd, n;
   struct sockaddr_in serv_addr;
   struct hostent    *server;

   sockfd = socket(AF_INET, SOCK_STREAM, 0);

   if (sockfd < 0) {
      error("ERROR opening socket");
   }
   server = gethostbyname(c->host);

   if (server == NULL) {
      fprintf(stderr, "ERROR, no such host\n");
      exit(0);
   }

   bzero((char *) &serv_addr, sizeof(serv_addr));

   serv_addr.sin_family = AF_INET;

   bcopy((char *)server->h_addr, (char *)&serv_addr.sin_addr.s_addr, server->h_length);
   serv_addr.sin_port = htons(c->port);

   if (connect(sockfd,(struct sockaddr *) &serv_addr,sizeof(serv_addr)) < 0) {
      error("ERROR connecting");
   }

   n = write(sockfd, c->msg, strlen(c->msg));

   if (n < 0) {
      error("ERROR writing to socket");
   }

   //printf ("%i\n", 104);

   n = read(sockfd, c->ret, 255);

   if (n < 0) {
      error("ERROR reading from socket");
   }
   printf("%s\n", c->ret);

   close(sockfd);

   return 1;
}

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

int c_dlms_adk_initialize (int l_register_protocol_type) {

   printf ("%i\n", l_register_protocol_type);

   return 1;
}

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

int c_dlms_adk_hook () {

   return 1;
}

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

int c_dlms_adk_register_protocol (struct c_dlms_adk_interface *c) {

  //printf ("%i\n", c->j);
  c->j = 10;

  return 1;
}
