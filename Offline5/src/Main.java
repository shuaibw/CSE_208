import java.util.Random;

public class Main {
    private static final int p = 1000000007;//crazy big prime
    private static int m = 80309;//hash table size
    private static final int n = 10000;//number of words
    private static final String[] spec = new String[]{"Separate chain (h1)", "Separate chain (h2)", "Double Hash (h1)", "Double hash (h2)",
            "Custom hash (h1)", "Custom hash (h2)"}; //order must maintain

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        m=scanner.nextInt();
        HashFunc h1 = (k) -> { //rolling poly hash
            int multiplier = 263;
            long hash = 0;
            for (int i = 0; i < k.length(); i++) hash = (hash * multiplier + k.charAt(i)) % p;
            return (int) hash % m;
        };
        HashFunc h2 = (k) -> { //djb2 hashing: http://www.cse.yorku.ca/~oz/hash.html
            long hash = 5381;
            for (int i = 0; i < k.length(); i++) hash = (((hash << 5) + hash) + k.charAt(i));
            int retVal = (int) hash % m;
            return retVal < 0 ? (retVal + m) % m : retVal;
        };
        MyHashTable[] tables = createTables(h1, h2);
        String[] words = RandomWord.generateWords(7, n);
        fillHashTables(tables, words);
        randomSearch(tables, words, 1000);
    }

    private static MyHashTable[] createTables(HashFunc h1, HashFunc h2) {
        MyHashTable table1 = new MyHashTable(m, h1, HashType.SINGLE);
        MyHashTable table2 = new MyHashTable(m, h2, HashType.SINGLE);
        MyHashTable table3 = new MyHashTable(m, h1, HashType.DOUBLE);
        MyHashTable table4 = new MyHashTable(m, h2, HashType.DOUBLE);
        MyHashTable table5 = new MyHashTable(m, h1, HashType.CUSTOM);
        MyHashTable table6 = new MyHashTable(m, h2, HashType.CUSTOM);
        return new MyHashTable[]{table1, table2, table3, table4, table5, table6};
    }

    private static void fillHashTables(MyHashTable[] tables, String[] words) {
        for (MyHashTable t : tables) {
            for (String word : words) {
                try {
                    t.insert(word);
                } catch (Exception e) {
                    System.out.println("Table overflow: " + e.getMessage());
                    break;
                }
            }
        }
    }

    private static void randomSearch(MyHashTable[] tables, String[] words, int searches) {
        Random random = new Random();
        for (int i = 0; i < tables.length; i++) {
            for (int j = 0; j < searches; j++) {
                tables[i].search(words[random.nextInt(n)]);
            }
            System.out.println(spec[i] +
                    String.format("=> Collisions: %d, Avg probs: %.2f", tables[i].getNumCollisions(), (double) tables[i].getNumProbes() / 1000));
        }
    }
}
