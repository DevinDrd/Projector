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

    private float force = 2f; // force value that acts on player

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

        System.out.println(body);
    }

    public void update() {
        addToPosition(velocity);
    }

    public void addToPosition(Vector d) {
        position = Tuple.add(position, d.toTuple());
        camera.addToPosition(d);
        model.addToPosition(d);
        body.addToPosition(d);
    }

    public void forwardForce(float f) {
        addVelocity(Vector.multiply(camera.getDirectionForward(), f));
    }

    public void backwardForce(float f) {
        subtractVelocity(Vector.multiply(camera.getDirectionForward(), f));
    }
    
    public void leftForce(float f) {
        subtractVelocity(Vector.multiply(camera.getDirectionRight(), f));
    }

    public void rightForce(float f) {
        addVelocity(Vector.multiply(camera.getDirectionRight(), f));
    }

    public void upForce(float f) {
        addVelocity(Vector.multiply(camera.getDirectionUp(), f));
    }

    public void downForce(float f) {
        subtractVelocity(Vector.multiply(camera.getDirectionUp(), f));
    }

    public void lookUp(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionRight(), alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void lookDown(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionRight(), -alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void lookLeft(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionUp(), alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void lookRight(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionUp(), -alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void spinCounterclockwise(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionForward(), -alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }

    public void spingClockwise(float alpha) {
        Matrix rotation = Matrix.rotate(camera.getDirectionForward(), alpha);

        velocity = Matrix.rotate(rotation, velocity);
        camera.rotate(rotation);
    }
    
    public void setVelocity(Vector vec) {
        velocity = vec;
        camera.setVelocity(vec);
    }

    public void addVelocity(Vector vec) {
        velocity = Vector.add(velocity, vec);
        camera.setVelocity(velocity);
    }

    public void subtractVelocity(Vector vec) {
        velocity = Vector.subtract(velocity, vec);
        camera.setVelocity(velocity);
    }

    public void setForce(float f) {
        force = f;
    }

    public Camera getCamera() {
        return camera;
    }

    public float getForce() {
        return force;
    }
    
}
