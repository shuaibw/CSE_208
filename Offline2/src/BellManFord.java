import java.util.Arrays;

public class BellManFord {
    private BellManFord() {
    }

    public static SSSPReturns solveForSSSP(WeightedDigraph g, int start, int end) {
        double[] dist = new double[g.V()];
        int[] prev = new int[g.V()];
        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;
        prev[start] = -2;
        boolean relaxed;
        for (int i = 0; i < g.V(); i++) {
            relaxed = false;
            for (Edge e : g.edges()) {
                double newDist = dist[e.from()] + e.weight();
                if (dist[e.to()] <= newDist) continue;
                dist[e.to()] = newDist;
                prev[e.to()] = e.from();
                relaxed = true;
            }
            if (!relaxed) return new SSSPReturns(start, end, prev, dist);
        }

        for (int i = 0; i < g.V(); i++) {
            relaxed = false;
            for (Edge e : g.edges()) {
                double newDist = dist[e.from()] + e.weight();
                if (dist[e.to()] <= newDist) continue;
                dist[e.to()] = Double.MIN_VALUE;
                prev[e.to()] = e.from();
                relaxed = true;
            }
            if (!relaxed) return new SSSPReturns(start, end, prev, dist);
        }
        return new SSSPReturns(start, end, prev, dist);
    }
}

