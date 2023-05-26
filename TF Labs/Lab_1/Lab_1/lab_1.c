#include <stdio.h>

void adder(const int* x, const int* y, int z) {
	z = *x + *y;
	printf("%d\n" z);
}

int foo(int a) {
	const int k = 10;
	int* x; int* y; int* z;
	x = &a;
	y = &k;
	z = 0;
	adder(&x, &y, &z);
}

int main() {
	foo(7);
}