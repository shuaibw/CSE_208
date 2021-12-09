import java.util.Comparator;

public class Edge implements Comparable<Edge> {
    private final int u;
    private final int v;
    private final double w;
    private final int id;

    public Edge(int u, int v, double w, int id) {
        if (u < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (v < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
        if (Double.isNaN(w)) throw new IllegalArgumentException("Weight is NaN");
        this.u = u;
        this.v = v;
        this.w = w;
        this.id = id;
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

    public int id() {
        return id;
    }

    public String toString() {
//        return u + "->" + v + " " + String.format("%5.2f", w);
        return "(" + u + "," + v + ")";
    }

    @Override
    public int compareTo(Edge o) {
        int compare = Double.compare(this.w, o.w);
        if (compare == 0) return Integer.compare(this.u, o.v);
        return compare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return id == edge.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
