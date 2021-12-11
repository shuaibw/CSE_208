import java.util.ArrayList;
import java.util.Collections;

public class SSSPReturns {
    private int[] prev;
    private double[] dist;
    private int start;
    private int end;

    public SSSPReturns(int start, int end, int[] prev, double[] dist) {
        this.prev = prev;
        this.dist = dist;
        this.start = start;
        this.end = end;
    }

    public double shortestPathDist() {
        return dist[end];
    }

    public void printShortestPath() {
        if (prev[end] == -1) {
            System.out.println("No connection between start and end");
            return;
        }
        if(dist[end]==Double.MIN_VALUE){
            System.out.println("Cannot print shortest path. Contains a negative cycle");
            return;
        }
        ArrayList<Integer> path = new ArrayList<>();
        int i = end;
        while (i != -2) {
            path.add(i);
            i = prev[i];
        }
        Collections.reverse(path);
        StringBuilder sb = new StringBuilder();
        for (Integer v : path) sb.append(v).append(" -> ");
        sb.setLength(sb.length() - 4);
        System.out.println(sb);
    }
}
