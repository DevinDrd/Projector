package Game.Graphics;

import java.io.IOException;

public class TextureMap {

    // A collage of textures all or which 
    // have the same dimensions

    private final int width;
    private final int height;

    private final int tWidth;
    private final int tHeight;

    // arraylist of texture colors
    private final byte[] data;

    private int id;
    private int slot;

    private static int COUNT = 0;

    // width and height are the dimensions of the textures
    // in the texturmap, not the dimensions of the texturemap
    // itself
    public TextureMap(String source, int tWidth, int tHeight) throws IOException {
        Image image = new Image(source);

        this.width = image.getWidth();
        this.height = image.getHeight();

        this.tWidth = tWidth;
        this.tHeight = tHeight;

        slot = -1;
        id = -1;
        COUNT++;

        System.out.println(COUNT);

        data = image.getData();
    }

    public byte[] getData() {
        return data;
    }

    public int getCols() {
        return width / tWidth;
    }
    
    public int getRows() {
        return height / tHeight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
    
}
