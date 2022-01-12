public class FlowEdge {
    private final int u, v;
    private final double cap;
    private double flow;

    public FlowEdge(int u, int v, double cap) {
        this.u = u;
        this.v = v;
        this.cap = cap;
    }

    public int from() {
        return u;
    }

    public int to() {
        return v;
    }

    public int otherEndPoint(int vertex){
        if(vertex==u) return v;
        else if(vertex==v) return u;
        else throw new IllegalArgumentException("Cannot find other endpoint: Vertex not on edge");
    }

    public double getCapacity() {
        return cap;
    }

    public double getFlow() {
        return flow;
    }

    public double resCapTo(int vertex) {
        if (vertex == u) return flow;
        else if (vertex == v) return cap - flow;
        else throw new IllegalArgumentException("Cannot find residual flow: Vertex not on edge");
    }

    public void addFlowTo(int vertex, double val) {
        if (vertex == u) flow -= val;
        else if (vertex == v) flow += val;
        else throw new IllegalArgumentException("Cannot add flow: Vertex not on edge");
    }
}
