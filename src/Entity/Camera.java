package Entity;

import Math.*;

public class Camera extends Entity {

    private Vector n; // camera direction
    private Vector u; // camera up orientation

    public Camera(Tuple position, Vector direction, Vector up) {
        super(position);

        n = Vector.subtract(direction, position.toVector());
        u = up;
    }

    
}
