package Math;

public class Tuple {

    private final float[] tuple;

    public Tuple(float... x) {
        tuple = x;
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

    public static Tuple add(Tuple a, Tuple b) {
        return new Tuple(a.get(0) + b.get(0), a.get(1) + b.get(1), a.get(2) + b.get(2));
    }

    public static Tuple subtract(Tuple a, Tuple b) {
        return new Tuple(a.get(0) - b.get(0), a.get(1) - b.get(1), a.get(2) - b.get(2));
    }

    public static Tuple multiply(Tuple a, Tuple b) {
        return new Tuple(a.get(0) * b.get(0), a.get(1) * b.get(1), a.get(2) * b.get(2));
    }

    public static Tuple divide(Tuple a, Tuple b) {
        return new Tuple(a.get(0) / b.get(0), a.get(1) / b.get(1), a.get(2) / b.get(2));
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
