package edu.team.kmeans.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ModelTest {
    private static final List<Point> CENTROIDS = List.of(new Point(1, 1), new Point(8, 8));

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteCoordinatesInEitherDimension(double coordinate) {
        assertThrows(IllegalArgumentException.class, () -> new Point(coordinate, 0));
        assertThrows(IllegalArgumentException.class, () -> new Point(0, coordinate));
    }

    @Test
    void acceptsFiniteNegativeAndZeroCoordinates() {
        assertDoesNotThrow(() -> new Point(-3.5, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void rejectsNonPositiveIntegerConfigurationFields(int value) {
        assertThrows(IllegalArgumentException.class, () -> new KMeansConfig(value, 10, 0, 42, 64, 2));
        assertThrows(IllegalArgumentException.class, () -> new KMeansConfig(2, value, 0, 42, 64, 2));
        assertThrows(IllegalArgumentException.class, () -> new KMeansConfig(2, 10, 0, 42, value, 2));
        assertThrows(IllegalArgumentException.class, () -> new KMeansConfig(2, 10, 0, 42, 64, value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsInvalidTolerance(double tolerance) {
        assertThrows(IllegalArgumentException.class, () -> new KMeansConfig(2, 10, tolerance, 42, 64, 2));
    }

    @Test
    void acceptsZeroToleranceAndAnySeed() {
        assertDoesNotThrow(() -> new KMeansConfig(1, 1, 0, Long.MIN_VALUE, 1, 1));
    }

    @Test
    void resultProtectsAssignmentsAndCentroidsFromCallerMutations() {
        var centroids = new ArrayList<>(CENTROIDS);
        int[] assignments = {0, 0, 1, 1};
        var result = new KMeansResult(centroids, assignments, 4.0, 2);
        assignments[0] = 1;
        centroids.clear();
        result.assignments()[1] = 1;
        assertArrayEquals(new int[]{0, 0, 1, 1}, result.assignments());
        assertEquals(CENTROIDS, result.centroids());
        assertThrows(UnsupportedOperationException.class, () -> result.centroids().clear());
        assertEquals(4.0, result.sse());
        assertEquals(2, result.iterations());
    }

    @Test
    void snapshotProtectsAssignmentsAndCentroidsFromCallerMutations() {
        var centroids = new ArrayList<>(CENTROIDS);
        int[] assignments = {0, 0, 1, 1};
        var snapshot = new IterationSnapshot(1, centroids, assignments);
        assignments[0] = 1;
        centroids.clear();
        snapshot.assignments()[1] = 1;
        assertArrayEquals(new int[]{0, 0, 1, 1}, snapshot.assignments());
        assertEquals(CENTROIDS, snapshot.centroids());
        assertThrows(UnsupportedOperationException.class, () -> snapshot.centroids().clear());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void resultRejectsInvalidSse(double sse) {
        assertThrows(IllegalArgumentException.class, () -> new KMeansResult(CENTROIDS, new int[]{0}, sse, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void rejectsNonPositiveIterationCounts(int iterations) {
        assertThrows(IllegalArgumentException.class, () -> new KMeansResult(CENTROIDS, new int[]{0}, 0, iterations));
        assertThrows(IllegalArgumentException.class, () -> new IterationSnapshot(iterations, CENTROIDS, new int[]{0}));
    }

    @Test
    void rejectsEmptyCentroidsOrAssignments() {
        assertThrows(IllegalArgumentException.class, () -> new KMeansResult(List.of(), new int[]{0}, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new IterationSnapshot(1, List.of(), new int[]{0}));
        assertThrows(IllegalArgumentException.class, () -> new KMeansResult(CENTROIDS, new int[0], 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new IterationSnapshot(1, CENTROIDS, new int[0]));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 2})
    void rejectsAssignmentsOutsideCentroidRange(int assignment) {
        assertThrows(IllegalArgumentException.class, () -> new KMeansResult(CENTROIDS, new int[]{assignment}, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new IterationSnapshot(1, CENTROIDS, new int[]{assignment}));
    }

    @Test
    void rejectsNullCollectionsAndElements() {
        assertThrows(NullPointerException.class, () -> new KMeansResult(null, new int[]{0}, 0, 1));
        assertThrows(NullPointerException.class, () -> new KMeansResult(CENTROIDS, null, 0, 1));
        assertThrows(NullPointerException.class, () -> new IterationSnapshot(1, null, new int[]{0}));
        assertThrows(NullPointerException.class, () -> new IterationSnapshot(1, CENTROIDS, null));
        var nullCentroid = new ArrayList<Point>();
        nullCentroid.add(null);
        assertThrows(NullPointerException.class, () -> new KMeansResult(nullCentroid, new int[]{0}, 0, 1));
        assertThrows(NullPointerException.class, () -> new IterationSnapshot(1, nullCentroid, new int[]{0}));
    }
}
