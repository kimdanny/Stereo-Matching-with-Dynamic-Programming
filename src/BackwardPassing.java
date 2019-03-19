
public class BackwardPassing {
    double[][] z1;
    double[][] z2;
    int maxLength;
    int N;
    int M;

    public BackwardPassing(double[][] z1, double[][] z2) {
        this.maxLength = Math.max(z1.length,z2.length);
        this.N = z1[maxLength -1].length;
        this.M = z2[maxLength -1].length;
        this.z1 = z1;
        this.z2 = z2;
    }

    public double[][] BackwardPassRight(double[][][] Match) {

        double[][] backpassedMatrixright = new double[maxLength][M];

        for(int k = 0; k < maxLength; k++) {

            int i = M - 1;
            int j = i;

            while (i > 0 && j > 0) {
                if (Match[k][i][j] == 1) {
                    backpassedMatrixright[k][j] = (i - j) * 10;
                    i--;
                    j--;
                }
                if (Match[k][i][j] == 2) {

                    j--;
                }
                if (Match[k][i][j] == 3) {
                    backpassedMatrixright[k][j] = 0;
                    i--;
                }
            }
        }
        return backpassedMatrixright;
    }

    public double[][] BackwardPassLeft(double[][][] Match) {

        double[][] backpassedMatrixleft = new double[maxLength][M];

        for(int k = 0; k < maxLength; k++) {

            int i = M - 1;
            int j = i;

            while (i > 0 && j > 0) {
                if (Match[k][i][j] == 1) {
                    backpassedMatrixleft[k][i] = (i - j) * 10;
                    i--;
                    j--;
                }
                if (Match[k][i][j] == 2) {
                    backpassedMatrixleft[k][i] = 0;
                    j--;
                }
                if (Match[k][i][j] == 3) {
                    i--;
                }
            }
        }
        return backpassedMatrixleft;
    }

}
