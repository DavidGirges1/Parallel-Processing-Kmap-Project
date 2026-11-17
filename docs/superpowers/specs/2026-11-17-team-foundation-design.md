# Collaboration setup proposal

**Approved by the user on 2026-11-17.** Repository observations below describe the pre-setup inspection. Publication of the initial commit, setup PR, and seven issues was explicitly authorized.

Repository: https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project

Source: the attached `AGENTS.md` and its agreed seven-person team plan. GitHub inspection on 2026-11-17 confirmed that the public repository has no files, commits, or branches. The connected DavidGirges1 account has administrative access. No repository changes have been published.

## Goal and scope

Prepare the foundation that lets all seven members begin independent work on Project 4, Parallel K-Means Clustering with Fork/Join. Deliver a buildable Java project, shared contracts, onboarding instructions, automated checks, and scoped work items. The algorithm, mathematical implementations, benchmark measurements, and visualization remain the owners' subsequent work.

The course requires Java, sequential K-Means, actual Fork/Join assignment, SSE, controlled comparisons at multiple dataset sizes and K values, a 3–5 page report, and a 5–8 minute presentation. Java 17, Maven, JUnit 5, package names, and exact APIs are team tooling decisions. No benchmark results or algorithm completion will be claimed for the setup.

## Approaches

1. **Documentation-only setup:** least initial code, but leaves M2–M7 waiting for shared signatures and build configuration.
2. **Single Maven foundation with shared contracts — recommended:** follows the supplied agreed plan and lets each owner develop against the same types. Requires leader approval of the exact signatures below.
3. **Seven independent Maven projects or permanent branches per member:** adds integration and API coordination overhead; conflicts with the agreed single-repository workflow.

The attachment already selects a single Maven repository. This proposal carries that choice forward; the new decisions needing review are the concrete contracts and publishing steps.

## Repository foundation

- Preserve the supplied `AGENTS.md` verbatim at the repository root.
- Add `pom.xml` for Java 17, JUnit 5, pinned build-plugin/dependency versions, UTF-8, and a standard Maven test build. Add Maven Wrapper scripts so contributors need only a JDK and network access on first use.
- Add `.gitignore` for build output, IDE-local metadata, logs, and local environment files. Keep actual experiment evidence eligible for version control.
- Add `.editorconfig` for consistent whitespace and line endings.
- Create package directories under `src/main/java/edu/team/kmeans/`: `api`, `model`, `app`, `engine`, `sequential`, `parallel`, `math`, `data`, `benchmark`, and `ui`, with corresponding test areas. Use package documentation for unimplemented modules rather than runnable placeholder algorithms.
- Add model/API tests for the foundation and a shared deterministic assignment fixture: points `(1,1), (2,2), (8,8), (9,9)`, centroids `(1,1), (8,8)`, expected assignments `[0,0,1,1]`. The fixture is not an implemented clustering algorithm.
- Do not select a GUI toolkit or add GUI dependencies at this stage.

## Proposed shared contracts — leader review required

Use immutable 2D point records and immutable result objects. Validate finite coordinates and protect arrays using defensive copies. Input point/centroid lists are read-only for the duration of a run; implementations must not mutate caller data. Configuration validation lives in M1 types; dataset validation remains M5's responsibility.

| Contract | Proposed shape and responsibility |
|---|---|
| `Point` | `record Point(double x, double y)`; reject non-finite coordinates. |
| `KMeansConfig` | `record KMeansConfig(int k, int maxIterations, double tolerance, long seed, int taskThreshold, int parallelism)`; positive integers, finite nonnegative tolerance. |
| `KMeans` | `KMeansResult fit(List<Point> points, List<Point> initialCentroids, KMeansConfig config)`; both public engines use this contract. |
| `AssignmentStrategy` | `void assign(List<Point> points, List<Point> centroids, int[] assignments)`; output length equals point count. Parallel tasks write disjoint index ranges. |
| `CentroidUpdater` | `List<Point> update(List<Point> points, int[] assignments, List<Point> previousCentroids)`; M4 owns implementation. |
| `ConvergenceChecker` | `boolean hasConverged(List<Point> previous, List<Point> current, double tolerance)`; M4 owns implementation. |
| `SSECalculator` | `double calculate(List<Point> points, int[] assignments, List<Point> centroids)`; M4 owns implementation. |
| `CentroidInitializer` | `List<Point> initialize(List<Point> points, int k, long seed)`; M5 owns implementation. |
| `IterationSnapshot` | Immutable iteration number, centroid coordinates, and assignments for M7; copies mutable inputs. Snapshot collection is opt-in through a callback supplied to the engine, so benchmark runs avoid snapshot allocation. Exact callback wiring is settled before engine implementation. |
| `KMeansResult` | Immutable final centroids, assignments, SSE, and iteration count; defensive copies. M6 measures runtime around `fit`, with no fabricated timing fields. |

### Proposed consistent algorithm policies

These policies are proposals for the owners' later implementations, not algorithms delivered by the setup:

- Select the lowest centroid index on a distance tie.
- Keep the previous centroid when a cluster is empty.
- Reject empty input, invalid parameters, non-finite data, incompatible initial centroid counts, and `K` larger than the number of distinct input points with clear errors.
- Converge when maximum Euclidean centroid movement is at most `tolerance`, or stop at `maxIterations`.
- Before returning, assign points once more against the returned centroids and compute SSE from those same assignments and centroids. Count iterations as completed assignment/update cycles; document the extra final assignment in benchmarking methodology.
- Pass identical point coordinates and identical initial centroid coordinates to sequential and parallel engines; a shared seed alone does not establish a controlled comparison.
- M2 implements one iteration loop. M3 substitutes its assignment strategy and manages its pool; it does not duplicate the loop or M4's math.

