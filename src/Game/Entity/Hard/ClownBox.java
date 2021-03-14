package Game.Entity.Hard;

import Game.Math.*;
import Game.Model.ClownBoxModel;
import Game.Physics.RigidBody;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;

public class ClownBox extends HardEntity {

    public ClownBox(Vector pos, float width, float height, float length) {
        position = pos;
        velocity = new Vector(0, 0, 0);

        rotationAxis = new Vector(0, 0, 0);

        model = new ClownBoxModel(position, width, height, length);

        buildRigidBody(width, height, length);
    }

    // syntax: position    velocity    rotation    width    height    length
    public ClownBox(Scanner source) throws FileNotFoundException {
        Vector pos = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        Vector vel = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        rotationAxis = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        float width = source.nextFloat();
        float height = source.nextFloat();
        float length = source.nextFloat();

        position = pos;
        velocity = vel;

        model = new ClownBoxModel(pos, width, height, length);

        buildRigidBody(width, height, length);
    }

    private void buildRigidBody(float width, float height, float length) {
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
    }
    
}
