import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MatrixToImage {

    public void Matrix2ImageConverter (double[][] Matrix, String saveToThis_filepath) throws IOException {

        BufferedImage bufferedImage = new BufferedImage(Matrix[0].length, Matrix.length, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wr = bufferedImage.getRaster();

        for (int j = 0; j < Matrix.length; j++){

            for (int i = 0; i < Matrix[j].length; i++){
                wr.setPixel(i, j, new double[]{Matrix[j][i]});
            }

        }

        ImageIO.write(bufferedImage, "png", new File(saveToThis_filepath));

    }

}
