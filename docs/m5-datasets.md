# M5: Synthetic datasets and initial centroids

**Owner:** Bola Hany (`@poula-eng`), with review fixes. **Tracked task:** [Issue #5](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/5).

M5 prepares inputs; it does **not** perform K-Means iterations or calculate updated centers (M4 does that). Data generation and starting-centroid selection are separate steps.

## APIs and example

```java
SyntheticDataGenerator generator = new SyntheticDataGenerator();
RandomCentroidInitializer initializer = new RandomCentroidInitializer();

int k = 3;
long seed = 42L;
List<Point> points = generator.generatePoints(100, k, 2.0, seed);
List<Point> starts = initializer.initialize(points, k, seed);

// Pass these SAME points and SAME explicit initial center coordinates to BOTH engines:
// sequential.fit(points, starts, config);
// parallel.fit(points, starts, config);
```

- `generatePoints(numPoints, numClusters, spread, seed)` returns exactly `numPoints` finite, immutable 2D points. It creates `numClusters` sample centers uniformly in `[0, 100)` on each axis; point `j` is sampled around center `j % numClusters` using independently sampled Gaussian offsets with standard deviation `spread`. `numPoints >= 1`, `1 <= numClusters <= numPoints`, and `spread` must be finite and `>= 0`. A zero spread creates points exactly on synthetic centers; very large finite spreads that produce non-finite coordinates are rejected. Same parameter values and seed produce the same ordered points. The old `GeneratePoints` method remains as a deprecated compatibility alias.
- `initialize(points, k, seed)` selects `k` distinct **existing** input point coordinates in reproducible shuffled order. It rejects null/empty input, null point elements, `k <= 0`, and `k` above the number of distinct coordinate positions (including duplicates and signed-zero equality). It does not mutate input and returns an immutable list.
- Use *exactly the returned* `starts` list for sequential and parallel comparisons. Calling two independent initializers with different input order or parameters is not sufficient for a fair experiment, even when their seeds match.
- The generator's `numClusters` controls the synthetic data layout; algorithmic `k` controls the number of K-Means starting centroids. They can differ intentionally, but using the same value is simpler for demos.

## Validation

From the project root, run `./mvnw --batch-mode --no-transfer-progress verify` (Windows: `mvnw.cmd --batch-mode --no-transfer-progress verify`). `DataSetTest` exercises reproducibility, correct center-plus-noise generation, round-robin assignment, output count/immutability, invalid values, duplicate points, signed zeros, and initializer boundaries. Use `java -cp target/classes edu.team.kmeans.data.QuickManualCheck` for a small smoke demo once the project has been compiled.

This component alone does not demonstrate a working K-Means engine or parallel speedup. M2/M3 need to integrate the returned points and starting centroids; M6 must run end-to-end correctness and benchmark experiments separately.
