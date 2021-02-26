package Game.Entity;

import Game.Math.*;
import Game.Model.*;

public class Entity {

    protected Tuple position;
    protected Vector velocity;

    // direction is axis of rotation, magnitude is angular velocity
    // in 60 degrees per second (depending on the update rate)
    protected Vector rotation;

    protected Model model;

    public Entity(){};

    public Entity(float x, float y, float z) {
        position = new Tuple(x, y, z);
        velocity = new Vector(0, 0, 0);
        rotation = new Vector(0, 0, 0);

        model = new Model(position, new float[] {}, new float[] {});
    }

    public Entity(Tuple position) {
        this.position = position;
        velocity = new Vector(0, 0, 0);
        rotation = new Vector(0, 0, 0);

        model = new Model(position, new float[] {}, new float[] {});
    }

    public Entity(float x, float y, float z, Model model) {
        position = new Tuple(x, y, z);
        velocity = new Vector(0, 0, 0);
        rotation = new Vector(0, 0, 0);

        this.model = model;
    }

    public Entity(Tuple position, Model model) {
        this.position = position;
        velocity = new Vector(0, 0, 0);
        rotation = new Vector(0, 0, 0);

        this.model = model;
    }

    public void update() {
        translate();
        if (rotation.magnitude() - 0.000001f > 0)
            rotate();
    }
    
    protected void translate() {
        position = Tuple.add(position, velocity.toTuple());
        model.addToPosition(velocity);
    }

    protected void rotate() {
        // rotate player --- don't need to do anything
        model.rotate(rotation, rotation.magnitude());
    }

    public Model getModel() {
        return model;
    }

    public Tuple getPosition() {
        return position;
    }

    public void addToPosition(Vector d) {
        position = Tuple.add(position, d.toTuple());
        model.addToPosition(d);
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getRotation() {
        return rotation;
    }

    // FIXME: update model as well
    public void setPosition(Tuple p) {
        throw new RuntimeException("This method has no implementation");
        // position = p;
    }

    public void setVelocity(Vector vel) {
        velocity = vel;
    }

    public void setRotation(Vector r) {
        rotation = r;
    }

    public String toString() {
        String output = "Entity:\n";
        output += "position: " + position + "\n";
        output += "velocity: " + velocity + "\n";
        output += model;
        return output;
    }
    
}