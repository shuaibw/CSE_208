import java.util.ArrayList;
import java.util.LinkedList;

public class EdmondKarp {
    private final int V;
    private final ArrayList<FlowEdge>[] adj;
    private double maxFlow;

    public EdmondKarp(int V) {
        this.V = V;
        adj = (ArrayList<FlowEdge>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
    }

    public void addEdge(FlowEdge e) {
        int u = e.from();
        int v = e.to();
        adj[u].add(e);
        adj[v].add(e);
    }

    private FlowEdge[] getAugPath(int s, int t) {
        boolean[] marked = new boolean[V];
        FlowEdge[] edgeOn = new FlowEdge[V];
        LinkedList<Integer> q = new LinkedList<>();
        q.addFirst(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int u = q.removeFirst();
            for (FlowEdge e : adj[u]) {
                int v = e.otherEndPoint(u);
                if (e.resCapTo(v) > 0 && !marked[v]) {
                    edgeOn[v] = e;
                    marked[v] = true;
                    q.addFirst(v);
                }
            }
        }
        if (!marked[t]) return null;
        return edgeOn;
    }

    public double calculateMaxFlow(int s, int t) {
        maxFlow = 0.0;
        FlowEdge[] edgeOn;
        while ((edgeOn = getAugPath(s, t)) != null) {
            double minInject = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeOn[v].otherEndPoint(v))
                minInject = Math.min(minInject, edgeOn[v].resCapTo(v));
            for (int v = t; v != s; v = edgeOn[v].otherEndPoint(v))
                edgeOn[v].addFlowTo(v, minInject);
            maxFlow += minInject;
        }
        return maxFlow;
    }

    private void dfs(int s, boolean[] visited){
        visited[s]=true;
        for (FlowEdge e : adj[s]) {
            if(e.from()==s){//forward edge: this edge originates from 's'
                if(visited[e.to()] || e.getCapacity()-e.getFlow()<=0) continue;
                dfs(e.to(), visited); //now forward edge has capacity-flow>0
            }else{//back edge: this edge is incident on 's'
                if(visited[e.from()] || e.getFlow()<=0) continue;
                dfs(e.from(), visited);//now back edge has flow>0
            }
        }
    }

    public ArrayList<Integer> getMinCut(int s){
        boolean[] visited=new boolean[V];
        dfs(s, visited);
        ArrayList<Integer> s_t = new ArrayList<>();
        for(int i=0;i<V;i++) if(visited[i]) s_t.add(i);
        return s_t;
    }

    public ArrayList<FlowEdge> neighbors(int v){
        return adj[v];
    }
}
