//koden för insertionsort och mergesort tog från geeks for geeks
//jag jämför de 2 metoder med att köra dem med olika många antal element men samma array
//varje kör jag 1000 gånger, blir olika array varje gång, sen tar jag medelvärdet
//så det blir mer noggrant
//graf https://docs.google.com/spreadsheets/d/1KonQiQ4eq4ipo8XQHJ2Yo73ZfuuOJP50qL3lyR_N-sU/edit?usp=sharing
public class Lab_5 {

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

    static void sort(int arr[], int l, int r)//recursive
    {
        if (l < r) {
            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);
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

    static long measureM(int arr[]){
        long start = System.nanoTime();
        sort(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        return end - start;
    }

    static long measureI(int arr[]){
        long start = System.nanoTime();
        insertionSort(arr);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args){
        for(int i = 1; i <= 10000;i = i * 10){
            System.out.println( i + " elements");
            long mTime = 0;
            long iTime = 0;
            for(int j = 0; j < 1000; j++){
                int[] arrM = createArr(i);
                int[] arrI = arrM.clone();
                mTime += measureM(arrM);
                iTime += measureI(arrI);
            }
            long averageMtime = mTime/1000;
            long averageItime = iTime/1000;
            System.out.println("Mergesort time: " + averageMtime);
            System.out.println("Insertionsort time: " + averageItime);
        }

    }
}
