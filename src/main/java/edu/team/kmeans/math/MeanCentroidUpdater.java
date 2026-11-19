package edu.team.kmeans.math;

import edu.team.kmeans.api.CentroidUpdater;
import edu.team.kmeans.model.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Recomputes each cluster centroid as the arithmetic mean of its assigned points.
 * Clusters with no assigned points retain their previous centroid.
 *
 * <p>Each contribution is divided by its cluster size before summing, so finite
 * point coordinates with finite means do not overflow an unscaled running sum.
 * Calculations still use ordinary double precision and are subject to rounding.
 */
public final class MeanCentroidUpdater implements CentroidUpdater {

    @Override
    public List<Point> update(
            List<Point> points,
            int[] assignments,
            List<Point> previousCentroids) {
        Objects.requireNonNull(points, "points");
        Objects.requireNonNull(assignments, "assignments");
        Objects.requireNonNull(previousCentroids, "previousCentroids");

        if (points.isEmpty()) {
            throw new IllegalArgumentException("Points must not be empty");
        }
        if (previousCentroids.isEmpty()) {
            throw new IllegalArgumentException("Previous centroids must not be empty");
        }
        if (assignments.length != points.size()) {
            throw new IllegalArgumentException("Assignments length must match points size");
        }

        int k = previousCentroids.size();
        for (int c = 0; c < k; c++) {
            Objects.requireNonNull(previousCentroids.get(c), "previousCentroids[" + c + "]");
        }

        int[] counts = new int[k];
        for (int i = 0; i < points.size(); i++) {
            Objects.requireNonNull(points.get(i), "points[" + i + "]");
            int cluster = assignments[i];
            if (cluster < 0 || cluster >= k) {
                throw new IllegalArgumentException(
                        "Assignment index out of range at position " + i + ": " + cluster);
            }
            counts[cluster]++;
        }

        double[] meanX = new double[k];
        double[] meanY = new double[k];
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            int cluster = assignments[i];
            meanX[cluster] += point.x() / counts[cluster];
            meanY[cluster] += point.y() / counts[cluster];
        }

        List<Point> updated = new ArrayList<>(k);
        for (int c = 0; c < k; c++) {
            if (counts[c] == 0) {
                updated.add(previousCentroids.get(c));
            } else {
                if (!Double.isFinite(meanX[c]) || !Double.isFinite(meanY[c])) {
                    throw new IllegalArgumentException(
                            "Computed centroid coordinates must be finite for cluster " + c);
                }
                updated.add(new Point(meanX[c], meanY[c]));
            }
        }

        return List.copyOf(updated);
    }
}
