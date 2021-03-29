package Game.Physics;

import Game.Entity.Entity;
import Game.Entity.Hard.Player;
import Game.Entity.Hard.HardEntity;
import Game.Math.Vector;

import java.util.ArrayList;

// credit to winterdev (https://blog.winter.dev/)
public class PhysicsEngine {

    public static final float G = 0.00006674f;

    private Simplex points;
    private Vector direction;

    public void update(ArrayList<HardEntity> entities) {
        gravity(entities);
        handleCollisions(entities);
    }

    private void gravity(ArrayList<HardEntity> entities) {
        for (int i = 0; i < entities.size() - 1; i++)
            for (int j = i + 1; j < entities.size(); j++)
                gravity(entities.get(i), entities.get(j));
    }

    // TODO: TEST
    private void gravity(Entity a, Entity b) {
        if (a instanceof Player || b instanceof Player)
            return;

        Vector r2 = b.position().subtract(a.position());
        r2 = r2.multiply(r2);

        float Gab = G*a.mass()*b.mass();

        float fX = (r2.get(0) != 0) ? Gab/r2.get(0): 0;
        float fY = (r2.get(1) != 0) ? Gab/r2.get(1): 0;
        float fZ = (r2.get(2) != 0) ? Gab/r2.get(2): 0;

        System.out.println(fZ);


        a.force(new Vector(fX, fY, fZ));
        b.force(new Vector(-fX, -fY, -fZ));
    }

    public void handleCollisions(ArrayList<HardEntity> entities) {
        for (int i = 0; i < entities.size() - 1; i++)
            for (int j = i + 1; j < entities.size(); j++)
                if (gjk(entities.get(i).getBody(), entities.get(j).getBody()))
                    collide(entities.get(i), entities.get(j));
    }

    private void collide(HardEntity a, HardEntity b) {
        a.translate(a.velocity().multiply(-1));
        a.setVelocity(new Vector(0, 0, 0));
        a.setAcceleration(new Vector(0, 0, 0));
        a.setRotation(new Vector(0, 0, 0));

        b.translate(b.velocity().multiply(-1));
        b.setVelocity(new Vector(0, 0, 0));
        b.setAcceleration(new Vector(0, 0, 0));
        b.setRotation(new Vector(0, 0, 0));
    }

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
