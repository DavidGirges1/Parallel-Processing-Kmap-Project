# Team Foundation Implementation Plan

> **For agentic workers:** Use superpowers:executing-plans to implement this plan task by task. The user approved implementation and publication on 2026-11-17; continue without requesting the same approval again.

**Goal:** Give seven contributors a tested Java project and shared contracts, with GitHub work items and a reviewable setup PR.

**Architecture:** One Maven project, immutable 2D models, interfaces shared by the later sequential and parallel engines. Algorithms remain with the owners in AGENTS.md.

**Tech Stack:** Java 17, Maven 3.9.9 Wrapper, JUnit 5, GitHub Actions.

**Spec:** ../specs/2026-11-17-team-foundation-design.md (approved by the user).

## Global Constraints

- Java 17 target; single Maven project; preserve supplied AGENTS.md.
- No algorithm implementation, fabricated results, invented usernames, or automatic merging.
- Identical input and initial centroids for controlled comparisons.
- Defensive copies of mutable result/snapshot inputs and outputs.
- Publish initial main, a feature branch/PR, and seven unassigned issues, as approved.

## Review Focus

- Invalid coordinates: reject NaN/infinity in either dimension.
- Configuration boundaries: positive integer fields and finite nonnegative tolerance.
- Result/snapshot aliasing: callers cannot mutate stored assignments or centroid collections.
- Malformed result/snapshot data: reject empty centroids, out-of-range assignments, and invalid SSE/counts.
- Onboarding: executable Unix wrapper and Windows wrapper, clean checkout, actual JDK 17 verification, accurate setup-only status.

### Task 1: Build and models

**Files:** `pom.xml`, `mvnw`, `mvnw.cmd`, `.mvn/wrapper/maven-wrapper.properties`, `.gitignore`, `.editorconfig`, `.gitattributes`, `model/{Point,KMeansConfig,KMeansResult,IterationSnapshot}.java`, `model/ModelTest.java` under standard Java source/test roots.

**Interfaces:** Produces `Point(double x,double y)`, `KMeansConfig(int k,int maxIterations,double tolerance,long seed,int taskThreshold,int parallelism)`, `KMeansResult(List<Point> centroids,int[] assignments,double sse,int iterations)`, `IterationSnapshot(int iteration,List<Point> centroids,int[] assignments)` in `edu.team.kmeans.model`. All mutable collections/arrays copied; document validation contracts.

- [ ] Write tests for finite points, positive configuration fields/zero tolerance, immutable results/snapshots, invalid metrics/counts/assignments, and null inputs. Concrete fixtures: centroids `(1,1),(8,8)`, assignments `[0,0,1,1]`, SSE `4.0`, iterations `2`.
- [ ] Generate official Maven Wrapper with Maven 3.9.9 and write the Java 17/JUnit build configuration.
- [ ] Run `./mvnw --batch-mode --no-transfer-progress test`; expect missing model types before production implementation.
- [ ] Implement immutable model validation and copying, then run `./mvnw --batch-mode --no-transfer-progress verify`; expect all tests passing.
- [ ] Commit the independently buildable model foundation.

### Task 2: APIs and collaboration documentation

**Files:** `api/{KMeans,AssignmentStrategy,CentroidUpdater,ConvergenceChecker,SSECalculator,CentroidInitializer,IterationObserver}.java`; package documentation in owner directories; `src/test/java/edu/team/kmeans/support/AssignmentFixture.java`; `README.md`, `CONTRIBUTING.md`, `docs/{architecture,ownership,workflow,experiments,deliverables,github-setup}.md`, `results/README.md`, `.github/workflows/ci.yml`, `.github/ISSUE_TEMPLATE/{module-task,bug-report}.yml`, `.github/PULL_REQUEST_TEMPLATE.md`.

**Interfaces:** Consume Task 1 models. Produce the exact six service signatures in the approved design. `IterationObserver.onIteration(IterationSnapshot snapshot)` and `IterationObserver.NONE` define opt-in snapshots through the shared-engine constructor; keep the approved three-argument `KMeans.fit` unchanged. No algorithm implementation.

- [ ] Document contracts, tie/empty-cluster/convergence/final-assignment policies and pool/snapshot lifecycle.
- [ ] Create the common deterministic fixture, without adding a clustering implementation or pretending its expected assignments were computed.
- [ ] Write onboarding and ownership guides; every unknown account/deadline/toolkit is explicitly pending.
- [ ] Add PR/issue forms and read-only CI running Java 17 `./mvnw --batch-mode --no-transfer-progress verify` on PRs and main pushes.
- [ ] Verify Maven build, XML/YAML syntax, executable wrapper and relative documentation links; inspect the diff and commit.

### Task 3: Publish and verify GitHub state

**Files:** `docs/ownership.md` issue links; all branch files published through GitHub Git database tools; PR and issue bodies through structured connector arguments.

**Interfaces:** Consumes verified foundation; produces initial main commit, `chore/team-foundation`, seven issues and one open PR.

- [ ] Request a fresh whole-branch reviewer; address material findings and rerun affected checks.
- [ ] Create seven issues with scoped paths, dependencies, acceptance criteria and tests; add their actual links to ownership documentation.
- [ ] Publish the verified tree with correct executable file mode and no ignored files; open the setup PR without merging.
- [ ] Observe the GitHub Actions outcome; report actual check state and any failed action with its reason.
- [ ] Verify a fresh checkout, compare its tree with the verified local branch, and record links plus remaining collaborator/admin steps.

## Self-review and execution choice

The approved spec is covered by these three tasks. The same model names/signatures are used throughout. Execute inline to keep tightly coupled contracts in one context; obtain one independent final code review. User approval already covers the proposed publication sequence; merge and collaborator invitations remain separate.
