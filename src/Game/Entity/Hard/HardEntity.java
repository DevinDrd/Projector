package Game.Entity.Hard;

import Game.Entity.Entity;
import Game.Math.Tuple;
import Game.Model.Model;
import Game.Physics.RigidBody;

public class HardEntity extends Entity {

    private RigidBody body;

    public HardEntity(){};

    public HardEntity(float x, float y, float z) {
        super(x, y, z);
    }

    public HardEntity(Tuple position) {
        super(position);
    }

    public HardEntity(float x, float y, float z, Model model) {
        super(x, y, z, model);
    }

    public HardEntity(Tuple position, Model model) {
        super(position, model);
    }

    public RigidBody getBody() {
        return body;
    }
    
}
