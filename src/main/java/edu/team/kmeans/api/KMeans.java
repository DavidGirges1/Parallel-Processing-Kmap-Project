package edu.team.kmeans.api;

import edu.team.kmeans.model.KMeansConfig;
import edu.team.kmeans.model.KMeansResult;
import edu.team.kmeans.model.Point;
import java.util.List;

/** Public contract for both engines; input collections must never be mutated. */
@FunctionalInterface
public interface KMeans {
    /**
     * Cluster nonempty points from explicitly supplied initial coordinates.
     * Reject K above distinct input count and initial centroid count != config.k().
     * Honor all supplied configuration values, including this fit's task settings.
     * Do not mutate caller data. Return k centroids and one assignment per point,
     * with final assignments/SSE evaluated against those returned centroids.
     * Iterations count completed assignment/update cycles, from 1 to maxIterations.
     * Engine instances need not support concurrent fits.
     *
     * @throws NullPointerException for null arguments or point elements
     * @throws IllegalArgumentException for empty data, invalid K, or mismatched starts
     */
    KMeansResult fit(List<Point> points, List<Point> initialCentroids, KMeansConfig config);
}
