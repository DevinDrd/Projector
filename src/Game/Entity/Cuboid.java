package Game.Entity;

import Game.Math.*;
import Game.Model.Model;
import Game.Model.CuboidModel;
import Game.Physics.*;

public class Cuboid extends Entity {

    private RigidBody body;

    public Cuboid(float x, float y, float z) {
        super(x, y, z);
    }

    public Cuboid(Tuple position) {
        super(position);
    }

    public Cuboid(float x, float y, float z, CuboidModel model) {
        super(x, y, z);

        this.model = model;
    }

    public Cuboid(Tuple position, Model model) {
        super(position);

        this.model = model;
    }
    
}
