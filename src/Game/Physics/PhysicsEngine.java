package Game.Physics;

import Game.Entity.Entity;
import Game.Math.Vector;

import java.util.ArrayList;

public class PhysicsEngine {

    private Simplex points;
    private Vector direction;

    public static float G = .01f;

    public void update(ArrayList<Entity> entities) {
        gravity(entities);
        handleCollisions(entities);
    }

    private void gravity(ArrayList<Entity> entities) {
        for (int i = 0; i < entities.size(); i++)
            gravity(entities.get(i));
    }

    private void gravity(Entity entity) {
        if (entity.acceleration().get(2) > -.12)
            entity.force(new Vector(0, 0, entity.mass()*(-G)));
    }

    public void handleCollisions(ArrayList<Entity> entities) {
        for (int i = 0; i < entities.size() - 1; i++)
            for (int j = i + 1; j < entities.size(); j++)
                if (gjk(entities.get(i).body(), entities.get(j).body()))
                    collide(entities.get(i), entities.get(j));
    }

    private void collide(Entity a, Entity b) {
        // make sure objects aren't overlapping
        for (int i = 0; i < 100 && gjk(a.body(), b.body()); i++) {
            a.translate(a.velocity().multiply(-.01f));
            b.translate(b.velocity().multiply(-.01f));
        }

        bounce(a, b);
    }

    private void bounce(Entity a, Entity b) {
        Vector aVel = a.velocity();
        Vector bVel = b.velocity();

        if (aVel.normalize().equals(bVel.normalize())) { // push
            System.out.println("1");
            if (aVel.magnitude() > bVel.magnitude()) {
                Vector diff = aVel.subtract(bVel).divide(2);
                b.impulse(diff);
                a.impulse(diff.multiply(-1.1f));
            }
            else {
                Vector diff = bVel.subtract(aVel).divide(2);
                a.impulse(diff);
                b.impulse(diff.multiply(-1.1f));
            }
        }
        else { // bounce
            if (aVel.normalize().multiply(-1).equals(bVel.normalize())) { // directly opposite
                Vector cVel = aVel.add(bVel);
                a.impulse(aVel.multiply(-1));
                a.impulse(reflect(aVel, cVel).multiply(0.8f));
                a.setRotation(a.rotation().multiply(.8f));
                b.impulse(bVel.multiply(-1));
                b.impulse(reflect(bVel, cVel).multiply(0.8f));
                b.setRotation(b.rotation().multiply(.8f));
            }
            else if (aVel.magnitude() == 0 && bVel.magnitude() == 0) { // if both are not moving, freeze them
                a.freeze();
                b.freeze();
            }
            else if (aVel.magnitude() == 0 || bVel.magnitude() == 0) { // if one or the other is not moving
                Vector cVel = aVel.add(bVel);

                a.impulse(aVel.multiply(-1));
                a.impulse(reflect(aVel, cVel).multiply(0.8f));
                a.setRotation(a.rotation().multiply(.8f));
                b.impulse(bVel.multiply(-1));
                b.impulse(reflect(bVel, cVel).multiply(0.8f));
                b.setRotation(b.rotation().multiply(.8f));
            }
            else { // they are both moving
                Vector cVel = aVel.add(bVel);
                Vector N = cVel.cross(aVel).cross(cVel);

                a.impulse(aVel.multiply(-1));
                a.impulse(reflect(aVel, N).multiply(0.8f));
                a.setRotation(a.rotation().multiply(.8f));
                b.impulse(bVel.multiply(-1));
                b.impulse(reflect(bVel, N).multiply(0.8f));
                b.setRotation(b.rotation().multiply(.8f));
            }
        }
    }

    // private void bounce(Entity a, Entity b) {
    //     Vector aVel = a.velocity();
    //     Vector bVel = b.velocity();

