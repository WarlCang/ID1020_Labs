//orkar inte....
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import edu.princeton.cs.algs4.BST;

public class Lab_h {
    public static void main(String args[])throws FileNotFoundException{

        File text = new File("C:\\Users\\Jiahao Wang\\Desktop\\ID1020 Labs\\Searching Labs\\Lab_h\\src\\text.txt");
        Scanner scan = new Scanner(text);
        Scanner input = new Scanner(System.in);
        BST<String, Integer> bst = new BST<String, Integer>();

        while (scan.hasNext()) {
            String word = scan.next();
            if(!bst.contains(word)){
                bst.put(word, 1);
            }else{
                bst.put(word, bst.get(word) + 1);
            }
        }

        System.out.println("kth common word?");
        int k = input.nextInt();


        String[] max = new String[k];

        for(int a = 0; a < k; a++){
            max[a] = "";
            bst.put(max[a], 0);
        }

        for (String word : bst.keys()) {
            for(int i = 0; i < k;  i++){
                if (bst.get(word) > bst.get(max[i])) {
                    for(int s = k-1; s > i; s--){
                        max[s] = max[s-1];
                    }
                    max[i] = word;
                    break;
                }
                if(word.equals(max[i])){
                    break;
                }
            }
        }

        System.out.println("Word " + k + "th commom is: " + max[k-1] + ", times: " + bst.get(max[k-1]));

        System.out.println("n to kth common word?");
        int n = input.nextInt();
        int k2 = input.nextInt();

        String[] max2 = new String[n];

        for(int b = 0; b < n; b++){
            max2[b] = "";
            bst.put(max2[b], 0);
        }

        for (String word : bst.keys()) {
            for(int i = 0; i < n;  i++){
                if (bst.get(word) > bst.get(max2[i])) {
                    for(int s = n-1; s > i; s--){
                        max2[s] = max2[s-1];
                    }
                    max2[i] = word;
                    break;
                }
                if(word.equals(max2[i])){
                    break;
                }
            }
        }
        for(int j = k2-1; j < n ; j++){
            System.out.println("Word " + (j+1) + "th commom is: " + max2[j] + ", times: " + bst.get(max2[j]));
        }

    }
}
