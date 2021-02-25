package Game.Physics;

import Game.Entity.Hard.HardEntity;
import Game.Math.Vector;

import java.util.ArrayList;

// credit to winterdev (https://blog.winter.dev/)
public class PhysicsEngine {

    private Simplex points;
    private Vector direction;

    public boolean collision(RigidBody a, RigidBody b) {
        return gjk(a, b);
    }

    public void update(ArrayList<HardEntity> entities) {
        handleCollisions(entities);
    }

    public void handleCollisions(ArrayList<HardEntity> entities) {
        for (int i = 0; i < entities.size() - 1; i++)
            for (int j = i + 1; j < entities.size(); j++)
                if (gjk(entities.get(i).getBody(), entities.get(j).getBody()))
                    collide(entities.get(i), entities.get(j));
    }

    private void collide(HardEntity a, HardEntity b) {
        a.addToPosition(Vector.multiply(a.getVelocity(), -1));
        a.setVelocity(new Vector(0, 0, 0));

        b.addToPosition(Vector.multiply(b.getVelocity(), -1));
        b.setVelocity(new Vector(0, 0, 0));
    }

    private boolean gjk(RigidBody a, RigidBody b) {
        Vector support = support(a, b, new Vector(1, 0, 0)); // arbitrary direction

        points = new Simplex();
        points.pushFront(support);

        direction = Vector.multiply(support, -1);

        while (true) {
            support = support(a, b, direction);

            if (Vector.dot(support, direction) <= 0)
                return false;

            points.pushFront(support);

            if (nextSimplex())
                return true;
        }
    }

    private Vector support(RigidBody a, RigidBody b, Vector direction) {
        return Vector.subtract(a.findFurthestPoint(direction), b.findFurthestPoint(Vector.multiply(direction, -1)));
    }

    private boolean sameDirection(Vector direction, Vector a) {
        return Vector.dot(direction, a) > 0;
    }

    private boolean nextSimplex() {
        if (points.length() == 2)
            return line();
        else if(points.length() == 3)
            return triangle();
        else if (points.length() == 4)
            return tetrahedron();

        // never here
        return false;
    }

    private boolean line() {
        Vector a = points.get(0);
        Vector b = points.get(1);

        Vector ab = Vector.subtract(b, a);
        Vector ao = Vector.multiply(a, -1);

        if (sameDirection(ab, ao))
            direction = Vector.cross(Vector.cross(ab, ao), ab);
        else {
            points = new Simplex();
            points.pushFront(a);
            direction = ao;
        }

        return false;
    }

    private boolean triangle() {
        Vector a = points.get(0);
        Vector b = points.get(1);
        Vector c = points.get(2);

        Vector ab = Vector.subtract(b, a);
        Vector ac = Vector.subtract(c, a);
        Vector ao = Vector.multiply(a, -1);

        Vector abc = Vector.cross(ab, ac);

        if (sameDirection(Vector.cross(abc, ac), ao)) {
            if (sameDirection(ac, ao)) {
                points = new Simplex();
                points.pushFront(c);
                points.pushFront(a);

                direction = Vector.cross(Vector.cross(ac, ao), ac);
            }
            else {
                points = new Simplex();
                points.pushFront(b);
                points.pushFront(a);

                return line();
            }
        }
        else {
            if (sameDirection(Vector.cross(ab, abc), ao)) {
                points = new Simplex();
                points.pushFront(b);
                points.pushFront(a);

                return line();
            }
            else {
                if (sameDirection(abc, ao)) {
                    direction = abc;
                }
                else {
                    points = new Simplex();
                    points.pushFront(b);
                    points.pushFront(c);
                    points.pushFront(a);

                    direction = Vector.multiply(abc, -1);
                }
            }
        }

        return false;
    }

    private boolean tetrahedron() {
        Vector a = points.get(0);
        Vector b = points.get(1);
        Vector c = points.get(2);
        Vector d = points.get(3);

        Vector ab = Vector.subtract(b, a);
        Vector ac = Vector.subtract(c, a);
        Vector ad = Vector.subtract(d, a);
        Vector ao = Vector.multiply(a, -1);

        Vector abc = Vector.cross(ab, ac);
        Vector acd = Vector.cross(ac, ad);
        Vector adb = Vector.cross(ad, ab);

        if (sameDirection(abc, ao)) {
            points = new Simplex();
            points.pushFront(c);
            points.pushFront(b);
            points.pushFront(a);

            return triangle();
        }
            
        if (sameDirection(acd, ao)) {
            points = new Simplex();
            points.pushFront(d);
            points.pushFront(c);
            points.pushFront(a);

            return triangle();
        }
     
        if (sameDirection(adb, ao)) {
            points = new Simplex();
            points.pushFront(b);
            points.pushFront(d);
            points.pushFront(a);

            return triangle();
        }

        return true;
    }
    
}
