import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;


public class GrayScaler {

    public double[][] imageToMatrix(String imageFilepath) throws IOException {

        File input = new File(imageFilepath);
        BufferedImage bufferedImage = ImageIO.read(input);
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        double[][] outputMatrix = new double[height][width];

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                Color color = new Color(bufferedImage.getRGB(j,i));
                outputMatrix[i][j] = (0.2989 * color.getRed()) + (0.5870 * color.getGreen())
                        + (0.1140 * color.getBlue());
            }
        }

        return outputMatrix;
    }

}
