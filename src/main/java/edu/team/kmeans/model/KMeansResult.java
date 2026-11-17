package edu.team.kmeans.model;

import java.util.List;
import java.util.Objects;

/**
 * Immutable final result. Assignments refer to these centroids, and SSE must be
 * calculated from that same pair. This type checks structure, not the SSE math.
 */
public final class KMeansResult {
    private final List<Point> centroids;
    private final int[] assignments;
    private final double sse;
    private final int iterations;

    public KMeansResult(List<Point> centroids, int[] assignments, double sse, int iterations) {
        this.centroids = List.copyOf(centroids);
        this.assignments = Objects.requireNonNull(assignments, "assignments").clone();
        if (this.centroids.isEmpty() || this.assignments.length == 0) {
            throw new IllegalArgumentException("Centroids and assignments must not be empty");
        }
        for (int assignment : this.assignments) {
            if (assignment < 0 || assignment >= this.centroids.size()) {
                throw new IllegalArgumentException("Assignment must index an existing centroid");
            }
        }
        if (!Double.isFinite(sse) || sse < 0) {
            throw new IllegalArgumentException("SSE must be finite and nonnegative");
        }
        if (iterations <= 0) {
            throw new IllegalArgumentException("Completed iterations must be positive");
        }
        this.sse = sse;
        this.iterations = iterations;
    }

    public List<Point> centroids() {
        return centroids;
    }

    public int[] assignments() {
        return assignments.clone();
    }

    public double sse() {
        return sse;
    }

    public int iterations() {
        return iterations;
    }
}
