import java.io.IOException;

public class Main {

    public static void main(String[] args)
    {
        double occlusion = 10;

        GrayScaler grayScaler = new GrayScaler();

        // get z1
        double[][] z1 = new double[0][];
        try {
            z1 = grayScaler.imageToMatrix("/Users/toeun_kim/IdeaProjects/" +
                    "algoQ2_stereo/view1_paint.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get z2
        double[][] z2 = new double[0][];
        try {
            z2 = grayScaler.imageToMatrix("/Users/toeun_kim/IdeaProjects/" +
                    "algoQ2_stereo/view2_paint.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // forward passing
        ForwardPassing forwardPassing = new ForwardPassing(z1,z2,occlusion);
        double[][][] forwardPassedMatched = forwardPassing.FordwardPass();

        // backward passing1
        BackwardPassing backwardPassing1 = new BackwardPassing(z1,z2);
        double[][] backwardPassed1 = backwardPassing1.BackwardPassRight(forwardPassedMatched);

        // backward passing2
        BackwardPassing backwardPassing2 = new BackwardPassing(z1,z2);
        double[][] backwardPassed2 = backwardPassing2.BackwardPassLeft(forwardPassedMatched);


        // final processing

        // store as a image file1
        MatrixToImage matrixToImage1 = new MatrixToImage();
        try {
            matrixToImage1.Matrix2ImageConverter(backwardPassed1, "/Users/toeun_kim/" +
                    "Comp05_algorithm/cw2/Question2/Disparity_image/DMap_paint1_oc10.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // store as a image file2
        MatrixToImage matrixToImage2 = new MatrixToImage();
        try {
            matrixToImage2.Matrix2ImageConverter(backwardPassed2, "/Users/toeun_kim/" +
                    "Comp05_algorithm/cw2/Question2/Disparity_image/DMap_paint2_oc10.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}






















