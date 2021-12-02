import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SCCNew {
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        GraphList graph = new GraphList(m, n);
        GraphList revGraph = new GraphList(m, n);
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            graph.addEdge(x, y);
            revGraph.addEdge(y, x);
        }
        Integer[] post = dfs(revGraph);
        System.out.println(Arrays.toString(post));
        Boolean[] visited = new Boolean[graph.vertices];
        Arrays.fill(visited, false);
        for (int i = 0; i < post.length; i++) {
            int idx = findMax(post);
            post[idx] = -1;
            if (visited[idx]) continue;
            vanillaDfs(graph, idx, visited);
            System.out.println();
        }
    }

    public static int findMax(Integer[] ara) {
        int idx = -1, max = -1;
        for (int i = 0; i < ara.length; i++) {
            if (ara[i] > max) {
                max = ara[i];
                idx = i;
            }
        }
        return idx;
    }

    public static Integer[] dfs(GraphList graph) {
        Integer[] pre = new Integer[graph.vertices];
        Integer[] post = new Integer[graph.vertices];
        Boolean[] visited = new Boolean[graph.vertices];
        Arrays.fill(visited, false);
        for (int i = 0; i < graph.vertices; i++) if (!visited[i]) dfs(graph.getAdj(), i, visited, pre, post);
        return post;
    }

    public static void vanillaDfs(GraphList graphList, int v, Boolean[] visited) {
        vanillaDfs(graphList.getAdj(), v, visited);
    }

    private static void vanillaDfs(ArrayList<Integer>[] adj, int s, Boolean[] visited) {
        if (visited[s]) return;
        visited[s] = true;
        System.out.print(s + 1 + " ");
        for (Integer n : adj[s]) {
            if (visited[n]) continue;
            vanillaDfs(adj, n, visited);
        }
    }

    private static void dfs(ArrayList<Integer> adj[], int s, Boolean[] visited, Integer[] pre, Integer[] post) {
        if (visited[s]) return;
        visited[s] = true;
        pre[s] = count++;
        for (Integer n : adj[s]) {
            if (visited[n]) continue;
            dfs(adj, n, visited, pre, post);
        }
        post[s] = count++;
    }
}