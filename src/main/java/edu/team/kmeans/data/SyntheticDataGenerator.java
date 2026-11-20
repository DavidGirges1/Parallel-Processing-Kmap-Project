package edu.team.kmeans.data;

import edu.team.kmeans.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Reproducible generator of two-dimensional Gaussian clusters.
 *
 * <p>First samples {@code numClusters} centers uniformly from [0, 100) on each
 * axis using {@code seed}; then assigns successive points round-robin to those
 * centers, adding independent Gaussian noise with standard deviation
 * {@code spread} on each axis. A zero spread produces exact center coordinates.
 * Both the point count and cluster count must be positive, and the number of
 * clusters must not exceed the number of points. Spread must be finite and
 * nonnegative. The output list and its Point elements are immutable.</p>
 *
 * <p>The generated cluster centers are for constructing sample data, not the
 * initial centroids passed to K-Means. Call {@link RandomCentroidInitializer}
 * on this returned list and reuse its explicit result for both engines.</p>
 */
public final class SyntheticDataGenerator {

    /**
     * @param numPoints total number of generated points, at least one
     * @param numClusters number of synthetic Gaussian centers, 1..numPoints
     * @param spread Gaussian standard deviation per coordinate, finite and >= 0
     * @param seed seed used for reproducibility (any long)
     * @return unmodifiable list of finite 2D points in round-robin cluster order
     * @throws IllegalArgumentException for invalid parameters or non-finite generated coordinates
     */
    public List<Point> generatePoints(int numPoints, int numClusters, double spread, long seed) {
        if (numPoints <= 0) {
            throw new IllegalArgumentException("numPoints must be positive");
        }
        if (numClusters <= 0 || numClusters > numPoints) {
            throw new IllegalArgumentException("numClusters must be between 1 and numPoints");
        }
        if (!Double.isFinite(spread) || spread < 0.0) {
            throw new IllegalArgumentException("spread must be finite and nonnegative");
        }

        Random random = new Random(seed);
        double[][] centers = new double[numClusters][2];
        for (int cluster = 0; cluster < numClusters; cluster++) {
            centers[cluster][0] = random.nextDouble() * 100.0;
            centers[cluster][1] = random.nextDouble() * 100.0;
        }

        List<Point> points = new ArrayList<>(numPoints);
        for (int index = 0; index < numPoints; index++) {
            double[] center = centers[index % numClusters];
            double x = center[0] + random.nextGaussian() * spread;
            double y = center[1] + random.nextGaussian() * spread;
            if (!Double.isFinite(x) || !Double.isFinite(y)) {
                throw new IllegalArgumentException("spread produces non-finite coordinates");
            }
            points.add(new Point(x, y));
        }
        return List.copyOf(points);
    }

    /** Preserves the method name from Bola Hany's original submission. */
    @Deprecated
    public List<Point> GeneratePoints(int numPoints, int numClusters, double spread, long seed) {
        return generatePoints(numPoints, numClusters, spread, seed);
    }
}
