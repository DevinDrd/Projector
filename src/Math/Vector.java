package Math;

public class Vector {

    private final float[] vec;

    public Vector(float... x) {
        vec = x;
    }

    public int getLength() {
        return vec.length;
    }

    public float get(int index) {
        return vec[index];
    }

    public Tuple toTuple() {
        return new Tuple(vec);
    }

    public String toString() {
        String output = "<";

        for (float f:vec) output += f + ", ";
        output = output.substring(0, vec.length - 2);
        
        output += ">";

        return output;
    }
    
}
