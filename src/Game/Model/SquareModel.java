package Game.Model;

import Game.Math.*;

import java.util.ArrayList;

public class SquareModel extends Model {

    // 4 vertices and 4 colors, specify counterclockwise
    public SquareModel(Vector p, ArrayList<Vector> verts) {
        if (verts.size() != 12) throw new IllegalArgumentException();

        position = p;
		
		vertices = new ArrayList<Vector>();

        vertices.add(verts.get(0));
        vertices.add(verts.get(1));
        vertices.add(verts.get(2));
        vertices.add(verts.get(2));
        vertices.add(verts.get(3));
        vertices.add(verts.get(0));
    }

    public SquareModel(Vector position, float width, float height) {
        if (position.length() != 3) throw new IllegalArgumentException();
        if (width <= 0) throw new IllegalArgumentException();
        if (height <= 0) throw new IllegalArgumentException();

        this.position = position;

        vertices = new ArrayList<Vector>();

        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) - (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) - (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) - (height/2), position.get(2)));
    }

}
