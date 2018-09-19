package imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) 
    {
        try
        {
            BufferedImage input = ImageIO.read(new File("images/Lenna.png"));
            BufferedImage output = ImageProcessing.applySharpen(input);
            ImageIO.write(output, "png", new File("images/Lenna_Blurred.png"));
        }
        catch(Exception e)
        {
        }
    }
}
