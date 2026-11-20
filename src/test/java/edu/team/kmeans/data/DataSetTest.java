package edu.team.kmeans.data;

import static org.junit.jupiter.api.Assertions.*;

import edu.team.kmeans.model.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DataSetTest {
    private final SyntheticDataGenerator generator = new SyntheticDataGenerator();
    private final RandomCentroidInitializer initializer = new RandomCentroidInitializer();

    @Test
    void sameSeedProducesIdenticalPointsAndInitialCentroids() {
        List<Point> first = generator.generatePoints(20, 3, 1.5, 99L);
        List<Point> second = generator.generatePoints(20, 3, 1.5, 99L);
        assertEquals(first, second);
        assertEquals(initializer.initialize(first, 3, 99L), initializer.initialize(second, 3, 99L));
        assertEquals(first, generator.GeneratePoints(20, 3, 1.5, 99L)); // Existing caller compatibility
    }

    @Test
    void usesRequestedNumberOfFinitePointsAndAllowsOneCluster() {
        List<Point> points = generator.generatePoints(25, 1, 2.0, -17L);
        assertEquals(25, points.size());
        assertTrue(points.stream().allMatch(p -> Double.isFinite(p.x()) && Double.isFinite(p.y())));
        assertEquals(1, initializer.initialize(points, 1, -17L).size());
    }

    @Test
    void zeroSpreadProducesExactCentersInRoundRobinOrder() {
        int k = 3;
        long seed = 42L;
        Random expectedRandom = new Random(seed);
        List<Point> centers = new ArrayList<>();
        for (int cluster = 0; cluster < k; cluster++) {
            centers.add(new Point(expectedRandom.nextDouble() * 100.0,
                    expectedRandom.nextDouble() * 100.0));
        }
        List<Point> points = generator.generatePoints(8, k, 0.0, seed);
        for (int j = 0; j < points.size(); j++) {
            assertEquals(centers.get(j % k), points.get(j), "point " + j);
        }
    }

    @Test
    void gaussianNoiseIsAddedToCenterRatherThanMultiplyingIt() {
        long seed = 42L;
        double spread = 3.0;
        Random expectedRandom = new Random(seed);
        double centerX = expectedRandom.nextDouble() * 100.0;
        double centerY = expectedRandom.nextDouble() * 100.0;
        Point expected = new Point(centerX + expectedRandom.nextGaussian() * spread,
                centerY + expectedRandom.nextGaussian() * spread);
        assertEquals(List.of(expected), generator.generatePoints(1, 1, spread, seed));
    }

    @Test
    void rejectsInvalidDatasetSizesAndClusterCounts() {
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(0, 1, 1.0, 1L));
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(-1, 1, 1.0, 1L));
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(1, 0, 1.0, 1L));
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(1, -1, 1.0, 1L));
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(1, 2, 1.0, 1L));
        assertEquals(3, generator.generatePoints(3, 3, 0.0, 1L).size());
    }

    @Test
    void rejectsNegativeNonFiniteOrOverflowingSpread() {
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(2, 1, -0.5, 1L));
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(2, 1, Double.NaN, 1L));
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(2, 1, Double.POSITIVE_INFINITY, 1L));
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(2, 1, Double.NEGATIVE_INFINITY, 1L));
        assertThrows(IllegalArgumentException.class, () -> generator.generatePoints(5, 2, Double.MAX_VALUE, 42L));
    }

    @Test
    void generatedDataAndInitialCentroidsAreImmutable() {
        List<Point> points = generator.generatePoints(10, 2, 1.0, 42L);
        List<Point> starts = initializer.initialize(points, 2, 42L);
        assertThrows(UnsupportedOperationException.class, () -> points.add(new Point(0, 0)));
        assertThrows(UnsupportedOperationException.class, () -> starts.add(new Point(0, 0)));
    }

    @Test
    void initializerSelectsDistinctInputPointsAndDoesNotMutateInput() {
        List<Point> input = new ArrayList<>(List.of(
                new Point(1.0, 1.0), new Point(1.0, 1.0),
                new Point(5.0, 5.0), new Point(9.0, 9.0)));
        List<Point> before = List.copyOf(input);
        List<Point> starts = initializer.initialize(input, 3, 7L);
        assertEquals(3, starts.size());
        assertEquals(3, Set.copyOf(starts).size());
        assertTrue(input.containsAll(starts));
        assertEquals(before, input);
        assertEquals(starts, initializer.initialize(input, 3, 7L));
        assertEquals(1, initializer.initialize(input, 1, 7L).size());
    }

    @Test
    void rejectsTooManyCentersEvenWhenInputContainsDuplicates() {
        List<Point> duplicates = List.of(
                new Point(2.0, 2.0), new Point(2.0, 2.0),
                new Point(8.0, 8.0), new Point(8.0, 8.0));
        assertThrows(IllegalArgumentException.class, () -> initializer.initialize(duplicates, 3, 1L));
    }

    @Test
    void positiveAndNegativeZeroRepresentSameCoordinate() {
        List<Point> points = List.of(new Point(0.0, -0.0), new Point(-0.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> initializer.initialize(points, 2, 1L));
        assertEquals(1, initializer.initialize(points, 1, 1L).size());
    }

    @Test
    void rejectsNullEmptyAndInvalidK() {
        List<Point> valid = List.of(new Point(1.0, 2.0));
        assertThrows(NullPointerException.class, () -> initializer.initialize(null, 1, 1L));
        assertThrows(NullPointerException.class,
                () -> initializer.initialize(Arrays.asList(new Point(1.0, 2.0), null), 1, 1L));
        assertThrows(IllegalArgumentException.class, () -> initializer.initialize(List.of(), 1, 1L));
        assertThrows(IllegalArgumentException.class, () -> initializer.initialize(valid, 0, 1L));
        assertThrows(IllegalArgumentException.class, () -> initializer.initialize(valid, -2, 1L));
        assertThrows(IllegalArgumentException.class, () -> initializer.initialize(valid, 2, 1L));
        assertThrows(IllegalArgumentException.class, () -> new Point(Double.NaN, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Point(1.0, Double.POSITIVE_INFINITY));
    }
}
