package Game.Entity;

import Game.Graphics.Texture;
import Game.Math.*;
import Game.Model.*;
import Game.Physics.RigidBody;

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
    protected RigidBody body;

    public Entity() {}

    public Entity(Vector position, Model model, Texture texture, RigidBody rigidBody) {
        this.position = position;
        velocity = new Vector(0, 0, 0);
        rotationAxis = new Vector(0, 0, 0);
        acceleration = new Vector(0, 0, 0);

        this.model = model;
        this.texture = texture;
        body = rigidBody;
    }

    public void update() {
        rotate();
        accelerate(acceleration);
        translate(velocity);
    }
    
    public void translate(Vector d) {
        position = position.add(d);
        model.translate(d);
        body.translate(d);
    }

    protected void rotate() {
        model.rotate(rotationAxis);
        body.rotate(rotationAxis);
    }

    public void freeze() {
        setRotation(new Vector(0, 0, 0));
        setVelocity(new Vector(0, 0, 0));
        setAcceleration(new Vector(0, 0, 0));
    }

    public void accelerate(Vector v) {
        velocity = velocity.add(v);
    }

    public void force(Vector f) {
        if (mass != 0)
            acceleration = acceleration.add(f.divide(mass));
    }

    public Model model() {
        return model;
    }

    public Texture texture() {
        return texture;
    }

    public RigidBody body() {
        return body;
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

    public Vector rotation() {
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