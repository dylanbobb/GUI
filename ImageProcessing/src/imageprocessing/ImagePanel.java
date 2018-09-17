package imageprocessing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{    
    private BufferedImage image;

    public ImagePanel() {
        setImage("images/noImage.png");
    }
    
    public void setImage(String filename)
    {
        try {                
            image = ImageIO.read(new File(filename));
            repaint();
        } catch (IOException ex) {
            //ImageGUI.displayError("Couldn't load image: " + filename);
        }
    }    

    public void setImage(BufferedImage img)
    {
        image = img;
        repaint();
    }

    public BufferedImage getBufferedImage()
    {
        return image;
    }
        
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (image != null)
        {
            int xOffset = 0;
            int yOffset = 0;

            int w = getWidth();
            int h = getHeight();

            float frameAspect = getWidth() / (float) getHeight();
            float imageAspect = image.getWidth() / (float) image.getHeight();

            if (imageAspect < frameAspect)
            {
                float mult = getHeight() / (float)image.getHeight();
                w = Math.round(mult * image.getWidth());

                xOffset = Math.round((getWidth() - w) * 0.5f);
            }
            else
            {
                float mult = getWidth() / (float)image.getWidth();
                h = Math.round(mult * image.getHeight());

                yOffset = Math.round((getHeight() - h) * 0.5f);
            }



            g.drawImage(image, xOffset, yOffset, w, h, null);
        
        }
    }

}
