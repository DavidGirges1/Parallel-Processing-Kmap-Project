package edu.team.kmeans.api;

import edu.team.kmeans.model.KMeansConfig;
import edu.team.kmeans.model.KMeansResult;
import edu.team.kmeans.model.Point;
import java.util.List;

/** Public contract for both engines; input collections must never be mutated. */
@FunctionalInterface
public interface KMeans {
    /**
     * Cluster points from explicitly supplied initial coordinates. Reject empty
     * data, K above distinct input count, and initial centroid count != config.k().
     * Return final assignments/SSE evaluated against the returned centroids.
     */
    KMeansResult fit(List<Point> points, List<Point> initialCentroids, KMeansConfig config);
}
