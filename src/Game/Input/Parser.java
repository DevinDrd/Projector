package Game.Input;

import Game.Entity.*;
import Game.Graphics.Camera;
import Game.Graphics.Texture;
import Game.Graphics.TextureMap;
import Game.Math.Vector;
import Game.Model.*;
import Game.Physics.RigidBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static boolean verbos = false;

    public static ArrayList<Object> level(Scanner source) throws IOException {
        ArrayList<Object> objects = new ArrayList<Object>();

        String type = source.nextLine();

        if (type.equals("texturemap"))
            objects.add(textureMap(source));
        else
            error("TextureMap was not specified at beginning of level file");

        while(source.hasNextLine()) {
            type = source.nextLine();

            if (type.equals("player"))
                objects.add(player(source));

            else if (type.equals("cuboid"))
                objects.add(cuboid(source));

            else if (type.equals("square"))
                objects.add(square(source));

            else if (type.equals("end"))
                break;
            else
                error("Unknown type of object \"" + type + "\" in map file ");

            if (source.hasNextLine()) source.nextLine();
        }

        return objects;
    }

    public static TextureMap textureMap(Scanner source) throws IOException {
        if (verbos) System.out.println("Level-> Loading Texture Map");
    
        String tex = source.nextLine();
        int width = source.nextInt();
        int height = source.nextInt();

        source.nextLine();
        
        return new TextureMap(tex, width, height);
    }

    // player: position    velocity    rotation    mass
    // camera: direction    up    l r b t n f
    // model: coords
    // texture: x y    coords    
    public static Entity player(Scanner source) {
        if (verbos) System.out.println("Level->Loading Player");

        Vector position = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        Vector velocity = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        Vector rotationAxis = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        float mass = source.nextFloat();

        source.nextLine();


        // camera
        Vector direction = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        Vector up = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        float l = source.nextFloat();
        float r = source.nextFloat();
        float b = source.nextFloat();
        float t = source.nextFloat();
        float n = source.nextFloat();
        float f = source.nextFloat();
        source.nextLine();

        Camera camera = new Camera(position, direction, up, l, r, b, t, n, f);


        // model
        ArrayList<Vector> verts = new ArrayList<Vector>();

        while (source.hasNextFloat())
            verts.add(new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat()));
        source.nextLine();

        Model model = new Model(position, verts);


        // texture
        ArrayList<Vector> coords = new ArrayList<Vector>();
        int textX = source.nextInt();
        int textY = source.nextInt();

        while (source.hasNextFloat())
            coords.add(new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat()));

        Texture texture = new Texture(coords, textX, textY);


        // rigidbody
        // FIXME: Changes based on the orientation of the camera
        ArrayList<Vector> list = new ArrayList<Vector>();
        list.add(new Vector(position.get(0) + l, position.get(1) + n + 1, position.get(2) + t));
        list.add(new Vector(position.get(0) + r, position.get(1) + n + 1, position.get(2) + t));
        list.add(new Vector(position.get(0) + r, position.get(1) + n + 1, position.get(2) + b));
        list.add(new Vector(position.get(0) + l, position.get(1) + n + 1, position.get(2) + b));
        list.add(new Vector(position.get(0), position.get(1), position.get(2)));

        RigidBody body = new RigidBody(position, list);

        Player player = new Player(position, camera, model, texture, body);
        player.setVelocity(velocity);
        player.setRotation(rotationAxis);
        player.setMass(mass);

        return player;
    }
    
    // position    velocity    rotation    width    height    length    texture: x y
    public static Entity cuboid(Scanner source) {
        if (verbos) System.out.println("Map->Loading Cuboid");
        
        Vector position = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        Vector velocity = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        Vector rotationAxis = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        float mass = source.nextFloat();

        float width = source.nextFloat();
        float height = source.nextFloat();
        float length = source.nextFloat();

        int textX = source.nextInt();
        int textY = source.nextInt();

        Cuboid cuboid = new Cuboid(position, textX, textY, width, height, length);
        cuboid.setVelocity(velocity);
        cuboid.setRotation(rotationAxis);
        cuboid.setMass(mass);

        return cuboid;
    }

    // position    velocity    rotation    width    height    texture: x y
    public static Entity square(Scanner source) {
        if (verbos) System.out.println("Map->Loading Square");

        Vector position = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());
        Vector velocity = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        Vector rotationAxis = new Vector(source.nextFloat(), source.nextFloat(), source.nextFloat());

        float width = source.nextFloat();
        float height = source.nextFloat();

        int textX = source.nextInt();
        int textY = source.nextInt();

        Square square = new Square(position, textX, textY, width, height);
        square.setVelocity(velocity);
        square.setRotation(rotationAxis);

        return square;
    }

    private static void error(String error) {
        System.out.println(error);
        System.exit(0);
    }
}
