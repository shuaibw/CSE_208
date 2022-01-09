public class FloydWarshall {
    public FloydWarshall() {

    }

    private static class DistMatrix {
        public double[][] ara;

        public DistMatrix(int sz) {
            ara = new double[sz][sz];
        }
    }

    public double[][] solveForAPSP(WeightedDigraphMatrix g) {
        DistMatrix[] floydAra = new DistMatrix[g.V() + 1];
        for (int i = 0; i < floydAra.length; i++) floydAra[i] = new DistMatrix(g.V());
        //initialize 3d floyd distance matrix
        for (int i = 0; i < g.V(); i++) {
            for (int j = 0; j < g.V(); j++) {
                floydAra[0].ara[i][j] = g.adjMat()[i][j];
            }
        }
        //update floyd distance matrix for utilizing vertex k
        for (int k = 1; k < floydAra.length; k++) {
            double[][] prev = floydAra[k - 1].ara;
            for (int i = 0; i < g.V(); i++) {
                for (int j = 0; j < g.V(); j++) {
                    double min = Double.min(prev[i][j], prev[i][k - 1] + prev[k - 1][j]);
                    floydAra[k].ara[i][j] = min;
                }
            }
        }
        //check values across diagonal for negative cycle
        double[][] finalMat = floydAra[floydAra.length - 1].ara;
        for(int i=0;i<g.V();i++){
            if(finalMat[i][i]<0) return null;
        }
        return floydAra[floydAra.length - 1].ara;
    }
}
