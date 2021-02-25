package Game;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import Game.Entity.*;
import Game.Entity.Hard.*;

public class Level {

    private ArrayList<Entity> entities;

    private int vertexCount;
    private int colorCount;

    private static boolean verbos = true;

    public Level() {
        init();
    }

    public Level(String pth) throws FileNotFoundException {
        init();
        loadLevel(pth);
    }

    private void init() {
        entities = new ArrayList<Entity>();

        vertexCount = 0;
        colorCount = 0;
    }
 
    public void loadLevel(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new File(path));

        entities = new ArrayList<Entity>();

        while(file.hasNextLine()) {
            String type = file.nextLine();

            if (type.equals("player"))
                loadPlayer(file);
            else if (type.equals("cuboid"))
                loadCuboid(file);
            else if (type.equals("clownbox"))
                loadClownBox(file);
            else if (type.equals("end"))
                break;
            else {
                System.out.println("Unknown type of object \"" + type + "\" in map file " + path);
                System.exit(0);
            }

            vertexCount += entities.get(entities.size() - 1).getModel().getVertices().length;
            colorCount += entities.get(entities.size() - 1).getModel().getColors().length;

            if (file.hasNextLine()) file.nextLine();
        }

        file.close();
    }

    private void loadPlayer(Scanner file) throws FileNotFoundException {
        if (verbos) System.out.println("Map->Loading Player");

        entities.add(new Player(file));
    }

    private void loadCuboid(Scanner file) throws FileNotFoundException {
        if (verbos) System.out.println("Map->Loading Cuboid");

        entities.add(new Cuboid(file));
    }

    private void loadClownBox(Scanner file) throws FileNotFoundException {
        if (verbos) System.out.println("Map->Loading ClownBox");

        entities.add(new ClownBox(file));
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

    public float[] getVertices() {
        float[] vertices = new float[vertexCount];

        int mark = 0;

        for (int i = 0; i < entities.size(); i++) {
            float[] f = entities.get(i).getModel().getVertices();

            for (int j = 0; j < f.length; j++)
                vertices[j + mark] = f[j];

            mark += f.length;
        }

        return vertices;
    }

    public float[] getColors() {
        float[] colors = new float[colorCount];

        int mark = 0;

        for (int i = 0; i < entities.size(); i++) {
            float[] f = entities.get(i).getModel().getColors();

            for (int j = 0; j < f.length; j++)
                colors[j + mark] = f[j];
            
            mark += f.length;
        }

        return colors;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getColorCount() {
        return colorCount;
    }
}
