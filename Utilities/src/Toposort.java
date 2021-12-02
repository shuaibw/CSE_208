import java.util.ArrayList;
import java.util.Scanner;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) dfs(adj, used, order, i);
//        Stack<Integer> stack = new Stack<>();
//        Stack<Integer> post = new Stack<>();
//        for (int i = 0; i < adj.length; i++) {
//            if(used[i]==0) stack.push(i);
//            while(!stack.isEmpty()){
//                int v=stack.peek();
//                for (Integer n : adj[v]) {
//                    if(used[n]==0) stack.p
//                }
//            }
//        }
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
        if (used[s] == 2) return;
        used[s] = 1;
        for (Integer n : adj[s]) {
            if (used[n] == 0) dfs(adj, used, order, n);
        }
        used[s] = 2;
        order.add(s);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        }
        ArrayList<Integer> order = toposort(adj);
        for (int i = order.size() - 1; i >= 0; i--) System.out.printf("%d ", order.get(i) + 1);

    }
}

