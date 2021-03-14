package Game.Model;

import Game.Math.*;

import java.util.ArrayList;

public class SquareModel extends Model {

    // 4 vertices and 4 colors, specify counterclockwise
    public SquareModel(Vector p, ArrayList<Vector> verts, ArrayList<Vector> cols) {
        if (verts.size() != 12 || cols.size() != 12) throw new IllegalArgumentException();

        position = p;
		
		vertices = new ArrayList<Vector>();
        colors = new ArrayList<Vector>();

        vertices.add(verts.get(0));
        vertices.add(verts.get(1));
        vertices.add(verts.get(2));
        vertices.add(verts.get(2));
        vertices.add(verts.get(3));
        vertices.add(verts.get(0));

        colors.add(cols.get(0));
        colors.add(cols.get(1));
        colors.add(cols.get(2));
        colors.add(cols.get(2));
        colors.add(cols.get(3));
        colors.add(cols.get(0));
    }

    public SquareModel(Vector position, Vector color, float width, float height) {
        if (position.getLength() != 3) throw new IllegalArgumentException();
        if (color.getLength() != 3) throw new IllegalArgumentException();
        if (width <= 0) throw new IllegalArgumentException();
        if (height <= 0) throw new IllegalArgumentException();

        this.position = position;

        vertices = new ArrayList<Vector>();
        colors = new ArrayList<Vector>();

        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) - (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) - (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) - (height/2), position.get(2)));

        for (int i = 0; i < vertices.size(); i++)
            colors.add(color);
    }

    public SquareModel(Vector position, float width, float height, ArrayList<Vector> texCoords) {
        this.position = position;

        vertices = new ArrayList<Vector>();

        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) - (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) - (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) + (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) + (height/2), position.get(2)));
        vertices.add(new Vector(position.get(0) - (width/2), position.get(1) - (height/2), position.get(2)));

        colors = texCoords;
    }

}
