package Entity;

import Math.*;
import Model.*;

public class Player extends Entity {

    private Camera camera;

    public Player(float x, float y, float z, Camera camera) {
        super(x, y, z);

        this.camera = camera;
    }

    public Player(Tuple position, Camera camera) {
        super(position);

        this.camera = camera;
    }

    public Player(float x, float y, float z, Model model, Camera camera) {
        super(x, y, z, model);

        this.camera = camera;
    }

    public Player(Tuple position, Model model, Camera camera) {
        super(position, model);

        this.camera = camera;
    }

    public void update() {
        camera.update();
        position = Tuple.add(position, velocity.toTuple());
        updateModel();
    }

    public Camera getCamera() {
        return camera;
    }
    
}
