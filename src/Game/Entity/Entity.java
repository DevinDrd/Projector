package Game.Entity;

import Game.Math.*;
import Game.Model.*;

public class Entity {

    protected Tuple position;
    protected Vector velocity;

    protected Model model;

    public Entity(float x, float y, float z) {
        position = new Tuple(x, y, z);
        velocity = new Vector(0, 0, 0);

        model = new Model(new float[] {}, new float[] {});
    }

    public Entity(Tuple position) {
        this.position = position;
        velocity = new Vector(0, 0, 0);

        model = new Model(new float[] {}, new float[] {});
    }

    public Entity(float x, float y, float z, Model model) {
        position = new Tuple(x, y, z);
        velocity = new Vector(0, 0, 0);

        this.model = model;
    }

    public Entity(Tuple position, Model model) {
        this.position = position;
        velocity = new Vector(0, 0, 0);

        this.model = model;
    }

    public void update() {
        position = Tuple.add(position, velocity.toTuple());
        updateModel();
    }

    protected void updateModel() {
        
    }

    public Model getModel() {
        return model;
    }

    public Tuple getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector vel) {
        velocity = vel;
    }

    public String toString() {
        String output = "Object:\n";
        output += "position: " + position + "\n";
        output += "velocity: " + velocity + "\n";
        output += model;
        return output;
    }
    
}