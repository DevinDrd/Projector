package Game.Physics;

import Game.Math.Vector;

import java.util.ArrayList;

public class RigidBody {

    private ArrayList<Vector> vertices;

    public RigidBody(ArrayList<Vector> vertices) {
        if (vertices.size() < 1) throw new IllegalArgumentException();

        this. vertices = vertices;
    }

    public Vector findFurthestPoint(Vector direction) {
        Vector maxPoint = null;
        double maxDistance = Double.NEGATIVE_INFINITY;

        for (Vector vertex: vertices) {
            double distance = Vector.dot(vertex, direction);

            if (distance > maxDistance) {
                maxDistance = distance;
                maxPoint = vertex;
            }
        }

        return maxPoint;
    }

    public void addToPosition(Vector displacement) {
        for (int i = 0; i < vertices.size(); i++)
            vertices.set(i, Vector.add(vertices.get(i), displacement));
    }

    public float[] getVertices() {
        float[] f = new float[vertices.size() * 3];

        for (int i = 0; i < vertices.size(); i++)
            for (int j = 0; j < 3; j++)
                f[i*3 + j] = vertices.get(i).get(j);

        return f;
    }

    public String toString() {
        String output = "";

        output += "RigidBody:\n";
        for (Vector v: vertices)
            output += v + "\n";

        return output;
    }
    
}
