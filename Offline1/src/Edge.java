public class Edge implements Comparable<Edge> {
    private final int u;
    private final int v;
    private final double w;

    public Edge(int u, int v, double w) {
        if (u < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (v < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (Double.isNaN(w)) throw new IllegalArgumentException("Weight is NaN");
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int from() {
        return u;
    }

    public int to() {
        return v;
    }

    public double weight() {
        return w;
    }

    public String toString() {
//        return u + "->" + v + " " + String.format("%5.2f", w);
        return "(" + u + "," + v + ")";
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.w, o.w);
    }
}
