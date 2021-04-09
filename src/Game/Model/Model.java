package Game.Model;

import Game.Math.*;

import java.util.ArrayList;

public class Model {

    protected Vector position;

    protected ArrayList<Vector> vertices;

    protected Model(){};

    public Model(Vector p, ArrayList<Vector> verts) {
        if (verts.size() % 3 != 0) throw new IllegalArgumentException();

        position = p;

        vertices = verts;
    }

    public void translate(Vector d) {
        position = position.add(d);
        
        for (int i = 0; i < vertices.size(); i++)
            vertices.set(i, vertices.get(i).add(d));
    }

    public void rotate(Vector axis) {
        Matrix rotation = Matrix.rotate(position, axis, axis.magnitude());

        for (int i = 0; i < vertices.size(); i++)
            vertices.set(i, Matrix.rotate(rotation, vertices.get(i)));
    }

    public Vector position() {
        return position;
    }

    public ArrayList<Vector> vertices() {
        return vertices;
    }

    public String toString() {
        String output = "Model:\n";

        output += "Vertices:\n";
        for (Vector v:vertices) output += v + "\n";

        return output.substring(0, output.length() - 1);
    }
    
}
