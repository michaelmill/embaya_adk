/* -----------------------------------------------------------------;
 * $source   : / $
 * $revision : 1.0 $
 * $author   : wyr@null.co.za $
 * $date     : 2015/11/10 15:07:00 SAST $
 * $desc     : $
 * $log      : $
 *
 * -----------------------------------------------------------------;
 */

#include <stdio.h>
#include <string.h>
#include <malloc.h>
#include <assert.h>
#include <string.h>
#include <stdlib.h> 
#include <time.h>       // clock_t, clock, CLOCKS_PER_SEC
#include <math.h>       // sqrt 

#include <netinet/in.h>
#include <netdb.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#include <unistd.h>    // write 
#include <pthread.h>   // for threading , link with lpthread

#include <vector>
#include <iostream>      
#include <string.h>

using namespace std;            

//---------------------------------------------------------------------------;
//
//---------------------------------------------------------------------------;

struct c_dlms_adk_interface {

   int   j, k, y;
   int   port;
   char *host;
   char *msg;
   char *ret;
};
