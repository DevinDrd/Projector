package Model;

import Math.*;

public class Model {

    protected Triangle[] tris;
    protected Tuple[][] cols; // [triangle][vertex]

    protected Model(){};

    public Model(float[] vertices, float[] colors) {
        if (vertices.length % 9 != 0) throw new IllegalArgumentException();
        if (vertices.length != colors.length) throw new IllegalArgumentException();

        tris = new Triangle[vertices.length / 9];
        cols = new Tuple[tris.length][3];

        for (int i = 0; i < tris.length; i++) {
            tris[i] = new Triangle(
                new float[] {vertices[0 + i], vertices[1 + i], vertices[2 + i],
                             vertices[3 + i], vertices[4 + i], vertices[5 + i],
                             vertices[6 + i], vertices[7 + i], vertices[8 + i]});

            cols[i][0] = new Tuple(colors[0 + i], colors[1 + i], colors[2 + i]);
            cols[i][1] = new Tuple(colors[3 + i], colors[4 + i], colors[5 + i]);
            cols[i][2] = new Tuple(colors[6 + i], colors[7 + i], colors[8 + i]);
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
        float[] colors = new float[cols.length*cols[0].length * 3];

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