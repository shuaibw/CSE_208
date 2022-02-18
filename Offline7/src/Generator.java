import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Generator {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/write.txt"));
        HashMap<Integer, Integer> insertedMap = new HashMap<>();
        Random random = new Random(1805010);
        String[] cmdList = {"I", "D"};
        int numberOfNodes = 100;
        while (numberOfNodes != 0) {
            String cmd = cmdList[random.nextInt(2)];
            int node = -500 + random.nextInt(1000);
            if (cmd.equals("D")) {
                if (!insertedMap.containsKey(node)) continue;
                insertedMap.remove(node);
            } else if (cmd.equals("I")) {
                if (!insertedMap.containsKey(node)) {
                    insertedMap.put(node, node);
                    numberOfNodes--;
                } else continue;
            }
            bw.write(String.format("%s %d\n", cmd, node));
        }
        bw.close();
    }
}
