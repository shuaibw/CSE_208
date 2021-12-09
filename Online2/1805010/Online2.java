import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Online2 {

    private static MSTReturn runKruskal(WeightedDigraph g, boolean[] usedEdges) {
        ArrayList<Edge> edges = g.edges();
        Edge[] mst = new Edge[g.V() - 1];
        edges.sort(Comparator.comparingDouble(Edge::weight));

        UF uf = new UF(g.V());
        int len = 0;
        double mstCost = 0;
        for (Edge e : edges) {
            if (uf.connected(e.from(), e.to())) continue;
            uf.union(e.from(), e.to());
            usedEdges[e.id()] = true;
            mst[len++] = e;
            mstCost += e.weight();
            if (len == g.V() - 1) break;
        }
        if (len != g.V() - 1) return null;
        return new MSTReturn(mst, mstCost);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        WeightedDigraph mGraph = new WeightedDigraph(m, n);
        WeightedDigraph fGraph = new WeightedDigraph(m, n);

        //keep track of used edges in each of male and female graph combined
        boolean[] usedEdges = new boolean[n];
        for (int i = 0; i < n; i++) {
            int u, v;
            double w;
            u = scanner.nextInt() - 1;
            v = scanner.nextInt() - 1;
            w = scanner.nextDouble();

            //male roads only to mGraph
            if (w == 1) {
                mGraph.addEdge(new Edge(u, v, w, i));
                mGraph.addEdge(new Edge(v, u, w, i));
            //female roads only to fGraph
            } else if (w == 2) {
                fGraph.addEdge(new Edge(u, v, w, i));
                fGraph.addEdge(new Edge(v, u, w, i));
            //combined roads to both graph
            } else {
                w = 0;
                mGraph.addEdge(new Edge(u, v, w, i));
                mGraph.addEdge(new Edge(v, u, w, i));
                fGraph.addEdge(new Edge(u, v, w, i));
                fGraph.addEdge(new Edge(v, u, w, i));
            }
        }
        MSTReturn male = runKruskal(mGraph, usedEdges);
        MSTReturn female = runKruskal(fGraph, usedEdges);

        //null if either disconnected
        if(male == null || female == null){
            System.out.println(-1);
            return;
        }
        int destroyable = 0;
        for (boolean b : usedEdges) if(!b) destroyable++;
        System.out.println(destroyable);
    }
}
