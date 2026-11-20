package edu.team.kmeans.sequential;

import edu.team.kmeans.api.AssignmentStrategy;
import edu.team.kmeans.model.Point;

import java.util.List;

public class SequentialAssignmentStrategy implements AssignmentStrategy {
    @Override
    public void assign(List<Point> points, List<Point> centroids, int[] assignments) {

        // for each point, find the distance from a centroid
        for (int i = 0; i < points.size(); i++) {
            // assume cluster 0 is the nearest
            int nearestCluster = 0;
            double minDistance = Double.POSITIVE_INFINITY;

            for (int j = 0; j < centroids.size(); j++) {
                double distance = squaredDistance(points.get(i), centroids.get(j));

                // the centroid is assigned the closest cluster and the minimum distance becomes the distance from centroid
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCluster = j;
                }
            }
            assignments[i] = nearestCluster;
        }
    }

    private double squaredDistance(Point a, Point b) {
        double dx = a.x() - b.x();
        double dy = a.y() - b.y();

        return dx * dx + dy * dy;
    }
}
