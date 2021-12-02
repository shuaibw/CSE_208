import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bipartite {

    private static int bipartite(ArrayList<Integer>[] adj) {
        Queue<Integer> queue = new LinkedList<>();
        int[] color = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        int p;
        for (int i = 0; i < adj.length; i++) {
            if (color[i] == 0) {
                queue.add(i);
                visited[i] = true;
            }
            while (!queue.isEmpty()) {
                int v = queue.remove();
                p = 1 ^ color[v];
                for (Integer n : adj[v]) {
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                        color[n] = p;
                    } else if (color[v] == color[n]) return 0;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

