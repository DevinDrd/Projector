package Game.Entity;

import Game.Math.*;

public class Camera extends Entity {

    private Vector d; // camera direction
    private Vector u; // camera up orientation

    private float l; // left side of the camera
    private float r; // right side of the camera
    private float b; // bottom side of the camera
    private float t; // top side of the camera
    private float n; // near side of the camera
    private float f; // far side of the camera

    public Camera(Tuple position, Vector direction, Vector up, float left, float right, float bottom, float top, float near, float far) {
        super(position);

        d = Vector.normalize(direction);
        u = Vector.normalize(up);

        l = left;
        r = right;
        b = bottom;
        t = top;
        n = near;
        f = far;
    }

    public Vector getDirection(Motion direction) {
        if (direction == Motion.FORWARD)
            return d;
        else if (direction == Motion.BACKWARD)
            return d.multiply(-1);
        else if (direction == Motion.LEFT)
            return Vector.normalize(Vector.cross(d, u)).multiply(-1);
        else if (direction == Motion.RIGHT)
             return Vector.normalize(Vector.cross(d, u));
        else if (direction == Motion.UP)
            return u;
        else if (direction == Motion.DOWN)
            return u.multiply(-1);
        else
            throw new IllegalArgumentException();
    }

    public Matrix getPerspective() {
        Matrix frustum = Matrix.frustum(l, r, b, t, n, f);
        Matrix lookat = Matrix.lookAt(position, d, u);
        return Matrix.multiply(frustum, lookat);
    }

    public Matrix getOrthogonal() {
        Matrix ortho = Matrix.ortho(l, r, b, t, n, f);
        Matrix lookat = Matrix.lookAt(position, d, u);
        return Matrix.multiply(ortho, lookat);
    }

    public void rotate(Vector axis, float alpha) {
        Matrix rotation = Matrix.rotate(axis, alpha);

        d = Matrix.rotate(rotation, d).normalize();
        u = Matrix.rotate(rotation, u).normalize();
    }

    public void rotate(Matrix rotation) {
        d = Matrix.rotate(rotation, d).normalize();
        u = Matrix.rotate(rotation, u).normalize();
    }
    
}
