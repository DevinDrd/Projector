package Game.Graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Image {

    private BufferedImage image;

    private int width;
    private int height;

    private byte[] data;

    public Image(String source) throws IOException{
        image = ImageIO.read(new File(source));

        width = image.getWidth();
        height = image.getHeight();

        int[] colors = image.getRGB(0, 0, width, height, null, 0, width);
        data = expandRGB(colors);
    }

    private byte[] expandRGB(int[] colors) {
        byte[] data = new byte[colors.length*4];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int k = (height-1 - r )*width + c;

                int rgb = colors[k];
    
                byte red = (byte) ((rgb >> 16) & 0xFF);
                byte green = (byte) ((rgb >> 8) & 0xFF);
                byte blue = (byte) (rgb & 0xFF);
                byte alpha = (byte) ((rgb >> 24) & 0xFF);
    
                data[r*width + c*4 + 0] = red;
                data[r*width + c*4 + 1] = green;
                data[r*width + c*4 + 2] = blue;
                data[r*width + c*4 + 3] = alpha;
            }
        }

        return data;
    }

    public byte[] getData() {
        return data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}