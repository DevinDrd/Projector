package Game.Graphics;

import java.util.ArrayList;

public class Texture {

    private TextureMap map;
    private ArrayList<Integer> texCoords;
    private int xIndex;
    private int yIndex;

    public Texture(TextureMap map, ArrayList<Integer> textureCoords, int xI, int yI) {
        this.map = map;
        texCoords = textureCoords;
        xIndex = xI;
        yIndex = yI;
    }

    public TextureMap getMap() {
        return map;
    }

    public ArrayList<Integer> getCoords() {
        return texCoords;
    }

    public int getX() {
        return xIndex;
    }

    public int getY() {
        return yIndex;
    }
    
}
