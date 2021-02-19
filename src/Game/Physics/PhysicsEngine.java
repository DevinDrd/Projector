package Game.Physics;

import Game.Entity.Hard.HardEntity;
import Game.Math.Vector;

import java.util.ArrayList;

// credit to winterdev (https://blog.winter.dev/)
public class PhysicsEngine {

    public void handleCollisions(ArrayList<HardEntity> entities) {
        for (int i = 0; i < entities.size() - 1; i++)
            for (int j = i + 1; j < entities.size(); j++)
                if (gjk(entities.get(i).getBody(), entities.get(j).getBody()))
                    collision(entities.get(i), entities.get(j));
    }

    private boolean gjk(RigidBody a, RigidBody b) {
        // TODO: IMPLEMENT
        return true;
    }

    private Vector support(RigidBody a, RigidBody b, Vector direction) {
        return Vector.subtract(a.findFurthestPoint(direction), b.findFurthestPoint(Vector.multiply(direction, -1)));
    }

    private void collision(HardEntity a, HardEntity b) {
        // TODO: IMPLEMENT
    }
    
}
