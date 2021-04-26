package Game.Entity;

import Game.Graphics.Texture;
import Game.Math.Vector;
import Game.Model.SquareModel;
import Game.Physics.RigidBody;

import java.util.ArrayList;

public class Square extends Entity{

    public Square(Vector position, int textX, int textY, float width, float height) {
        this.position = position;
        velocity = new Vector(0, 0, 0);
        acceleration = new Vector(0, 0, 0);

        rotationAxis = new Vector(0, 0, 0);

        mass = 0;
        volume = width*height;

        model = new SquareModel(position, width, height);

        build(width, height, textX, textY);
    }

    private void build(float width, float height, int textX, int textY) {
        float x = position.get(0);
        float y = position.get(1);
        float z = position.get(2);

        float w2 = width/2;
        float h2 = height/2;

        ArrayList<Vector> coords = new ArrayList<Vector>();

        coords.add(new Vector(0, 0));
        coords.add(new Vector(1, 0));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(1, 1));
        coords.add(new Vector(0, 1));
        coords.add(new Vector(0, 0));

        texture = new Texture(coords, textX, textY);

        ArrayList<Vector> points = new ArrayList<Vector>();

        points.add(new Vector(x + w2, y - h2, z));
        points.add(new Vector(x - w2, y - h2, z));
        points.add(new Vector(x + w2, y + h2, z));
        points.add(new Vector(x + w2, y + h2, z));

        body = new RigidBody(position, points);
    }
}
