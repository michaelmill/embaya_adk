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

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

int main (int   argc, 
          char* argv[]) {

   FILE *fp         = NULL;
   pid_t process_id = 0;
   pid_t sid        = 0;

   // create child process

   process_id = fork();

   // indication of fork() failure

   if (process_id < 0) {
      printf("fork failed!\n");

   // return failure in exit status

      exit(1);
   }

   // PARENT PROCESS. need to kill it.

   if (process_id > 0) {

      printf("process_id of child process %d \n", process_id);
      // return success in exit status
      exit(0);
   }

   // unmask the file mode

   umask(0);

   // set new session

   sid = setsid();

   if (sid < 0) {
   // return failure
      exit(1);
   }

   // change the current working directory to root.

   chdir("/");

   // close stdin. stdout and stderr

   close(STDIN_FILENO);
   close(STDOUT_FILENO);
   close(STDERR_FILENO);

   // open a log file in write mode.

   fp = fopen ("log.txt", "w+");

   while (1) {
   // dont block context switches, let the process sleep for some time
      sleep(1);
      fprintf (fp, "Logging info...\n");
      fflush  (fp);
   // implement and call some function that does core work for this daemon.
   }
   fclose(fp);
   return (0);
}
