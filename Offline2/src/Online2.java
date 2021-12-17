import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Online2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("src/graph.txt"));
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();
        WeightedDigraph g = new WeightedDigraph(vertices);
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            double w = scanner.nextDouble();
            g.addEdge(new Edge(u, v, w, i));
        }
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        runBellmanFord(g, start, end);
    }

    public static void runBellmanFord(WeightedDigraph g, int start, int end) {
        SSSPReturns ans = ModBellFord.solveForSSSP(g, start, end);
        double dist = ans.shortestPathDist();
        if (dist == Double.POSITIVE_INFINITY) {
            System.out.println("There is an anomaly");
        } else {
            System.out.println("There are no anomalies");
            System.out.println("Best exchange rate: " + dist);
            ans.printShortestPath();
        }
    }
}
