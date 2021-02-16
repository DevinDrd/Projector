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

    public static float magnitude(Vector v) {
        float mag = 0;
        for (float f: v.vec)
            mag += Math.pow(f, 2);

        return (float) Math.sqrt(mag);
    }

    public static Vector normalize(Vector v) {
        float[] n = new float[v.length];
        float mag = magnitude(v);

        for (int i = 0; i < n.length; i++)
            n[i] = v.vec[i]/mag;
 
        return new Vector(n);
    }

    public  Vector homogenize() {
        float[] f = new float[vec.length + 1];
        for (int i = 0; i < vec.length; i++)
            f[i] = vec[i];

        f[f.length - 1] = 1;
        return new Vector(f);
    }

    public  Vector perspectiveDivide() {
        float[] f = new float[vec.length - 1];
        for (int i = 0; i < f.length; i++)
            f[i] = vec[i] / vec[vec.length - 1];

        return new Vector(f);
    }

    public static Vector add(Vector v, float f) {
        float[] sum = new float[v.length];

        for (int i = 0; i < sum.length; i++) 
            sum[i] = v.vec[i] + f;

        return new Vector(sum);
    }

    public static Vector subtract(Vector v, float f) {
        float[] diff = new float[v.length];

        for (int i = 0; i < diff.length; i++) 
            diff[i] = v.vec[i] - f;

        return new Vector(diff);
    }

    public static Vector multiply(Vector v, float f) {
        float[] product = new float[v.length];

        for (int i = 0; i < product.length; i++) 
            product[i] = v.vec[i] * f;

        return new Vector(product);
    }

    public static Vector divide(Vector v, float f) {
        float[] quotient = new float[v.length];

        for (int i = 0; i < quotient.length; i++) 
            quotient[i] = v.vec[i] / f;

        return new Vector(quotient);
    }

    public static Vector add(Vector v1, Vector v2) {
        if (v1.length != v2.length) throw new ArithmeticException();

        float[] sum = new float[v2.length];

        for (int i = 0; i < sum.length; i++)
            sum[i] = v1.vec[i] + v2.vec[i];

        return new Vector(sum);
    }

    public static Vector subtract(Vector v1, Vector v2) {
        if (v1.length != v2.length) throw new ArithmeticException();

        float[] diff = new float[v2.length];

        for (int i = 0; i < diff.length; i++)
            diff[i] = v1.vec[i] - v2.vec[i];

        return new Vector(diff);
    }

    // TODO: TEST
    public static float dot(Vector v1, Vector v2) {
        double dot = 0;

        for (int i = 0; i < v1.length; i++)
            dot = Math.pow(v1.vec[i], 2) + Math.pow(v2.vec[i], 2);
        
        return (float) Math.sqrt(dot);
    }

    public static Vector cross(Vector v1, Vector v2) {
        if (v1.length != 3 || v2.length != 3) throw new ArithmeticException();

        float[] product = new float[3];
        product[0] = (v1.vec[1]*v2.vec[2]) - (v2.vec[1]*v1.vec[2]);
        product[1] = (v2.vec[0]*v1.vec[2]) - (v1.vec[0]*v2.vec[2]);
        product[2] = (v1.vec[0]*v2.vec[1]) - (v2.vec[0]*v1.vec[1]);

        return new Vector(product);
    }

    public Tuple toTuple() {
        return new Tuple(vec);
    }

    public Matrix toMatrix() {
        float[][] mat = new float[length][1];

        for (int i = 0; i < length; i++)
            mat[i][0] = vec[i];

        return new Matrix(mat);
    }

    public String toString() {
        String output = "<";

        for (float f:vec) output += f + ", ";
        output = output.substring(0, output.length() - 2);
        
        output += ">";

        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;

        Vector object = (Vector) obj;

        if (this.length != object.length) return false;

        for (int i = 0; i < length; i++)
            if (Math.abs(this.vec[i] - object.vec[i]) > .000001f) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (float f:vec)
            sum += (int) f;
        return (length * sum) % 100000;
    }
}
