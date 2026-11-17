# Codex Project Context and Team Working Agreement

> **Project:** Project 4 — Parallel K-Means Clustering with Fork/Join  
> **Course:** Parallel Processing, Fall 2026  
> **Team:** 7 members total, including the team leader  
> **Implementation language:** Java (required by the assignment)  
> **Status:** Agreed team plan and technical direction; implementation and individual assignments are not confirmed complete.

## 0. How Codex must use this file

This repository concerns **Project 4 only**. Do not implement requirements from other projects appearing in the two course PDFs.

### Authority and interpretation

1. **Official university requirements are authoritative:** Sections 1–3 below reproduce/faithfully summarize the Project 4 brief and the v2 detailed rubric. Follow them before any team preferences.
2. **Team agreements are project-specific decisions, not university requirements:** Sections 4–10 describe our agreed seven-person development plan, proposed architecture, and workflow. They may be refined by the team leader, provided the official requirements remain satisfied.
3. **Suggestions are not mandates:** Concrete example interfaces, package names, build tooling, test values, and schedules below are team recommendations. Do not claim the professor required them.
4. **Never fabricate evidence:** No made-up runtime, SSE, speedup, benchmark screenshots, successful tests, or completed tasks. Only report measurements actually obtained.
5. **Avoid scope creep:** Prioritize the required Java algorithm, Fork/Join, correctness, and benchmarks before bonuses or cosmetic UI changes.
6. **Respect module ownership:** When asked to implement a member's module, keep edits within that module and its tests unless shared changes are necessary and approved. Explain cross-module contract changes before making them.
7. **Do not push, merge, deploy, or overwrite other people's work without explicit permission.** Propose small reviewable pull requests. Keep the application buildable.
8. **Do not infer implementation progress from this context file.** Inspect the current repository and tests to establish what exists.

### Primary sources

- **Official A:** `ParallelProcessing_ProjectIdeas_Fall2026.pdf`, **Project 4 on PDF pages 6–7**.
- **Official B:** `Parallel_Projects_Detailed_Rubrics_v2.pdf`, **standard scoring on PDF page 1 and Project 4 rubric on PDF pages 8–9**.

These filenames refer to supplied course materials; they might not be committed in the repository. This file contains the relevant Project 4 requirements so the agent can work even if the PDFs are not available locally. If the PDFs are present, prefer verifying against them for exact wording.

---

## 1. OFFICIAL: Project 4 assignment specification (Official A, pp. 6–7)

### Title and purpose

**Project 4 — Parallel K-Means Clustering with Fork/Join**

Implement K-Means in Java, then parallelize it with Java's Fork/Join framework. Cluster data points into `K` groups; compare **sequential versus parallel performance** and **clustering quality**.

### Learning goals

- Understand and implement K-Means clustering.
- Apply Fork/Join to a data-parallel workload.
- Structure a machine-learning algorithm using object-oriented programming.
- Compute and interpret clustering quality using **SSE (sum of squared errors)**.

### All required functional capabilities

**A. Sequential K-Means**

- Represent at least **2D data points**.
- Initialize `K` centroids, for example by selecting random data points.
- Repeatedly **assign each point to the nearest centroid**.
- **Recompute centroids** as the mean of their assigned points.
- Stop after a configured maximum number of iterations **or** when centroid movement is below a configured tolerance/threshold.

**B. Parallel K-Means with Fork/Join**

- Use real Java **Fork/Join** parallelism for **at least the point-assignment step**.
- Divide points into chunks and let tasks assign their own subsets to their nearest centroids.
- Parallelizing centroid recomputation is **optional**, not required.

**C. Data and evaluation**

- Use **either** a generated synthetic 2D dataset **or** a simple CSV input. Both are not required.
- Compute **SSE** for clustering quality.
- Compare sequential and parallel **runtimes and SSE**.

### Official suggested OOP structure (suggestions, not prescribed exact names)

