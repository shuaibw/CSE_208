

public class WeightedDigraphMatrix {
    private final int V;
    private int E;
    private final double[][] adjMat;
    private int[] inDeg;
    private int[] outDeg;

    public WeightedDigraphMatrix(int v) {
        V = v;
        adjMat = new double[v][v];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                double value = 0;
                if (i != j) value = Double.POSITIVE_INFINITY;
                adjMat[i][j] = value;
            }
        }
        inDeg = new int[V];
        outDeg = new int[V];
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
        adjMat[u][v] = e.weight();
        inDeg[v]++;
        outDeg[u]++;
        E++;
    }

    public double[] neighbors(int v) {
        return adjMat[v];
    }

    public int outDeg(int v) {
        return outDeg[v];
    }

    public int inDeg(int v) {
        return inDeg[v];
    }

    public double[][] adjMat() {
        return adjMat;
    }
}
