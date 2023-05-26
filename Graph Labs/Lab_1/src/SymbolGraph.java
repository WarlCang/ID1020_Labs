import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SymbolGraph {
    private BinarySearchST<String, Integer> bsst;  // string -> index
    private String[] keys;           // index  -> string
    private Graph graph;             // the underlying graph

    public SymbolGraph(String filename) throws FileNotFoundException {
        bsst = new BinarySearchST<String, Integer>();


        File text = new File(filename);
        Scanner scan = new Scanner(text);

        while (scan.hasNext()) {
            String a = scan.next();
            if (!bsst.contains(a)){
                bsst.put(a, bsst.size());
            }
        }

        keys = new String[bsst.size()];
        for (String name : bsst.keys()) {
            keys[bsst.get(name)] = name;
        }

        graph = new Graph(bsst.size());
        text = new File(filename);
        scan = new Scanner(text);

        while (scan.hasNext()) {
            String V = scan.next();
            String W = scan.next();
            int v = bsst.get(V);
            int w = bsst.get(W);
            graph.addEdge(v, w);
        }

    }

    public int indexOf(String s) {
        return bsst.get(s);
    }

    public String nameOf(int v) {
        return keys[v];
    }


    @Deprecated
    public Graph G() {
        return graph;
    }


    public Graph graph() {
        return graph;
    }

    public String toString(Iterable<Integer> places){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int place : places){
            sb.append(this.nameOf(place) + " <-> ");
        }
        sb.replace(sb.length()-4,sb.length(),"}");

        return sb.toString();
    }
}