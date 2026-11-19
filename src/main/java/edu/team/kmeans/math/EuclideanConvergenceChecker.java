package edu.team.kmeans.math;

import edu.team.kmeans.api.ConvergenceChecker;
import edu.team.kmeans.model.Point;
import java.util.List;
import java.util.Objects;

/**
 * Checks K-Means convergence by comparing the maximum Euclidean movement across
 * corresponding centroids against a nonnegative finite tolerance.
 */
public final class EuclideanConvergenceChecker implements ConvergenceChecker {

    @Override
    public boolean hasConverged(List<Point> previous, List<Point> current, double tolerance) {
        Objects.requireNonNull(previous, "previous");
        Objects.requireNonNull(current, "current");

        if (!Double.isFinite(tolerance) || tolerance < 0.0) {
            throw new IllegalArgumentException("Tolerance must be finite and nonnegative");
        }
        if (previous.isEmpty() || current.isEmpty()) {
            throw new IllegalArgumentException("Centroid lists must not be empty");
        }
        if (previous.size() != current.size()) {
            throw new IllegalArgumentException(
                    "Previous and current centroid lists must have equal size");
        }

        double maxMovement = 0.0;
        for (int i = 0; i < previous.size(); i++) {
            Point prev = Objects.requireNonNull(previous.get(i), "previous[" + i + "]");
            Point curr = Objects.requireNonNull(current.get(i), "current[" + i + "]");
            double dx = curr.x() - prev.x();
            double dy = curr.y() - prev.y();
            double movement = Math.hypot(dx, dy);
            if (movement > maxMovement) {
                maxMovement = movement;
            }
        }

        return maxMovement <= tolerance;
    }
}
