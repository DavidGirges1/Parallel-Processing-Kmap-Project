package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M5 selects reproducible initial centroids before either engine is run. */
@FunctionalInterface
public interface CentroidInitializer {
    /** Reject invalid K/data and return K initial coordinates using the given seed. */
    List<Point> initialize(List<Point> points, int k, long seed);
}
