package Math;

public class Vector {

    private final float[] vec;
    private final int length;

    public Vector(float... x) {
        vec = x;
        length = vec.length;
    }

    public int getLength() {
        return length;
    }

    public float get(int index) {
        return vec[index];
    }

    public Tuple toTuple() {
        return new Tuple(vec);
    }

    // TODO: TESTME
    public Matrix toMatrix() {
        float[][] mat = new float[1][length];

        for (int i = 0; i < length; i++)
            mat[i][0] = vec[i];

        return new Matrix(mat);
    }

    // TODO: IMPLEMENT, TEST
    public static float dot(Vector v1, Vector v2) {
        return -1;
    }

    public static Vector cross(Vector v1, Vector v2) {
        if (v1.length != 3 || v2.length != 3) throw new ArithmeticException();

        float[] product = new float[3];
        product[0] = (v1.vec[1]*v2.vec[2]) - (v2.vec[1]*v1.vec[2]);
        product[1] = (v2.vec[0]*v1.vec[2]) - (v1.vec[0]*v2.vec[2]);
        product[2] = (v1.vec[0]*v2.vec[1]) - (v2.vec[0]*v1.vec[1]);

        return new Vector(product);
    }

    public String toString() {
        String output = "<";

        for (float f:vec) output += f + ", ";
        output = output.substring(0, output.length() - 2);
        
        output += ">";

        return output;
    }
    
}
