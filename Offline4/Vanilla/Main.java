import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner=new Scanner(new FileInputStream("src/edmond.txt"));
        int V=scanner.nextInt();
        int E=scanner.nextInt();
        EdmondKarp flowNet = new EdmondKarp(V);
        for(int i=0;i<E;i++){
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            double cap = scanner.nextDouble();
            flowNet.addEdge(new FlowEdge(u,v,cap));
        }
        double maxFlow = flowNet.calculateMaxFlow(0,6);
        System.out.println(maxFlow);
        for (Integer u : flowNet.getMinCut(0)) {
            System.out.println(u);
        }
    }
}
