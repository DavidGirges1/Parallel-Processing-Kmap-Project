package edu.team.kmeans.data;

import edu.team.kmeans.model.Point;
import java.util.List;

/** Optional, stand-alone smoke demonstration for M5's data preparation. */
public final class QuickManualCheck {
    private QuickManualCheck() {}

    public static void main(String[] args) {
        int k = 3;
        long seed = 42L;
        List<Point> points = new SyntheticDataGenerator().generatePoints(20, k, 2.0, seed);
        List<Point> initialCentroids = new RandomCentroidInitializer().initialize(points, k, seed);

        System.out.println("Generated " + points.size() + " synthetic 2D points (seed " + seed + ")");
        System.out.println("First five points: " + points.subList(0, 5));
        System.out.println("Initial centroids (pass these exact coordinates to BOTH engines): "
                + initialCentroids);
    }
}