| Suggested class | Purpose |
|---|---|
| `Point` or `Vector` | Represents points, e.g., `double[]` or `x,y`. |
| `Cluster` | Holds a centroid and assigned points/indices. |
| `KMeansConfig` | Config such as `k`, `maxIterations`, `tolerance`. |
| `KMeansSequential` | Sequential clustering implementation. |
| `KMeansParallel` | Parallel implementation using `ForkJoinPool` and an inner `KMeansAssignTask`. |
| `DataSetLoader` | Optional helper when choosing CSV input. |
| `KMeansExperiment` | Runs both implementations and records runtime/SSE. |

The solution must demonstrate clean OOP separation. The team is free to adapt this suggested class structure as long as the required functionality is clear.

### Official suggested implementation roadmap

1. Implement `Point`, `Cluster`, and sequential K-Means on a small synthetic dataset; check whether clusters look reasonable.
2. Implement parallel assignment with Fork/Join (`RecursiveAction` is suggested) using chunks.
3. Add SSE and experiment runner; test several `K` values and dataset sizes.
4. Collect time/SSE tables or plots and discuss when parallelism helps and how clustering quality behaves.
5. Write the report explaining algorithm, OOP design, and results.

### Official deliverables

1. **Java source code:** Domain classes, sequential and parallel K-Means, a data-loading helper if used, and experiments.
2. **Report: approximately 3–5 pages:** K-Means explanation, Fork/Join design, experiments with **different K values and dataset sizes**, runtime/SSE comparisons, analysis of behavior and tradeoffs.
3. **Short team presentation: 5–8 minutes:** Algorithm, design, key results, and lessons learned.

### Official A bonus description: up to +5 points

- **Up to +3:** GUI or visualization, e.g., 2D colored scatter plot or centroid-movement animation.
- **Up to +2:** Extra features, e.g., improved initialization (K-Means++-like) or multiple random restarts selecting the lowest-SSE result.

**Source:** Official A, PDF pages 6–7. These are bonus examples, not core requirements.

---

## 2. OFFICIAL: Grading model (Official B, p. 1; Project 4 on pp. 8–9)

| Category | Marks | Evidence needed for excellent performance |
|---|---:|---|
| **A. Functional correctness** | **6** | Correct nearest-centroid assignment/update; correct stopping rules; consistent sequential/parallel clustering quality on the same dataset, allowing small floating-point differences; safe handling of empty clusters, `K > unique points`, and invalid inputs. |
| **B. Parallel design and performance** | **5** | Correct Fork/Join design and configurable threshold; avoid shared mutable contention using non-overlapping writes and/or local accumulators with safe reduction; varying dataset sizes and `K`, **time per iteration and total time**, interpretation of when speedup occurs and why. |
| **C. OOP design and code quality** | **2** | Separated point/centroid domain, K-Means abstraction, sequential and parallel engines, data and experiments; configurable `K`, iterations, threshold, seed; clean APIs with little hard-coding. |
| **D. Report and presentation quality** | **2** | Clear K-Means explanation, parallel/reduction explanation, runtime-versus-size/`K`, iterations and SSE with analysis; demo runs a dataset and shows sequential/parallel timing. |
| **E. Individual oral/quiz** | **5** | Each student independently explains algorithm, stopping criteria, empty clusters, safe parallel aggregation and follow-up questions accurately. |
| **Total (base)** | **20** | **Individual oral is INCLUDED in the 20.** |
| **F. Bonus** | **up to +5 separately** | Must be working, integrated, and demonstrated. |

### Official evaluation bands, summarized faithfully

- **Functional correctness (6):** 6 excellent as above; **4–5** core works with minor convergence/empty-cluster/consistency issues; **2–3** partial/unstable logic and weak tests; **0–1** incorrect/failing or no real sequential/parallel validation.
- **Parallel design (5):** 5 effective, race-safe Fork/Join and credible experiments; **3–4** works but limited measurement/justification; **1–2** contention/task explosion or very weak performance evidence; **0** no meaningful/correct parallel implementation.
- **OOP (2):** **2** clean modular structure; **1** mixed responsibilities or incomplete encapsulation; **0** monolithic/unmaintainable.
- **Report/presentation (2):** **2** complete explanation, data/plots, interpretation and running demonstration; **1** basic/limited; **0** missing or disconnected from implementation.
- **Individual oral (5):** **5** detailed accurate explanations; **3–4** good basics with gaps; **1–2** superficial; **0** cannot explain code/concepts/contribution.

