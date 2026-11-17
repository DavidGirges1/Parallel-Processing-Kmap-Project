package edu.team.kmeans.support;

import edu.team.kmeans.model.Point;
import java.util.List;

/** Hand-checked assignment inputs for M2/M3; this is not a clustering implementation. */
public final class AssignmentFixture {
    private AssignmentFixture() {
    }

    public static List<Point> points() {
        return List.of(new Point(1, 1), new Point(2, 2), new Point(8, 8), new Point(9, 9));
    }

    public static List<Point> initialCentroids() {
        return List.of(new Point(1, 1), new Point(8, 8));
    }

    public static int[] expectedAssignments() {
        return new int[]{0, 0, 1, 1};
    }
}
