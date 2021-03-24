package Game.Entity.Hard;

import Game.Entity.Entity;
import Game.Graphics.Texture;
import Game.Math.Vector;
import Game.Model.Model;
import Game.Physics.RigidBody;

public class HardEntity extends Entity {

    protected RigidBody body;

    public HardEntity(){};

    public HardEntity(Vector position, Model model, Texture texture, RigidBody rigidBody) {
        super(position, model, texture);

        body = rigidBody;
    }

    protected void translate() {
        position = position.add(velocity);
        model.addToPosition(velocity);
        body.addToPosition(velocity);
    }

    protected void rotate() {
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
