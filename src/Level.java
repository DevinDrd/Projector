import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import Entity.*;
import Math.*;
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
        entitys = new ArrayList<Entity>();
        players = new ArrayList<Player>();

        vertexCount = 0;
        colorCount = 0;
        
        loadMap(pth);
    }

    public void loadMap(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new File(path));

        entitys = new ArrayList<Entity>();
        players = new ArrayList<Player>();

        while(file.hasNextLine()) {
            String type = file.nextLine();

            if (type.equals("player"))
                loadPlayer(file);
            else if (type.equals("triangle"))
                loadTriangle(file);
            else if (type.equals("square"))
                loadSquare(file);
            else if (type.equals("end"))
                break;
            else {
                System.out.println("Unknown type of object \"" + type + "\" in map file " + path);
                System.exit(0);
            }
        }

        file.close();
    }

    private void loadPlayer(Scanner file) {
        if (verbos) System.out.println("Map->Loading Player");

        ArrayList<Float> v = new ArrayList<Float>();
        ArrayList<Float> c = new ArrayList<Float>();

        while (file.hasNextFloat())
            v.add(file.nextFloat());

        file.nextLine();

        while (file.hasNextFloat())
            c.add(file.nextFloat());

        file.nextLine();


        float[] verts = new float[v.size()];
        for (int i = 0; i < verts.length; i++)
            verts[i] = v.get(i);

        float[] cols = new float[c.size()];
        for (int i = 0; i < verts.length; i++)
            cols[i] = c.get(i);

        Model model = new Model(verts, cols);




        Tuple pPosition = new Tuple(file.nextFloat(), file.nextFloat(), file.nextFloat());


        file.nextLine();

        Tuple cPosition = new Tuple(file.nextFloat(), file.nextFloat(), file.nextFloat());
        Vector direction = new Vector(file.nextFloat(), file.nextFloat(), file.nextFloat());
        Vector up = new Vector(file.nextFloat(), file.nextFloat(), file.nextFloat());
        float l = file.nextFloat();
        float r = file.nextFloat();
        float b = file.nextFloat();
        float t = file.nextFloat();
        float n = file.nextFloat();
        float f = file.nextFloat();

        if (file.hasNextLine()) file.nextLine();


        Camera cam = new Camera(cPosition, direction, up, l, r, b, t, n, f);
        Player p = new Player(pPosition, model, cam);
        players.add(p);

        vertexCount += players.get(players.size() - 1).getModel().getVertices().length;
        colorCount += players.get(players.size() - 1).getModel().getColors().length;
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

        vertexCount += entitys.get(entitys.size() - 1).getModel().getVertices().length;
        colorCount += entitys.get(entitys.size() - 1).getModel().getColors().length;
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

        vertexCount += entitys.get(entitys.size() - 1).getModel().getVertices().length;
        colorCount += entitys.get(entitys.size() - 1).getModel().getColors().length;
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

    public float[] getVertices() {
        float[] vertices = new float[vertexCount];

        int mark = 0;

        for (int i = 0; i < players.size(); i++) {
            float[] f = players.get(i).getModel().getVertices();

            for (int j = 0; j < f.length; j++)
                vertices[j + mark] = f[j];

            mark += f.length;
        }

        for (int i = 0; i < entitys.size(); i++) {
            float[] f = entitys.get(i).getModel().getVertices();

            for (int j = 0; j < f.length; j++)
                vertices[j + mark] = f[j];

            mark += f.length;
        }

        return vertices;
    }

    public float[] getColors() {
        float[] colors = new float[colorCount];

        int mark = 0;

        for (int i = 0; i < players.size(); i++) {
            float[] f = players.get(i).getModel().getColors();

            for (int j = 0; j < f.length; j++)
                colors[j + mark] = f[j];

            mark += f.length;
        }

        for (int i = 0; i < entitys.size(); i++) {
            float[] f = entitys.get(i).getModel().getColors();

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
