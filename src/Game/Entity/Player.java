package Game.Entity;

import Game.Graphics.Camera;
import Game.Graphics.Texture;
import Game.Math.*;
import Game.Model.Model;
import Game.Physics.RigidBody;

public class Player extends Entity {

    private Camera camera;

    private float speed = 2f; // how quickly player moves from user input
    private float angularSpeed = 15f; // how quickly player rotates from user input in degrees

    private Vector moving; // when user if moving the character

    public Player(Vector position, Camera camera, Model model, Texture texture, RigidBody rigidBody) {
        super(position, model, texture, rigidBody);
        moving = new Vector(0, 0, 0);

        this.camera = camera;
    } 

    public void move(Motion m) {
        Vector go = null;

        if (m == Motion.FORWARD)
            go = camera.getDirection(Motion.FORWARD).multiply(speed);
        else if (m == Motion.BACKWARD)
            go = camera.getDirection(Motion.BACKWARD).multiply(speed);
        else if (m == Motion.LEFT)
            go = camera.getDirection(Motion.LEFT).multiply(speed);
        else if (m == Motion.RIGHT)
            go = camera.getDirection(Motion.RIGHT).multiply(speed);
        else if (m == Motion.UP)
            go = camera.getDirection(Motion.UP).multiply(speed);
        else if (m == Motion.DOWN)
            go = camera.getDirection(Motion.DOWN).multiply(speed);
        else
            throw new IllegalArgumentException();

        moving = moving.add(go);
    }

    public void stopMove(Motion m) {
        Vector slow = null;

        if (m == Motion.FORWARD)
            slow = camera.getDirection(Motion.FORWARD).multiply(speed).project(moving);
        else if (m == Motion.BACKWARD)
            slow = camera.getDirection(Motion.BACKWARD).multiply(speed).project(moving);
        else if (m == Motion.LEFT)
            slow = camera.getDirection(Motion.LEFT).multiply(speed).project(moving);
        else if (m == Motion.RIGHT)
            slow = camera.getDirection(Motion.RIGHT).multiply(speed).project(moving);
        else if (m == Motion.UP)
            slow = camera.getDirection(Motion.UP).multiply(speed).project(moving);
        else if (m == Motion.DOWN)
            slow = camera.getDirection(Motion.DOWN).multiply(speed).project(moving);
        else
            throw new IllegalArgumentException();

        moving = moving.subtract(slow);
    }

    public void rotate(Motion m) {
        Matrix rotation;
        Vector zero = new Vector(0, 0, 0);

        if (m == Motion.SPINUP)
            rotation = Matrix.rotate(zero, camera.getDirection(Motion.RIGHT), angularSpeed);
        else if (m == Motion.SPINDOWN)
            rotation = Matrix.rotate(zero, camera.getDirection(Motion.LEFT), angularSpeed);
        else if (m == Motion.SPINLEFT)
            rotation = Matrix.rotate(zero, camera.getDirection(Motion.UP), angularSpeed);
        else if (m == Motion.SPINRIGHT)
            rotation = Matrix.rotate(zero, camera.getDirection(Motion.DOWN), angularSpeed);
        else if (m == Motion.COUNTERCLOCKWISE)
            rotation = Matrix.rotate(zero, camera.getDirection(Motion.FORWARD), angularSpeed);
        else if (m == Motion.CLOCKWISE)
            rotation = Matrix.rotate(zero, camera.getDirection(Motion.BACKWARD), angularSpeed);
        else
            throw new IllegalArgumentException();

        moving = Matrix.rotate(rotation, moving);
        camera.rotate(rotation);
    }

    public void update() {
        move();
        accelerate(acceleration);
        translate(velocity);
        rotate();
    }

    public void translate(Vector d) {
        position = position.add(d);
        model.translate(d);
        body.translate(d);
        camera.translate(d);
    }

    protected void rotate() {
        model.rotate(rotationAxis);
        body.rotate(rotationAxis);
        camera.rotate(rotationAxis);
    }

    private void move() {
        translate(moving);
    }

    public void freeze() {
        setRotation(new Vector(0, 0, 0));
        setVelocity(new Vector(0, 0, 0));
        setAcceleration(new Vector(0, 0, 0));

        moving = new Vector(0, 0, 0);
    }

    public float speed() {
        return speed;
    }

    public Camera camera() {
        return camera;
    }
}
