package edu.team.kmeans.math;

import static org.junit.jupiter.api.Assertions.*;

import edu.team.kmeans.model.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MathServicesTest {
    private final MeanCentroidUpdater updater = new MeanCentroidUpdater();
    private final EuclideanConvergenceChecker convergence = new EuclideanConvergenceChecker();
    private final EuclideanSSECalculator sse = new EuclideanSSECalculator();

    private static final List<Point> POINTS = List.of(
            new Point(1, 1), new Point(2, 2), new Point(8, 8), new Point(9, 9));
    private static final List<Point> STARTS = List.of(new Point(1, 1), new Point(8, 8));

    @Test
    void computesHandCheckedClusterMeansAndSse() {
        int[] assignments = {0, 0, 1, 1};
        List<Point> updated = updater.update(POINTS, assignments, STARTS);
        assertEquals(List.of(new Point(1.5, 1.5), new Point(8.5, 8.5)), updated);
        assertEquals(4.0, sse.calculate(POINTS, assignments, STARTS));
        assertEquals(2.0, sse.calculate(POINTS, assignments, updated));
    }

    @Test
    void retainsEmptyClusterAndHandlesDuplicateAndNegativePoints() {
        List<Point> points = List.of(new Point(-4, 2), new Point(-4, 2), new Point(2, -4));
        List<Point> centers = List.of(new Point(0, 0), new Point(7, 7), new Point(9, 9));
        List<Point> result = updater.update(points, new int[]{0, 0, 0}, centers);
        assertEquals(List.of(new Point(-2, 0), centers.get(1), centers.get(2)), result);
    }

    @Test
    void handlesFiniteExtremeCoordinatesWithoutOverflowingIntermediateSum() {
        double max = Double.MAX_VALUE;
        List<Point> result = updater.update(
                List.of(new Point(max, max), new Point(-max, max)),
                new int[]{0, 0},
                List.of(new Point(0, 0)));
        assertEquals(new Point(0, max), result.get(0));
    }

    @Test
    void returnsAnUnmodifiableListWithoutChangingInputs() {
        List<Point> points = new ArrayList<>(POINTS);
        List<Point> oldCenters = new ArrayList<>(STARTS);
        int[] assignments = {0, 0, 1, 1};
        List<Point> result = updater.update(points, assignments, oldCenters);
        assertEquals(POINTS, points);
        assertEquals(STARTS, oldCenters);
        assertArrayEquals(new int[]{0, 0, 1, 1}, assignments);
        assertThrows(UnsupportedOperationException.class, () -> result.add(new Point(0, 0)));
    }

    @Test
    void rejectsMalformedUpdaterInputs() {
        assertThrows(NullPointerException.class, () -> updater.update(null, new int[]{0}, STARTS));
        assertThrows(NullPointerException.class, () -> updater.update(POINTS, null, STARTS));
        assertThrows(NullPointerException.class, () -> updater.update(POINTS, new int[4], null));
        assertThrows(NullPointerException.class, () -> updater.update(
                Arrays.asList((Point) null), new int[]{0}, STARTS));
        assertThrows(NullPointerException.class, () -> updater.update(
                POINTS, new int[4], Arrays.asList(new Point(0, 0), null)));
        assertThrows(IllegalArgumentException.class, () -> updater.update(
                List.of(), new int[0], STARTS));
        assertThrows(IllegalArgumentException.class, () -> updater.update(
                POINTS, new int[4], List.of()));
        assertThrows(IllegalArgumentException.class, () -> updater.update(
                POINTS, new int[]{0}, STARTS));
        assertThrows(IllegalArgumentException.class, () -> updater.update(
                POINTS, new int[]{-1, 0, 1, 1}, STARTS));
        assertThrows(IllegalArgumentException.class, () -> updater.update(
                POINTS, new int[]{0, 0, 2, 1}, STARTS));
    }

    @Test
    void convergenceUsesMaximumEuclideanMovementIncludingExactBoundary() {
        List<Point> before = List.of(new Point(0, 0), new Point(10, 10));
        List<Point> after = List.of(new Point(3, 4), new Point(10, 10));
        assertTrue(convergence.hasConverged(before, after, 5.0));
        assertFalse(convergence.hasConverged(before, after, 4.999));
        assertTrue(convergence.hasConverged(before, before, 0.0));
        assertFalse(convergence.hasConverged(before, after, 0.0));
    }

    @Test
    void convergenceChecksAllClustersRatherThanOnlyTheFirst() {
        List<Point> before = List.of(new Point(0, 0), new Point(0, 0));
        List<Point> after = List.of(new Point(0, 0), new Point(6, 8));
        assertFalse(convergence.hasConverged(before, after, 5.0));
        assertTrue(convergence.hasConverged(before, after, 10.0));
    }

    @Test
    void convergenceRejectsMalformedInputs() {
        assertThrows(NullPointerException.class, () -> convergence.hasConverged(null, STARTS, 0));
        assertThrows(NullPointerException.class, () -> convergence.hasConverged(STARTS, null, 0));
        assertThrows(NullPointerException.class, () -> convergence.hasConverged(
                Arrays.asList((Point) null), List.of(new Point(0, 0)), 0));
        assertThrows(IllegalArgumentException.class, () -> convergence.hasConverged(
                List.of(), List.of(), 0));
        assertThrows(IllegalArgumentException.class, () -> convergence.hasConverged(
                STARTS, List.of(new Point(0, 0)), 0));
        for (double bad : new double[]{-0.01, Double.NaN,
                Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class,
                    () -> convergence.hasConverged(STARTS, STARTS, bad));
        }
    }

    @Test
    void sseUsesExactlySuppliedAssignmentsEvenWhenNotNearest() {
        List<Point> points = List.of(new Point(0, 0), new Point(10, 0));
        List<Point> centers = List.of(new Point(0, 0), new Point(10, 0));
        assertEquals(200.0, sse.calculate(points, new int[]{1, 0}, centers));
        assertEquals(0.0, sse.calculate(points, new int[]{0, 1}, centers));
    }

    @Test
    void sseWorksWithNegativeValuesAndOneCluster() {
        assertEquals(4.0, sse.calculate(
                List.of(new Point(-1, -1), new Point(1, 1)),
                new int[]{0, 0}, List.of(new Point(0, 0))));
    }

    @Test
    void sseRejectsMalformedInputsAndNonFiniteResults() {
        assertThrows(NullPointerException.class, () -> sse.calculate(null, new int[4], STARTS));
        assertThrows(NullPointerException.class, () -> sse.calculate(POINTS, null, STARTS));
        assertThrows(NullPointerException.class, () -> sse.calculate(POINTS, new int[4], null));
        assertThrows(NullPointerException.class, () -> sse.calculate(
                Arrays.asList((Point) null), new int[]{0}, STARTS));
        assertThrows(NullPointerException.class, () -> sse.calculate(
                POINTS, new int[4], Arrays.asList(new Point(0, 0), null)));
        assertThrows(IllegalArgumentException.class, () -> sse.calculate(
                List.of(), new int[0], STARTS));
        assertThrows(IllegalArgumentException.class, () -> sse.calculate(
                POINTS, new int[4], List.of()));
        assertThrows(IllegalArgumentException.class, () -> sse.calculate(
                POINTS, new int[]{0}, STARTS));
        assertThrows(IllegalArgumentException.class, () -> sse.calculate(
                POINTS, new int[]{0, -1, 1, 1}, STARTS));
        assertThrows(IllegalArgumentException.class, () -> sse.calculate(
                POINTS, new int[]{0, 0, 2, 1}, STARTS));
        assertThrows(IllegalArgumentException.class, () -> sse.calculate(
                List.of(new Point(Double.MAX_VALUE, 0)), new int[]{0},
                List.of(new Point(-Double.MAX_VALUE, 0))));
    }
}
