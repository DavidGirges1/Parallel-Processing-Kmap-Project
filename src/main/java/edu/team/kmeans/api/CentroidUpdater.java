package edu.team.kmeans.api;

import edu.team.kmeans.model.Point;
import java.util.List;

/** M4 computes means; empty clusters retain their previous centroid. */
@FunctionalInterface
public interface CentroidUpdater {
    /** Return a new centroid list without mutating any caller input. */
    List<Point> update(List<Point> points, int[] assignments, List<Point> previousCentroids);
}
