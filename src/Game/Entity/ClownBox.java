package Game.Entity;

import Game.Math.*;
import Game.Model.ClownBoxModel;
import Game.Physics.RigidBody;

import java.util.Scanner;

import java.io.FileNotFoundException;

public class ClownBox extends Entity {

    private RigidBody body;

    public ClownBox(Tuple pos, float width, float height, float length) {
        position = pos;
        velocity = new Vector(0, 0, 0);

        model = new ClownBoxModel(position, width, height, length);
    }

    // syntax: position    width    height    length
    public ClownBox(Scanner source) throws FileNotFoundException {
        Tuple pos = new Tuple(source.nextFloat(), source.nextFloat(), source.nextFloat());

        float width = source.nextFloat();
        float height = source.nextFloat();
        float length = source.nextFloat();

        position = new Tuple(pos.get(0), pos.get(1), pos.get(2));
        velocity = new Vector(0, 0, 0);

        model = new ClownBoxModel(pos, width, height, length);
    }
    
}
