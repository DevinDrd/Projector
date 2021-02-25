package Game.Entity.Hard;

import Game.Entity.Camera;
import Game.Math.*;
import Game.Model.*;
import Game.Physics.RigidBody;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends HardEntity {

    private Camera camera;

    private float speed = 2f; // how quickly player moves
    private float alpha = 15; // rotation speed in degrees

    public Player(float x, float y, float z, Camera camera, RigidBody rigidBody) {
        super(x, y, z, rigidBody);

        this.camera = camera;
    }

    public Player(Tuple position, Camera camera, RigidBody rigidBody) {
        super(position, rigidBody);

        this.camera = camera;
    }

    public Player(float x, float y, float z, Model model, Camera camera, RigidBody rigidBody) {
        super(x, y, z, model, rigidBody);

        this.camera = camera;
    }

    public Player(Tuple position, Model model, Camera camera, RigidBody rigidBody) {
        super(position, model, rigidBody);

        this.camera = camera;
    }

    public Player(Scanner file) throws FileNotFoundException {
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

        model = new Model(verts, cols);


        position = new Tuple(file.nextFloat(), file.nextFloat(), file.nextFloat());
        velocity = new Vector(0, 0, 0);

        file.nextLine();

        Tuple cP = new Tuple(file.nextFloat(), file.nextFloat(), file.nextFloat());
        Vector direction = new Vector(file.nextFloat(), file.nextFloat(), file.nextFloat());
        Vector up = new Vector(file.nextFloat(), file.nextFloat(), file.nextFloat());

        float l = file.nextFloat();
        float r = file.nextFloat();
        float b = file.nextFloat();
        float t = file.nextFloat();
        float n = file.nextFloat();
        float f = file.nextFloat();

        camera = new Camera(cP, direction, up, l, r, b, t, n, f);

        // TODO: FIXME: Changes based on the orientation of the camera
        ArrayList<Vector> list = new ArrayList<Vector>();
        list.add(new Vector(cP.get(0) + l, cP.get(1) + n + 1, cP.get(2) + t));
        list.add(new Vector(cP.get(0) + r, cP.get(1) + n + 1, cP.get(2) + t));
        list.add(new Vector(cP.get(0) + r, cP.get(1) + n + 1, cP.get(2) + b));
        list.add(new Vector(cP.get(0) + l, cP.get(1) + n + 1, cP.get(2) + b));
        list.add(new Vector(cP.get(0), cP.get(1), cP.get(2)));
        body = new RigidBody(list);
    }

    public void update() {
        addToPosition(velocity);
    }

    public void move(Motion m) {
        Vector go = null;

        if (m == Motion.FORWARD)
            go = camera.getDirection(Motion.FORWARD).multiply(speed);
        else if (m == Motion.BACKWARD)
            go = camera.getDirection(Motion.BACKWARD).multiply(speed);
        else if (m == Motion.LEFT)
            go = camera.getDirection(Motion.LEFT).multiply(speed);
        else if (m == Motion.RIGHT)
            go = camera.getDirection(Motion.RIGHT).multiply(speed);
        else if (m == Motion.UP)
            go = camera.getDirection(Motion.UP).multiply(speed);
        else if (m == Motion.DOWN)
            go = camera.getDirection(Motion.DOWN).multiply(speed);
        else
            throw new IllegalArgumentException();

        velocity = velocity.add(go);
    }

    public void stopMove(Motion m) {
        Vector slow = null;

        if (m == Motion.FORWARD)
            slow = camera.getDirection(Motion.FORWARD).multiply(speed).project(velocity);
        else if (m == Motion.BACKWARD)
            slow = camera.getDirection(Motion.BACKWARD).multiply(speed).project(velocity);
        else if (m == Motion.LEFT)
            slow = camera.getDirection(Motion.LEFT).multiply(speed).project(velocity);
        else if (m == Motion.RIGHT)
            slow = camera.getDirection(Motion.RIGHT).multiply(speed).project(velocity);
        else if (m == Motion.UP)
            slow = camera.getDirection(Motion.UP).multiply(speed).project(velocity);
        else if (m == Motion.DOWN)
            slow = camera.getDirection(Motion.DOWN).multiply(speed).project(velocity);
        else
            throw new IllegalArgumentException();

        velocity = velocity.subtract(slow);
    }

    public void rotate(Motion m) {
        Matrix rotation;

        if (m == Motion.SPINUP)
            rotation = Matrix.rotate(camera.getDirection(Motion.RIGHT), alpha);
        else if (m == Motion.SPINDOWN)
            rotation = Matrix.rotate(camera.getDirection(Motion.LEFT), alpha);
        else if (m == Motion.SPINLEFT)
            rotation = Matrix.rotate(camera.getDirection(Motion.UP), alpha);
        else if (m == Motion.SPINRIGHT)
            rotation = Matrix.rotate(camera.getDirection(Motion.DOWN), alpha);
        else if (m == Motion.COUNTERCLOCKWISE)
            rotation = Matrix.rotate(camera.getDirection(Motion.FORWARD), alpha);
        else if (m == Motion.CLOCKWISE)
            rotation = Matrix.rotate(camera.getDirection(Motion.BACKWARD), alpha);
        else
            throw new IllegalArgumentException();

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void addToPosition(Vector d) {
        position = Tuple.add(position, d.toTuple());
        camera.addToPosition(d);
        model.addToPosition(d);
        body.addToPosition(d);
    }

    public void force(Vector f) {
        addVelocity(f);
    }
    
    public void setVelocity(Vector vec) {
        velocity = vec;
        camera.setVelocity(vec);
    }

    public void addVelocity(Vector vec) {
        velocity = Vector.add(velocity, vec);
        camera.setVelocity(velocity);
    }

    public Camera getCamera() {
        return camera;
    }

}
