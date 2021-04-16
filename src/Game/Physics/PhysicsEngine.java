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
        Vector mVel = a.velocity().subtract(b.velocity());
        Vector[] face = findFace(points, mVel);

        System.out.println(mVel);
        System.out.println(mVel.magnitude());

        Vector correction = findIntersect(face, mVel);
        float c = (Float.valueOf(mVel.magnitude()).equals(0f)) ?
                        0 : correction.magnitude()/mVel.magnitude() + 0.00001f;

        a.translate(a.velocity().multiply(-c));
        b.translate(b.velocity().multiply(-c));

        a.freeze();
        b.freeze();
    }

    // finds the face of a simplex in 3d that a vector direction passes through
    // only call this method if the origin is in the simplex
    private Vector[] findFace(Simplex simplex, Vector direction) {
        Vector[] points = simplex.toArray();
        ArrayList<Vector> results = new ArrayList<Vector>();

        float min = Float.MAX_VALUE;
        int marker = 0;

        // find where the smallest dot product is
        for (int i = 0; i < 4; i++) {
            float dot = points[i].dot(direction);
            if (dot < min) {
                min = dot;
                marker = i;
            }
        }

        // leave out the smallest dot product
        for (int i = 0; i < 4; i++)
            if (i != marker)
                results.add(points[i]);

        // convert back to Vector[]
        Vector[] v = new Vector[3];
        for (int i = 0; i < 3; i++)
            v[i] = results.get(i);
        
        return v;
    }

    // finds where a line intersects a plane and returns it as a vector
    private Vector findIntersect(Vector[] plane, Vector line) {
        if (plane.length != 3) throw new IllegalArgumentException();
        if (line.equals(new Vector(0, 0, 0))) return line;

        Vector A = plane[0];
        Vector B = plane[1];
        Vector C = plane[2];

        Vector N = A.subtract(B).cross(B.subtract(C)); // Normal to the plane

        float t = N.dot(A)/N.dot(line);
        if (Float.valueOf(t).equals(Float.NaN)) return new Vector(0, 0, 0);

        return new Vector(line.get(0)*t, line.get(1)*t, line.get(2)*t);
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
        return direction.dot(a) >= 0;
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
