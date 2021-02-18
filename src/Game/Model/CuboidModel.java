package Game.Model;

import Game.Math.*;

public class CuboidModel extends Model {

    // one face at a time counterclockwise
    public CuboidModel(Tuple pos, Tuple color, float width, float height, float length) {
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

        for (int i = 0; i < cols.length; i++)
            for (int j = 0; j < cols[0].length; j++)
                cols[i][j] = color;
    }
    
}
