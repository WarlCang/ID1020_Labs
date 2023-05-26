#pragma warning(disable:4996)
#include "stdio.h"
#include "stdlib.h"
#include "ctype.h"

void filter() {

    FILE* input = fopen("gutenberg.txt", "r");
    FILE* output = fopen("gutenberg_out.txt", "w");

    if (input == NULL) {
        perror("Error in opening file");
        return(-1);
    }

    int c;

    while ((c = fgetc(input)) != EOF) {
        if (isalpha(c) == 0 && c != ' ' && c != '\n')
            putc(' ', output);
        else
            putc(c, output);
    }
}
int main() {
    filter();
}
