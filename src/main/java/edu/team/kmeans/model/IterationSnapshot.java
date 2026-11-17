package edu.team.kmeans.model;

import java.util.List;
import java.util.Objects;

/**
 * Immutable end-of-update trace: updated centroids and assignments used to compute
 * them. Iterations are numbered from one. Final fit results additionally reassign
 * points to the returned centroids; see docs/architecture.md.
 */
public final class IterationSnapshot {
    private final int iteration;
    private final List<Point> centroids;
    private final int[] assignments;

    public IterationSnapshot(int iteration, List<Point> centroids, int[] assignments) {
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
        if (iteration <= 0) {
            throw new IllegalArgumentException("Completed iteration must be positive");
        }
        this.iteration = iteration;
    }

    public int iteration() {
        return iteration;
    }

    public List<Point> centroids() {
        return centroids;
    }

    public int[] assignments() {
        return assignments.clone();
    }
}
