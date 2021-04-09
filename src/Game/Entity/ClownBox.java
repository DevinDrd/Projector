package Game.Entity;

import Game.Graphics.Texture;
import Game.Math.*;
import Game.Model.ClownBoxModel;
import Game.Physics.RigidBody;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;

public class ClownBox extends Entity {

    public ClownBox(Vector pos, int textX, int textY, float width, float height, float length) {
        position = pos;
        velocity = new Vector(0, 0, 0);
        acceleration = new Vector(0, 0, 0);

        rotationAxis = new Vector(0, 0, 0);
        
        mass = 0;

        model = new ClownBoxModel(position, width, height, length);

        buildRigidBody(width, height, length, textX, textY);
    }

    // syntax: position    velocity    rotation    width    height    length    texture: x y
    public ClownBox(Scanner source) throws FileNotFoundException {
        position = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        velocity = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        acceleration = new Vector(0, 0, 0);

        rotationAxis = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        mass = source.nextFloat();

        float width = source.nextFloat();
        float height = source.nextFloat();
        float length = source.nextFloat();

        int textX = source.nextInt();
        int textY = source.nextInt();

        model = new ClownBoxModel(position, width, height, length);

        buildRigidBody(width, height, length, textX, textY);
    }

    private void buildRigidBody(float width, float height, float length, int textX, int textY) {
        float x = position.get(0);
        float y = position.get(1);
        float z = position.get(2);

        float w2 = width/2;
        float h2 = height/2;
        float l2 = length/2;

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
    }
    
}
