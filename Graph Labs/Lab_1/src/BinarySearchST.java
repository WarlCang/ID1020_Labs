public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;


    public BinarySearchST() {
        this(INIT_CAPACITY);
    }


    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }


    private void resize(int capacity) {
        assert capacity >= n;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }


    public int size() {
        return n;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }


    public Value get(Key key) {
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    public int rank(Key key) {

        int lo = 0, hi = n-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val)  {

        int i = rank(key);

        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        if (n == keys.length) resize(2*keys.length);

        for (int j = n; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[n-1];
    }


    public Iterable<Key> keys() {
        return keys(min(), max());
    }


    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }
}