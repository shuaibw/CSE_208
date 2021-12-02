import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphList {
    private ArrayList<Integer>[] adj;
    int vertices, edges;
    int[] inDeg, outDeg;

    public GraphList(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        inDeg = new int[vertices];
        outDeg = new int[vertices];
        adj = (ArrayList<Integer>[]) new ArrayList[vertices];
        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<>();
    }

    public ArrayList<Integer>[] getAdj() {
        return adj;
    }

    public boolean isEdge(int x, int y) {
        return adj[x].contains(y);
    }

    public boolean addEdge(int x, int y) {
        if (isEdge(x, y)) return false;
        adj[x].add(y);
        outDeg[x]++;
        inDeg[y]++;
        return true;
    }

    public boolean removeEdge(int x, int y) {
        boolean removed = adj[x].remove(Integer.valueOf(y));
        if (removed) {
            outDeg[x]--;
            inDeg[y]--;
        }
        return removed;
    }

    public int getInDegree(int x) {
        return inDeg[x];
    }

    public int getOutDegree(int x) {
        return outDeg[x];
    }

    public boolean hasCommonNeighbours(int x, int y) {
        for (int i = 0; i < vertices; i++) if (isEdge(x, i) && isEdge(y, i)) return true;
        return false;
    }

    public int getBFSDist(int x, int y) {
        int[] parents = bfs(x);
        if (parents[y] == -1) return -1;
        int dist = 0;
        while (parents[y] != -2) {
            y = parents[y];
            dist++;
        }
        return dist;
    }

    public int[] bfs(int source) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];
        int[] prev = new int[vertices];
        Arrays.fill(prev, -1);
        prev[source] = -2;
        queue.add(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Integer n : adj[v]) {
                if (visited[n]) continue;
                queue.add(n);
                visited[n] = true;
                prev[n] = v;
            }
        }
        return prev;
    }

    public boolean isAcyclic() {
        int[] visited = new int[vertices];
        boolean[] hasCycle = new boolean[1];
        for (int i = 0; i < vertices; i++) if (!hasCycle[0]) dfsCycleDetect(i, visited, hasCycle);
        return hasCycle[0];
    }

    private void dfsCycleDetect(int v, int[] visited, boolean[] hasCycle) {
        if (visited[v] == 2) return;
        visited[v] = 1;
        for (Integer n : adj[v]) {
            if (visited[n] == 1) hasCycle[0] = true;
            else if (visited[n] == 0) dfsCycleDetect(n, visited, hasCycle);
        }
        visited[v] = 2;
    }
}
