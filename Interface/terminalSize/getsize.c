#include <stdio.h>
#include <string.h>    
#include <sys/ioctl.h>  
#include <unistd.h>

int main(int argc, char *argv[]) {
    if (argc > 1 && strcmp(argv[1], "--help") == 0) {
        printf("v1.0 - 23.05.2025\nUsage: tty-size\nReturns rows and columns of terminal.\n");
        return 0;
    }

    struct winsize w;
    if (ioctl(STDOUT_FILENO, TIOCGWINSZ, &w) == 0) {
        printf("%d %d\n", w.ws_row, w.ws_col);
        return 0;
    } else {
        printf("24 80\n");
        return 1;
    }
}
