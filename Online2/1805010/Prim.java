import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Prim {
    WeightedDigraph g;
    IndexMinPQ<Edge> ipq;
    boolean[] visited;
    public Prim() {
    }

    public MSTReturn solve(int start){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/graph.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        g = new WeightedDigraph(m, n);
        visited = new boolean[m];
        for (int i = 0; i < n; i++) {
            int u, v;
            double w;
            u = scanner.nextInt();
            v = scanner.nextInt();
            w = scanner.nextDouble();
            g.addEdge(new Edge(u, v, w, i));
            g.addEdge(new Edge(v, u, w, i));
        }
        return primsMstEdges(start);
    }

    private MSTReturn primsMstEdges(int start) {
        int expectedEdges = g.V() - 1;
        int currentEdges = 0, mstCost = 0;
        Edge[] mstEdges = new Edge[expectedEdges];
        ipq = new IndexMinPQ<>(g.V());
        relaxNeighbors(start);
        while (!ipq.isEmpty() && currentEdges != expectedEdges) {
            Edge minEdge = ipq.minKey();
            ipq.delMin();
            mstEdges[currentEdges++] = minEdge;
            mstCost += minEdge.weight();
            relaxNeighbors(minEdge.to());
        }
        if (currentEdges != expectedEdges) return null;
        return new MSTReturn(mstEdges, mstCost);
    }

    private void relaxNeighbors(int s) {
        visited[s] = true;
        ArrayList<Edge> neighbors = g.neighbors(s);
        for (Edge e : neighbors) {
            int to = e.to();
            if (visited[to]) continue;
            if (!ipq.contains(to)) ipq.insert(to, e);
            else if (e.weight() < ipq.keyOf(to).weight()) ipq.decreaseKey(to, e);
        }
    }

    public static void main(String[] args) {
        new Prim().solve(0);
    }
}
