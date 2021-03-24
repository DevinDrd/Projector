package Game.Model;

import Game.Math.*;

import java.util.ArrayList;

public class CuboidModel extends Model {

    // one face at a time counterclockwise
    public CuboidModel(Vector pos, float width, float height, float length) {
        float x = pos.get(0);
        float y = pos.get(1);
        float z = pos.get(2);

        float w2 = width/2;
        float h2 = height/2;
        float l2 = length/2;

        position = pos;
        vertices = new ArrayList<Vector>();

        vertices.add(new Vector(x - w2, y + h2, z - l2));
        vertices.add(new Vector(x + w2, y + h2, z - l2));
        vertices.add(new Vector(x + w2, y + h2, z + l2));
        vertices.add(new Vector(x + w2, y + h2, z + l2));
        vertices.add(new Vector(x - w2, y + h2, z + l2));
        vertices.add(new Vector(x - w2, y + h2, z - l2));

        vertices.add(new Vector(x - w2, y - h2, z - l2));
        vertices.add(new Vector(x + w2, y - h2, z - l2));
        vertices.add(new Vector(x + w2, y - h2, z + l2));
        vertices.add(new Vector(x + w2, y - h2, z + l2));
        vertices.add(new Vector(x - w2, y - h2, z + l2));
        vertices.add(new Vector(x - w2, y - h2, z - l2));

        vertices.add(new Vector(x - w2, y - h2, z - l2));
        vertices.add(new Vector(x - w2, y - h2, z + l2));
        vertices.add(new Vector(x - w2, y + h2, z + l2));
        vertices.add(new Vector(x - w2, y + h2, z + l2));
        vertices.add(new Vector(x - w2, y + h2, z - l2));
        vertices.add(new Vector(x - w2, y - h2, z - l2));

        vertices.add(new Vector(x + w2, y - h2, z - l2));
        vertices.add(new Vector(x + w2, y - h2, z + l2));
        vertices.add(new Vector(x + w2, y + h2, z + l2));
        vertices.add(new Vector(x + w2, y + h2, z + l2));
        vertices.add(new Vector(x + w2, y + h2, z - l2));
        vertices.add(new Vector(x + w2, y - h2, z - l2));

        vertices.add(new Vector(x - w2, y - h2, z + l2));
        vertices.add(new Vector(x + w2, y - h2, z + l2));
        vertices.add(new Vector(x + w2, y + h2, z + l2));
        vertices.add(new Vector(x + w2, y + h2, z + l2));
        vertices.add(new Vector(x - w2, y + h2, z + l2));
        vertices.add(new Vector(x - w2, y - h2, z + l2));

        vertices.add(new Vector(x - w2, y - h2, z - l2));
        vertices.add(new Vector(x + w2, y - h2, z - l2));
        vertices.add(new Vector(x + w2, y + h2, z - l2));
        vertices.add(new Vector(x + w2, y + h2, z - l2));
        vertices.add(new Vector(x - w2, y + h2, z - l2));
        vertices.add(new Vector(x - w2, y - h2, z - l2));
    }
    
}
