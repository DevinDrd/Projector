package Game.Entity;

import Game.Graphics.Texture;
import Game.Math.*;
import Game.Model.*;

public class Entity {

    protected Vector position;
    protected Vector velocity;

    // direction is axis of rotation, magnitude is angular velocity
    // in 60 degrees per second (depending on the update rate)
    protected Vector rotationAxis;

    protected Model model;
    protected Texture texture;

    public Entity(){};

    public Entity(Vector position, Model model, Texture texture) {
        this.position = position;
        velocity = new Vector(0, 0, 0);
        rotationAxis = new Vector(0, 0, 0);

        this.model = model;
        this.texture = texture;
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
        model.rotate(rotationAxis, rotationAxis.magnitude());
    }

    public Model getModel() {
        return model;
    }

    public Texture getTexture() {
        return texture;
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