public class Triangle {

    private final Triple[] vertices;

    public final static int LENGTH = 3;
    
    public Triangle(float[] vertices) {
        this.vertices = new Triple[3];

        this.vertices[0] = new Triple(vertices[0], vertices[1], vertices[2]);
        this.vertices[1] = new Triple(vertices[3], vertices[4], vertices[5]);
        this.vertices[2] = new Triple(vertices[6], vertices[7], vertices[8]);
    }

    public Triangle(Triple a, Triple b, Triple c) {
        vertices = new Triple[3];

        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
    }

    public Triangle(Triple[] verts) {
        vertices = verts;
    }

    public float[] getFloats() {
		float[] floats = new float[9];
		
		for (int i = 0; i < LENGTH; i++)
			for (int j = 0; j < Triple.LENGTH; j++)
				floats[i*Triple.LENGTH + j] = vertices[i].get(j);
		
		return floats;
    }

    public static void main(String[] args) {
        float[] floats = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        Triangle tri = new Triangle(floats);

        float[] f = tri.getFloats();
        for (float fl:f) System.out.print(fl + ", ");
        System.out.println();
    }
    
}
