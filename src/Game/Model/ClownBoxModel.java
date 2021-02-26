package Game.Model;

import Game.Math.*;

import java.util.Random;

public class ClownBoxModel extends Model {

    // one face at a time counterclockwise
    public ClownBoxModel(Tuple pos, float width, float height, float length) {
        build(pos, width, height, length);
    }

    private void build(Tuple pos, float width, float height, float length) {
        float x = pos.get(0);
        float y = pos.get(1);
        float z = pos.get(2);

        float w2 = width/2;
        float h2 = height/2;
        float l2 = length/2;

        position = pos;
        tris = new Triangle[12];
        cols = new Tuple[tris.length][3];

        tris[0] = new Triangle(new Tuple(x - w2, y + h2, z - l2),
                               new Tuple(x + w2, y + h2, z - l2),
                               new Tuple(x + w2, y + h2, z + l2));

        tris[1] = new Triangle(new Tuple(x + w2, y + h2, z + l2),
                               new Tuple(x - w2, y + h2, z + l2),
                               new Tuple(x - w2, y + h2, z - l2));

        tris[2] = new Triangle(new Tuple(x - w2, y - h2, z - l2),
                               new Tuple(x + w2, y - h2, z - l2),
                               new Tuple(x + w2, y - h2, z + l2));

        tris[3] = new Triangle(new Tuple(x + w2, y - h2, z + l2),
                               new Tuple(x - w2, y - h2, z + l2),
                               new Tuple(x - w2, y - h2, z - l2));

        tris[4] = new Triangle(new Tuple(x - w2, y - h2, z - l2),
                               new Tuple(x - w2, y - h2, z + l2),
                               new Tuple(x - w2, y + h2, z + l2));

        tris[5] = new Triangle(new Tuple(x - w2, y + h2, z + l2),
                               new Tuple(x - w2, y + h2, z - l2),
                               new Tuple(x - w2, y - h2, z - l2));

        tris[6] = new Triangle(new Tuple(x + w2, y - h2, z - l2),
                               new Tuple(x + w2, y - h2, z + l2),
                               new Tuple(x + w2, y + h2, z + l2));

        tris[7] = new Triangle(new Tuple(x + w2, y + h2, z + l2),
                               new Tuple(x + w2, y + h2, z - l2),
                               new Tuple(x + w2, y - h2, z - l2));
        
        tris[8] = new Triangle(new Tuple(x - w2, y - h2, z + l2),
                               new Tuple(x + w2, y - h2, z + l2),
                               new Tuple(x + w2, y + h2, z + l2));

        tris[9] = new Triangle(new Tuple(x + w2, y + h2, z + l2),
                               new Tuple(x - w2, y + h2, z + l2),
                               new Tuple(x - w2, y - h2, z + l2));

        tris[10] = new Triangle(new Tuple(x - w2, y - h2, z - l2),
                                new Tuple(x + w2, y - h2, z - l2),
                                new Tuple(x + w2, y + h2, z - l2));

        tris[11] = new Triangle(new Tuple(x + w2, y + h2, z - l2),
                                new Tuple(x - w2, y + h2, z - l2),
                                new Tuple(x - w2, y - h2, z - l2));

        Random rand = new Random();
        Tuple color;

        color = new Tuple(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        for (int i = 0; i < cols[0].length; i++) {
            cols[0][i] = color;
            cols[1][i] = color;
        }

        color = new Tuple(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        for (int i = 0; i < cols[1].length; i++) {
            cols[2][i] = color;
            cols[3][i] = color;
        }

        color = new Tuple(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        for (int i = 0; i < cols[2].length; i++) {
            cols[4][i] = color;
            cols[5][i] = color;
        }

        color = new Tuple(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        for (int i = 0; i < cols[3].length; i++) {
            cols[6][i] = color;
            cols[7][i] = color;
        }

        color = new Tuple(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        for (int i = 0; i < cols[4].length; i++) {
            cols[8][i] = color;
            cols[9][i] = color;
        }

        color = new Tuple(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        for (int i = 0; i < cols[5].length; i++) {
            cols[10][i] = color;
            cols[11][i] = color;
        }
    }
   
}
