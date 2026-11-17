# Architecture and shared contracts

This is the team architecture, not a professor-prescribed class API. M1 owns `api/`, `model/`, `app/`; affected owners review contract changes. The foundation supplies types and interfaces only. Read `../AGENTS.md` for authoritative requirements.

## Dependency flow

M5 prepares points and initial centroids → M2 shared engine repeats assignment (M2 sequential or M3 Fork/Join), M4 update, and M4 convergence → M4 SSE yields final result → M6 measures / M7 displays. M2 and M3 can test assignment independently before full math integration. Do not duplicate M4 math or the shared loop.

All types are in `edu.team.kmeans`. Models live in `model`; interfaces live in `api`.

| Type | Public contract |
|---|---|
| `Point` | `Point(double x, double y)`; immutable finite 2D coordinates |
| `KMeansConfig` | `KMeansConfig(int k, int maxIterations, double tolerance, long seed, int taskThreshold, int parallelism)` |
| `KMeansResult` | `KMeansResult(List<Point> centroids, int[] assignments, double sse, int iterations)`; read via same-named accessors |
| `IterationSnapshot` | `IterationSnapshot(int iteration, List<Point> centroids, int[] assignments)`; read via same-named accessors |
| `KMeans` | `KMeansResult fit(List<Point> points, List<Point> initialCentroids, KMeansConfig config)` |
| `AssignmentStrategy` | `void assign(List<Point> points, List<Point> centroids, int[] assignments)` |
| `CentroidUpdater` | `List<Point> update(List<Point> points, int[] assignments, List<Point> previousCentroids)` |
| `ConvergenceChecker` | `boolean hasConverged(List<Point> previous, List<Point> current, double tolerance)` |
| `SSECalculator` | `double calculate(List<Point> points, int[] assignments, List<Point> centroids)` |
| `CentroidInitializer` | `List<Point> initialize(List<Point> points, int k, long seed)` |
| `IterationObserver` | `void onIteration(IterationSnapshot snapshot)`; optional shared-engine constructor dependency |

Config requires positive `k`, `maxIterations`, `taskThreshold`, and `parallelism`; tolerance must be finite and nonnegative. Any `long` seed is valid. Dataset-dependent validation belongs to M5 and fit entry-point integration, not the config record.

Results and snapshots require nonempty centroid/assignment collections, in-range cluster indices, positive completed-iteration counts, and nonnull finite points. SSE must be finite and nonnegative. Arrays are copied on input and access; centroid lists are immutable copies. Null inputs/elements throw `NullPointerException`; invalid numerical/state values throw `IllegalArgumentException`. These objects validate structure, not that a caller supplied the mathematically correct means or SSE.

## Service input rules

The Java comments in `api/` define the implementer contract: nonempty point/centroid lists without nulls, matching assignment-array lengths, valid cluster indices, and read-only inputs. Assignment writes every output position. Null values cause `NullPointerException`; invalid sizes/indices/parameters cause `IllegalArgumentException`. The interfaces themselves do not execute checks. M4 services and M2/M3 assignment implementations validate their own direct inputs; M5 provides dataset validation for complete fit calls. See [M1 handoff](m1-handoff.md).

## Engine policies for owner implementation

- `fit` rejects empty input, invalid data, `K > unique points`, and initial centroid count different from `config.k()`. M5 provides reusable dataset validation. Use squared Euclidean distance for assignment and SSE; do not add other metrics before the core works.
- Treat caller points, centroid lists, and config as read-only. Owners must not mutate input collections. Make a stable centroid snapshot per iteration before launching assignment tasks.
- Distance ties choose the lowest cluster index. Empty clusters retain their previous centroid.
- Converge when the maximum Euclidean movement of a centroid is `<= tolerance`; also enforce `maxIterations`.
- Count one iteration for each completed assignment/update cycle. After stopping, perform a final assignment against returned centroids and calculate SSE from those exact assignments/centroids. The final reassignment is included in total fit time; final centroids need not be the means of this final reassignment when stopped at the iteration limit.
- A snapshot describes the end of a completed update cycle: centroids after updating and the assignments used for that update. It is an iteration trace, not the final result contract. At an iteration limit, snapshot assignments can differ from the final result's reassignment.
- For controlled comparisons, pass the exact same initial centroid coordinates to both fits. Do not rely only on the seed or compare independently generated starts.

## Fork/Join and lifecycle

M3 implements real `RecursiveAction`/equivalent Fork/Join tasks on chunk ranges. Tasks read identical centroid coordinates and write exclusively within their assigned output indices. Finish/join all tasks before M4 reads the assignments. Do not spawn one task per point or let tasks mutate shared centroid sums/counts. If updates are later parallelized, use local accumulators and a safe reduction.

The parallel engine owns a configured `ForkJoinPool`, reused across iterations and fits with the same worker count; M3 should expose `AutoCloseable` on its concrete engine and shut down/wait for its owned pool in `close()`. Do not shut down the common pool. Document pool creation/teardown separately from measured fit time. `taskThreshold` controls split granularity; `parallelism` controls pool workers. Honor the configuration passed to each fit: if the worker count changes between fits, replace the idle owned pool, and configure assignment with that fit's threshold. Never silently reuse incompatible settings.

## Optional observation

`IterationObserver` is a hook for M2's shared-engine constructor, separate from the three-argument `KMeans.fit` API. M2 provides an engine constructor taking its assignment/math collaborators and using `IterationObserver.NONE`, plus an overload accepting an observer. Agree concrete constructor signatures with M3 during integration; a no-argument constructor is not required. Check for `NONE` before allocating a snapshot, so headless benchmarking pays no snapshot-copy cost. Notify synchronously after all Fork/Join work and centroid updating finish; the UI must marshal updates to its own thread. Observer errors propagate and stop the run; callbacks must not reenter the engine. Engine instances need not support concurrent fits.

M7 consumes Java results/snapshots. UI toolkit selection is pending. Development mocks must be labeled and kept separate from measured results. A JavaScript-only clustering simulation does not replace Java Fork/Join.

## Shared assignment fixture

`src/test/java/edu/team/kmeans/support/AssignmentFixture.java` provides points `(1,1),(2,2),(8,8),(9,9)`, starts `(1,1),(8,8)`, and hand-derived expected assignments `[0,0,1,1]`. M2/M3 tests call their real strategies with this fixture. It establishes neither convergence nor benchmark performance.
