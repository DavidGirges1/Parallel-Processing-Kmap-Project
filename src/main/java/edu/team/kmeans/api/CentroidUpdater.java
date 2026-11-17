package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M4 computes cluster means and handles empty clusters. */
@FunctionalInterface
public interface CentroidUpdater {
    /**
     * Return a new list with the same size and cluster ordering as previousCentroids.
     * Points and previous centroids must be nonempty, with no null elements.
     * Assignments must have one valid previous-centroid index per point.
     * Retain the previous centroid when its cluster has no assigned points.
     * Do not mutate input lists or assignments. Returned points must be finite.
     *
     * @throws NullPointerException for null arguments or point elements
     * @throws IllegalArgumentException for empty inputs, invalid assignment shape
     *         or indices, or a computation that cannot yield finite coordinates
     */
    List<Point> update(List<Point> points, int[] assignments, List<Point> previousCentroids);
}
