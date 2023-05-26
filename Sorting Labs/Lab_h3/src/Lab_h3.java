//Koden till quicksort tog från webbsidan geeks for geeks
//Samma idé som lab5
//https://docs.google.com/spreadsheets/d/1aHoPMjs9xLhMTFz3SquiDAUoxT7_A2pvneofXRnCCOk/edit?usp=sharing
public class Lab_h3 {

    public static int randomInteger(int min, int max){ //random integer
        int a = (int)(Math.random()*((max-min)+1))+min;
        return a;
    }

    static int partitionM(int arr[], int low, int high)
    {
        int pivot = pivotMid(arr, low, high);
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
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

    static int pivotMid(int arr[], int low, int high){
        int mid = high / 2;
        int array[] = {arr[low], arr[mid], arr[high]};
        for(int i = 0; i < 2; i++){
            for(int j = 1; j < 3; j++)
            {
                if(array[i] > array[j]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array[1];
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    static void sortM(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partitionM(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sortM(arr, low, pi-1);
            sortM(arr, pi+1, high);
        }
    }


    static int partitionF(int arr[], int low, int high)
    {
        int pivot = arr[low];
        int i = (high+1); // index of smaller element
        for (int j=high; j>=low; j--)
        {
            // If current element is smaller than the pivot
            if (arr[j] > pivot)
            {
                i--;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i-1];
        arr[i-1] = arr[low];
        arr[low] = temp;

        return i-1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    static void sortF(int arr[], int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partitionF(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sortF(arr, low, pi-1);
            sortF(arr, pi+1, high);
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

    static long measureF(int arr[]){
        long start = System.nanoTime();
        sortF(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args){
        for(int i = 1; i <= 100000;i = i * 10){
            System.out.println( i + " elements");
            long mTime = 0;
            long fTime = 0;
            for(int j = 0; j < 1000; j++){
                int[] arrM = createArr(i);
                int[] arrF = arrM.clone();
                mTime += measureM(arrM);
                fTime += measureF(arrF);
            }
            long averageMtime = mTime/1000;
            long averageFtime = fTime/1000;
            System.out.println("Quicksort(mid) time: " + averageMtime);
            System.out.println("Quicksort(first) time: " + averageFtime);
        }

    }
}

