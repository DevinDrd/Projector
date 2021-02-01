import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import Model.*;

public class Map {

    private ArrayList<Object> objects;

    private int vertexCount;
    private int colorCount;

    private static boolean verbos = true;

    public Map() {
        objects = new ArrayList<Object>();

        vertexCount = 0;
        colorCount = 0;
    }

    public Map(String pth) throws FileNotFoundException {
        vertexCount = 0;
        colorCount = 0;
        
        loadMap(pth);
    }

    public void loadMap(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new File(path));

        objects = new ArrayList<Object>();

        while(file.hasNextLine()) {
            String type = file.nextLine();

            if (type.equals("triangle"))
                loadTriangle(file);
            else if (type.equals("square"))
                loadSquare(file);
            else {
                System.out.println("Unknown type of object \"" + type + "\" in map file " + path);
                System.exit(0);
            }

            vertexCount += objects.get(objects.size() - 1).getModel().getVertices().length;
            colorCount += objects.get(objects.size() - 1).getModel().getColors().length;
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

        objects.add(new Object(0, 0, 0, new TriangleModel(vertices, colors)));
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

        objects.add(new Object(0, 0, 0, new SquareModel(vertices, colors)));
    }

    public void update() {
        for (Object o:objects) o.update();
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public float[] getVertices() {
        float[] vertices = new float[vertexCount];

        int mark = 0;

        for (int i = 0; i < objects.size(); i++) {
            float[] f = objects.get(i).getModel().getVertices();

            for (int j = 0; j < f.length; j++)
                vertices[j + mark] = f[j];

            mark += f.length;
        }

        return vertices;
    }

    public float[] getColors() {
        float[] colors = new float[colorCount];

        int mark = 0;

        for (int i = 0; i < objects.size(); i++) {
            float[] f = objects.get(i).getModel().getColors();

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
