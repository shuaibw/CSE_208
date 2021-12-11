import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
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
        runDijkstra(g, start, end);
//        runBellmanFord(g, start, end);
    }

    public static void runDijkstra(WeightedDigraph g, int start, int end) {
        SSSPReturns ans = Dijkstra.solveForSSSP(g, start, end);
        double dist = ans.shortestPathDist();
        if (dist == Double.MAX_VALUE) {
            System.out.println("Start and end disconnected");
        } else {
            System.out.println("Shortest path cost: " + dist);
            ans.printShortestPath();
        }
    }

    public static void runBellmanFord(WeightedDigraph g, int start, int end) {
        SSSPReturns ans = BellManFord.solveForSSSP(g, start, end);
        double dist = ans.shortestPathDist();
        if (dist == Double.MIN_VALUE) {
            System.out.println("The graph contains a negative cycle");
        } else {
            System.out.println("The graph does not contain a negative cycle");
            System.out.println("Shortest path cost: " + dist);
            ans.printShortestPath();
        }
    }
}