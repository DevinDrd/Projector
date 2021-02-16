package Entity;

import Math.*;

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

    public Vector getDirectionForward() {
        return d;
    }

    public Vector getDirectionRight() {
        return Vector.normalize(Vector.cross(d, u));
    }

    public Vector getDirectionUp() {
        return u;
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
        Matrix rotation;
        Vector dh;

        rotation = Matrix.rotate(axis, alpha);
        dh = d.homogenize();
        dh = Matrix.multiply(rotation, dh.toMatrix()).toVector();
        dh = dh.perspectiveDivide();
        dh = Vector.normalize(dh);

        if (dh.equals(u)) {
            rotation = Matrix.rotate(axis, alpha+.001f);
            dh = d.homogenize();
            dh = Matrix.multiply(rotation, dh.toMatrix()).toVector();
            dh = dh.perspectiveDivide();
            dh = Vector.normalize(dh);
        }

        d = dh;
        System.out.println(d);
    }
    
}
