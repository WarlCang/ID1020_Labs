import java.util.Scanner;
import java.io.FileNotFoundException;

public class Lab_1 {
    public static void main(String[] args) throws FileNotFoundException {
        SymbolGraph G = new SymbolGraph("C:\\Users\\Jiahao Wang\\Desktop\\ID1020 Labs\\Graph Labs\\Lab_1\\src\\the_database.txt");
        Scanner in = new Scanner(System.in);
        boolean test = false;
        //System.out.println(G.toString());
        while(!test){
            try{
                System.out.println("Start in?");
                String start = in.next().toUpperCase();
                DepthFirstPaths Find = new DepthFirstPaths(G.graph(), G.indexOf(start));
                System.out.println("Destination?");
                String end = in.next().toUpperCase();
                System.out.println("Take this path!");
                System.out.println(G.toString(Find.pathTo(G.indexOf(end))));
                test = true;
            }catch(Exception e){
                System.out.println("No such place!");
            }
        }
    }
}