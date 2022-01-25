import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        simulate();
    }

    private static void simulate() {
        BinomialHeap heap = new BinomialHeap();
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("")) {
            String[] data = line.split(" ");
            String cmd = data[0];
            switch (cmd) {
                case "INS":
                    int key = Integer.parseInt(data[1]);
                    heap.insert(key);
                    System.out.printf("Inserted %d%n", key);
                    break;
                case "PRI":
                    System.out.printf("Printing Binomial Heap...%n—-------------------------%n");
                    heap.print();
                    break;
                case "INC":
                    int oldKey = Integer.parseInt(data[1]);
                    int newKey = Integer.parseInt(data[2]);
                    boolean ok = heap.increaseKey(oldKey, newKey);
                    if (ok) System.out.printf("Increased %d. The updated value is %d.%n", oldKey, newKey);
                    else System.out.println("Cannot increase key: either non-existent or duplicate");
                    break;
                case "FIN":
                    int max = heap.findMax();
                    System.out.println(max != Integer.MIN_VALUE ? String.format("FindMax returned %d", max) : "Cannot find max from empty tree");
                    break;
                case "EXT":
                    int ext = heap.extractMax();
                    System.out.println(ext != Integer.MIN_VALUE ? String.format("ExtractMax returned %d", ext) : "Cannot extract from empty heap");
            }
        }
    }
}
