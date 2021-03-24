package Game;

import Game.Entity.*;
import Game.Entity.Hard.*;

import Game.Graphics.TextureMap;

import Game.Math.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    private ArrayList<Entity> entities;

    private ArrayList<TextureMap> texMaps;

    private static boolean verbos = true;

    public Level() {
        entities = new ArrayList<Entity>();
        texMaps = new ArrayList<TextureMap>();
    }

    public Level(String pth) throws FileNotFoundException, IOException {
        entities = new ArrayList<Entity>();
        texMaps = new ArrayList<TextureMap>();
        loadLevel(pth);
    }

    public void loadLevel(String path) throws FileNotFoundException, IOException {
        Scanner file = new Scanner(new File(path));

        entities = new ArrayList<Entity>();

        while(file.hasNextLine()) {
            String type = file.nextLine();

            if (type.equals("texturemap")) {
                if (verbos) System.out.println("Map->Texture Map");

                String tex = file.nextLine();
                int width = file.nextInt();
                int height = file.nextInt();
                
                texMaps.add(new TextureMap(tex, width, height));
            }
            else if (type.equals("player")) {
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
            else if (type.equals("square")) {
                if (verbos) System.out.println("Map->Loading Square");
                entities.add(new Square(file));
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

    public ArrayList<TextureMap> getTexMaps() {
        return texMaps;
    }

    public ArrayList<Float> getVertices() {
        ArrayList<Float> verts = new ArrayList<Float>();

        for (Entity e:entities) {
            ArrayList<Vector> points = e.getModel().vertices();
            
            for (Vector v: points)
                for (float f: v.getFloats())
                    verts.add(f);
        }

        return verts;
    }

    public ArrayList<Float> getCoords() {
        ArrayList<Float> cols = new ArrayList<Float>();

        for (Entity e:entities) {
            ArrayList<Vector> coords = e.getTexture().getCoords();
            
            for (Vector v: coords)
                for (float f: v.getFloats())
                    cols.add(f);
        }

        return cols;
    }

}
