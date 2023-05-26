import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.algs4.StdIn;
import java.util.Scanner;

//https://docs.google.com/spreadsheets/d/1rbAqULgpzBvlgnND-WkfT3vEWAU__ozRGBziaZ7bQC4/edit?usp=sharing
public class Lab_22 {

    public static long measureBSST(Scanner scanBSST, int Num){
        BinarySearchST<String, Integer> bsst = new BinarySearchST<String, Integer>();
        int i = 0;

        long startBSST = System.nanoTime();

        while (scanBSST.hasNext() && i < Num) {
            String word = scanBSST.next();
            if(!bsst.contains(word)){
                bsst.put(word, 1);
            }else{
                bsst.put(word, bsst.get(word) + 1);
            }
            i++;
        }

        long endBSST = System.nanoTime();
        long timeBSST = endBSST - startBSST;

        String max = "";
        bsst.put(max, 0);
        for (String word : bsst.keys()) {
            if (bsst.get(word) > bsst.get(max))
                max = word;
        }

        //System.out.println("Word: '"+max + "'");
        //System.out.println("Amount: "+bsst.get(max));
        //System.out.println("word: " + i);
        return timeBSST;
    }

    public static void main(String args[])throws FileNotFoundException{
        File text2 = new File("C:\\Users\\Jiahao Wang\\Desktop\\ID1020 Labs\\Searching Labs\\Lab_2\\src\\gutenberg2.txt");
        Scanner scanBSST = new Scanner(text2);

        System.out.println("Antal ord som du vill söka på(*100): ");
        int N = StdIn.readInt();
        int Num = N * 100;


        System.out.println("Time: " + measureBSST(scanBSST, Num));
    }
}
