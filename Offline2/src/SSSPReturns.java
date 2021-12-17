import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
        for (double v : dist) {
            if (v == Double.NEGATIVE_INFINITY) {
                System.out.println("Cannot print shortest path. Contains an anomaly, but there might exist a path to the target without encountering an anomaly. Do you want to force print the path?");
                Scanner scanner=new Scanner(System.in);
                String inp = scanner.nextLine();
                if(inp.equalsIgnoreCase("yes")) break;
                return;
            }
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
