import java.util.LinkedList;

public class MyHashTable {
    private final int m; //hash table length
    private final LinkedList<String>[] set;
    private final HashFunc hashFunc;
    private final HashType hashType;
    private int numCollisions;
    private int numProbes;

    public MyHashTable(int m, HashFunc hashFunc, HashType hashType) {
        this.m = m;
        set = (LinkedList<String>[]) new LinkedList[m];
        for (int i = 0; i < m; i++) set[i] = new LinkedList<>();
        this.hashFunc = hashFunc;
        this.hashType = hashType;
    }

    public int getNumCollisions() {
        return numCollisions;
    }

    public int getNumProbes() {
        return numProbes;
    }

    public void insert(String k) {
        int idx;
        if (hashType == HashType.SINGLE) { //separate chaining
            idx = hashFunc.compute(k);
            if (!set[idx].isEmpty() && !set[idx].get(0).equals(k)) numCollisions++;
            if (!set[idx].contains(k)) set[idx].addFirst(k);
        } else { //probing along main array
            for (int i = 0; i < m; i++) {
                idx = computeHash(k, i);
                if (set[idx].isEmpty()) { // then add and done
                    set[idx].addFirst(k);
                    return;
                } else if (set[idx].get(0).equals(k)) return; // else if contains key then do nothing
                numCollisions++;
            }
            throw new RuntimeException("Looped m times: no where to insert");
        }
    }

    public boolean search(String k) {
        int idx;
        if (hashType == HashType.SINGLE) {
            idx = hashFunc.compute(k);
            for (int i = 0; i < set[idx].size(); i++) {
                numProbes++;
                if (set[idx].get(i).equals(k)) return true;
            }
        } else {
            for (int i = 0; i < m; i++) {
                idx = computeHash(k, i);
                numProbes++;
                if (set[idx].isEmpty()) return false;
                else if (set[idx].get(0).equals(k)) return true;
            }
        }
        return false;
    }

    public boolean delete(String k) {
        int idx;
        if (hashType == HashType.SINGLE) {
            idx = hashFunc.compute(k);
            return set[idx].remove(k);
        } else {
            for (int i = 0; i < m; i++) {
                idx = computeHash(k, i);
                if (set[idx].isEmpty()) break;
                if (set[idx].get(0).equals(k)) {
                    set[idx].remove(k);
                    return true;
                }
            }
            return false;
        }
    }

    private int auxHash(String k) {
        int multiplier = 0xDEADBEEF;
        long hash = 0;
        for (int i = 0; i < k.length(); i++) hash = (hash + k.charAt(i) ^ multiplier) % 1000000009;
        return (int) (hash % m);
    }

    private int doubleHash(String k, int i) {
        int retVal = (hashFunc.compute(k) + i * auxHash(k)) % m;
        return retVal < 0 ? (retVal + m) % m : retVal;
    }

    private int customHash(String k, int i) {
        int c1 = 311;
        int c2 = 409;
        int retVal = (hashFunc.compute(k) + c1 * i * auxHash(k) + (c2 * i ^ 2)) % m;
        if (retVal < 0) retVal = (retVal + m) % m;
        return retVal;
    }

    private int computeHash(String k, int i) {
        return hashType == HashType.DOUBLE ? doubleHash(k, i) : customHash(k, i);
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
    }
}
