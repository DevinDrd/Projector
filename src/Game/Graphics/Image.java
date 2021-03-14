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

                byte[] color = intToByte(colors[k]);
    
                data[r*width + c*4 + 0] = color[0];
                data[r*width + c*4 + 1] = color[1];
                data[r*width + c*4 + 2] = color[2];
                data[r*width + c*4 + 3] = color[3];
            }
        }

        return data;
    }

    private byte[] intToByte(int rgb) {
        byte[] color = new byte[4];

        color[0] = (byte) ((rgb >> 16) & 0xFF); // red
        color[1] = (byte) ((rgb >> 8) & 0xFF); // green
        color[2] = (byte) (rgb & 0xFF); // blue
        color[3] = (byte) 255; // alpha

        return color;
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