### Individual oral: exact topics from the Project 4 question bank

The official rubric suggests the instructor select 2–3 questions per student:

1. **What does K-Means optimize intuitively, and why can it converge to local minima?**
2. **How do you handle empty clusters, and why do they occur?**
3. **Which step did you parallelize, and why is it the bottleneck?**
4. **How did you avoid shared-state contention when accumulating centroid sums?**

All seven team members must understand these, regardless of individual ownership.

### Official B bonus qualitative scoring

- **+5:** High-quality **2D visualization showing clusters and centroids per iteration**, **OR** several distance metrics and initialization strategies (e.g., K-Means++) with evidence and clean architecture.
- **+3–4:** One strong, implemented and demonstrated enhancement (visualizer, improved initialization, or quality-metrics dashboard).
- **+1–2:** Minor extras such as basic plotting/export only.
- **+0:** Not functional or not integrated.

**Important source distinction:** Official A describes a **+3 visualization / +2 extra-feature** breakdown, whereas Official B v2 describes **qualitative +0–5 tiers**, including a possible +5 for a standout visualization alone. Preserve both source statements; do **not** promise any specific bonus allocation. Confirm grading interpretation with the instructor if it materially affects scope.

**Official B general notes:** The oral grade is assigned individually and can vary within a team. Bonus is awarded only for **functional, integrated, demonstrated** features. Evidence must be **repeatable runs and tests, not screenshots alone**.

---

## 3. Implementation acceptance checklist, derived from the official rubric

The project is **not complete** until the team can demonstrate all of the following:

- [ ] Sequential Java K-Means performs initialization, nearest-centroid assignment, centroid means and stopping correctly.
- [ ] Parallel Java K-Means uses `ForkJoinPool` with an actual split/chunk strategy and configurable threshold on at least assignment.
- [ ] Sequential and parallel runs use **the same input dataset and same initial centroids** for controlled comparisons.
- [ ] Both implementations return valid comparable clustering results; SSE is computed correctly and any numerical difference is explained.
- [ ] Handles empty datasets and malformed input gracefully; handles `K > unique points` gracefully; handles empty clusters via a documented policy (keep or reseed).
- [ ] No unsafe shared writes during Fork/Join; parallel tasks process disjoint assignment indices or use a correct safe reduction.
- [ ] OOP responsibilities are separated; `K`, max iterations, convergence tolerance, task threshold, and random seed are configurable.
- [ ] Experiment runner tests several dataset sizes and `K` values; captures total time, time per iteration, iteration count, SSE, and speedup.
- [ ] Benchmarks are repeatable and discuss task overhead; **speedup is not assumed** for small workloads.
- [ ] 3–5 page report includes method, design, Fork/Join approach, experiments, interpretation, and tradeoffs.
- [ ] 5–8 minute presentation/demo shows the algorithm and real sequential/parallel timing.
- [ ] Every student can explain the system, contribution, and the four oral-question topics.

**Definition of SSE:** For points `x_i`, assignments `a_i`, and centroids `c_j`, `SSE = sum_i ||x_i - c_(a_i)||^2` (for Euclidean K-Means). Compare SSE fairly using the same dataset and `K`. Use a consistent final-assignment/final-centroid convention.

---

## 4. AGREED TEAM PLAN: Seven meaningful engineering owners

This ownership structure was agreed in team planning. **M1 is the team leader and is included in the total team size of seven.** Actual teammate names/GitHub usernames remain to be assigned.

