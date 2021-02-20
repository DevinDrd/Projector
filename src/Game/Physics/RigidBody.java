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

    public void addVector(Vector displacement) {
        for (int i = 0; i < vertices.size(); i++)
            vertices.set(i, Vector.add(vertices.get(i), displacement));
    }
    
}
