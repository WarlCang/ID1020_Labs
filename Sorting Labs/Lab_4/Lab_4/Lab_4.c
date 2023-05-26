#include <stdio.h>
// in place theorem

void print(int arr[], int size) {
    for (int i = 0; i < size; i++) {
        printf("[%d], ", arr[i]);
    }
    printf("\n");
}

void move(int arr[], int size) {
    int j = 0;
    int temp = 0;
    for (int i = 0; i < size; i++) {
        if (arr[i] < 0) {
            temp = arr[j];
            arr[j++] = arr[i];
            arr[i] = temp;
        }
    }
    print(arr, 10);
}

int main() {
    int arr[] = {-1,-2,-3,4,-5,1,2,-6,7,-8};
    print(arr, 10);
    move(arr, 10);
}