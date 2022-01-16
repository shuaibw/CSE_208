import java.io.IOException;
import java.util.*;

public class Bipartite {
    private static ArrayList<Integer>[] bipartite(ArrayList<Integer>[] adj) {
        Queue<Integer> queue = new LinkedList<>();
        int[] color = new int[adj.length];//0-->unv
        int p = 1;
        for (int i = 0; i < adj.length; i++) {
            if (color[i] == 0) {
                queue.add(i);
                color[i] = p;
            }
            while (!queue.isEmpty()) {
                int v = queue.remove();
                if (color[v] == 1) {
                    p = 2;
                } else p = 1;
                for (Integer n : adj[v]) {
                    if (color[n] == 0) {
                        queue.add(n);
                        color[n] = p;
                    } else if (color[v] == color[n]) return null;
                }
            }
        }
        ArrayList<Integer>[] set = (ArrayList<Integer>[]) new ArrayList[2];
        for(int i=0;i<set.length;i++)set[i]=new ArrayList<>();

        for(int i=0;i<adj.length;i++){
            set[color[i]-1].add(i);
        }
        return set;
    }
    public static void main(String[] args) throws IOException {
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
            adj[x].add(y);
            adj[y].add(x);
        }
        for (ArrayList<Integer> integers : bipartite(adj)) {
            System.out.println(Arrays.toString(integers.toArray()));
        }
    }
}

