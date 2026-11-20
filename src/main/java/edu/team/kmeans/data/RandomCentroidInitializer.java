package edu.team.kmeans.data;

import edu.team.kmeans.api.CentroidInitializer;
import edu.team.kmeans.model.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/** Selects K distinct input-point coordinates reproducibly without mutation. */
public final class RandomCentroidInitializer implements CentroidInitializer {

    /** Returns an immutable list of initial centers from the input coordinates. */
    @Override
    public List<Point> initialize(List<Point> points, int k, long seed) {
        List<Point> distinctOrdered = validateAndDeduplicate(points, k);
        Collections.shuffle(distinctOrdered, new Random(seed));
        return List.copyOf(distinctOrdered.subList(0, k));
    }

    /**
     * Validates input data and returns a separate, mutable list of distinct
     * coordinates in encounter order. Signed zeros represent the same position.
     * The original point objects are preserved in the returned list.
     *
     * @throws NullPointerException if the input or any element is null
     * @throws IllegalArgumentException if empty, K <= 0, or K exceeds the
     *     number of distinct coordinate positions
     */
    public static List<Point> validateAndDeduplicate(List<Point> points, int k) {
        Objects.requireNonNull(points, "points must not be null");
        if (points.isEmpty()) {
            throw new IllegalArgumentException("points must not be empty");
        }
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive, got: " + k);
        }
        Set<Point> seen = new HashSet<>();
        List<Point> distinctOrdered = new ArrayList<>();

        for (Point point : points) {
            Objects.requireNonNull(point, "points must not contain null elements");
            // Java record equality distinguishes +0.0 and -0.0; geometric coordinates do not.
            Point key = new Point(
                    point.x() == 0.0 ? 0.0 : point.x(),
                    point.y() == 0.0 ? 0.0 : point.y());
            if (seen.add(key)) {
                distinctOrdered.add(point);
            }
        }
        if (k > distinctOrdered.size()) {
            throw new IllegalArgumentException(
                    "k (" + k + ") cannot exceed distinct point count (" + distinctOrdered.size() + ")");
        }
        return distinctOrdered;
    }
}
