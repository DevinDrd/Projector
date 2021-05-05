package Game;

import Game.Entity.Cuboid;
import Game.Math.Vector;

import java.util.Random;

public class Spawner {
// random cuboid spawner

    private Random random;
    private Level level;

    private int period;
    private int ticker;

    private int cols;   // texturemap columns
    private int rows;   // texturemap rows

    public Spawner(Level level, int period) {
        this.level = level;
        random = new Random();

        this.period = period;
        ticker = 0;

        cols = level.getTextureMap().getCols();
        rows = level.getTextureMap().getRows();
    }

    public void update() {
        if (ticker >= period) {
            ticker = 0;

            spawn();
        }
        else
            ticker++;
    }

    private void spawn() {
        Vector position = new Vector(0, 0, 500);
        int tX = random.nextInt(rows);
        int tY = random.nextInt(cols);

        float size = random.nextFloat()*50 + 15;

        Cuboid cube = new Cuboid(position, tX, tY, size, size, size);

        float x = random.nextFloat()*10 - 5;
        float y = random.nextFloat()*10 - 5;
        float z = random.nextFloat()*10 - 5;

        cube.setVelocity(new Vector(x, y, z));

        x = random.nextFloat()*10 - 5;
        y = random.nextFloat()*10 - 5;
        z = random.nextFloat()*10 - 5;

        cube.setRotation(new Vector(x, y, z));
        cube.setMass(random.nextFloat()*50 + 15);

        level.addEnitity(cube);
    }
    
}
