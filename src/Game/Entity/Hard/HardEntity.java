package Game.Entity.Hard;

import Game.Entity.Entity;
import Game.Math.Vector;
import Game.Model.Model;
import Game.Physics.RigidBody;

public class HardEntity extends Entity {

    protected RigidBody body;

    public HardEntity(){};

    public HardEntity(float x, float y, float z, RigidBody rigidBody) {
        super(x, y, z);

        body = rigidBody;
    }

    public HardEntity(Vector position, RigidBody rigidBody) {
        super(position);

        body = rigidBody;
    }

    public HardEntity(float x, float y, float z, Model model, RigidBody rigidBody) {
        super(x, y, z, model);

        body = rigidBody;
    }

    public HardEntity(Vector position, Model model, RigidBody rigidBody) {
        super(position, model);

        body = rigidBody;
    }

    protected void translate() {
        position = position.add(velocity);
        model.addToPosition(velocity);
        body.addToPosition(velocity);
    }

    protected void rotate() {
        // rotate player --- don't need to do anything
        model.rotate(rotationAxis, rotationAxis.magnitude());
        body.rotate(rotationAxis, rotationAxis.magnitude());
    }

    public void addToPosition(Vector d) {
        position = position.add(d);
        model.addToPosition(d);
        body.addToPosition(d);
    }

    // FIXME: update model and rigid body as well
    public void setPosition(Vector p) {
        throw new RuntimeException("This method has no implementation");
        // position = p;
    }

    public RigidBody getBody() {
        return body;
    }

    public String toString() {
        String output = "Entity:\n";
        output += "position: " + position + "\n";
        output += "velocity: " + velocity + "\n";
        output += model + "\n";
        output += body;
        return output;
    }
    
}
