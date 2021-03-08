package Game.Math;

public class Triangle {

    private final Vector[] vertices;

    public final static int LENGTH = 3;
    
    public Triangle(float[] vertices) {
        if (vertices.length != 9) throw new IllegalArgumentException();

        this.vertices = new Vector[3];

        this.vertices[0] = new Vector(vertices[0], vertices[1], vertices[2]);
        this.vertices[1] = new Vector(vertices[3], vertices[4], vertices[5]);
        this.vertices[2] = new Vector(vertices[6], vertices[7], vertices[8]);
    }

    public Triangle(Vector a, Vector b, Vector c) {
        vertices = new Vector[3];

        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
    }

    public Triangle(Vector... verts) {
        if (verts.length != 3) throw new IllegalArgumentException();
        
        vertices = verts;
    }

    public Vector get(int index) {
        return vertices[index];
    }

    public float[] getVertices() {
		float[] floats = new float[9];
		
		for (int i = 0; i < LENGTH; i++)
			for (int j = 0; j < 3; j++)
				floats[i*3 + j] = vertices[i].get(j);
		
		return floats;
    }

    public String toString() {
        String output = "Triangle: ";
        for (Vector t:vertices) output += t + ", ";
        return output.substring(0, output.length() - 2);
    }
    
}
