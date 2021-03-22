package Game.Graphics;

import Game.Math.Vector;

import java.util.ArrayList;

public class Texture {

    private ArrayList<Vector> texCoords;
    private int xIndex;
    private int yIndex;

    public Texture(ArrayList<Vector> textureCoords, int xI, int yI) {
        texCoords = textureCoords;
        xIndex = xI;
        yIndex = yI;
    }

    public ArrayList<Vector> getCoords() {
        return texCoords;
    }

    public int getX() {
        return xIndex;
    }

    public int getY() {
        return yIndex;
    }
    
}
