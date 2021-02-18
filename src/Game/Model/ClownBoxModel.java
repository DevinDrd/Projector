package Game.Model;

import Game.Math.*;

public class ClownBoxModel extends Model {

    // one face at a time counterclockwise
    public ClownBoxModel(Tuple pos, float width, float height, float length) {
        float x = pos.get(0);
        float y = pos.get(1);
        float z = pos.get(2);

        float w2 = width/2;
        float h2 = height/2;
        float l2 = length/2;

        tris = new Triangle[12];
        cols = new Tuple[tris.length][3];

        tris[0] = new Triangle(new Tuple(x - w2, y + h2, z - l2),
                               new Tuple(x + w2, y + h2, z - l2),
                               new Tuple(x + w2, y + h2, z + l2)
        );
        tris[1] = new Triangle(new Tuple(x + w2, y + h2, z + l2),
                               new Tuple(x - w2, y + h2, z + l2),
                               new Tuple(x - w2, y + h2, z - l2)
        );

        tris[2] = new Triangle(new Tuple(x - w2, y - h2, z - l2),
                               new Tuple(x + w2, y - h2, z - l2),
                               new Tuple(x + w2, y - h2, z + l2)
        );
        tris[3] = new Triangle(new Tuple(x + w2, y - h2, z + l2),
                               new Tuple(x - w2, y - h2, z + l2),
                               new Tuple(x - w2, y - h2, z - l2)
        );

        tris[4] = new Triangle(new Tuple(x - w2, y - h2, z - l2),
                               new Tuple(x - w2, y - h2, z + l2),
                               new Tuple(x - w2, y + h2, z + l2)
        );
        tris[5] = new Triangle(new Tuple(x - w2, y + h2, z + l2),
                               new Tuple(x - w2, y + h2, z - l2),
                               new Tuple(x - w2, y - h2, z - l2)
        );

        tris[6] = new Triangle(new Tuple(x + w2, y - h2, z - l2),
                               new Tuple(x + w2, y - h2, z + l2),
                               new Tuple(x + w2, y + h2, z + l2)
        );
        tris[7] = new Triangle(new Tuple(x + w2, y + h2, z + l2),
                               new Tuple(x + w2, y + h2, z - l2),
                               new Tuple(x + w2, y - h2, z - l2)
        );
        
        tris[8] = new Triangle(new Tuple(x - w2, y - h2, z + l2),
                               new Tuple(x + w2, y - h2, z + l2),
                               new Tuple(x + w2, y + h2, z + l2)
        );
        tris[9] = new Triangle(new Tuple(x + w2, y + h2, z + l2),
                               new Tuple(x - w2, y + h2, z + l2),
                               new Tuple(x - w2, y - h2, z + l2)
        );

        tris[10] = new Triangle(new Tuple(x - w2, y - h2, z - l2),
                                new Tuple(x + w2, y - h2, z - l2),
                                new Tuple(x + w2, y + h2, z - l2)
        );
        tris[11] = new Triangle(new Tuple(x + w2, y + h2, z - l2),
                                new Tuple(x - w2, y + h2, z - l2),
                                new Tuple(x - w2, y - h2, z - l2)
        );

        Tuple[] colors = new Tuple[6];
        colors[0] = new Tuple(1, 0, 0);
        colors[1] = new Tuple(0, 1, 0);
        colors[2] = new Tuple(0, 0, 1);
        colors[3] = new Tuple(1, 1, 0);
        colors[4] = new Tuple(0, 1, 1);
        colors[5] = new Tuple(1, 0, 1);

        for (int i = 0; i < cols[0].length; i++) {
            cols[0][i] = colors[0];
            cols[1][i] = colors[0];
        }

        for (int i = 0; i < cols[1].length; i++) {
            cols[2][i] = colors[1];
            cols[3][i] = colors[1];
        }

        for (int i = 0; i < cols[2].length; i++) {
            cols[4][i] = colors[2];
            cols[5][i] = colors[2];
        }

        for (int i = 0; i < cols[3].length; i++) {
            cols[6][i] = colors[3];
            cols[7][i] = colors[3];
        }

        for (int i = 0; i < cols[4].length; i++) {
            cols[8][i] = colors[4];
            cols[9][i] = colors[4];
        }

        for (int i = 0; i < cols[5].length; i++) {
            cols[10][i] = colors[5];
            cols[11][i] = colors[5];
        }

    }
    
}
