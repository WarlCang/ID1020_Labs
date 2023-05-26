//koden för mergesort och insertionsort tog från geeks for geeks
//jag använder cutoff[1-30] och jämför när det är olika värde på cutoff
//samma idé som lab 5
//det blir plötsligt stor skillnad med tiden och ibland inte, p.g.a att mergesort delar array i 2 delar många gånger, så när cutoff når till vissa
//värde då delar mergesort en gång mindre.
//graf https://docs.google.com/spreadsheets/d/1nNc7heq6g_eCGNSohkn5Bzz9doxHNQxMTyeBgnqf_c0/edit?usp=sharing
public class Lab_6 {

    public static int randomInteger(int min, int max){ //random integer
        int a = (int)(Math.random()*((max-min)+1))+min;
        return a;
    }

    static void merge(int arr[], int l, int m, int r)
    {
        int sizeL = m - l + 1; //bestämmer storlek på 2 halvor
        int sizeR = r - m;

        int L[] = new int[sizeL]; //kopierar dem till nya arrays
        int R[] = new int[sizeR];

        for (int i = 0; i < sizeL; ++i)
        {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < sizeR; ++j)
        {
            R[j] = arr[m + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = l;

        //merge, jämför o lägg dem i rätt plats i array
        while (i < sizeL && j < sizeR) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        //Om det finns elementer kvar efter merge, kopiera dem
        while (i < sizeL) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < sizeR) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void sort(int arr[], int l, int r, int cutOff)//recursive
    {
        if(r - l <= cutOff){
            insertionSort(arr);
        }
        else if (l < r) {
            int m = (l + r) / 2;

            sort(arr, l, m,cutOff);
            sort(arr, m + 1, r,cutOff);
            merge(arr, l, m, r);
        }
    }

    static void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) { //outer-loop, går genom alla elementer i array.
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) { // Inner-loop, tar alla elementer som är större o ligger framför key, en position framåt.
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    static int[] createArr(int size){
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = randomInteger(0, 100000);
        }
        return arr;
    }

    static long measureM(int arr[], int cutOff){
        long start = System.nanoTime();
        sort(arr, 0, arr.length - 1, cutOff);
        long end = System.nanoTime();
        return end - start;
    }


    public static void main(String[] args){
        System.out.println( "1000 elements");
        int[] arr = createArr(1000);
        for(int cutOff = 1; cutOff <= 30; cutOff++){
            long mTime = 0;
            int[] arrM = arr;
            for(int j = 0; j < 1000; j++){
                mTime += measureM(arrM,cutOff);
            }
            long averageMtime = mTime/1000;
            System.out.println("Cut-off : " + cutOff + " Time: " + averageMtime);
        }

    }
}

