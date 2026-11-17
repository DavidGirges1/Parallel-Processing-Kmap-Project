# M1 handoff: what is ready for the team

**Leader:** [DavidGirges1](https://github.com/DavidGirges1). The foundation and beginner guides are merged in PRs #8 and #9. This handoff finishes the contract clarification needed for independent module work; it does not claim that the algorithms are implemented.

## Ready to use

- Java 17, one Maven project, Wrapper, JUnit, and GitHub `build-and-test`.
- Immutable `Point`, `KMeansConfig`, `KMeansResult`, and `IterationSnapshot`.
- The existing service signatures below, with input/output rules in their Java comments.
- Shared assignment fixture with fixed points and known expected indices.
- Confirmed M1 review routing in `.github/CODEOWNERS`. Other accounts remain pending.

There are no new frameworks, production stubs, or copies of another member's implementation. Interfaces define behavior; they do not perform input validation or calculations themselves. Each owner implements and tests that behavior.

## Start here by member

| Owner | Read/use | First independent deliverable |
|---|---|---|
| M2 | `AssignmentStrategy`, fixture, M4 service interfaces | Sequential assignment returning `[0,0,1,1]`; then shared loop |
| M3 | Same `AssignmentStrategy` and fixture | Fork/Join assignment with disjoint output ranges |
| M4 | `CentroidUpdater`, `ConvergenceChecker`, `SSECalculator` | Independently tested mean, movement, and SSE calculations |
| M5 | `Point`, `KMeansConfig`, `CentroidInitializer` | Seeded synthetic points and explicit initial centers |
| M6 | Same shared types/fixture and [experiment protocol](experiments.md) | Test fixtures, integration plan, experiment harness |
| M7 | `KMeansResult`, `IterationSnapshot`, `IterationObserver` | Agree toolkit; draw labeled development data |

Source interfaces are under `src/main/java/edu/team/kmeans/api/`. Models are under `model/`. The fixture is `src/test/java/edu/team/kmeans/support/AssignmentFixture.java`. Exact signatures and engine policies are in [Architecture](architecture.md); the [member guide](ownership.md) lists tasks and dependencies.

## Shared rules to implement consistently

1. Inputs are read-only, except `AssignmentStrategy`'s output array. Assignment must overwrite every output position.
2. Points/centroids passed to algorithm services are nonempty and contain no nulls. Assignment arrays have exactly one valid cluster index per point.
3. Null inputs/elements cause `NullPointerException`; invalid sizes, indices, K, or tolerance cause `IllegalArgumentException`. Model constructors already enforce their own checks; owners implement service checks.
4. Assignment uses squared Euclidean distance and lowest-index tie breaking. Duplicate centers are allowed for assignment/update tests; fit rejects K above distinct point count.
5. Empty clusters retain their previous center. Convergence compares maximum Euclidean center movement to `tolerance`, including equality and zero tolerance.
6. Initialize once for a comparison, then give both engines identical ordered points and initial-center coordinates. The initializer chooses K distinct input coordinates reproducibly.
7. The shared loop uses M4 services. After stopping, reassign against the returned centers and calculate SSE from that pair. Iteration count is completed update cycles, not final reassignment.
8. Snapshots represent update-cycle state, while final results include final reassignment. Observer errors propagate. Skip snapshot copying entirely when using `IterationObserver.NONE`.
9. M3 honors the threshold/workers for the current fit and owns pool cleanup. Finish all assignment tasks before updating centers. Coordinate the concrete engine constructors with M2 during integration; independent assignment work does not need those constructors yet.

### Hand-checked example

| Item | Expected value |
|---|---|
| Points | `(1,1), (2,2), (8,8), (9,9)` |
| Starting centers | `(1,1), (8,8)` |
| Nearest-center assignments | `[0,0,1,1]` |
| Updated means for those assignments | `(1.5,1.5), (8.5,8.5)` |
| SSE against starting centers | `4.0` |
| SSE against updated means | `2.0` |

These are arithmetic expectations for owner tests, not executed algorithm outputs or benchmark measurements.

## M1 work that still requires people or other modules

- **People/access:** collect the six teammate usernames, invite them, map roles, and confirm they can build. Their accounts cannot be invented. Extend CODEOWNERS once access is confirmed.
- **Team acknowledgment:** M2–M5 should read these contracts and raise any mismatch before coding. Publishing a handoff is not evidence that they have already agreed to it.
- **Administration:** branch protection and automatic branch deletion need owner configuration; connected administration writes previously returned HTTP 403. Follow [the settings checklist](github-setup.md).
- **Later application integration:** once M2–M5 merge real services, M1 wires the application entry point and real demo. It cannot be completed honestly while those implementations are absent.

Issue [#1](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/1) stays open for remaining M1 integration and access work. The team can start the independent tasks above without waiting for that whole issue to close.
