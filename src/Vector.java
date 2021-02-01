public class Vector {

    private final float a;
    private final float b;
    private final float c;

    public Vector(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triple toTriple() {
        return new Triple(a, b, c);
    }

    public String toString() {
        String output = "<";
        
        output += a + ", ";
        output += b + ", ";
        output += c + ">";

        return output;
    }
    
}
