package Game;

import Game.Entity.*;
import Game.Entity.Hard.*;

import Game.Math.Vector;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    private ArrayList<Entity> entities;

    private static boolean verbos = true;

    public Level() {
        entities = new ArrayList<Entity>();
    }

    public Level(String pth) throws FileNotFoundException {
        entities = new ArrayList<Entity>();
        loadLevel(pth);
    }

    public void loadLevel(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new File(path));

        entities = new ArrayList<Entity>();

        while(file.hasNextLine()) {
            String type = file.nextLine();

            if (type.equals("player")) {
                if (verbos) System.out.println("Map->Loading Player");
                entities.add(new Player(file));
            }
            else if (type.equals("cuboid")) {
                if (verbos) System.out.println("Map->Loading Cuboid");
                entities.add(new Cuboid(file));
            }
            else if (type.equals("clownbox")) {
                if (verbos) System.out.println("Map->Loading ClownBox");
                entities.add(new ClownBox(file));
            }
            else if (type.equals("end"))
                break;
            else {
                System.out.println("Unknown type of object \"" + type + "\" in map file " + path);
                System.exit(0);
            }

            if (file.hasNextLine()) file.nextLine();
        }

        file.close();
    }

    public void update() {
        for (Entity o:entities) o.update();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<HardEntity> getHardEntities() {
        ArrayList<HardEntity> hardEntities = new ArrayList<HardEntity>();

        for (Entity entity: entities)
            if (entity instanceof HardEntity)
                hardEntities.add((HardEntity) entity);

        return hardEntities;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        for (Entity entity: entities)
            if (entity instanceof Player)
                players.add((Player) entity);

        return players;
    }

    public ArrayList<Float> getVertices() {
        ArrayList<Float> verts = new ArrayList<Float>();

        for (Entity e:entities) {
            ArrayList<Vector> points = e.getModel().vertices();
            
            for (Vector v: points) {
                verts.add(v.get(0));
                verts.add(v.get(1));
                verts.add(v.get(2));
            }
        }

        return verts;
    }

    public ArrayList<Float> getColors() {
        ArrayList<Float> cols = new ArrayList<Float>();

        for (Entity e:entities) {
            ArrayList<Vector> colors = e.getModel().colors();
            
            for (Vector v: colors) {
                cols.add(v.get(0));
                cols.add(v.get(1));
                cols.add(v.get(2));
            }
        }

        return cols;
    }

}
