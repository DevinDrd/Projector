package Game.Model;

import Game.Math.*;

public class SquareModel extends Model {

    // 4 vertices and 4 colors, specify counterclockwise
    public SquareModel(Vector p, float[] vertices, float[] colors) {
        if (vertices.length != 12 || colors.length != 12) throw new IllegalArgumentException();

        position = p;
		
		tris = new Triangle[2];
        cols = new Vector[2][3];
        
        tris[0] = new Triangle(
                new Vector(vertices[0], vertices[1], vertices[2]),
                new Vector(vertices[3], vertices[4], vertices[5]),
                new Vector(vertices[6], vertices[7], vertices[8]) );

        tris[1] = new Triangle(
                new Vector(vertices[6], vertices[7], vertices[8]),
                new Vector(vertices[9], vertices[10], vertices[11]),
                new Vector(vertices[0], vertices[1], vertices[2]) );


        cols[0][0] = new Vector(colors[0], colors[1], colors[2]);
        cols[0][1] = new Vector(colors[3], colors[4], colors[5]);
        cols[0][2] = new Vector(colors[6], colors[7], colors[8]);
        cols[1][0] = new Vector(colors[6], colors[7], colors[8]);
        cols[1][1] = new Vector(colors[9], colors[10], colors[11]);
        cols[1][2] = new Vector(colors[0], colors[1], colors[2]);
    }

    public SquareModel(Vector position, Vector color, float width, float height) {
        if (position.getLength() != 3) throw new IllegalArgumentException();
        if (color.getLength() != 3) throw new IllegalArgumentException();
        if (width <= 0) throw new IllegalArgumentException();
        if (height <= 0) throw new IllegalArgumentException();

        this.position = position;
        tris = new Triangle[2];
        cols = new Vector[tris.length][3];

        tris[0] = new Triangle(new Vector(position.get(0) - (width/2), position.get(1) - (height/2), position.get(2)),
                               new Vector(position.get(0) + (width/2), position.get(1) - (height/2), position.get(2)),
                               new Vector(position.get(0) + (width/2), position.get(1) + (height/2), position.get(2))
        );

        tris[1] = new Triangle(new Vector(position.get(0) + (width/2), position.get(1) + (height/2), position.get(2)),
                               new Vector(position.get(0) - (width/2), position.get(1) + (height/2), position.get(2)),
                               new Vector(position.get(0) - (width/2), position.get(1) - (height/2), position.get(2))
        );

        for (int i = 0; i < cols.length; i++)
            for (int j = 0; j < cols[0].length; j++)
                cols[i][j] = color;
    }
    
}
