package Game.Entity;

import Game.Graphics.Texture;
import Game.Math.Vector;
import Game.Model.SquareModel;

import java.util.ArrayList;
import java.util.Scanner;

public class Square extends Entity{

    public Square(Scanner source) {
        position = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        velocity = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        rotationAxis = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        float width = source.nextFloat();
        float height = source.nextFloat();

        source.nextLine();

        int textureX = source.nextInt(); // for the texture
        int textureY = source.nextInt(); // for the texture

        ArrayList<Vector> texCoords = new ArrayList<Vector>();

        Vector p1 = new Vector(source.nextFloat(), source.nextFloat());
        Vector p2 = new Vector(source.nextFloat(), source.nextFloat());
        Vector p3 = new Vector(source.nextFloat(), source.nextFloat());
        Vector p4 = new Vector(source.nextFloat(), source.nextFloat());

        texCoords.add(p1);
        texCoords.add(p2);
        texCoords.add(p3);
        texCoords.add(p3);
        texCoords.add(p4);
        texCoords.add(p1);

        model = new SquareModel(position, width, height, texCoords);
    }
    
}