| Owner | Module(s) | Owns/implements | Explicitly does NOT own |
|---|---|---|---|
| **M1 — Leader** | `api/`, `model/`, `app/` | Shared Java models, interface signatures, configuration/results contracts, project setup, integration and architecture reviews. Has genuine coding responsibility. | Other members' algorithms and math implementations. |
| **M2 — Sequential & shared loop** | `engine/`, `sequential/` | Sequential assignment to nearest centroids, **shared iteration/control loop** (assignment → update → convergence → repeat), public sequential entry point. Uses M4 math and M5 data contracts. | Fork/Join task execution, M4's centroid math, dataset generation. |
| **M3 — Parallel** | `parallel/` | Fork/Join assignment strategy, task splitting, threshold, safe writes, pool lifecycle; public parallel entry point using the shared iteration engine. | Duplicating centroid updates and whole K-Means iteration logic. |
| **M4 — Mathematics** | `math/` | Centroid mean update, convergence checks, SSE calculation, empty-cluster policy/handling, and safe reduction helpers **if** a reduction-based design is chosen. | Dataset generation or running the complete K-Means loop. |
| **M5 — Data & initialization** | `data/` | Synthetic 2D dataset generator, reproducible seeds, initial-centroid selection, input checks; **optional** CSV support and K-Means++ bonus after core work. | Point assignment or updating centroids at each iteration. |
| **M6 — Test & performance** | `benchmark/`, integration tests | Repeatable experiments, correctness comparison, timing/speedup/SSE reporting, edge-case and integration testing, charts/export as useful. | Sole responsibility for others' unit tests. |
| **M7 — Visualization** | `ui/` | Functional 2D scatter visualizer, colored clusters, centroids, optional per-iteration movement, controls and real-result demo. | Reimplementing K-Means in the UI; inventing timings. |

**Shared responsibility:** Every member writes tests for their own code and supplies their report section and oral explanation; final report and presentation belong to the entire team.

### Why this split matters

- **M5** prepares points and *initial* centroids **before** clustering starts.
- **M2/M3** determine which cluster each point belongs to during each **assignment** step.
- **M4** computes *updated* centroids **after assignments**, checks convergence, and computes SSE.
- **M2's shared engine** repeats the assignment/update cycle. **M3 substitutes a parallel assignment strategy** without rebuilding the whole algorithm.
- **M6** verifies and benchmarks; **M7** displays genuine results.

---

## 5. AGREED ARCHITECTURE: One Java Maven repository, shared contracts

### Proposed repository layout

```text
parallel-kmeans/
├── AGENTS.md                    # This project context / Codex instructions
├── README.md
├── pom.xml
├── .gitignore
├── .github/
│   ├── workflows/ci.yml
│   ├── ISSUE_TEMPLATE/
│   ├── PULL_REQUEST_TEMPLATE.md
│   └── CODEOWNERS               # Set only after knowing actual GitHub usernames
├── docs/
│   ├── architecture.md
│   ├── ownership.md
│   ├── workflow.md
│   └── experiments.md
├── src/
│   ├── main/java/edu/team/kmeans/
│   │   ├── api/                  # M1
│   │   ├── model/                # M1
│   │   ├── app/                  # M1
│   │   ├── engine/               # M2 shared iteration engine
│   │   ├── sequential/           # M2
│   │   ├── parallel/             # M3
│   │   ├── math/                 # M4
│   │   ├── data/                 # M5
│   │   ├── benchmark/            # M6
│   │   └── ui/                   # M7
│   └── test/java/edu/team/kmeans/
│       ├── sequential/
│       ├── parallel/
│       ├── math/
│       ├── data/
│       ├── benchmark/
│       └── integration/
└── results/                     # Recorded actual experimental results
```

**Build/tooling direction:** Java 17 + Maven + JUnit 5 are **team recommendations**, not requirements quoted from the university PDFs. Prefer a simple single Maven project rather than seven independent projects. Do not introduce complicated infrastructure without need.

### Shared contracts to settle first (illustrative API, not yet an imposed exact signature)

- Immutable `Point` (at least 2D; potentially configurable dimensionality if agreed).
- `KMeansConfig` containing `k`, `maxIterations`, `tolerance`, `seed`, and parallel task threshold (plus parallelism if needed).
- `KMeansResult` containing final assignments, centroids, SSE, iterations, and optionally snapshots/timing data.
- `KMeans` abstraction with a consistent `fit`/`cluster` operation for either execution mode.
- `AssignmentStrategy` that accepts **points + current centroids** and produces **cluster indices**.
- `CentroidUpdater`, `ConvergenceChecker`, and `SSECalculator` as shared, testable services.
- `CentroidInitializer` with a fixed-seed implementation and optional K-Means++.

