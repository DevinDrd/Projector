package Game.Graphics;

import Game.Util.BufferUtil;

import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.geom.*;
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

        int[] colors = new int[4*width*height];
        image.getRGB(0, 0, width, height, colors, 0, width);

        data = BufferUtil.intToByte(colors);
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
