package Game;

import Game.Entity.*;
import Game.Graphics.TextureMap;
import Game.Input.Parser;
import Game.Math.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    private ArrayList<Entity> entities;

    private TextureMap textureMap;

    public Level() {
        entities = new ArrayList<Entity>();
    }

    public Level(String pth) throws FileNotFoundException, IOException {
        entities = new ArrayList<Entity>();
        loadLevel(pth);
    }

    public void loadLevel(String path) throws FileNotFoundException, IOException {
        Scanner file = new Scanner(new File(path));
        ArrayList<Object> objects = Parser.level(file);
        file.close();

        textureMap = (TextureMap) objects.remove(0);
        for (Object obj:objects)
            entities.add((Entity) obj);
    }

    public void update() {
        for (Entity o:entities) o.update();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        for (Entity entity: entities)
            if (entity instanceof Player)
                players.add((Player) entity);

        return players;
    }

    public TextureMap getTextureMap() {
        return textureMap;
    }

    public ArrayList<Float> getVertices() {
        ArrayList<Float> verts = new ArrayList<Float>();

        for (Entity e:entities) {
            ArrayList<Vector> points = e.model().vertices();
            
            for (Vector v: points)
                for (float f: v.getFloats())
                    verts.add(f);
        }

        return verts;
    }

    public ArrayList<Float> getCoords() {
        ArrayList<Float> cols = new ArrayList<Float>();

        for (Entity e:entities) {
            ArrayList<Vector> coords = textureMap.convert(e.texture());
            
            for (Vector v: coords)
                for (float f: v.getFloats())
                    cols.add(f);
        }

        return cols;
    }

}
