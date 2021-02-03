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
        position = Tuple.add(position, velocity.toTuple());
        camera.update();
        updateModel();
    }

    public void setVelocity(Vector vec) {
        velocity = vec;
        camera.setVelocity(vec);
    }

    public void addVelocity(Vector vec) {
        velocity = Vector.add(velocity, vec);
        camera.setVelocity(velocity);
    }

    public Camera getCamera() {
        return camera;
    }
    
}
