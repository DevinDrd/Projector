package Game.Physics;

import Game.Entity.Entity;
import Game.Entity.Player;
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
            a.translate(a.velocity().multiply(-.05f));
            b.translate(b.velocity().multiply(-.05f));
        }

        if (a instanceof Player || b instanceof Player) {
            a.freeze();
            b.freeze();
        }
        else {
            a.addCount();
            b.addCount();
            bounce(a, b);
        }
    }

    private void bounce(Entity a, Entity b) {
        Vector aVel = a.velocity();
        Vector bVel = b.velocity();
        Vector mVel = aVel.add(bVel);

        if (aVel.magnitude() == 0 && bVel.magnitude() == 0) { // both are not moving
            a.freeze();
            b.freeze();
            a.setExists(false);
            b.setExists(false);
        }
        else if (aVel.magnitude() == 0 || bVel.magnitude() == 0) { // one is not moving
            a.impulse(aVel.multiply(-1));
            a.impulse(reflect(aVel, mVel).multiply(0.8f));
            a.setRotation(a.rotation().multiply(.8f));
            b.impulse(bVel.multiply(-1));
            b.impulse(reflect(bVel, mVel).multiply(0.8f));
            b.setRotation(b.rotation().multiply(.8f));
        }
        else if (aVel.normalize().equals(bVel.normalize())) { // same exact direction
            Entity fast = (aVel.magnitude() >= bVel.magnitude()) ? a : b;
            Entity slow = (aVel.magnitude() < bVel.magnitude()) ? a : b;

            Vector diff = fast.velocity().subtract(slow.velocity());
            fast.impulse(diff.multiply(-1));
            slow.impulse(diff);
        }
        else if (aVel.normalize().equals(bVel.normalize().multiply(-1))) { // exactly opposite directions
            a.impulse(aVel.multiply(-1.8f));
            b.impulse(bVel.multiply(-1.8f));
        }
        else { // general case
            Vector N = mVel.cross(aVel).cross(mVel);

            a.impulse(aVel.multiply(-1));
            a.impulse(reflect(aVel, N).multiply(0.8f));
            a.setRotation(a.rotation().multiply(.8f));
            b.impulse(bVel.multiply(-1));
            b.impulse(reflect(bVel, N).multiply(0.8f));
            b.setRotation(b.rotation().multiply(.8f));
        }
    }

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
