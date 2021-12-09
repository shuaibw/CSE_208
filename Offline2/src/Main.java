import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner=new Scanner(new FileInputStream("src/graph.txt"));
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();
        WeightedDigraph g=new WeightedDigraph(vertices);
        for(int i=0;i<edges;i++){
            int u = scanner.nextInt() - 1;
            int v =scanner.nextInt() - 1;
            double w =scanner.nextDouble();
            g.addEdge(new Edge(u,v,w,i));
        }
        int start = scanner.nextInt() - 1;
        int end = scanner.nextInt() - 1;

        SSSPReturns ans = new Dijkstra().solveForSSSP(g, start, end);
        System.out.println(ans.shortestPathDist());
        ans.printShortestPath();
    }
}