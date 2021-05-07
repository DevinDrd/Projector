package Game;

import Game.Entity.Cuboid;
import Game.Math.Vector;

public class Spawner {
// random cuboid spawner

    private Level level;

    private Vector position;
    private Vector velocity;
    private Vector rotation;

    private float size;
    private float mass;

    private int period;
    private int ticker;

    private int col;
    private int row;

    public Spawner(Level level, int period, Vector position, Vector velocity, Vector rotation, float size, float mass, int row, int col) {
        this.level = level;

        this.position = position;
        this.velocity = velocity;
        this.rotation = rotation;

        this.size = size;
        this.mass = mass;

        this.col = col;
        this.row = row;

        this.period = period;
        ticker = 0;
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
        Cuboid cube = new Cuboid(position, row, col, size, size, size);

        cube.setVelocity(velocity);
        cube.setRotation(rotation);
        cube.setMass(mass);

        level.addEnitity(cube);
    }
    
}
