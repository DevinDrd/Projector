import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Map {

    private ArrayList<Object> objects;

    private int vertexCount;
    private int colorCount;

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

            if (type.equals("triangle")) {
                loadTriangle(file);
            }
            else {
                System.out.println("Unknown type of object \"" + type + "\" in map file " + path);
                System.exit(0);
            }
        }

        file.close();
    }

    private void loadTriangle(Scanner file) {
        float[] vertices = new float[9];
        float[] colors = new float[9];

        for (int i = 0; i < vertices.length; i++)
            vertices[i] = file.nextFloat();

        file.nextLine();

        for (int i = 0; i < vertices.length; i++)
            colors[i] = file.nextFloat();

        if (file.hasNextLine()) file.nextLine();

        objects.add(new Object(0, 0, 0, new Model(vertices, colors)));

        vertexCount += 9;
        colorCount += 9;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public float[] getVertices() {
        float[] vertices = new float[vertexCount];

        for (int i = 0; i < objects.size(); i++) {
            float[] f = objects.get(i).getModel().getVertices();

            for (int j = 0; j < f.length; j++) {
                vertices[j + i*f.length] = f[j];
            }
        }

        return vertices;
    }

    public float[] getColors() {
        float[] colors = new float[colorCount];

        for (int i = 0; i < objects.size(); i++) {
            float[] f = objects.get(i).getModel().getColors();

            for (int j = 0; j < f.length; j++) {
                colors[j + i*f.length] = f[j];
            }
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
