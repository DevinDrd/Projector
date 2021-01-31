import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Map {

    private ArrayList<Object> objects;

    public Map() {
        objects = new ArrayList<Object>();
    }

    public Map(String pth) throws FileNotFoundException {
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
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }
    
}
