package Game.Entity;

import Game.Graphics.Texture;
import Game.Math.*;
import Game.Model.*;

public class Entity {

    protected Vector position;
    protected Vector velocity;
    protected Vector acceleration;
    protected float mass;

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
        acceleration = new Vector(0, 0, 0);

        this.model = model;
        this.texture = texture;
    }

    public void update() {
        
        accelerate(acceleration);
        translate(velocity);
        if (rotationAxis.magnitude() - 0.000001f > 0)
            rotate();
    }
    
    public void translate(Vector d) {
        position = position.add(d);
        model.translate(d);
    }

    public void accelerate(Vector v) {
        velocity = velocity.add(v);
    }

    public void force(Vector f) {
        acceleration = acceleration.add(f.divide(mass));
    }

    protected void rotate() {
        model.rotate(rotationAxis, rotationAxis.magnitude());
    }

    public Model model() {
        return model;
    }

    public Texture texture() {
        return texture;
    }

    public Vector position() {
        return position;
    }

    public Vector velocity() {
        return velocity;
    }

    public Vector acceleration() {
        return acceleration;
    }

    public float mass() {
        return mass;
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

    public void setAcceleration(Vector a) {
        acceleration = a;
    }

    public void setMass(float m) {
        mass = m;
    }

    public String toString() {
        String output = "Entity:\n";
        output += "position: " + position + "\n";
        output += "velocity: " + velocity + "\n";
        output += "acceleration: " + acceleration + "\n";
        output += "mass: " + mass + "\n";
        output += model;
        output += texture;
        return output;
    }
    
}