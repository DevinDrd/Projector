package Game.Physics;

import Game.Math.Vector;

// credit to winterdev (https://blog.winter.dev/)
public class Simplex {

    private Vector[] list;

    public Simplex() {
        list = new Vector[] {null, null, null, null};
    }

    public void pushFront(Vector point) {
        list = new Vector[]{point, list[0], list[1], list[2]};
    }

    public Vector get(int i) {
        return list[i];
    }

    public int length() {
        return list.length;
    }
    
}
