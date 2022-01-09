import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("src/graph.txt"));
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();
        WeightedDigraphMatrix g = new WeightedDigraphMatrix(vertices);
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            double w = scanner.nextDouble();
            g.addEdge(new Edge(u, v, w, i));
        }
        double[][] distMat = new FloydWarshall().solveForAPSP(g);
        if (distMat == null) {
            System.out.println("Negative cycle exists");
            return;
        }
        System.out.println("Shortest distance matrix\n");
        for (double[] a : distMat) {
            for (double v : a) {
                if (v == Double.POSITIVE_INFINITY) System.out.printf("%6s", "INF");
                else if (v == Double.NEGATIVE_INFINITY) System.out.printf("%6s", "-INF");
                else System.out.printf("%5d ", Math.round(v));
            }
            System.out.println();
        }
    }
}
