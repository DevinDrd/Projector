package Game.Entity;

import Game.Graphics.Texture;
import Game.Math.*;
import Game.Model.CuboidModel;
import Game.Physics.RigidBody;

import java.util.ArrayList;

public class Cuboid extends Entity {

    public Cuboid(Vector pos, int textX, int textY, float width, float height, float length) {
        position = pos;
        velocity = new Vector(0, 0, 0);
        acceleration = new Vector(0, 0, 0);

        rotationAxis = new Vector(0, 0, 0);
        mass = 0;

        model = new CuboidModel(position, width, height, length);
        
        buildRigidBody(width, height, length, textX, textY);
    }

    private void buildRigidBody(float width, float height, float length, int textX, int textY) {
        float x = position.get(0);
        float y = position.get(1);
        float z = position.get(2);

        float w2 = width/2;
        float h2 = height/2;
        float l2 = length/2;

        ArrayList<Vector> coords = new ArrayList<Vector>();

        coords.add(new Vector(0, 0));
        coords.add(new Vector(1, 0));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(0, 1));
        coords.add(new Vector(0, 0));

        coords.add(new Vector(0, 0));
        coords.add(new Vector(1, 0));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(0, 1));
        coords.add(new Vector(0, 0));

        coords.add(new Vector(0, 0));
        coords.add(new Vector(1, 0));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(0, 1));
        coords.add(new Vector(0, 0));

        coords.add(new Vector(0, 0));
        coords.add(new Vector(1, 0));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(0, 1));
        coords.add(new Vector(0, 0));

        coords.add(new Vector(0, 0));
        coords.add(new Vector(1, 0));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(0, 1));
        coords.add(new Vector(0, 0));

        coords.add(new Vector(0, 0));
        coords.add(new Vector(1, 0));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(0, 1));
        coords.add(new Vector(0, 0));

        texture = new Texture(coords, textX, textY);

        ArrayList<Vector> points = new ArrayList<Vector>();

        points.add(new Vector(x-w2, y+h2, z-l2));
        points.add(new Vector(x+w2, y+h2, z-l2));
        points.add(new Vector(x+w2, y-h2, z-l2));
        points.add(new Vector(x-w2, y-h2, z-l2));
        points.add(new Vector(x-w2, y+h2, z+l2));
        points.add(new Vector(x+w2, y+h2, z+l2));
        points.add(new Vector(x+w2, y-h2, z+l2));
        points.add(new Vector(x-w2, y-h2, z+l2));

        body = new RigidBody(position, points);


    }
    
}
