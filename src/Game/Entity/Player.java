package Game.Entity;

import Game.Math.*;
import Game.Model.*;

public class Player extends Entity {

    private Camera camera;

    private float force = .5f; // force value that acts on player

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

    public void forwardForce(float f) {
        addVelocity(Vector.multiply(camera.getDirectionForward(), f));
    }

    public void backwardForce(float f) {
        subtractVelocity(Vector.multiply(camera.getDirectionForward(), f));
    }
    
    public void leftForce(float f) {
        subtractVelocity(Vector.multiply(camera.getDirectionRight(), f));
    }

    public void rightForce(float f) {
        addVelocity(Vector.multiply(camera.getDirectionRight(), f));
    }

    public void upForce(float f) {
        addVelocity(Vector.multiply(camera.getDirectionUp(), f));
    }

    public void downForce(float f) {
        subtractVelocity(Vector.multiply(camera.getDirectionUp(), f));
    }

    public void lookUp(float alpha) {
        camera.rotate(camera.getDirectionRight(), alpha);
    }

    public void lookDown(float alpha) {
        camera.rotate(camera.getDirectionRight(), -alpha);
    }

    public void lookLeft(float alpha) {
        camera.rotate(camera.getDirectionUp(), alpha);
    }

    public void lookRight(float alpha) {
        camera.rotate(camera.getDirectionUp(), -alpha);
    }

    public void spinCounterclockwise(float alpha) {
        camera.rotate(camera.getDirectionForward(), alpha);
    }

    public void spingClockwise(float alpha) {
        camera.rotate(camera.getDirectionForward(), -alpha);
    }
    
    public void setVelocity(Vector vec) {
        velocity = vec;
        camera.setVelocity(vec);
    }

    public void addVelocity(Vector vec) {
        velocity = Vector.add(velocity, vec);
        camera.setVelocity(velocity);
    }

    public void subtractVelocity(Vector vec) {
        velocity = Vector.subtract(velocity, vec);
        camera.setVelocity(velocity);
    }

    public void setForce(float f) {
        force = f;
    }

    public Camera getCamera() {
        return camera;
    }

    public float getForce() {
        return force;
    }
    
}
