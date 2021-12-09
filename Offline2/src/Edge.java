public class Edge implements Comparable<Edge> {
    private int u, v, id;
    private double w;

    public Edge(int u, int v, double w, int id) {
        this.u = u;
        this.v = v;
        this.w = w;
        this.id = id;
    }

    public int id() {
        return id;
    }

    public double weight() {
        return w;
    }

    public int from() {
        return u;
    }

    public int to() {
        return v;
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

    @Override
    public int compareTo(Edge o) {
        int compare = Double.compare(w, o.w);
        if (compare == 0) compare = Integer.compare(u, o.u);
        return compare;
    }
}