## Collaboration files and guidance

- `README.md`: project purpose, accurate setup-only status, Java/Maven quick start for Windows/macOS/Linux, build/test commands, team/module map, and links to the guides.
- `CONTRIBUTING.md`: choose an issue, create a short-lived branch, make scoped edits and tests, open a PR, obtain another member's review and passing CI, then merge.
- `docs/architecture.md`: contracts, dependencies, lifecycle, safe Fork/Join writes, and the identical-initial-centroids rule.
- `docs/ownership.md`: M1–M7 ownership from the attachment, with real names/usernames explicitly pending confirmation.
- `docs/workflow.md`: branch examples, review responsibilities, definition of done, conflict handling, and integration gates.
- `docs/experiments.md`: correctness-first measurement protocol and required real evidence: machine/JDK, N, K, seed, initial centroids, thresholds, parallelism, warmups/trials, total/per-iteration time, iterations, SSE, and speedup. Include no invented numbers.
- `docs/deliverables.md`: report/presentation checklist and individual oral topics, preserving the distinct bonus descriptions in the attachment.
- `.github/PULL_REQUEST_TEMPLATE.md`: linked issue, owner/scope, contract changes, tests, and evidence.
- `.github/ISSUE_TEMPLATE/`: module-task and bug-report forms, requesting acceptance criteria, dependencies, affected files, and test/demo evidence.
- `results/README.md`: actual measured outputs only; mock development data stays separately identified.
- No license selection without a team decision; no invented CODEOWNERS accounts.

## Automated checks

GitHub Actions runs a named `build-and-test` check on pull requests and pushes to `main`, using a Java 17 setup, Maven dependency caching, read-only repository permissions, and a bounded timeout. Run `./mvnw --batch-mode --no-transfer-progress verify`. Avoid privileged `pull_request_target` workflows.

Foundation verification must demonstrate that a clean checkout compiles, its model/contract tests execute successfully, and wrapper commands match the README and CI. This establishes a working development baseline, not the completed K-Means assignment. The current environment has Java and Git but no `mvn` command; use the wrapper for verification.

## Seven starter work items

Publish as GitHub issues only after permission to publish the setup. Each issue will contain scope, dependencies, acceptance criteria, tests, and demonstration evidence. Leave assignees unset until real usernames are supplied.

| Owner | Issue | Acceptance and dependencies |
|---|---|---|
| M1 | Project foundation, shared contracts, and integration | Foundation builds; contracts are reviewed by M2–M5; guides and automated checks work. Mark complete only once the foundation is actually merged and verified. Later integration remains open. |
| M2 | Sequential assignment and shared iteration engine | Fixture assignments pass; engine calls M4 services, implements stopping/final-result rules, and exposes sequential K-Means. Assignment can start after contracts; full loop needs M4. |
| M3 | Fork/Join assignment and parallel K-Means | Real tasks split at a configurable threshold, write disjoint ranges, and match sequential fixture outputs; pool lifecycle is tested. Full wrapper needs M2/M4. |
| M4 | Centroid means, convergence, SSE, and empty clusters | Independently tested math and documented policies; duplicate points, empty clusters, and invalid inputs covered. Needs shared models/contracts. |
| M5 | Reproducible datasets and centroid initialization | Fixed-seed synthetic 2D data, explicit initial centroids, validation of K/data, and tests. CSV/K-Means++ deferred until core is stable. |
| M6 | Correctness integration and reproducible experiments | Compare both engines on identical inputs; test edge cases; produce real multi-N/multi-K timing/SSE evidence. Fixtures/harness can start immediately; final comparisons need integrated engines. |
| M7 | Integrated 2D visualization and demo | Show real Java results and centroids; clearly label development mocks; avoid implementing a second clustering algorithm. UI toolkit remains a team choice. |

Suggested issue tracking: Backlog → Ready → In Progress → In Review → Done. A live GitHub Project board is optional and must not block onboarding; document this workflow even if board creation is unavailable.

## Repository administration and publication

The attachment explicitly says: “Do not push, merge, deploy, or overwrite other people's work without explicit permission.” Implementation preparation and verification can happen locally after design approval. Publishing requires explicit permission.

Because the repository has no initial commit, opening a normal setup pull request requires a base branch first. Proposed publishing sequence:

1. Create a minimal initial `main` commit with project identity and the supplied context.
2. Publish the full reviewed foundation on `chore/team-foundation` and open a pull request against `main`.
3. Create the seven unassigned module issues and link them from the documentation/PR.
4. Leave the setup PR for review and merge; do not merge automatically. Gate 0 is only reached after the approved foundation is on `main` and its checks pass.

Recommended admin settings: enable Issues, use squash merging for small PRs, delete merged feature branches, and protect `main` with PRs, one approving reviewer, the passing `build-and-test` check, resolved conversations, and blocked force pushes/deletions. Configure the required check after its first observed run. Ensure the leader can administer setup without locking an empty repository. Apply settings through supported tools where available; report any unavailable settings with exact owner steps rather than claiming they were applied.

Invite the six other members using confirmed GitHub usernames. They need repository write access to push branches. Add CODEOWNERS only after names, ownership, and collaborator eligibility are confirmed. Never fabricate usernames, invitations, deadlines, successful checks, or finished tasks.

## Completion evidence and next gate

Report created files, actual verification commands/results, published commit/PR/issue links if authorized, and outstanding teammate/admin steps. The next integration gate is a reviewed, tested foundation on `main`; then M2–M7 can work concurrently within their scopes.

## Approval requested

Approve the single Maven foundation and proposed contracts/policies above, and state whether publishing the initial commit, setup branch/PR, and seven issues is authorized. Actual teammate usernames can follow separately; they are not needed to prepare the foundation.
