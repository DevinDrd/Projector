package Game.Entity.Hard;

import Game.Entity.Camera;
import Game.Math.*;
import Game.Model.*;
import Game.Physics.RigidBody;

public class Player extends HardEntity {

    private Camera camera;

    private float force = 2f; // force value that acts on player

    public Player(float x, float y, float z, Camera camera, RigidBody rigidBody) {
        super(x, y, z, rigidBody);

        this.camera = camera;
    }

    public Player(Tuple position, Camera camera, RigidBody rigidBody) {
        super(position, rigidBody);

        this.camera = camera;
    }

    public Player(float x, float y, float z, Model model, Camera camera, RigidBody rigidBody) {
        super(x, y, z, model, rigidBody);

        this.camera = camera;
    }

    public Player(Tuple position, Model model, Camera camera, RigidBody rigidBody) {
        super(position, model, rigidBody);

        this.camera = camera;
    }

    public void update() {
        addToPosition(velocity);
    }

    public void addToPosition(Vector d) {
        position = Tuple.add(position, d.toTuple());
        camera.addToPosition(d);
        model.addToPosition(d);
        body.addToPosition(d);
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
        Matrix rotation = Matrix.rotate(camera.getDirectionRight(), alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void lookDown(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionRight(), -alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void lookLeft(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionUp(), alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void lookRight(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionUp(), -alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void spinCounterclockwise(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionForward(), -alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void spingClockwise(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionForward(), alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
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
