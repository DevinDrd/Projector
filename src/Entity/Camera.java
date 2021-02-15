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


    // usefull for testing map.txt
    // Camera c = new Camera(new Tuple(0, 0, 4f), new Vector(0, 0, -1), new Vector(0, 1, 0), -5, 5, -5, 5, 2, 10);          TODO:DELETE
    // Camera o = new Camera(new Tuple(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0), -10, 10, -10, 10, -10, 10);     TODO:DELETE


    public Camera(Tuple position, Vector direction, Vector up, float left, float right, float bottom, float top, float near, float far) {
        super(position);

        // TODO if (Vector.dot(d, u) != 0) throw new IllegalArgumentException();

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
        return Vector.cross(d, u);
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
        Matrix rotation = Matrix.rotate(axis, alpha);
        Vector dh = Vector.homogenize(d);
        dh = Matrix.multiply(rotation, dh.toMatrix()).toVector();
    }
    
}
