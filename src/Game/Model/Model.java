package Game.Model;

import Game.Math.*;

public class Model {

    protected Vector position;

    protected Triangle[] tris;
    protected Vector[][] cols; // [triangle][vertex]

    protected Model(){};

    public Model(Vector p, float[] vertices, float[] colors) {
        loadModel(p, vertices, colors);
    }

    private void loadModel(Vector p, float[] vertices, float[] colors) {
        if (vertices.length % 9 != 0) throw new IllegalArgumentException();
        if (vertices.length != colors.length) throw new IllegalArgumentException();

        position = p;

        tris = new Triangle[vertices.length / 9];
        cols = new Vector[tris.length][3];

        for (int i = 0; i < tris.length; i++) {
            tris[i] = new Triangle(
                new float[] {vertices[0 + i*9], vertices[1 + i*9], vertices[2 + i*9],
                             vertices[3 + i*9], vertices[4 + i*9], vertices[5 + i*9],
                             vertices[6 + i*9], vertices[7 + i*9], vertices[8 + i*9]});

            cols[i][0] = new Vector(colors[0 + i*9], colors[1 + i*9], colors[2 + i*9]);
            cols[i][1] = new Vector(colors[3 + i*9], colors[4 + i*9], colors[5 + i*9]);
            cols[i][2] = new Vector(colors[6 + i*9], colors[7 + i*9], colors[8 + i*9]);
        }
    }

    public void rotate(Vector axis, float alpha) {
        Matrix rotation = Matrix.rotate(position, axis, alpha);

        for (int i = 0; i < tris.length; i++) {
            Vector p1 = Matrix.rotate(rotation, tris[i].get(0));
            Vector p2 = Matrix.rotate(rotation, tris[i].get(1));
            Vector p3 = Matrix.rotate(rotation, tris[i].get(2));

            tris[i] = new Triangle(p1, p2, p3);
        }
    }

    public float[] getVertices() {
        float[] verts = new float[tris.length * 9];

        for (int i = 0; i < tris.length; i++) { // for each triangle
            float[] t = tris[i].getVertices();
            for (int j = 0; j < t.length; j++) // for each vertex
                verts[j + i*t.length] = t[j];
        }

        return verts;
    }

    public float[] getColors() {
        float[] colors = new float[cols.length*3*3]; // 3 vertices per triangle, 3 coordinates per vertex

        int mark = 0;

        for (int i = 0; i < cols.length; i++) { // for each triangle
            for (int j = 0; j < cols[i].length; j++) { // for each vertex of a triangle
                float[] t = cols[i][j].getFloats();
                
                for (int k = 0; k < t.length; k++) // for each dimension of a vertex
                    colors[k + mark] = t[k];

                mark += t.length;
            }
        }

        return colors;
    }

    public void addToPosition(Vector v) {
        position = position.add(v);
        Vector vt = v;
        
        for (int i = 0; i < tris.length; i++) {
            Vector v1 = tris[i].get(0);
            Vector v2 = tris[i].get(1);
            Vector v3 = tris[i].get(2);

            tris[i] = new Triangle(v1.add(vt), v2.add(vt), v3.add(vt));
        }
    }

    public Vector getPosition() {
        return position;
    }

    public Triangle[] getTris() {
        return tris;
    }

    public Vector[][] getCols() {
        return cols;
    }

    public String toString() {
        String output = "Model:\n";

        output += "Vertices:\n";
        for (Triangle t:tris) output += t + "\n";

        output += "Colors:\n";
        for (int i = 0; i < cols.length; i++) {
            for (int j = 0; j < cols[i].length; j++)
                output += cols[i][j];
            
            output += "\n";
        }

        return output.substring(0, output.length() - 1);
    }
    
}
