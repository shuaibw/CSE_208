import java.util.Arrays;

public class Dijkstra {
    private Dijkstra() {
    }

    public static SSSPReturns solveForSSSP(WeightedDigraph g, int start, int end) {
        IndexMinPQ<Double> pq = new IndexMinPQ<>(g.E());
        int[] prev = new int[g.V()];
        boolean[] visited = new boolean[g.V()];
        double[] dist = new double[g.V()];
        Arrays.fill(prev, -1);
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[start] = 0;
        prev[start] = -2;
        pq.insert(start, 0.0);
        while (!pq.isEmpty()) {
            int idx = pq.minIndex();
            if (idx == end) break;
            double minDist = pq.minKey();
            pq.delMin();
            visited[idx] = true;
            if (dist[idx] < minDist) continue;
            for (Edge n : g.neighbors(idx)) {
                double newDist = dist[idx] + n.weight();
                if (visited[n.to()] || newDist >= dist[n.to()]) continue;
                dist[n.to()] = newDist;
                prev[n.to()] = idx;
                if (pq.contains(n.to())) pq.decreaseKey(n.to(), newDist);
                else pq.insert(n.to(), newDist);
            }
        }
        return new SSSPReturns(start, end, prev, dist);
    }
}