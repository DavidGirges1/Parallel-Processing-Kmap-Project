package edu.team.kmeans.model;

/** Run parameters. Dataset-dependent checks belong at the fit/data boundary. */
public record KMeansConfig(
        int k,
        int maxIterations,
        double tolerance,
        long seed,
        int taskThreshold,
        int parallelism) {
    public KMeansConfig {
        if (k <= 0 || maxIterations <= 0 || taskThreshold <= 0 || parallelism <= 0) {
            throw new IllegalArgumentException(
                    "K, maxIterations, taskThreshold, and parallelism must be positive");
        }
        if (!Double.isFinite(tolerance) || tolerance < 0) {
            throw new IllegalArgumentException("Tolerance must be finite and nonnegative");
        }
    }
}
