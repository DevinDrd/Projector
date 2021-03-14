package Game.Entity;

import Game.Math.*;
import Game.Model.*;

import java.util.ArrayList;

public class Entity {

    protected Vector position;
    protected Vector velocity;

    // direction is axis of rotation, magnitude is angular velocity
    // in 60 degrees per second (depending on the update rate)
    protected Vector rotationAxis;

    protected Model model;

    public Entity(){};

    public Entity(float x, float y, float z) {
        position = new Vector(x, y, z);
        velocity = new Vector(0, 0, 0);
        rotationAxis = new Vector(0, 0, 0);

        model = new Model(position, new ArrayList<Vector>(), new ArrayList<Vector>());
    }

    public Entity(Vector position) {
        this.position = position;
        velocity = new Vector(0, 0, 0);
        rotationAxis = new Vector(0, 0, 0);

        model = new Model(position, new ArrayList<Vector>(), new ArrayList<Vector>());
    }

    public Entity(float x, float y, float z, Model model) {
        position = new Vector(x, y, z);
        velocity = new Vector(0, 0, 0);
        rotationAxis = new Vector(0, 0, 0);

        this.model = model;
    }

    public Entity(Vector position, Model model) {
        this.position = position;
        velocity = new Vector(0, 0, 0);
        rotationAxis = new Vector(0, 0, 0);

        this.model = model;
    }

    public void update() {
        translate();
        if (rotationAxis.magnitude() - 0.000001f > 0)
            rotate();
    }
    
    protected void translate() {
        position = position.add(velocity);
        model.addToPosition(velocity);
    }

    protected void rotate() {
        // rotate player --- don't need to do anything
        model.rotate(rotationAxis, rotationAxis.magnitude());
    }

    public Model getModel() {
        return model;
    }

    public Vector getPosition() {
        return position;
    }

    public void addToPosition(Vector d) {
        position = position.add(d);
        model.addToPosition(d);
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getRotation() {
        return rotationAxis;
    }

    // FIXME: update model as well
    public void setPosition(Vector p) {
        throw new RuntimeException("This method has no implementation");
        // position = p;
    }

    public void setVelocity(Vector vel) {
        velocity = vel;
    }

    public void setRotation(Vector r) {
        rotationAxis = r;
    }

    public String toString() {
        String output = "Entity:\n";
        output += "position: " + position + "\n";
        output += "velocity: " + velocity + "\n";
        output += model;
        return output;
    }
    
}