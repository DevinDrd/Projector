package Model;

import Math.*;
import Util.*;

public class SquareModel extends Model {

    // 4 vertices and 4 colors
    public SquareModel(float[] vertices, float[] colors) {
        if (vertices.length != 12 || colors.length != 12) throw new IllegalArgumentException();
		
		tris = new Triangle[2];
        cols = new Tuple[2][3];
        
        tris[0] = new Triangle(
                new Tuple(vertices[0], vertices[1], vertices[2]),
                new Tuple(vertices[3], vertices[4], vertices[5]),
                new Tuple(vertices[6], vertices[7], vertices[8]) );

        tris[1] = new Triangle(
                new Tuple(vertices[6], vertices[7], vertices[8]),
                new Tuple(vertices[9], vertices[10], vertices[11]),
                new Tuple(vertices[0], vertices[1], vertices[2]) );


        cols[0][0] = new Tuple(colors[0], colors[1], colors[2]);
        cols[0][1] = new Tuple(colors[3], colors[4], colors[5]);
        cols[0][2] = new Tuple(colors[6], colors[7], colors[8]);
        cols[1][0] = new Tuple(colors[6], colors[7], colors[8]);
        cols[1][1] = new Tuple(colors[9], colors[10], colors[11]);
        cols[1][2] = new Tuple(colors[0], colors[1], colors[2]);
    }
    
}
