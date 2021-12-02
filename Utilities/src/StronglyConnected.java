import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StronglyConnected {
    private static int postCount = 0;

    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] adjRev) {
        //write your code here
        int[] post = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        for (int i = 0; i < adjRev.length; i++) dfsR(i, adjRev, visited, post);

        Arrays.fill(visited, false);
        int idx = maxPost(post);
        int scc = 0;
        while (idx != -1) {
            dfs(idx, adj, visited, post);
            System.out.println();
            scc++;
            idx = maxPost(post);
        }

        return scc;
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

    private static void dfsR(int v, ArrayList<Integer>[] adjRev, boolean[] visited, int[] post) {
        if (visited[v]) return;
        visited[v] = true;
        for (Integer n : adjRev[v]) {
            if (!visited[n]) dfsR(n, adjRev, visited, post);
        }
        post[v] = postCount++;
    }

    private static void dfs(int v, ArrayList<Integer>[] adj, boolean[] visited, int[] post) {
        if (visited[v]) return;
        visited[v] = true;
        System.out.print(v + " ");
        post[v] = -1;
        for (Integer n : adj[v]) {
            if (!visited[n]) dfs(n, adj, visited, post);
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
            x = scanner.nextInt() + 1;
            y = scanner.nextInt() + 1;
            adj[x - 1].add(y - 1);
            adjRev[y - 1].add(x - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, adjRev));
    }
}

