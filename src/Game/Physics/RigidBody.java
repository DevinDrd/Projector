package Game.Physics;

import Game.Math.Matrix;
import Game.Math.Vector;

import java.util.ArrayList;

public class RigidBody {

    private Vector position;

    private ArrayList<Vector> vertices;

    public RigidBody(Vector pos, ArrayList<Vector> vertices) {
        if (vertices.size() < 1) throw new IllegalArgumentException();

        position = pos;
        this. vertices = vertices;
    }

    public Vector findFurthestPoint(Vector direction) {
        Vector maxPoint = vertices.get(0);
        double maxDistance = Double.NEGATIVE_INFINITY;

        for (Vector vertex: vertices) {
            double distance = vertex.dot(direction);

            if (distance > maxDistance) {
                maxDistance = distance;
                maxPoint = vertex;
            }
        }

        return maxPoint;
    }

    public void translate(Vector d) {
        position = position.add(d);
        for (int i = 0; i < vertices.size(); i++)
            vertices.set(i, vertices.get(i).add(d));
    }

    public void rotate(Vector axis, float alpha) {
        Matrix rotation = Matrix.rotate(position, axis, alpha);

        for (int i = 0; i < vertices.size(); i++)
            vertices.set(i, Matrix.rotate(rotation, vertices.get(i)));
    }

    public float[] getVertices() {
        float[] f = new float[vertices.size() * 3];

        for (int i = 0; i < vertices.size(); i++)
            for (int j = 0; j < 3; j++)
                f[i*3 + j] = vertices.get(i).get(j);

        return f;
    }

    public Vector getPosition() {
        return position;
    }

    public String toString() {
        String output = "";

        output += "RigidBody:\n";
        for (Vector v: vertices)
            output += v + "\n";

        return output;
    }
    
}
