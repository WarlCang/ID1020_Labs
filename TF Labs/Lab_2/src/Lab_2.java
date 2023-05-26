import java.util.*;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Lab_2{//testede bara att skriva in ord av slumpliga bokstäver flera gånger, olika längd. Andra tester känns onödiga.

    static void recursive(Stack<Character> stack){ //typ samma som recursive i C
        char c = StdIn.readChar(); // typ samma som getchar i C
        if(c !='\n'){
            stack.push(c);
            recursive(stack);
        }

        else{
            while (stack.size() != 0) {
                StdOut.print(stack.pop());
            }
        }
    }


    static void iterative(Stack<Character> stack){
        char c = StdIn.readChar(); // typ samma som getchar i C

        while(c != '\n'){
            stack.push(c); // lägger chars i en array(class stack)
            c = StdIn.readChar();
        }

        while(stack.size() != 0) {
            StdOut.print(stack.pop()); //pop retunerar bokstäverna, börjar med den sista, klassen tack är LIFO.
        }
    }

    public static void main(String[] args){
        Stack<Character> stack = new Stack<>();
        recursive(stack);
        System.out.println("\n");
        iterative(stack);
    }
}
