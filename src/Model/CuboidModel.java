package Model;

import Math.*;

public class CuboidModel extends Model {

    // one face at a time counterclockwise
    public CuboidModel(float[] vertices, float[] colors) {
        if (vertices.length != 24 || colors.length != 24) throw new IllegalArgumentException();
		
		tris = new Triangle[12];
        cols = new Tuple[tris.length][3];
        
        for (int i = 0; i < tris.length; i++) {
            tris[i] = new Triangle(
                    new Tuple(vertices[0 + i*9], vertices[1 + i*9], vertices[2 + i*9]),
                    new Tuple(vertices[3 + i*9], vertices[4 + i*9], vertices[5 + i*9]),
                    new Tuple(vertices[6 + i*9], vertices[7 + i*9], vertices[8 + i*9]) );

            cols[i][0] = new Tuple(colors[0 + i*9], colors[1 + i*9], colors[2 + i*9]);
            cols[i][1] = new Tuple(colors[3 + i*9], colors[4 + i*9], colors[5 + i*9]);
            cols[i][2] = new Tuple(colors[6 + i*9], colors[7 + i*9], colors[8 + i*9]);
        }
    }
    
}
