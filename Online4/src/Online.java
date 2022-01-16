import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Online {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("src/input.txt"));
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int p = scanner.nextInt();
        int s = x + y;
        int t = x + y + 1;
        int[] xReg = new int[x]; //track which girls registered: 1->registered, 0->not registered
        int[] yReg = new int[y]; //track which boys registered
        EdmondKarp flowNet = new EdmondKarp(x + y + 2);
        for (int i = 0; i < p; i++) {
            int xID = scanner.nextInt();
            int yID = scanner.nextInt();
            xReg[xID] = 1;
            yReg[yID] = 1;
            flowNet.addEdge(new FlowEdge(xID, yID + x, m)); //yID+x ensures girl and boy id is not same
        }
        for (int i = 0; i < xReg.length; i++) {
            if (xReg[i] == 0) continue;
            flowNet.addEdge(new FlowEdge(s, i, n));
        }
        for (int i = 0; i < yReg.length; i++) {
            if (yReg[i] == 0) continue;
            flowNet.addEdge(new FlowEdge(i + x, t, n));
        }

        int maxFlow = (int) flowNet.calculateMaxFlow(s, t);
        System.out.println(maxFlow);

        for (int i = 0; i < xReg.length; i++) {
            if (xReg[i] == 0) continue;
            ArrayList<FlowEdge> neighbors = flowNet.neighbors(i);
            for (FlowEdge edge : neighbors) {
                if (edge.from() != i) continue; //incident edge on vertex
                int flow = (int) edge.getFlow();
                while (flow-- != 0) System.out.println(i + "->" + (edge.to() - x));
            }
        }
    }
}
