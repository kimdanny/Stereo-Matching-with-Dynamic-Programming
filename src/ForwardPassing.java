/**
 * <Reference>
 * I.J.Cox, et.al.(1992), "Stereo Without Disparity Gradient Smoothing: a Bayesian Sensor fusion solution", pp.337-346, 
 * Available at: http://www.bmva.org/bmvc/1992/bmvc-92-035.pdf
 */
public class ForwardPassing {
    double[][] z1;
    double[][] z2;
    int maxLength;
    // N and M is same in the coursework
    int N;
    int M;
    double occlusion;

    public ForwardPassing(double[][] z1, double[][] z2, double occlusion){
        this.occlusion = occlusion;
        this.z1 = z1;
        this.z2 = z2;
        this.maxLength = Math.max(z1.length, z2.length);
        this.N = z1[maxLength -1].length;
        this.M = z2[maxLength -1].length;
    }


    private double small_c(int k, int i , int j){
        double small_c_function;
        small_c_function = (0.5 * (((z1[k][i]+ z2[k][j])/2) - z1[k][i])/16 * (((z1[k][i]+ z2[k][j])/2) - z1[k][i])) +
                (0.5*(((z1[k][i]+ z2[k][j])/2) - z2[k][j])/16 * (((z1[k][i]+ z2[k][j])/2) - z2[k][j]));
        return small_c_function;
    }


    public double[][][] FordwardPass() {

        double[][][] Cost = new double[maxLength][N][M];
        double[][][] Match = new double[maxLength][N][M];
        // every line
        for(int k = 0; k < maxLength; k++)
        {
            // one pair of line forward passing
            for (int i = 0; i < N; i++) {
                Cost[k][i][0] = i * occlusion;
            }

            for (int i = 0; i < M; i++) {
                Cost[k][0][i] = i * occlusion;
            }

            for (int i = 1; i < N; i++) {

                for (int j = 1; j < M; j++) {

                    double minimum = getMinimum(Cost[k][i-1][j-1] + small_c(k, i, j),
                            Cost[k][i][j-1] + occlusion, Cost[k][i-1][j] + occlusion);

                    Cost[k][i][j] = minimum;

                    if (Cost[k][i-1][j-1] + small_c(k, i, j) == minimum) {
                        Match[k][i][j] = 1;
                    }
                    else if (Cost[k][i][j-1] + occlusion == minimum) {
                        Match[k][i][j] = 2;
                    }
                    else if (Cost[k][i-1][j] + occlusion == minimum) {
                        Match[k][i][j] = 3;
                    }

                }
            }
        }

        return Match;
    }

    // getting minimum value of three arguments. Math.min() supports only two arguments
    private double getMinimum(double x, double y, double z) {
        if (x <= y && x <= z){
            return x;
        }
        else if (y <= x && y <= z){
            return y;
        }
        else {
            return z;
        }
    }

}

