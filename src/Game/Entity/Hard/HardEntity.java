package Game.Entity.Hard;

import Game.Entity.Entity;
import Game.Math.Tuple;
import Game.Model.Model;
import Game.Physics.RigidBody;

public class HardEntity extends Entity {

    protected RigidBody body;

    public HardEntity(){};

    public HardEntity(float x, float y, float z, RigidBody rigidBody) {
        super(x, y, z);

        body = rigidBody;
    }

    public HardEntity(Tuple position, RigidBody rigidBody) {
        super(position);

        body = rigidBody;
    }

    public HardEntity(float x, float y, float z, Model model, RigidBody rigidBody) {
        super(x, y, z, model);

        body = rigidBody;
    }

    public HardEntity(Tuple position, Model model, RigidBody rigidBody) {
        super(position, model);

        body = rigidBody;
    }

    public void update() {
        position = Tuple.add(position, velocity.toTuple());
        model.addToPosition(velocity);
        body.addToPosition(velocity);
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