**Fair-comparison design:** Allow the caller/experiment runner to pass the **same initial centroid coordinates** into both implementations; a seed alone is not enough if initialization call order differs.

### Implementation rules for safe parallelism

- Each task writes assignment results only for its own **non-overlapping point-index range**, reading the same immutable centroid snapshot for that iteration.
- If centroid accumulation is ever parallelized, use **per-task accumulators with a safe merge/reduction**. Do not have unsynchronized tasks mutate shared sums/counts.
- `ForkJoinPool` threshold controls granularity and must be configurable and explainable.
- Do not spawn one task per point. Do not claim speedup without controlled measurements.
- Shared iteration logic should call **one of two interchangeable assignment strategies**; avoid two diverging copies of the K-Means loop.
- Still expose clearly understandable sequential and parallel public implementations consistent with the assignment's intent (e.g., `KMeansSequential` and `KMeansParallel`).
- Decide and document how to handle ties, empty clusters, non-finite coordinates, invalid `K`, and convergence. Keep this policy consistent across modes.
- UI should consume real model results/snapshots, not recalculate the clustering algorithm in JavaScript or a display layer.

---

## 6. AGREED EXECUTION DEPENDENCIES: Who starts first and who waits

### Phase 0 — M1 first: minimal foundation (short session)

M1 creates the buildable repository and commits the shared `Point`, config, result, and interface contracts. Agree on signatures with M2–M5 **before** parallel coding. M1 does not need to finish all integration work first.

**Gate 0:** The foundation compiles and is available on `main`.

### Phase 1 — M2, M3, M4, M5, M6 and M7 start concurrently

| Member | Can start immediately after Gate 0 | What is genuinely blocked until integration |
|---|---|---|
| **M2** | Implement **sequential assignment** with a tiny **hardcoded test fixture** (fixed points and fixed initial centroids); scaffold shared loop against agreed M4 interfaces. | Running the full real update/convergence loop needs M4; normal dataset wiring needs M5. |
| **M3** | Implement **Fork/Join assignment** independently, using the **same fixed fixture** and `AssignmentStrategy` contract. Test thresholds and disjoint output writes. | Full parallel K-Means wrapper/integration needs M2 shared loop and M4 math. |
| **M4** | Implement centroid updates, SSE, convergence and empty-cluster handling against simple fixed points + assignment arrays. | Only final integration depends on the other modules. |
| **M5** | Implement reproducible synthetic points and initial-centroid selection using `Point`/config contracts. | Only final integration depends on the other modules. |
| **M6** | Write test fixtures, unit/integration test skeleton and benchmark harness; specify experiment matrices. | Final sequential-vs-parallel numbers require working integrated algorithms. |
| **M7** | Create visualization and interactions using **clearly labeled mock result data** in development. | Real integrated demo needs `KMeansResult`/iteration snapshots from the engine. |

#### Critical dependency clarification

**M2 and M3 cannot finish complete K-Means without M4's centroid calculations.** They can, however, complete useful, independently testable **assignment algorithms** before M4 has finished. They must **not** reproduce M4's maths as a temporary production implementation.

For an early shared test fixture:

```text
Points:            (1,1), (2,2), (8,8), (9,9)
Initial centroids: (1,1), (8,8)
Expected nearest-centroid assignments: [0, 0, 1, 1]
```

M2 computes these indices sequentially; M3 computes **the same** indices using Fork/Join. This verifies assignment but **does not** demonstrate a complete K-Means solution yet.

### Phase 2 — Merge and integrate core pieces

1. Merge tested M4 math and M5 data modules once their public interfaces match M1 contracts.
2. M2 connects assignment → M4 centroid update → convergence → repeat, using M5's inputs. Finish end-to-end **sequential K-Means** first as the reference.
3. M3 connects its Fork/Join strategy to the same engine and M4 computations, exposing a full **parallel K-Means** entry point.
4. M1 coordinates small PRs, code reviews, and consistent contracts throughout.

