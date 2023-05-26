import java.io.File;
import java.io.FileNotFoundException;
import edu.princeton.cs.algs4.StdIn;
import java.util.Scanner;

//https://docs.google.com/spreadsheets/d/1rbAqULgpzBvlgnND-WkfT3vEWAU__ozRGBziaZ7bQC4/edit?usp=sharing

public class Lab_2 {

    public static long measureBST(Scanner scanBST, int Num){
        BST<String, Integer> bst = new BST<String, Integer>();
        int i = 0;

        long startBST = System.nanoTime();

        while (scanBST.hasNext() && i < Num) {
            String word = scanBST.next();
            if(!bst.contains(word)){
                bst.put(word, 1);
            }else{
                bst.put(word, bst.get(word) + 1);
            }
            i++;
        }

        long endBST = System.nanoTime();
        long timeBST = endBST - startBST;

        String max = "";
        bst.put(max, 0);
        for (String word : bst.keys()) {
            if (bst.get(word) > bst.get(max))
                max = word;
        }

        //System.out.println("Word: '"+max + "'");
        //System.out.println("Amount: "+bst.get(max));
        //System.out.println("word: " + i);
        return timeBST;
    }

    public static void main(String args[])throws FileNotFoundException {
        File text1 = new File("C:\\Users\\Jiahao Wang\\Desktop\\ID1020 Labs\\Searching Labs\\Lab_2\\src\\gutenberg1.txt");
        Scanner scanBST = new Scanner(text1);

        System.out.println("Antal ord som du vill söka på(*100): ");
        int N = StdIn.readInt();
        int Num = N * 100;

        System.out.println("Time: " + measureBST(scanBST, Num));

    }
}
