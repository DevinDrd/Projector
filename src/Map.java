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
                System.out.println("Unknown type of object \"" + type + "\" if map file " + path);
                System.exit(0);
            }
        }

        file.close();
    }

    private void loadTriangle(Scanner file) {
        
    }
    
}
