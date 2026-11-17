package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M2/M3 interchangeable assignment step used by the shared iteration engine. */
@FunctionalInterface
public interface AssignmentStrategy {
    /**
     * Write one cluster index for every point into the supplied output array.
     * Use squared Euclidean distance; ties choose the lowest centroid index.
     * Points and centroids must be nonempty and contain no nulls; output length
     * must equal point count. Do not modify either input list. Every output
     * position must be overwritten, regardless of its incoming value.
     * Parallel implementations join all tasks before returning and write only
     * disjoint point-index ranges. Duplicate centroids are allowed here.
     *
     * @throws NullPointerException for null arguments or point elements
     * @throws IllegalArgumentException for empty inputs or output-length mismatch
     */
    void assign(List<Point> points, List<Point> centroids, int[] assignments);
}
