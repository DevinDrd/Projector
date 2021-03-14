package Game.Model;

import Game.Math.*;

import java.util.ArrayList;

public class Model {

    protected Vector position;

    protected ArrayList<Vector> vertices;
    protected ArrayList<Vector> colors;

    protected Model(){};

    public Model(Vector p, ArrayList<Vector> verts, ArrayList<Vector> cols) {
        if (verts.size() % 3 != 0) throw new IllegalArgumentException();
        if (verts.size() != cols.size()) throw new IllegalArgumentException();

        position = p;

        vertices = verts;
        colors = cols;
    }

    public void rotate(Vector axis, float alpha) {
        Matrix rotation = Matrix.rotate(position, axis, alpha);

        for (int i = 0; i < vertices.size(); i++)
            vertices.set(i, Matrix.rotate(rotation, vertices.get(i)));
    }

    public float[] getVertices() {
        float[] verts = new float[vertices.size()*3];

        for (int i = 0; i < vertices.size(); i++)
            for (int j = 0; j < 3; j++)
                verts[i*3 + j] = vertices.get(i).get(j);

        return verts;
    }

    public float[] getColors() {
        float[] cols = new float[colors.size()*3];

        for (int i = 0; i < colors.size(); i++)
            for (int j = 0; j < 3; j++)
                cols[i*3 + j] = colors.get(i).get(j);

        return cols;
    }

    public void addToPosition(Vector v) {
        position = position.add(v);
        
        for (int i = 0; i < vertices.size(); i++)
            vertices.set(i, vertices.get(i).add(v));
    }

    public Vector getPosition() {
        return position;
    }

    public ArrayList<Vector> vertices() {
        return vertices;
    }

    public ArrayList<Vector> colors() {
        return colors;
    }

    public String toString() {
        String output = "Model:\n";

        output += "Vertices:\n";
        for (Vector v:vertices) output += v + "\n";

        output += "Colors:\n";
        for (Vector v:vertices) output += v + "\n";

        return output.substring(0, output.length() - 1);
    }
    
}
