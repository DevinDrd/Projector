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
    private ArrayList<Spawner> spawners;

    private TextureMap textureMap;

    public Level(String pth) throws FileNotFoundException, IOException {
        entities = new ArrayList<Entity>();
        spawners = new ArrayList<Spawner>();
        loadLevel(pth);
    }

    public void loadLevel(String path) throws FileNotFoundException, IOException {
        Scanner file = new Scanner(new File(path));
        ArrayList<Object> objects = Parser.level(file, this);
        file.close();

        textureMap = (TextureMap) objects.remove(0);
        for (Object obj:objects) {
            if (obj instanceof Entity) entities.add((Entity) obj);
            else spawners.add((Spawner) obj);
        }
    }

    public void update() {
        for (Spawner s:spawners) s.update();
        for (Entity o:entities) o.update();
        for (int i = entities.size() - 1; i > 1; i--)
            if (entities.get(i).getCount() >= 10)
                entities.remove(i);
    }

    public void addEnitity(Entity e) {
        entities.add(e);
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
