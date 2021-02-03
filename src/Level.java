import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import Entity.*;
import Model.*;

public class Level {

    private ArrayList<Entity> entitys;
    private ArrayList<Player> players;

    private int vertexCount;
    private int colorCount;

    private static boolean verbos = true;

    public Level() {
        entitys = new ArrayList<Entity>();
        players = new ArrayList<Player>();

        vertexCount = 0;
        colorCount = 0;
    }

    public Level(String pth) throws FileNotFoundException {
        vertexCount = 0;
        colorCount = 0;
        
        loadMap(pth);
    }

    public void loadMap(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new File(path));

        entitys = new ArrayList<Entity>();

        while(file.hasNextLine()) {
            String type = file.nextLine();

            if (type.equals("triangle"))
                loadTriangle(file);
            else if (type.equals("square"))
                loadSquare(file);
            else if (type.equals("end"))
                break;
            else {
                System.out.println("Unknown type of object \"" + type + "\" in map file " + path);
                System.exit(0);
            }

            vertexCount += entitys.get(entitys.size() - 1).getModel().getVertices().length;
            colorCount += entitys.get(entitys.size() - 1).getModel().getColors().length;
        }

        file.close();
    }

    private void loadTriangle(Scanner file) {
        if (verbos) System.out.println("Map->Loading Triangle");

        float[] vertices = new float[9];
        float[] colors = new float[9];

        for (int i = 0; i < vertices.length; i++)
            vertices[i] = file.nextFloat();

        file.nextLine();

        for (int i = 0; i < vertices.length; i++)
            colors[i] = file.nextFloat();

        if (file.hasNextLine()) file.nextLine();

        entitys.add(new Entity(0, 0, 0, new TriangleModel(vertices, colors)));
    }

    private void loadSquare(Scanner file) {
        if (verbos) System.out.println("Map->Loading Square");

        float[] vertices = new float[12];
        float[] colors = new float[12];

        for (int i = 0; i < vertices.length; i++)
            vertices[i] = file.nextFloat();

        file.nextLine();

        for (int i = 0; i < vertices.length; i++)
            colors[i] = file.nextFloat();

        if (file.hasNextLine()) file.nextLine();

        entitys.add(new Entity(0, 0, 0, new SquareModel(vertices, colors)));
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void update() {
        for (Entity o:entitys) o.update();
        for (Player p:players) p.update();
    }

    public ArrayList<Entity> getEntities() {
        return entitys;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    // FIXME: handle player vertices
    public float[] getVertices() {
        float[] vertices = new float[vertexCount];

        int mark = 0;

        for (int i = 0; i < entitys.size(); i++) {
            float[] f = entitys.get(i).getModel().getVertices();

            for (int j = 0; j < f.length; j++)
                vertices[j + mark] = f[j];

            mark += f.length;
        }

        return vertices;
    }

    // FIXME: handle player colors
    public float[] getColors() {
        float[] colors = new float[colorCount];

        int mark = 0;

        for (int i = 0; i < entitys.size(); i++) {
            float[] f = entitys.get(i).getModel().getColors();

            for (int j = 0; j < f.length; j++)
                colors[j + mark] = f[j];
            
            mark += f.length;
        }

        return colors;
    }

    // FIXME: handle player vertices
    public int getVertexCount() {
        return vertexCount;
    }

    // FIXME: handle player colors
    public int getColorCount() {
        return colorCount;
    }
    
}
