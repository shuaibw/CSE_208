import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FindSourceVertex {
    private static int postCount = 0;

    private static int findSourceVertex(ArrayList<Integer>[] adj, ArrayList<Integer>[] adjRev) {
        int[] post = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        for (int i = 0; i < adjRev.length; i++) dfsWithPost(i, adj, visited, post);

        Arrays.fill(visited, false);
        int idx = maxPost(post);
        dfs(idx, adj, visited);
        for (boolean b : visited) {
            if (!b) return -1;
        }
        return idx;
    }

    private static int maxPost(int[] post) {
        int idx = -1, max = -1;
        for (int i = 0; i < post.length; i++) {
            if (post[i] > max) {
                max = post[i];
                idx = i;
            }
        }
        return idx;
    }

    private static void dfsWithPost(int v, ArrayList<Integer>[] adjRev, boolean[] visited, int[] post) {
        if (visited[v]) return;
        visited[v] = true;
        for (Integer n : adjRev[v]) {
            if (!visited[n]) dfsWithPost(n, adjRev, visited, post);
        }
        post[v] = postCount++;
    }

    private static void dfs(int v, ArrayList<Integer>[] adj, boolean[] visited) {
        if (visited[v]) return;
        visited[v] = true;
//        System.out.print(v + " ");
        for (Integer n : adj[v]) {
            if (!visited[n]) dfs(n, adj, visited);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] adjRev = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            adjRev[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x].add(y);
            adjRev[y].add(x);
        }
        System.out.println(findSourceVertex(adj, adjRev));
    }
}

