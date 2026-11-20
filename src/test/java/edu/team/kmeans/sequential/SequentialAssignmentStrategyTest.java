package edu.team.kmeans.sequential;

import edu.team.kmeans.api.AssignmentStrategy;
import edu.team.kmeans.model.Point;
import edu.team.kmeans.support.AssignmentFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SequentialAssignmentStrategyTest {
    // (pseudo)inject the strategy dependency
    private AssignmentStrategy strategy;

    @BeforeEach
    void setUp() {
        // this makes me miss spring framework 😔
        strategy = new SequentialAssignmentStrategy();
    }

    // test matching with fixture
    @Test
    void assignmentMatchesFixture() {
        List<Point> points = AssignmentFixture.points();
        List<Point> centroids = AssignmentFixture.initialCentroids();

        int[] assignment = new int[points.size()];

        strategy.assign(points, centroids, assignment);

        assertArrayEquals(assignment, AssignmentFixture.expectedAssignments());
    }
    // test that it assigns to the lowest numbered cluster
    @Test
    void assignmentToLowestCluster() {
        List<Point> points = List.of(
            new Point(5, 0)
        );
        List<Point> centroids = List.of(
            new Point(0, 0),
            new Point(10, 0)
        );

        int[] assignment = new int[points.size()];

        strategy.assign(points, centroids, assignment);

        assertArrayEquals(new int[]{0}, assignment);
    }

    // test that assignment handles empty points
    @Test
    void shouldHandleEmptyPoints() {
        List<Point> points = List.of();
        List<Point> centroids = AssignmentFixture.initialCentroids();

        int[] assignments = new int[0];

        assertDoesNotThrow(
            () -> strategy.assign(points, centroids, assignments)
        );

        assertEquals(0, assignments.length);
    }
}

