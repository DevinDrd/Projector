package Game.Model;

import Game.Math.*;

import java.util.ArrayList;
import java.util.Random;

public class ClownBoxModel extends Model {

    // one face at a time counterclockwise
    public ClownBoxModel(Vector pos, float width, float height, float length) {
        build(pos, width, height, length);
    }

    private void build(Vector pos, float width, float height, float length) {
        float x = pos.get(0);
        float y = pos.get(1);
        float z = pos.get(2);

        float w2 = width/2;
        float h2 = height/2;
        float l2 = length/2;

        position = pos;
        vertices = new ArrayList<Vector>();
        colors = new ArrayList<Vector>();

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

        Random rand = new Random();
        Vector color;

        for (int i = 0; i < vertices.size() / 2; i++) {
            color = new Vector(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            colors.add(color);
            colors.add(color);
        }
            
    }
   
}
