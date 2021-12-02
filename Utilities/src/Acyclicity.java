import java.io.IOException;
import java.util.ArrayList;

public class Acyclicity {
    private static ArrayList<Integer>[] adj;
    private static int[] visited;
    private static int hasCycle = 0;

    private static int acyclic() {
        for (int i = 0; i < adj.length; i++) dfs(i);
        return hasCycle;
    }

    private static void dfs(int v) {
        if (visited[v] == 2) return;
        visited[v] = 1;
        for (Integer n : adj[v]) {
            if (visited[n] == 1) hasCycle = 1;
            if (visited[n] == 0) dfs(n);
        }
        visited[v] = 2;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        adj = (ArrayList<Integer>[]) new ArrayList[n];
        visited = new int[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic());
    }
}

