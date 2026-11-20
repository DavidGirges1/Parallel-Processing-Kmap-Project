# Milestone 1 → Milestone 2 handoff

**Milestone 2 can start from `main`.** The Java foundation, shared fixture, math services, and seeded data preparation work together at the component level. The next milestone implements both K-Means engines and connects them to those components.

Reviewed baseline: [`08da4a6`](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/commit/08da4a65cb3e48ecf603afc4cbf69300f71239ad) on 20 November 2026. Milestone numbers refer to stages of work. M1–M7 are the repository's member roles.

## What Milestone 1 delivered

| Deliverable | Where to look | What you can use today |
|---|---|---|
| Shared contracts and models · M1 | [`api/`](src/main/java/edu/team/kmeans/api), [`model/`](src/main/java/edu/team/kmeans/model) | Immutable 2D points, configuration, results and snapshots. Both engines implement `KMeans.fit(points, initialCentroids, config)`. |
| Shared test fixture | [`AssignmentFixture.java`](src/test/java/edu/team/kmeans/support/AssignmentFixture.java) | Four fixed points, two initial centers and expected assignment indices. Both assignment strategies should use it in tests. |
| Math engine · M4 (Ahmed AboAsal) | [`math/`](src/main/java/edu/team/kmeans/math), [PR #14](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/pull/14) | `MeanCentroidUpdater` calculates means and retains empty-cluster centers. `EuclideanConvergenceChecker` checks maximum center movement. `EuclideanSSECalculator` uses the supplied assignments and centers. |
| Dataset and initialization · M5 (Bola Hany) | [`data/`](src/main/java/edu/team/kmeans/data), [M5 usage guide](docs/m5-datasets.md), [PR #15](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/pull/15) | `SyntheticDataGenerator.generatePoints(...)` produces seeded Gaussian 2D data. `RandomCentroidInitializer.initialize(...)` picks K distinct input coordinates. `validateAndDeduplicate(points, k)` is available for fit validation. |

**Verification.** [Java 17 GitHub CI](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/actions/runs/44727406670) passed all 45 existing tests (23 model, 11 math, 11 data) for the dataset PR. Its head has the same repository tree as the reviewed `main`. A local JUnit run also passed all 45 tests. A separate smoke check fed fixture assignments into the math services and passed seeded generator → initializer → math checks using known assignments. These checks establish component compatibility. Full sequential and Fork/Join fits still need implementation and end-to-end tests.

**Math review finding.** Three identical points with x = `Double.MAX_VALUE` can overflow the scaled sum in `MeanCentroidUpdater` even though their mean is representable. The edge case is documented in [#4](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/4). It does not block the fixture or ordinary generated datasets.

## Milestone 2 work

| Owner | What to implement | What to hand over |
|---|---|---|
| **M2 · Sequential and shared loop** · [#2](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/2) | In `sequential/`, implement nearest-center assignment. In `engine/`, build the shared loop around `AssignmentStrategy` and the three M4 services. Expose the sequential `KMeans` implementation. | Working sequential fit with known expected assignments, centroids, SSE and iteration counts. Agree the shared engine constructor with M3. |
| **M3 · Fork/Join** · [#3](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/3) | In `parallel/`, implement assignment using a real `ForkJoinPool` and disjoint point-index ranges. Join before returning. Honor the current fit's `taskThreshold` and `parallelism`, and close the owned pool. Plug the strategy into M2's loop. | Working parallel `KMeans` fit with the same results as the sequential reference on controlled inputs. Test both sides of the split threshold and pool cleanup. |
| **M1 · Integration** · [#1](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/1) | Coordinate the engine contract and PRs. Wire a runnable comparison once both engines are available. | One Java run that prepares a dataset and starts once, runs both engines and reports both results. |
| **M6 · Correctness comparison** · [#6](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/6) | Prepare parity tests and confirm the integrated engines on the fixture and seeded datasets. | Reproducible correctness evidence for later timing and speedup experiments. |

M2 and M3 can begin their assignment strategies independently on the same fixture. Integrate the sequential fit first as the reference, then plug in the Fork/Join strategy. Start work from current `main`. At the reviewed baseline, the remote `sequential-algorithm` branch only has a README change relative to its merge base.

### How to wire the pieces

1. Generate points once with `SyntheticDataGenerator` and choose starts once with `RandomCentroidInitializer`. Pass the same ordered points and the same explicit starting coordinates to both fits.
2. Validate the fit inputs, including `K > unique point count` and a starting-center count different from `config.k()`. Reuse the M5 validation helper while keeping duplicate points in the dataset passed to the engine.
3. Each iteration assigns every point to its nearest center by squared Euclidean distance, with ties going to the lowest cluster index. Keep caller inputs read-only. Update through `MeanCentroidUpdater`, then stop when maximum movement is at most the tolerance or the iteration cap is reached.
4. After stopping, reassign against the returned centroids and compute SSE from those final assignments and centroids. Count only completed assignment/update cycles as iterations. Follow [Architecture](docs/architecture.md) for snapshot semantics and pool lifecycle.

### What must pass before the next handoff

- [ ] Both assignment strategies return `[0, 0, 1, 1]` for `(1,1), (2,2), (8,8), (9,9)` with starts `(1,1), (8,8)`. The hand-checked updated centers are `(1.5,1.5), (8.5,8.5)`, with initial SSE `4.0` and final SSE `2.0`.
- [ ] Both engines agree on assignments for the fixed fixture and agree on centroids and SSE within floating-point tolerance on seeded inputs with identical starts.
- [ ] Tests cover ties, empty clusters, invalid inputs, both stopping rules, and final reassignment at the iteration cap. For a final-reassignment check, use points with x coordinates `0, 2, 3, 10` (all y = 0), starting x coordinates `0, 2`, and a one-iteration cap. Expected returned centers are `(0,0), (5,0)`, final assignments are `[0, 0, 1, 1]`, and SSE is `33.0`.
- [ ] Both full fits run on the same data and starts from a reproducible Java entry point or automated integration test. The review and Java 17 build pass.

From the repo root with JDK 17, run `./mvnw --batch-mode --no-transfer-progress clean verify` on macOS/Linux or `mvnw.cmd --batch-mode --no-transfer-progress clean verify` in Windows CMD. See [Getting started](docs/getting-started.md) for setup and [Experiments](docs/experiments.md) for the later benchmark protocol.
