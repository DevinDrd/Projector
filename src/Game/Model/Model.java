package Game.Model;

import Game.Math.*;

public class Model {

    protected Triangle[] tris;
    protected Tuple[][] cols; // [triangle][vertex]

    protected Model(){};

    public Model(float[] vertices, float[] colors) {
        loadModel(vertices, colors);
    }

    private void loadModel(float[] vertices, float[] colors) {
        if (vertices.length % 9 != 0) throw new IllegalArgumentException();
        if (vertices.length != colors.length) throw new IllegalArgumentException();

        tris = new Triangle[vertices.length / 9];
        cols = new Tuple[tris.length][3];

        for (int i = 0; i < tris.length; i++) {
            tris[i] = new Triangle(
                new float[] {vertices[0 + i*9], vertices[1 + i*9], vertices[2 + i*9],
                             vertices[3 + i*9], vertices[4 + i*9], vertices[5 + i*9],
                             vertices[6 + i*9], vertices[7 + i*9], vertices[8 + i*9]});

            cols[i][0] = new Tuple(colors[0 + i*9], colors[1 + i*9], colors[2 + i*9]);
            cols[i][1] = new Tuple(colors[3 + i*9], colors[4 + i*9], colors[5 + i*9]);
            cols[i][2] = new Tuple(colors[6 + i*9], colors[7 + i*9], colors[8 + i*9]);
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
        Tuple vt = v.toTuple();
        
        for (int i = 0; i < tris.length; i++) {
            Tuple v1 = tris[i].get(0);
            Tuple v2 = tris[i].get(1);
            Tuple v3 = tris[i].get(2);

            tris[i] = new Triangle(Tuple.add(v1, vt), Tuple.add(v2, vt), Tuple.add(v3, vt));
        }
    }

    public Triangle[] getTris() {
        return tris;
    }

    public Tuple[][] getCols() {
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
