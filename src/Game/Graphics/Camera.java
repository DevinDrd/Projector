package Game.Graphics;

import Game.Math.*;

public class Camera {

    private Vector p; // camera position in space

    private Vector d; // camera direction
    private Vector u; // camera up orientation

    private float l; // left side of the camera
    private float r; // right side of the camera
    private float b; // bottom side of the camera
    private float t; // top side of the camera
    private float n; // near side of the camera
    private float f; // far side of the camera

    public Camera(Vector position, Vector direction, Vector up, float left, float right, float bottom, float top, float near, float far) {
        p = position;

        d = direction.normalize();
        u = up.normalize();

        l = left;
        r = right;
        b = bottom;
        t = top;
        n = near;
        f = far;
    }

    public void levelOut() {
        d = new Vector(d.get(0), d.get(1), 0).normalize();
        u = new Vector(0, 0, 1);
    }

    public Vector getDirection(Motion direction) {
        if (direction == Motion.FORWARD)
            return d;
        else if (direction == Motion.BACKWARD)
            return d.multiply(-1);
        else if (direction == Motion.LEFT)
            return d.cross(u).normalize().multiply(-1);
        else if (direction == Motion.RIGHT)
             return d.cross(u).normalize();
        else if (direction == Motion.UP)
            return u;
        else if (direction == Motion.DOWN)
            return u.multiply(-1);
        else
            throw new IllegalArgumentException();
    }

    public Matrix getProjection(Projection p) {
        Matrix matrix = null;
        Matrix lookAt = Matrix.lookAt(this.p, d, u);

        if (p == Projection.PERSPECTIVE)
            matrix = Matrix.frustum(l, r, b, t, n, f);
        else if (p == Projection.ORTHOGONAL)
            Matrix.ortho(l, r, b, t, n, f);
        else
            throw new IllegalArgumentException();

        return matrix.multiply(lookAt);
    }

    public void translate(Vector d) {
        p = p.add(d);
    }

    public void rotate(Vector axis) {
        Matrix rotation = Matrix.rotate(new Vector(0, 0, 0), axis, axis.magnitude());

        d = Matrix.rotate(rotation, d).normalize();
        u = Matrix.rotate(rotation, u).normalize();
    }

    public void rotate(Matrix rotation) {
        d = Matrix.rotate(rotation, d).normalize();
        u = Matrix.rotate(rotation, u).normalize();
    }

}
