public class Triangle {

    private final Tuple[] vertices;

    public final static int LENGTH = 3;
    
    public Triangle(float[] vertices) {
        if (vertices.length != 9) throw new IllegalArgumentException();

        this.vertices = new Tuple[3];

        this.vertices[0] = new Tuple(vertices[0], vertices[1], vertices[2]);
        this.vertices[1] = new Tuple(vertices[3], vertices[4], vertices[5]);
        this.vertices[2] = new Tuple(vertices[6], vertices[7], vertices[8]);
    }

    public Triangle(Tuple a, Tuple b, Tuple c) {
        vertices = new Tuple[3];

        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
    }

    public Triangle(Tuple[] verts) {
        if (verts.length != 3) throw new IllegalArgumentException();
        
        vertices = verts;
    }

    public float[] getVertices() {
		float[] floats = new float[9];
		
		for (int i = 0; i < LENGTH; i++)
			for (int j = 0; j < 3; j++)
				floats[i*3 + j] = vertices[i].get(j);
		
		return floats;
    }

    public String toString() {
        String output = "";
        for (Tuple t:vertices) output += t + ", ";
        return output.substring(0, output.length() - 2);
    }
    
}