**Gate 2:** Both full implementations run on the same dataset and initial centroids.

### Phase 3 — Validate correctness before official performance claims

M6 runs unit, integration, edge-case and sequential-vs-parallel comparison tests; M2–M5 fix their own issues. Ensure stable SSE and proper behavior for empty clusters and invalid configurations.

**Gate 3:** Repeatable tests pass and results are consistent. Do not start presenting performance claims before this gate.

### Phase 4 — Measure and add optional enhancements in parallel

- **M3 + M6:** threshold experiments and fair sequential/parallel benchmarks. Record total runtime, per-iteration runtime, iterations, SSE, speedup, and machine/Java configuration.
- **M7:** wire visualizer to real results; demonstrate clusters and centroid movement where available.
- **M5:** add K-Means++ or multiple restarts only if core features are stable.
- **M4 + M2:** support quality/performance fixes; help explain convergence and SSE.

### Phase 5 — Official deliverables and defense

- Integrate source, report (3–5 pages), team presentation (5–8 minutes), executable demo, and measured results.
- Each owner writes their report subsection, explains their contribution, and prepares for individual oral questions.
- Team leader validates a fresh build and reproducible demonstration.

**Timing:** A 7-day illustrative schedule was discussed previously, **but no official due date or required duration was provided**. Schedule from the actual deadline, not from invented dates.

---

## 7. GitHub workflow and contribution discipline

Use **one repository**, small feature branches, and reviewable PRs rather than separate repos or permanent branches per person.

**Flow:** `GitHub Issue → feature branch → implementation + tests → pull request → review + CI → merge to main`.

### Recommended issues

1. **M1 — Shared project foundation and contracts.**
2. **M2 — Sequential assignment and shared K-Means iteration engine.**
3. **M3 — Fork/Join assignment and parallel public implementation.**
4. **M4 — Centroid updates, SSE, convergence, empty clusters.**
5. **M5 — Synthetic datasets, deterministic initialization, optional extensions.**
6. **M6 — Tests, reproducible experiment runner and results.**
7. **M7 — Integrated 2D visualization and presentation demo.**

For each issue require **owner, scoped files, dependencies, acceptance criteria, tests and demo evidence**.

### Recommended PR rules

- Protect `main` if available; require PRs and at least one other person's review plus successful build/tests.
- Contributors review each other's module-specific work; **M1 reviews shared APIs and cross-module changes** without becoming the sole bottleneck.
- Use `.github/CODEOWNERS` only with confirmed real GitHub accounts; do not invent usernames.
- Do not amend another member's public interface without communicating and updating affected tests.
- Track `Backlog → Ready → In Progress → In Review → Done`; label a blocked issue and identify its dependency.
- Integrate incrementally. No huge last-day merge.
- Each member remains responsible for module-level unit tests, even though M6 owns system-wide validation.

### Definition of Done per owner

| Owner | Demonstrable acceptance evidence |
|---|---|
| **M1** | Clean build, stable contracts, end-to-end entry point, reproducible run and documented architecture. |
| **M2** | Validated sequential assignment **and** working complete sequential K-Means loop. |
| **M3** | Actual Fork/Join tasks, correct parallel assignments, configurable threshold, safe execution, full engine integration. |
| **M4** | Correct tested centroids, SSE, convergence and empty-cluster behavior. |
| **M5** | Reproducible datasets and initial centroids; invalid-input behavior tested. |
| **M6** | Working automated tests, repeatable benchmarks, real recorded metrics and interpretation. |
| **M7** | Working visualization integrated with actual Java clustering outputs. |

---

## 8. Testing and benchmarking protocol (team recommendations derived from rubric)

### Correctness tests

- Deterministic tiny 2D datasets with expected nearest-centroid assignments.
- Multiple datasets and `K` values with matching initial centroids in both execution modes.
- One cluster, duplicate points, empty clusters, `K > unique points`, invalid/empty data, non-finite inputs, negative/zero tolerance or iteration settings as applicable.
- Check that every point has an assignment in range, centroid coordinates are valid, and measured SSE is consistent.
- Account for floating-point tolerances and potential label permutations where appropriate; don't insist on byte-identical floating-point results.
- Verify convergence and maximum-iteration termination, including non-converging or degenerate cases.