    //     if (aVel.normalize().equals(bVel.normalize())) { // push
    //         System.out.println("1");
    //         if (aVel.magnitude() > bVel.magnitude()) {
    //             Vector diff = aVel.subtract(bVel).divide(2);
    //             b.impulse(diff);
    //             a.impulse(diff.multiply(-1.1f));
    //         }
    //         else {
    //             Vector diff = bVel.subtract(aVel).divide(2);
    //             a.impulse(diff);
    //             b.impulse(diff.multiply(-1.1f));
    //         }
    //     }
    //     else { // bounce
    //         Vector[] face;
    //         if (a.volume() > b.volume())
    //             face = findFace(b.position().subtract(a.position()), a);
    //         else
    //             face = findFace(a.position().subtract(b.position()), b);

    //         Vector N = face[0].subtract(face[1]).cross(face[0].subtract(face[2]));

    //         a.impulse(aVel.multiply(-1));
    //         a.impulse(reflect(aVel, N).multiply(.8f));
    //         b.impulse(bVel.multiply(-1));
    //         b.impulse(reflect(bVel, N).multiply(.8f));
    //     }
    // }

    // private Vector reflect(Vector d, Vector n) {
    //     Vector r = n.normalize().multiply(2*d.dot(n.normalize()));
    //     return d.subtract(r);
    // }

    // private Vector[] findFace(Vector d, Entity a) {
    //     ArrayList<Vector> points = a.body().points();
    //     d = d.normalize();

    //     for (int i = 0; i < points.size(); i++)
    //         points.set(i, points.get(i).normalize());

    //     while (points.size() > 3) {
    //         float min = points.get(0).dot(d);;
    //         int marker = 0;

    //         for (int i = 1; i < points.size(); i++) {
    //             float dot = points.get(i).dot(d);
    //             if (dot < min) {
    //                 min = dot;
    //                 marker = i;
    //             }
    //         }

    //         points.remove(marker);
    //     }

    //     Vector[] vec = new Vector[3];
    //     for (int i = 0; i < points.size(); i++)
    //         vec[i] = points.get(i);

    //     return vec;
    // }

    private Vector reflect(Vector d, Vector n) {
        Vector r = n.normalize().multiply(2*d.dot(n.normalize()));
        return d.subtract(r);
    }

    // credit to winterdev (https://blog.winter.dev/)
    private boolean gjk(RigidBody a, RigidBody b) {
        Vector support = support(a, b, new Vector(1, 0, 0)); // arbitrary direction

        points = new Simplex();
        points.pushFront(support);

        direction = support.multiply(-1);

        while (true) {
            support = support(a, b, direction);

            if (support.dot(direction) <= 0)
                return false;

            points.pushFront(support);

            if (nextSimplex())
                return true;
        }
    }

    private Vector support(RigidBody a, RigidBody b, Vector direction) {
        return a.findFurthestPoint(direction).subtract(b.findFurthestPoint(direction.multiply(-1)));
    }

    private boolean sameDirection(Vector direction, Vector a) {
        return direction.dot(a) > 0;
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

        Vector ab = b.subtract(a);
        Vector ao = a.multiply(-1);

        if (sameDirection(ab, ao))
            direction = ab.cross(ao).cross(ab);
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

        Vector ab = b.subtract(a);
        Vector ac = c.subtract(a);
        Vector ao = a.multiply(-1);

        Vector abc = ab.cross(ac);

        if (sameDirection(abc.cross(ac), ao)) {
            if (sameDirection(ac, ao)) {
                points = new Simplex();
                points.pushFront(c);
                points.pushFront(a);

                direction = ac.cross(ao).cross(ac);
            }
            else {
                points = new Simplex();
                points.pushFront(b);
                points.pushFront(a);

                return line();
            }
        }
        else {
            if (sameDirection(ab.cross(abc), ao)) {
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

                    direction = abc.multiply(-1);
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

        Vector ab = b.subtract(a);
        Vector ac = c.subtract(a);
        Vector ad = d.subtract(a);
        Vector ao = a.multiply(-1);

        Vector abc = ab.cross(ac);
        Vector acd = ac.cross(ad);
        Vector adb = ad.cross(ab);

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
