package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** Interchangeable sequential/Fork-Join assignment step used by M2's engine. */
@FunctionalInterface
public interface AssignmentStrategy {
    /**
     * Write one cluster index per point to an equally sized output array, using
     * squared Euclidean distance and lowest-index tie breaking. Inputs are read-only.
     * Parallel tasks must write disjoint ranges; all writes finish before returning.
     */
    void assign(List<Point> points, List<Point> centroids, int[] assignments);
}
