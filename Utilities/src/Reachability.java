import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int start, int end) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adj.length];
        int[] label = new int[adj.length];
        for (int i = 0; i < label.length; i++) label[i] = i;
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) stack.push(i);
            while (!stack.isEmpty()) {
                int cur = stack.pop();
                visited[cur] = true;
                label[cur] = i;
                for (Integer n : adj[cur]) {
                    if (!visited[n]) {
                        stack.push(n);
                        visited[n] = true;
                        label[n] = i;
                    }
                }
            }
        }
        return label[start] == label[end] ? 1 : 0;
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

