//Koden till quicksort och mergesort tog från webbsidan geeks for geeks
//Samma idé som lab5
//https://docs.google.com/spreadsheets/d/1YSGHa8QNCBeGp8QyZNqQ5YuIuMcQHfalvKErZ_aElsA/edit?usp=sharing
public class Lab_h2 {

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

    static void sortM(int arr[], int l, int r)//recursive
    {
        if (l < r) {
            int m = (l + r) / 2;

            sortM(arr, l, m);
            sortM(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<=high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    static void sortQ(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sortQ(arr, low, pi-1);
            sortQ(arr, pi+1, high);
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
        sortM(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        return end - start;
    }

    static long measureQ(int arr[]){
        long start = System.nanoTime();
        sortQ(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args){
        for(int i = 1; i <= 1000000;i = i * 10){
            System.out.println( i + " elements");
            long mTime = 0;
            long qTime = 0;
            for(int j = 0; j < 1000; j++){
                int[] arrM = createArr(i);
                int[] arrQ = arrM.clone();
                mTime += measureM(arrM);
                qTime += measureQ(arrQ);
            }
            long averageMtime = mTime/1000;
            long averageQtime = qTime/1000;
            System.out.println("Mergesort time: " + averageMtime);
            System.out.println("Quicksort time: " + averageQtime);
        }

    }
}

