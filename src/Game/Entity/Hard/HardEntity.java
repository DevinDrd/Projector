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

        acceleration = new Vector(0, 0, 0);
        mass = 0;

        body = rigidBody;
    }

    public void translate(Vector d) {
        position = position.add(d);
        model.translate(d);
        body.translate(d);
    }

    protected void rotate() {
        model.rotate(rotationAxis, rotationAxis.magnitude());
        body.rotate(rotationAxis, rotationAxis.magnitude());
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
