package Game.Math;

public class Vector {

    private final float[] vec;
    private final int length;

    public Vector(float... x) {
        vec = x;
        length = vec.length;
    }

    public int length() {
        return length;
    }

    public float get(int index) {
        return vec[index];
    }

    public Vector normalize() {
        float[] n = new float[length];
        float mag = magnitude();

        for (int i = 0; i < n.length; i++)
            n[i] = vec[i]/mag;
 
        return new Vector(n);
    }

    public float magnitude() {
        float mag = 0;
        for (float f: vec)
            mag += Math.pow(f, 2);

        return (float) Math.sqrt(mag);
    }

    public Vector homogenize() {
        float[] f = new float[vec.length + 1];
        for (int i = 0; i < vec.length; i++)
            f[i] = vec[i];

        f[f.length - 1] = 1;
        return new Vector(f);
    }

    public Vector perspectiveDivide() {
        float[] f = new float[vec.length - 1];
        for (int i = 0; i < f.length; i++)
            f[i] = vec[i] / vec[vec.length - 1];

        return new Vector(f);
    }

    public Vector add(float f) {
        float[] sum = new float[length];

        for (int i = 0; i < sum.length; i++) 
            sum[i] = vec[i] + f;

        return new Vector(sum);
    }

    public Vector subtract(float f) {
        float[] diff = new float[length];

        for (int i = 0; i < diff.length; i++) 
            diff[i] = vec[i] - f;

        return new Vector(diff);
    }

    public Vector multiply(float factor) {
        float[] product = new float[length];

        for (int i = 0; i < product.length; i++) 
            product[i] = vec[i] * factor;

        return new Vector(product);
    }

    public Vector divide(float f) {
        float[] quotient = new float[length];

        for (int i = 0; i < quotient.length; i++) 
            quotient[i] = vec[i] / f;

        return new Vector(quotient);
    }

    public Vector add(Vector v) {
        float[] sum = new float[length];

        for (int i = 0; i < sum.length; i++) 
            sum[i] = vec[i] + v.vec[i];

        return new Vector(sum);
    }

    public  Vector subtract(Vector v) {
        if (length != v.length) throw new ArithmeticException();

        float[] diff = new float[v.length];

        for (int i = 0; i < diff.length; i++)
            diff[i] = vec[i] - v.vec[i];

        return new Vector(diff);
    }

    public float dot(Vector v) {
        if (length != v.length) throw new ArithmeticException();

        double dot = 0;

        for (int i = 0; i < v.length; i++)
            dot += vec[i]*v.vec[i];
        
        return (float) dot;
    }

    public Vector cross(Vector v) {
        if (length != 3 || v.length != 3) throw new ArithmeticException();

        float[] product = new float[3];
        product[0] = (vec[1]*v.vec[2]) - (v.vec[1]*vec[2]);
        product[1] = (v.vec[0]*vec[2]) - (vec[0]*v.vec[2]);
        product[2] = (vec[0]*v.vec[1]) - (v.vec[0]*vec[1]);

        return new Vector(product);
    }

    // project v onto this vector
    public Vector project(Vector v) {
        if (length != v.length) throw new ArithmeticException();

        float vDot = dot(v);
        float magSqr = dot(this);

        return multiply(vDot/magSqr);
    }

    public Matrix toMatrix() {
        float[][] mat = new float[length][1];

        for (int i = 0; i < length; i++)
            mat[i][0] = vec[i];

        return new Matrix(mat);
    }

    public float[] getFloats() {
        return vec;
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
