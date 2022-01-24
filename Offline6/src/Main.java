import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            simulate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void simulate() throws Exception {
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
                    heap.increaseKey(oldKey, newKey);
                    System.out.printf("Increased %d. The updated value is %d.%n", oldKey, newKey);
                    break;
                case "FIN":
                    int max = heap.findMax();
                    System.out.printf("FindMax returned %d%n", max);
                    break;
                case "EXT":
                    int ext = heap.extractMax();
                    System.out.printf("ExtractMax returned %d%n", ext);
            }
        }
    }
}
