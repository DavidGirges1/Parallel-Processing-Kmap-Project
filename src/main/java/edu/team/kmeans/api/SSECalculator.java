package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M4 calculates sum_i ||points[i] - centroids[assignments[i]]|| squared. */
@FunctionalInterface
public interface SSECalculator {
    /**
     * Return finite nonnegative SSE for exactly the supplied assignment/centroid pair.
     * Points and centroids must be nonempty and contain no nulls; assignments
     * must contain one valid centroid index per point. Do not reassign points
     * or mutate any input. Zero SSE is valid.
     *
     * @throws NullPointerException for null arguments or point elements
     * @throws IllegalArgumentException for empty inputs, invalid assignment shape
     *         or indices, or a computation that cannot yield finite SSE
     */
    double calculate(List<Point> points, int[] assignments, List<Point> centroids);
}
