import Math.*;
import Model.*;

public class Entity {

    private Tuple position;
    private Vector velocity;

    private Model model;

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

    private void updateModel() {
        
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

    public String toString() {
        String output = "Object:\n";
        output += "position: " + position + "\n";
        output += "velocity: " + velocity + "\n";
        output += model;
        return output;
    }
    
}