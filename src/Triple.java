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

    public String toString() {
        String output = "(";

        output += a + ", ";
        output += b + ", ";
        output += c + ")";

        return output;
    }
    
}
