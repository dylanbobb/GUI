import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageProcessor {
    
    public static BufferedImage convertToGreyScale(final BufferedImage img)
    {
        BufferedImage output = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        for (int j=0; j<img.getHeight(); ++j){
            for (int i=0; i<img.getWidth(); ++i){
                int argb = img.getRGB(i, j);
                
                int alpha = (argb & 0xFF000000) >> 24;
                int red   = (argb & 0x00FF0000) >> 16;
                int green = (argb & 0x0000FF00) >> 8;
                int blue  = (argb & 0x000000FF);

                int grey = (red + green + blue) / 3;
                
                int greyARGB = alpha << 24 | grey << 16 | grey << 8 | grey;
                
                output.setRGB(i, j, greyARGB);
            }
        }
        return output;
    }
    
    public static BufferedImage applyGaussianBlur(BufferedImage img)
    {
        final float gaussianKernel[][] = new float[][]
        {
            {0.00000067f, 0.00002292f, 0.00019117f, 0.00038771f, 0.00019117f, 0.00002292f, 0.00000067f},
            {0.00002292f, 0.00078634f, 0.00655965f, 0.01330373f, 0.00655965f, 0.00078633f, 0.00002292f},
            {0.00019117f, 0.00655965f, 0.05472157f, 0.11098164f, 0.05472157f, 0.00655965f, 0.00019117f},
            {0.00038771f, 0.01330373f, 0.11098164f, 0.22508352f, 0.11098164f, 0.01330373f, 0.00038771f},
            {0.00019117f, 0.00655965f, 0.05472157f, 0.11098164f, 0.05472157f, 0.00655965f, 0.00019117f},
            {0.00002292f, 0.00078634f, 0.00655965f, 0.01330373f, 0.00655965f, 0.00078633f, 0.00002292f},
            {0.00000067f, 0.00002292f, 0.00019117f, 0.00038771f, 0.00019117f, 0.00002292f, 0.00000067f}
        };
        return applyConvolutionFilter(img, gaussianKernel);
    }
    
    public static BufferedImage applyBoxBlur(BufferedImage img) {
        final float[][] boxKernel = new float[3][3];
        // Filling the 3x3 matrix
        float value = 1.0f/(3*3);
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                boxKernel[i][j] = value;
            }
        }
        return applyConvolutionFilter(img, boxKernel);
    }
    
    public static BufferedImage applyEdgeFilter(BufferedImage img) {
        // Creating a fillint a 3x3 matrix that represents an edge filter
        final float[][] edgeKernel = new float[3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (i==1 && j==1) {edgeKernel[i][j] = 8;}
                else {edgeKernel[i][j] = -1;}
            }
        }
        return applyConvolutionFilter(img, edgeKernel);
    }
    
    public static BufferedImage applySharpenFilter(BufferedImage img) {
        final float[][] sharpenKernel = {{0,-1,0},{-1,5,-1},{0,-1,0}};
        return applyConvolutionFilter(img, sharpenKernel);
    }
    
    public static BufferedImage applyGammaCorrection(BufferedImage img, float value) {
        // We need to divide the slider value by 100 because it goes from 1 to 200 when we really want it to go from 0.01 to 2
        value = value/100;
        BufferedImage output = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        for (int j = 0; j < img.getHeight(); ++j) {
                for (int i = 0; i < img.getWidth(); ++i) {
                    // Read color from input pixel
                    Color color = new Color(img.getRGB(i, j));
                    float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
                    float hue = hsb[0];
                    float saturation = hsb[1];
                    float brightness = hsb[2];
                    
                    brightness = (float)Math.pow(brightness, value);
      
                    output.setRGB(i, j, Color.HSBtoRGB(hue, saturation, brightness));
                }
            }
        return output;
    }
    
    private static BufferedImage applyConvolutionFilter(BufferedImage img, float[][] kernel)
    {
        BufferedImage output = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        // Set output image from input image (img)
        for (int j=0; j<img.getHeight(); ++j)
        {
            for (int i=0; i<img.getWidth(); ++i)
            {
                Color pixelColor = applyKernel(img, kernel, i, j);
                output.setRGB(i, j, pixelColor.getRGB());                
            }
        }
        return output;
    }
    
    private static Color applyKernel(BufferedImage img, float[][] kernel, int row, int column)
    {
        float red = 0.0f;
        float green = 0.0f;
        float blue = 0.0f;

        int minIndexH = -(kernel.length / 2);
        int maxIndexH = minIndexH + kernel.length;
        int minIndexV = -(kernel[0].length / 2);
        int maxIndexV = minIndexV + kernel[0].length;
        
        for (int i = minIndexH; i < maxIndexH; ++i)
        {
            for (int j=minIndexV; j<maxIndexV; ++j)
            {
                int indexH = wrapHorizontalIndex(img, row + i);
                int indexV = wrapVerticalIndex(img, column + j);
                Color col = new Color(img.getRGB(indexH, indexV));

                red += col.getRed() * kernel[i-minIndexH][j-minIndexV];
                green += col.getGreen() * kernel[i-minIndexH][j-minIndexV];
                blue += col.getBlue() * kernel[i-minIndexH][j-minIndexV];
            }
        }

        red = Math.min(Math.max(red, 0.0f), 255.0f);
        green = Math.min(Math.max(green, 0.0f), 255.0f);
        blue = Math.min(Math.max(blue, 0.0f), 255.0f);
        
        return new Color((int) red, (int) green, (int) blue);
    }
    
    private static int wrapHorizontalIndex(BufferedImage img, int i)
    {
        // This takes care of negative and positive indices beyond the image resolution
        return (i + img.getWidth()) % img.getWidth();
    }

    private static int wrapVerticalIndex(BufferedImage img, int i)
    {
        // This takes care of negative and positive indices beyond the image resolution
        return (i + img.getHeight()) % img.getHeight();
    }
    
}
