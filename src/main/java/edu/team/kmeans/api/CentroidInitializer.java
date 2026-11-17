package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M5 selects reproducible initial centroids before either engine is run. */
@FunctionalInterface
public interface CentroidInitializer {
    /**
     * Return exactly k distinct input-point coordinates, in reproducible order for
     * the same ordered input, k, and seed. Do not modify the input list.
     * Points must be nonempty with no null elements; k must be positive and
     * no greater than the number of distinct point coordinates. Any long seed
     * is valid. Call once and pass the resulting coordinates to both engines.
     *
     * @throws NullPointerException for null input or point elements
     * @throws IllegalArgumentException for empty input or invalid k
     */
    List<Point> initialize(List<Point> points, int k, long seed);
}
