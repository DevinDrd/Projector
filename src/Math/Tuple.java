package Math;

public class Tuple {

    private final float[] tuple;

    private int length;

    public Tuple(float... x) {
        tuple = x;
        length = x.length;
    }

    public float get(int index) {
        return tuple[index];
    }

    public float[] getFloats() {
        return tuple;
    }

    public int getLength() {
        return tuple.length;
    }

    public Vector toVector() {
        return new Vector(tuple);
    }

    public static Tuple add(Tuple a, Tuple b) {
        if (a.length != b.length) throw new ArithmeticException();

        float[] sum = new float[a.length];

        for (int i = 0; i < sum.length; i++)
            sum[i] = a.tuple[i] + b.tuple[i];

        return new Tuple(sum);
    }

    public static Tuple subtract(Tuple a, Tuple b) {
        if (a.length != b.length) throw new ArithmeticException();

        float[] diff = new float[a.length];

        for (int i = 0; i < diff.length; i++)
            diff[i] = a.tuple[i] - b.tuple[i];

        return new Tuple(diff);
    }

    public String toString() {
        String output = "(";

        for (float f:tuple)
            output += f + ", ";

        output = output.substring(0, output.length() - 2);
        
        output += ")";

        return output;
    }
    
}
