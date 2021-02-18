package Game.Entity.Hard;

import Game.Math.*;
import Game.Model.CuboidModel;

import java.util.Scanner;

import java.io.FileNotFoundException;

public class Cuboid extends HardEntity {

    public Cuboid(Tuple pos, Tuple color, float width, float height, float length) {
        position = pos;
        velocity = new Vector(0, 0, 0);

        model = new CuboidModel(position, color, width, height, length);
    }

    // syntax: position    color    width    height    length
    public Cuboid(Scanner source) throws FileNotFoundException {
        Tuple pos = new Tuple(source.nextFloat(), source.nextFloat(), source.nextFloat());
        Tuple color = new Tuple(source.nextFloat(), source.nextFloat(), source.nextFloat());

        float width = source.nextFloat();
        float height = source.nextFloat();
        float length = source.nextFloat();

        position = new Tuple(pos.get(0), pos.get(1), pos.get(2));
        velocity = new Vector(0, 0, 0);

        model = new CuboidModel(pos, color, width, height, length);
    }
    
}
