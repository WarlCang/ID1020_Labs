import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import edu.princeton.cs.algs4.Queue;

public class Lab_3 {

    /**
     * Symbol Table, Linked List, each node contains a value and key and reference to next node
     * Used in the separate chaining hash table
     */
    public static class SequentialSearchST<Key, Value> {
        private Node first;      // the linked list of key-value pairs

        // a helper linked list data type
        private class Node {
            private Key key;
            private Value val;
            private Node next;

            public Node(Key key, Value val, Node next)  {
                this.key  = key;
                this.val  = val;
                this.next = next;
            }
        }

        /**
         * Initializes an empty symbol table.
         */
        public SequentialSearchST() {
        }

        /**
         * Does this symbol table contain the given key?
         * @param key the key
         * @return {@code true} if this symbol table contains {@code key} and
         *     {@code false} otherwise
         */
        public boolean contains(Key key) {
            return get(key) != null;
        }

        /**
         * Returns the value associated with the given key.
         * @param key the key
         * @return the value associated with the given key if the key is in the symbol table
         *     and {@code null} if the key is not in the symbol table
         */
        public Value get(Key key) {
            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key))
                    return x.val;
            }
            return null;
        }

        /**
         * Inserts the key-value pair into the symbol table, overwriting the old value
         * with the new value if the key is already in the symbol table.
         * If the value is {@code null}, this effectively deletes the key from the symbol table.
         * @param key the key
         * @param val the value
         */
        public void put(Key key, Value val) {
            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key)) {
                    x.val = val;
                    return;
                }
            }
            first = new Node(key, val, first);
        }
        /**
         * Returns all keys in the symbol table as an {@code Iterable}.
         * To iterate over all of the keys in the symbol table named {@code st},
         * use the foreach notation: {@code for (Key key : st.keys())}.
         * @return all keys in the symbol table as an {@code Iterable}
         */
        public Iterable<Key> keys()  {
            Queue<Key> queue = new Queue<Key>();
            for (Node x = first; x != null; x = x.next)
                queue.enqueue(x.key);
            return queue;
        }

        public int printST() {
            int i = 0;
            Node node = first;
            while (node != null) {
                node = node.next;
                i++;
            }
            return i;
        }
    }

    /**
     * Separate chaining hash table.
     * Each hash code refers to a linked list (ST). So the objects
     * with same hash code is placed in the same linked list with
     * its key and value to avoid collision resolution.
     */
    public static class SeparateChainingHashST<Key, Value> {
        private static final int INIT_CAPACITY = 4;
        private int n;                                // number of key-value pairs
        private int m;                                // hash table size
        private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


        /**
         * Initializes an empty symbol table.
         */
        public SeparateChainingHashST() {
            this(INIT_CAPACITY);
        }

        /**
         * Initializes an empty symbol table with {@code m} chains.
         * @param m the initial number of chains
         */
        public SeparateChainingHashST(int m) {
            this.m = m;
            st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
            for (int i = 0; i < m; i++)
                st[i] = new SequentialSearchST<Key, Value>();
        }

        // resize the hash table to have the given number of chains,
        // rehashing all of the keys
        private void resize(int chains) {
            SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
            for (int i = 0; i < m; i++) {
                for (Key key : st[i].keys()) {
                    temp.put(key, st[i].get(key));
                }
            }
            this.m  = temp.m;
            this.n  = temp.n;
            this.st = temp.st;
        }

        // hash value between 0 and m-1
        private int hash(Key key) {
            return (key.hashCode() & 0x7fffffff) % m;
        }

        /**
         * Inserts the specified key-value pair into the symbol table, overwriting the old
         * value with the new value if the symbol table already contains the specified key.
         * Deletes the specified key (and its associated value) from this symbol table
         * if the specified value is {@code null}.
         *
         * @param  key the key
         * @param  val the value
         * @throws IllegalArgumentException if {@code key} is {@code null}
         */
        public void put(Key key, Value val) {

            // double table size if average length of list >= 10
            //if (n >= 10*m) resize(2*m);

            int i = hash(key);
            if (!st[i].contains(key)) n++;
            st[i].put(key, val);
        }

        // return keys in symbol table as an Iterable
        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (int i = 0; i < m; i++) {
                for (Key key : st[i].keys())
                    queue.enqueue(key);
            }
            return queue;
        }

        public void printTable() {
            int total = 0;
            int min = 10000;
            int max = 0;
            for(int i = 0; i < m; i++){
                System.out.print("Chain " + i + ": ");
                System.out.print(st[i].printST() + " words.");
                total += st[i].printST();
                if(st[i].printST() > max) max = st[i].printST();
                if(st[i].printST() < min) min = st[i].printST();
                System.out.println();
                System.out.println();
            }
            System.out.println("The minimum number of words in chains: " + min);
            System.out.println("The maximum number of words in chains: " + max);
            System.out.println("The mean number of words in chains: " + total/m);

        }
    }


    /**
     * Test program, read words from given textfile and put them into the
     * hashtable then print the table using print methods
     */
    public static void main(String[] args) throws IOException {

        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>(100);
        File text = new File("C:\\Users\\Jiahao Wang\\Desktop\\ID1020 Labs\\Searching Labs\\Lab_3\\src\\gutenberg.txt");
        Scanner scan = new Scanner(text);

        String input;
        int i = 0;
        while (scan.hasNext()) {
            input = scan.next();
            st.put(input, i);
            i++;
        }
        st.printTable();
    }
}
