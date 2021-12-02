import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adj.length];
        int components = 0;
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                stack.push(i);
                components++;
            }
            while (!stack.isEmpty()) {
                int cur = stack.pop();
                visited[cur] = true;
                for (Integer n : adj[cur]) {
                    if (!visited[n]) {
                        stack.push(n);
                        visited[n] = true;
                    }
                }
            }
        }
        return components;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

