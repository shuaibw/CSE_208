import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {
    private class Vertex {
        private int v;
        private double cost;

        private Vertex(int v, double cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    private double[] dist;
    private boolean[] visited;
    private int[] prev;
    private PriorityQueue<Vertex> pq;

    public Dijkstra(WeightedDigraph g) {
        dist = new double[g.V()];
        visited = new boolean[g.V()];
        prev = new int[g.V()];
        pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.cost));
    }

    public int[] computeSSSP(int from, int to) {
        clear();
        dist[from] = 0;
        pq.add(new Vertex(from, 0));
        while(!pq.isEmpty()){
            Vertex cur = pq.poll();

        }
    }

    private void clear() {
        Arrays.fill(visited, false);
        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(prev, -1);
        pq.clear();
    }

}
