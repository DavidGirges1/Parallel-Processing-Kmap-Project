package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M4 checks maximum Euclidean centroid movement. */
@FunctionalInterface
public interface ConvergenceChecker {
    /**
     * Return true when maximum corresponding-centroid movement is <= tolerance.
     * Both lists must be nonempty, contain no nulls, and have equal size/order.
     * Tolerance must be finite and nonnegative; zero is valid. Inputs are read-only.
     *
     * @throws NullPointerException for null lists or point elements
     * @throws IllegalArgumentException for empty/unequal lists or invalid tolerance
     */
    boolean hasConverged(List<Point> previous, List<Point> current, double tolerance);
}
