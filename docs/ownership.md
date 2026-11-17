# What each member is responsible for

**Start here after setup.** Ask the leader which M1–M7 role is yours. Names and GitHub assignments are still to be confirmed; these labels are not usernames.

All source paths below start at `src/main/java/edu/team/kmeans/`. Put your own unit tests in the matching package under `src/test/java/edu/team/kmeans/`.

## Jump to your role

- [M1: Team leader and integration](#m1-team-leader-and-integration)
- [M2: Sequential algorithm and shared loop](#m2-sequential-algorithm-and-shared-loop)
- [M3: Parallel assignment with Fork/Join](#m3-parallel-assignment-with-forkjoin)
- [M4: Centroid math, stopping, and SSE](#m4-centroid-math-stopping-and-sse)
- [M5: Data and starting centers](#m5-data-and-starting-centers)
- [M6: Complete-program tests and performance](#m6-complete-program-tests-and-performance)
- [M7: Visualization and demo](#m7-visualization-and-demo)

## Everyone does these things

1. Build your assigned part and test your own behavior.
2. Keep changes in your agreed folders; discuss shared API/other-owner changes first.
3. Share small PRs and review another member's work.
4. Write your report contribution and help prepare the presentation.
5. Be able to explain your code and the four oral-question topics in [Deliverables](deliverables.md).

**Before coding:** follow [Getting started](getting-started.md), then [Contributing](../CONTRIBUTING.md).

## M1: Team leader and integration

**Your task:** [Issue #1](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/1)

**Your folders:** `api/`, `model/`, `app/`

**Your job:** Keep shared types consistent, help everyone connect their modules, and build the eventual application entry point.

### Do this first

1. Confirm the six teammates' GitHub usernames and map names to M1–M7.
2. Help each member accept their invitation and get a successful build.
3. Walk M2–M5 through the shared interfaces and agree any questions before they code.

### Then build

1. Coordinate small integration PRs and changes to shared models/interfaces.
2. Connect data, engines, and results in app/ once those parts exist.
3. Provide a reproducible real Java demo command when the application is ready.

### What you receive and provide

You provide shared models/contracts to everyone. You need M2–M5 for the real application, M6 for evidence, and M7 for visualization.

### Done when

- [ ] Shared contract changes are agreed with affected owners and the build stays usable.
- [ ] The integrated Java application runs with documented commands.
- [ ] The team can reproduce the demo; setup alone is not completion of integration.

**Start/wait:** The foundation is already merged in PR #8. Keep issue #1 open for remaining coordination and application integration.

## M2: Sequential algorithm and shared loop

**Your task:** [Issue #2](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/2)

**Your folders:** `engine/`, `sequential/`

**Your job:** Build sequential point assignment and the single iteration loop used by both sequential and parallel engines.

### Do this first

1. Read AssignmentStrategy and the shared AssignmentFixture in the paths below.
2. Write a test using its fixed points and centers; expected assignments are [0, 0, 1, 1].
3. Implement sequential nearest-center assignment, including lowest-index tie handling.

### Then build

1. Build the loop: assign points → ask M4 to update centers → ask M4 whether to stop → repeat.
2. Stop at the iteration limit or movement tolerance, then use the agreed final-assignment/SSE policy.
3. Expose complete sequential K-Means; support the agreed optional iteration-observer hook for M7.

### What you receive and provide

You receive shared contracts from M1 and math services from M4. M5 supplies normal datasets/starts. M3 uses your shared loop with its parallel assignment strategy.

### Done when

- [ ] Your real assignment passes the hand-checked fixture and tie/edge-case tests.
- [ ] Complete sequential K-Means works with M4 services and obeys both stopping rules.
- [ ] The loop is reusable by M3, with no copied M4 math.

**Start/wait:** You can start assignment now. Finishing the full algorithm needs M4; do not wait to begin or duplicate M4's work.

## M3: Parallel assignment with Fork/Join

**Your task:** [Issue #3](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/3)

**Your folders:** `parallel/`

**Your job:** Split point assignment into real Java Fork/Join tasks and connect that strategy to the shared engine.

### Do this first

1. Read AssignmentStrategy and the same AssignmentFixture used by M2.
2. Write a parallel-assignment test expecting [0, 0, 1, 1].
3. Implement chunked Fork/Join tasks: each task writes only its own point-index range.

### Then build

1. Make task threshold and worker count configurable, and wait for all tasks before returning.
2. Manage/reuse your owned pool and shut it down through the agreed lifecycle.
3. Connect your strategy to M2's engine and expose complete parallel K-Means.

### What you receive and provide

You receive shared types from M1 and the loop from M2. M4 supplies math used by that loop. You provide parallel assignment/results for M6 and M7.

### Done when

- [ ] Real Fork/Join tasks pass fixture, odd-size, threshold, and worker-count tests.
- [ ] Writes are safe and the owned pool is cleaned up correctly.
- [ ] The full parallel engine works with the shared loop; actual speedup is measured with M6.

**Start/wait:** You can test assignment independently now. The full parallel engine waits for M2/M4; do not copy their loop or math.

## M4: Centroid math, stopping, and SSE

**Your task:** [Issue #4](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/4)

**Your folders:** `math/`

**Your job:** Implement the math services both engines call: new centers, convergence, and clustering error.

### Do this first

1. Read CentroidUpdater, ConvergenceChecker, and SSECalculator.
2. Use fixed points (1,1), (2,2), (8,8), (9,9) with assignments [0,0,1,1].
3. Write hand-checked tests: updated centers are (1.5,1.5) and (8.5,8.5); SSE against those centers is 2.0.

### Then build

1. Compute means, retaining the previous center for an empty cluster.
2. Check maximum Euclidean center movement against the tolerance.
3. Compute SSE from the supplied points, assignments, and centers without changing them.

### What you receive and provide

You need only M1's models/interfaces to start. M2's loop calls your services, so M2/M3 need your implementations for complete K-Means.

### Done when

- [ ] Means, SSE, movement, and tolerance-boundary tests pass.
- [ ] Empty clusters and invalid inputs follow the agreed policies.
- [ ] Both engines use your shared math instead of separate copies.

**Start/wait:** You can start immediately with fixed data; you do not need a generator or a completed engine.

## M5: Data and starting centers

**Your task:** [Issue #5](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/5)

**Your folders:** `data/`

**Your job:** Create reproducible sample points and initial centers before clustering starts.

### Do this first

1. Read Point, KMeansConfig, and CentroidInitializer.
2. Generate a small synthetic 2D dataset with an explicit seed.
3. Write a test showing the same seed/configuration produces the same data and starting centers.

### Then build

1. Validate input and reject K larger than the number of distinct points.
2. Return explicit starting-center coordinates that callers can pass to both engines.
3. Document dataset parameters; defer optional CSV/K-Means++/restarts until required work is stable.

### What you receive and provide

You need M1's shared types. You give points and initial centers to M2/M3 and reproducible inputs to M6. M4, not M5, updates centers during iterations.

### Done when

- [ ] Generation/initialization is repeatable and invalid/duplicate/empty inputs are tested.
- [ ] Both engines can receive the exact same points and initial centers.
- [ ] Required synthetic data support is complete before optional extras.

**Start/wait:** You can start independently now. Your responsibility is preparation/initialization, not repeated centroid updates.

## M6: Complete-program tests and performance

**Your task:** [Issue #6](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/6)

**Your folders:** `benchmark/`; integration tests under `src/test/java/edu/team/kmeans/integration/`

**Your job:** Check that the assembled engines agree, then measure real runtimes and SSE fairly.

### Do this first

1. Read the experiment protocol and reuse the shared fixture.
2. List the complete-program edge cases and prepare small expected-answer datasets.
3. Prepare the experiment runner/configuration and output format without inventing measurements.

### Then build

1. Run both complete engines on identical data, K, and starting-center coordinates.
2. Warm up and repeat runs across multiple sizes/K/thresholds; record total time, iteration time, iterations, SSE, and speedup.
3. Save actual samples and machine/JDK details in results/, then explain overhead and results.

### What you receive and provide

You can prepare fixtures/harness now. Real end-to-end comparisons need integrated M2–M5. Each owner still writes unit tests for their own code.

### Done when

- [ ] Complete-engine correctness and edge-case comparisons pass.
- [ ] Experiments are repeatable and required metrics come from actual runs.
- [ ] Tables/plots have raw evidence and explain when parallelism helps or hurts.

**Start/wait:** Name integration tests SomethingTest.java so Maven runs them. Final timings wait for working engines; test/harness preparation does not.

## M7: Visualization and demo

**Your task:** [Issue #7](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/7)

**Your folders:** `ui/`

**Your job:** Show points, cluster colors, and centers using results produced by the Java program.

### Do this first

1. Read KMeansResult, IterationSnapshot, and IterationObserver.
2. Agree the UI toolkit with the leader before adding dependencies.
3. Prepare a basic scatter view with clearly labeled development mock data.

### Then build

1. Connect the view to actual Java results when engines are available.
2. Show centers and, if supported, movement across iteration snapshots.
3. Prepare the real-result demo and handle UI-thread updates without changing engine state.

### What you receive and provide

You use M1's result types and M2/M3's real engine outputs. The shared engine supplies optional snapshots. You do not implement another clustering algorithm in the display layer.

### Done when

- [ ] The view displays real clustered points and centers from Java outputs.
- [ ] Mocks are clearly separate from the real demo and measured results.
- [ ] Another teammate can reproduce the integrated demo.

**Start/wait:** This is the agreed M7 task and an optional course bonus. It must not delay required Java correctness or benchmark work; no bonus mark is guaranteed.

## Shared files to read before implementing

| What you need | Where it is |
|---|---|
| Point, config, results, snapshots | `src/main/java/edu/team/kmeans/model/` |
| Shared method contracts | `src/main/java/edu/team/kmeans/api/` |
| Fixed assignment fixture for M2/M3 | `src/test/java/edu/team/kmeans/support/AssignmentFixture.java` |
| Exact signatures and algorithm policies | [Architecture](architecture.md) |
| Test/benchmark method | [Experiments](experiments.md) |

**Interface** means the agreed method inputs/outputs; **fixture** means fixed test data. See [Glossary](glossary.md) for other unfamiliar terms.

## When you are blocked

1. Identify the exact input/method you need and the owner who provides it.
2. Tell the leader and record the blocker on your issue, linking the other issue where possible.
3. Continue an independent piece, such as a fixed-data test, if one exists.
4. Do not implement someone else's production logic just to bypass the dependency.

Do not close your module issue after only its first small PR. Close it only when its full completion checklist is satisfied. CODEOWNERS and named assignments will be added after actual usernames/access are confirmed.
