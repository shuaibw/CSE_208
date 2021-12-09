import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {

    public Kruskal() {}

    public MSTReturn solve(int start){
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/graph.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        WeightedDigraph g = new WeightedDigraph(m, n);
        for (int i = 0; i < n; i++) {
            int u, v;
            double w;
            u = scanner.nextInt();
            v = scanner.nextInt();
            w = scanner.nextDouble();
            g.addEdge(new Edge(u, v, w));
            g.addEdge(new Edge(v, u, w));
        }
        ArrayList<Edge> edges = g.edges();
        Edge[] mst = new Edge[g.V() - 1];
        edges.sort(Comparator.comparingDouble(Edge::weight));

        UF uf = new UF(g.V());
        int len = 0;
        double mstCost = 0;
        for (Edge e : edges) {
            if (uf.connected(e.from(), e.to())) continue;
            uf.union(e.from(), e.to());
            mst[len++] = e;
            mstCost += e.weight();
            if (len == g.V() - 1) break;
        }
        return new MSTReturn(mst, mstCost);
    }

    public static void main(String[] args) {
        new Kruskal().solve(0);
    }
}
