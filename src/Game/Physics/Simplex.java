package Game.Physics;

import java.util.ArrayList;

import Game.Math.Vector;

// credit to winterdev (https://blog.winter.dev/)
public class Simplex {

    private Vector[] list;
    private int length;

    public Simplex() {
        list = new Vector[] {null, null, null, null};
        length = 0;
    }

    public void pushFront(Vector point) {
        list = new Vector[]{point, list[0], list[1], list[2]};
        length = Math.min(length + 1, 4);
    }

    public Vector get(int i) {
        return list[i];
    }

    public Vector[] toArray() {
        return list;
    }

    public int length() {
        return length;
    }

    public ArrayList<Vector> toArrayList() {
        ArrayList<Vector> array = new ArrayList<Vector>();

        for (Vector v:list)
            array.add(v);

        return array;
    }
    
}
