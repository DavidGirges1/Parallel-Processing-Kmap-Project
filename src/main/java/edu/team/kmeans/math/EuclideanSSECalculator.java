package edu.team.kmeans.math;

import edu.team.kmeans.api.SSECalculator;
import edu.team.kmeans.model.Point;
import java.util.List;
import java.util.Objects;

/**
 * Calculates the sum of squared Euclidean errors (SSE) for a supplied
 * point-to-centroid assignment without reassigning points or mutating inputs.
 */
public final class EuclideanSSECalculator implements SSECalculator {

    @Override
    public double calculate(List<Point> points, int[] assignments, List<Point> centroids) {
        Objects.requireNonNull(points, "points");
        Objects.requireNonNull(assignments, "assignments");
        Objects.requireNonNull(centroids, "centroids");

        if (points.isEmpty()) {
            throw new IllegalArgumentException("Points must not be empty");
        }
        if (centroids.isEmpty()) {
            throw new IllegalArgumentException("Centroids must not be empty");
        }
        if (assignments.length != points.size()) {
            throw new IllegalArgumentException("Assignments length must match points size");
        }

        int k = centroids.size();
        for (int c = 0; c < k; c++) {
            Objects.requireNonNull(centroids.get(c), "centroids[" + c + "]");
        }

        double sse = 0.0;
        for (int i = 0; i < points.size(); i++) {
            Point point = Objects.requireNonNull(points.get(i), "points[" + i + "]");
            int cluster = assignments[i];
            if (cluster < 0 || cluster >= k) {
                throw new IllegalArgumentException(
                        "Assignment index out of range at position " + i + ": " + cluster);
            }
            Point centroid = centroids.get(cluster);
            double dx = point.x() - centroid.x();
            double dy = point.y() - centroid.y();
            sse += dx * dx + dy * dy;
        }

        if (!Double.isFinite(sse)) {
            throw new IllegalArgumentException("Computed SSE must be finite");
        }

        return sse;
    }
}
