import edu.princeton.cs.algs4.StdIn;

//En operator ändras så blir listan descending

public class Lab_h1 {

    static void insertionSort(int arr[])
    {
        int swap = 0;
        int n = arr.length;
        for (int i = 1; i < n; ++i) { //outer-loop, går genom alla elementer i array.
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) { // Inner-loop, tar alla elementer som är större o ligger framför key, en position framåt.
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            swap++;
        }
        System.out.println("Antalet swaps:" + swap);
        printArray(arr);
    }

    static void insertionSortH(int arr[]){

        int swap = 0;

        for(int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] < key) { // Inner-loop, tar alla elementer som är större o ligger framför key, en position framåt.
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
            swap++;
        }
        System.out.println("Antalet swaps:" + swap);
        printArray(arr);
    }

    static void printArray(int arr[])
    {
        System.out.print("Sorterat: ");
        int n = arr.length;
        for (int i = 0; i < n; ++i){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static void inversionCount(int arr[]){//jämför varje element med elementer som ligger efter sig, om det är större, då kommer det blir en swap.
        int inversion = 0;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    inversion++;
                    System.out.print("[" + i + "," + arr[i] + "],[" + j + "," + arr[j] + "]\n");
                }
            }
        }
        System.out.println("Antalet inversions:" + inversion);

    }

    public static void main(String[] args){
        /*System.out.println("Array's length?");
        int size = StdIn.readInt();
        int[] arr = new int[size];
        System.out.println("Now fill the array");
        for(int i = 0; i < size; i++){
            arr[i] = StdIn.readInt();
        }
        inversionCount(arr);
        insertionSort(arr);*/

        int[] arr2 = new int[]{1, 2, 4, 3, 5, 0, 8, 9, 5};
        int[] arr3 = arr2.clone();
        inversionCount(arr2);
        insertionSort(arr2);
        inversionCount(arr3);
        insertionSortH(arr3);
    }
}
