import java.util.ArrayList;

public class TopSort {
    private static boolean hasCycle = false;

    private static ArrayList<Integer> topsort(ArrayList<Integer>[] adj) {
        int[] visited = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) dfs(adj, visited, order, i);
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] visited, ArrayList<Integer> order, int s) {
        if (visited[s] == 2) return;
        visited[s] = 1;
        for (Integer n : adj[s]) {
            if (visited[n] == 1) hasCycle = true;
            else if (visited[n] == 0) dfs(adj, visited, order, n);
        }
        visited[s] = 2;
        order.add(s);
    }

    public static void main(String[] args) {
        AdjList graph = new AdjList(true); //read input inside
        ArrayList<Integer> order = topsort(graph.adj);
        if (hasCycle) System.out.println("not possible");
        else {
            System.out.println("Order: ");
            for (Integer i : order) System.out.print(i + 1 + " ");
        }
    }
}
