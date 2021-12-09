public class Main {
    public static void main(String[] args) {
        MSTReturn kruskal = new Kruskal().solve(0);
        MSTReturn prims = new Prim().solve(0);
        assert kruskal.mstCost == prims.mstCost;
        System.out.printf("Cost of the minimum spanning tree: %.1f\n", kruskal.mstCost);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Edge e : kruskal.mstEdges) sb.append(e).append(",");
        sb.setLength(sb.length() - 1);
        sb.append("}");
        System.out.println("List of edges selected by Kruskal's: " + sb);
        sb.setLength(1);
        for (Edge e : prims.mstEdges) sb.append(e).append(",");
        sb.setLength(sb.length() - 1);
        sb.append("}");
        System.out.println("List of edges selected by Prim's: " + sb);
    }
}
