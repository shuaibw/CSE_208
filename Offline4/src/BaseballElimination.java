import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BaseballElimination {
    private int numberTeams;
    private String[] teams;
    private int[][] g;
    private int[] w;
    private int[] l;
    private int[] r;
    private static final double INF = Double.POSITIVE_INFINITY;
    private int[] trivElimList;

    public BaseballElimination(String file) {
        processInput(file);
        checkTrivialElimination(); //fills trivElimList for appropriate teams
        for (int i = 0; i < numberTeams; i++) {
            if (trivElimList[i] == -1) modelFlowNetForTeam(i); //if not trivially eliminated
            else printDefeat(i, new int[]{trivElimList[i]});
        }
    }

    private void processInput(String file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            numberTeams = Integer.parseInt(br.readLine());
            teams = new String[numberTeams];
            g = new int[numberTeams][numberTeams];
            w = new int[numberTeams];
            l = new int[numberTeams];
            r = new int[numberTeams];
            trivElimList = new int[numberTeams];
            for (int i = 0; i < numberTeams; i++) trivElimList[i] = -1;

            for (int i = 0; i < numberTeams; i++) {
                String[] pieces = br.readLine().strip().split(" +");
                teams[i] = pieces[0];
                w[i] = Integer.parseInt(pieces[1]);
                l[i] = Integer.parseInt(pieces[2]);
                r[i] = Integer.parseInt(pieces[3]);
                for (int j = 0; j < numberTeams; j++) g[i][j] = Integer.parseInt(pieces[4 + j]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkTrivialElimination() {
        int maxWinID = -1;
        int maxWins = -1;
        for (int i = 0; i < numberTeams; i++) {
            if (w[i] <= maxWins) continue;
            maxWinID = i;
            maxWins = w[i];
        }
        for (int i = 0; i < numberTeams; i++) {
            int maxPossibleWins = w[i] + r[i];
            if (i == maxWinID || maxPossibleWins >= maxWins) continue;
            trivElimList[i] = maxWinID;
        }
    }

    private void modelFlowNetForTeam(int teamID) {
        int pairCount = 0; //id counter for paired team vertices
        int numPairs = ((numberTeams - 1) * (numberTeams - 2)) / 2;
        int teamCount = 1 + numPairs; //id counter for single team vertices
        int saturation = 0;
        Vertex[] singleTeams = new Vertex[numberTeams];
        Vertex s = new Vertex(0, -1, -1, pairCount++);
        Vertex t = new Vertex(0, -1, -1, numberOfVertices() - 1);
        EdmondKarp flowNet = new EdmondKarp(numberOfVertices());
        for (int i = 0; i < singleTeams.length; i++) {
            if (i == teamID) continue;
            singleTeams[i] = new Vertex(1, i, -1, teamCount++); //mark each team vertex
            flowNet.addEdge(new FlowEdge(singleTeams[i], t, w[teamID] + r[teamID] - w[i])); //add edge to sink
        }

        for (int i = 0; i < numberTeams; i++) {
            if (i == teamID) continue;
            for (int j = i + 1; j < numberTeams; j++) {
                if (j == teamID) continue;
                saturation += g[i][j];
                Vertex pair = new Vertex(2, i, j, pairCount++); //mark each pair vertex
                flowNet.addEdge(new FlowEdge(s, pair, g[i][j])); //add source->pair edge
                flowNet.addEdge(new FlowEdge(pair, singleTeams[pair.x], INF));//add pair->team edge
                flowNet.addEdge(new FlowEdge(pair, singleTeams[pair.y], INF));//add pair->team edge
            }
        }
        int maxFlow = (int) flowNet.calculateMaxFlow(s.id, t.id);
        if (maxFlow == saturation) return;
        processMinCut(flowNet.getMinCut(0), 1 + numPairs, singleTeams, teamID);
    }

    private void processMinCut(ArrayList<Integer> cut, int teamStartID, Vertex[] singleTeams, int teamID) {
        int idx = 0;
        ArrayList<Integer> opponents = new ArrayList<>();
        for (Integer id : cut) {
            if (id < teamStartID) continue;
            while (singleTeams[idx].id != id) {
                idx++;
                if (singleTeams[idx] == null) idx++;
            }
            opponents.add(idx);
        }

        printDefeat(teamID, opponents.stream().mapToInt(i -> i).toArray());
    }

    private void printDefeat(int teamID, int[] opponents) {
        int totalWins = 0;
        int totalPlays = 0;
        for (int i = 0; i < opponents.length; i++) {
            totalWins += w[opponents[i]];
            for (int j = i + 1; j < opponents.length; j++) {
                totalPlays += g[opponents[i]][opponents[j]];
            }
        }
        StringBuilder sb = new StringBuilder();
        if (opponents.length == 1) sb.append(teams[opponents[0]]);
        else {
            for (int i = 0; i < opponents.length - 1; i++) sb.append(teams[opponents[i]]).append(", ");
            sb.setLength(sb.length() - 2);
            sb.append(" and ").append(teams[opponents[opponents.length - 1]]);
        }
        System.out.println(teams[teamID] + " is eliminated\n" +
                String.format("They can win at most %d + %d = %d games.", w[teamID], r[teamID], w[teamID] + r[teamID]));
        int maxPossibleWins = totalWins + totalPlays;
        System.out.println(sb + " have won a total of " + totalWins + " games.\n" +
                "They play each other " + totalPlays + " times.\n" +
                String.format("So on average, each of the teams wins %d/%d = %.2f games\n\n", maxPossibleWins, opponents.length, (double) maxPossibleWins / opponents.length));
    }

    private int numberOfVertices() {
        return 1 + ((numberTeams - 1) * (numberTeams - 2)) / 2 + numberTeams; //(n-1)C2 choices of teams
    }
}