### Benchmark protocol

- Run **headless algorithm benchmarks**, not animation/UI frame timing.
- Keep dataset content, `K`, and starting centroids the same between sequential and parallel runs.
- Warm up the JVM; perform multiple measured runs rather than quoting one run.
- Measure dataset sizes and `K` choices, different threshold values, and optionally different pool parallelism.
- Report `total runtime`, `time per iteration`, `iterations`, `SSE`, `speedup = sequential_time / parallel_time`, and experimental configuration (Java version, CPU, etc.).
- Make results reproducible, and explain overhead / why small datasets may show speedup < 1.
- Export plots/tables from **actual data**; keep recorded results separate from test fixtures.

No dataset sizes, numbers of trials, or exact performance targets were mandated for Project 4 in the PDF; choose reasonable values for the machine and explain the choice.

---

## 9. Report, presentation, oral preparation, and bonus priorities

### Report (official: ~3–5 pages)

Suggested **team organization**, not an official page-by-page template:

1. Problem definition, K-Means objective and algorithm.
2. OOP structure, assignment strategies, Fork/Join split and safe updates/reduction.
3. Reproducible experiment setup with different N and K.
4. Tables/plots for runtime, time per iteration, iterations, SSE and speedup with interpretation.
5. Conclusions and limits/tradeoffs (optional within 3–5 pages).

### Presentation (official: 5–8 minutes)

Show a real Java run, contrast sequential vs parallel timing on the **same dataset**, briefly explain the design, and report evidence plus lessons. Bonus visualization is useful but must not displace the algorithm and benchmark evidence.

### Individual oral (official: 5/20 for each student)

Every student should be able to explain nearest-centroid assignment, updating centroid means, convergence, empty clusters, task splitting/threshold, safe shared-state design, SSE, speedup, and personal changes. Use the four official Project 4 sample questions in Section 2 to rehearse.

### Bonus priority

1. First make all **required** functionality correct and repeatable.
2. Then target a polished 2D visualizer **integrated with real outputs** (M7).
3. Add K-Means++ / multiple restarts if core and visualization remain stable (M5).
4. Follow the **two distinct source descriptions** of bonus grading; do not guarantee +5 from any feature until it is judged.

An HTML-only explanatory simulation previously discussed with the team is a learning/prototyping artifact, **not a substitute** for the required Java Fork/Join implementation or an automatically qualifying integrated GUI bonus.

---

## 10. Instructions for a Codex agent working on this repository

Before changing code:

1. Read this `AGENTS.md`, inspect the actual source tree and run existing tests/build if possible.
2. Identify **Project 4 official requirement(s)** the requested work supports.
3. Identify the **module owner**, dependencies, exact input/output contracts and acceptance tests.
4. Explain whether the work is **required**, **team architecture**, or **optional bonus**.
5. Make the smallest coherent change with tests, avoiding duplicated implementations across owners.
6. Run relevant build/tests; report the **actual commands and observed results**, or honestly report what could not be run.
7. If an API or design change blocks another member, highlight it explicitly before proceeding.
8. Provide a concise delivery summary: changed files, test evidence, outstanding dependencies, and next integration gate.

### Non-negotiable design constraints

- Java K-Means and actual Java Fork/Join for assignment.
- Shared, unit-testable centroid/convergence/SSE logic.
- Sequential and parallel implementations comparable from identical starting data.
- Race-safe chunk processing and threshold rationale.
- Configurable parameters and modular OOP.
- Repeatable measured tests and no fabricated benchmarks.
- Respect the seven-member ownership plan and the **official** grading/deliverables.

### When information is missing

Do not invent a deadline, teammate's username, existing repository state, measured performance, exact preferred GUI toolkit, or a professor-imposed numerical threshold. Ask or clearly mark proposals. The team leader approves cross-module architecture decisions.
