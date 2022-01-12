public class FlowEdge {
    private final Vertex u, v;
    private final double cap;
    private double flow;

    public FlowEdge(Vertex u, Vertex v, double cap) {
        this.u = u;
        this.v = v;
        this.cap = cap;
    }

    public int from() {
        return u.id;
    }

    public int to() {
        return v.id;
    }

    public int otherEndPoint(int vertexID) {
        if (vertexID == u.id) return v.id;
        else if (vertexID == v.id) return u.id;
        else throw new IllegalArgumentException("Cannot find other endpoint: Vertex not on edge");
    }

    public double getCapacity() {
        return cap;
    }

    public double getFlow() {
        return flow;
    }

    public double resCapTo(int vertexID) {
        if (vertexID == u.id) return flow;
        else if (vertexID == v.id) return cap - flow;
        else throw new IllegalArgumentException("Cannot find residual flow: Vertex not on edge");
    }

    public void addFlowTo(int vertexID, double val) {
        if (vertexID == u.id) flow -= val;
        else if (vertexID == v.id) flow += val;
        else throw new IllegalArgumentException("Cannot add flow: Vertex not on edge");
    }

    @Override
    public String toString() {
        return String.format("%d->%d, %d/%d", u.id, v.id, (int) flow, (int) cap);
    }
}
