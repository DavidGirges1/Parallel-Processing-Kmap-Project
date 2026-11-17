package edu.team.kmeans.model;

/** Immutable 2D coordinates shared by datasets, centroids, and visualization. */
public record Point(double x, double y) {
    public Point {
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("Point coordinates must be finite");
        }
    }
}
