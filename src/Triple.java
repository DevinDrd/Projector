public class Triple {

    private final float a, b, c;

    public final static int LENGTH = 3;

    public Triple(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float get(int index) {
        if (index == 0) return a;
        else if (index == 1) return b;
        else if (index == 2) return c;
        throw new ArrayIndexOutOfBoundsException();
    }

    public float[] getFloats() {
        return new float[] {a, b, c};
    }

    public static Triple add(Triple a, Triple b) {
        return new Triple(a.get(0) + b.get(0), a.get(1) + b.get(1), a.get(2) + b.get(2));
    }

    public static Triple subtract(Triple a, Triple b) {
        return new Triple(a.get(0) - b.get(0), a.get(1) - b.get(1), a.get(2) - b.get(2));
    }

    public static Triple multiply(Triple a, Triple b) {
        return new Triple(a.get(0) * b.get(0), a.get(1) * b.get(1), a.get(2) * b.get(2));
    }

    public static Triple divide(Triple a, Triple b) {
        return new Triple(a.get(0) / b.get(0), a.get(1) / b.get(1), a.get(2) / b.get(2));
    }

    public String toString() {
        String output = "(";

        output += a + ", ";
        output += b + ", ";
        output += c + ")";

        return output;
    }
    
}
