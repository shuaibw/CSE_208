import java.util.ArrayList;

public class WeightedDigraph {
    private final int V;
    private int E;
    private final ArrayList<Edge>[] adj;
    private int[] inDeg;

    public WeightedDigraph(int v, int e) {
        V = v;
//        E = e;
        adj = (ArrayList<Edge>[]) new ArrayList[v];
        inDeg = new int[V];
        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int u = e.from();
        int v = e.to();
        adj[u].add(e);
        inDeg[v]++;
        E++;
    }

    public ArrayList<Edge> neighbors(int v) {
        return adj[v];
    }

    public int outDeg(int v) {
        return adj[v].size();
    }

    public int inDeg(int v) {
        return inDeg[v];
    }

    public ArrayList<Edge> edges() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (ArrayList<Edge> v : adj) {
            edges.addAll(v);
        }
        return edges;
    }
}
