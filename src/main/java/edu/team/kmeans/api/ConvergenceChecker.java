package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M4 compares maximum Euclidean centroid movement to the configured tolerance. */
@FunctionalInterface
public interface ConvergenceChecker {
    /** Return true when maximum movement is at most finite nonnegative tolerance. */
    boolean hasConverged(List<Point> previous, List<Point> current, double tolerance);
}
