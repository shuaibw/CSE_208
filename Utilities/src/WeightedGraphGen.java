import edu.princeton.cs.algs4.Edge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class WeightedGraphGen {
    public static void main(String[] args) throws IOException {
        int n = 10;
        Edge[][] adjMat = new Edge[n][n];
        Random random = new Random(1805010);
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/graph.txt"));
        bw.write(String.format("%d %d\n", n, (n * (n - 1)) / 2));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                Edge e = new Edge(i, j, -10 + random.nextDouble() * 20);
                adjMat[i][j] = e;
                adjMat[j][i] = e;
                int a = e.either();
                int b = e.other(a);
                bw.write(String.format("%d %d %.2f\n", a, b, e.weight()));
            }
        }
        bw.close();
    }
}
