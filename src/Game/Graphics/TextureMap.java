package Game.Graphics;

import Game.Math.Vector;

import java.util.ArrayList;
import java.io.IOException;

public class TextureMap {

    // A collage of textures all of which 
    // *should* have the same dimensions

    private final int width; // width of entire texturemap
    private final int height; // height of entire texturemap

    private final int tWidth; // texture width
    private final int tHeight; // texture height

    private final float xRatio;
    private final float yRatio;

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

        xRatio = ((float) tWidth)/width;
        yRatio = ((float) tHeight)/height;

        slot = 0;
        id = COUNT;
        COUNT++;

        data = image.getData();
    }

    public byte[] getData() {
        return data;
    }

    public ArrayList<Vector> convert(Texture texture) {
        ArrayList<Vector> coords = new ArrayList<Vector>();
        for (Vector v:texture.getCoords())
            coords.add(v);

        float x = texture.getX();
        float y = texture.getY();

        for (int i = 0; i < coords.size(); i++) {

            float xCoord = coords.get(i).get(0)*xRatio + x*xRatio;
            float yCoord = coords.get(i).get(1)*yRatio + y*yRatio;

            coords.set(i, new Vector(xCoord, yCoord));
        }

        return coords;
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
