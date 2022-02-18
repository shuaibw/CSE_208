import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new FileInputStream("src/input.txt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        while (scanner.hasNextLine()) {
            try {
                String[] input = scanner.nextLine().split(" ");
                String cmd = input[0].toUpperCase();
                int val = Integer.parseInt(input[1]);
                switch (cmd) {
                    case "I":
                        bst.insertItem(val);
                        break;
                    case "F":
                        System.out.println(bst.searchItem(val));
                        break;
                    case "D":
                        bst.delete(val);
                        break;
                    default:
                        System.out.println("Invalid command");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
