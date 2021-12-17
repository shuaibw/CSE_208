import java.util.Arrays;


public class ModBellFord {
    private ModBellFord() {
    }

    public static SSSPReturns solveForSSSP(WeightedDigraph g, int start, int end) {
        double[] dist = new double[g.V()];
        double [] actualDist = new double[g.V()];
        int[] prev = new int[g.V()];
        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(actualDist, 1);
        Arrays.fill(prev, -1);
        dist[start] = 0;
        prev[start] = -2;
        boolean relaxed;
        for (int i = 0; i < g.V(); i++) {
            relaxed = false;
            for (Edge e : g.edges()) {
                double newDist = dist[e.from()] - Math.log(e.weight());
                if (dist[e.to()] <= newDist) continue;
                dist[e.to()] = newDist;
                actualDist[e.to()] = actualDist[e.from()] * e.weight();
                prev[e.to()] = e.from();
                relaxed = true;
            }
            if (!relaxed) return new SSSPReturns(start, end, prev, actualDist);
        }

        for (int i = 0; i < g.V(); i++) {
            relaxed = false;
            for (Edge e : g.edges()) {
                double newDist = dist[e.from()] - Math.log(e.weight());
                if (dist[e.to()] <= newDist) continue;
                dist[e.to()] = Double.NEGATIVE_INFINITY;
                actualDist[e.to()] = Double.POSITIVE_INFINITY;
                prev[e.to()] = e.from();
                relaxed = true;
            }
            if (!relaxed) return new SSSPReturns(start, end, prev, actualDist);
        }
        return new SSSPReturns(start, end, prev, actualDist);
    }
}

