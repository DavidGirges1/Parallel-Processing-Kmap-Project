package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M4 calculates sum_i ||points[i] - centroids[assignments[i]]|| squared. */
@FunctionalInterface
public interface SSECalculator {
    /** Use the supplied assignment/centroid pair; do not silently reassign points. */
    double calculate(List<Point> points, int[] assignments, List<Point> centroids);
}
