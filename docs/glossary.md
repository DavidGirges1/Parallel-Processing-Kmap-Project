# Words you will see in this project

## GitHub and teamwork

| Word | Meaning here |
|---|---|
| Repository / repo | Shared project files and their history |
| Clone | Download a working copy connected to GitHub |
| `main` | Shared branch containing reviewed, merged work |
| Branch | A separate line of work for one small task |
| Issue | A task page with scope and completion criteria |
| Commit | A named checkpoint saved on your computer |
| Fetch | Check GitHub for changes |
| Pull | Bring shared changes into your local branch |
| Push | Upload commits to GitHub |
| Pull request / PR | Ask the team to review and merge your changes |
| Review | Read changes and approve or request corrections |
| Merge | Combine reviewed changes into a branch, usually `main` |
| Merge conflict | Overlapping changes that a person must reconcile |
| CI / automated checks | GitHub builds/tests the changes; our check is `build-and-test` |
| Dependency / blocked | Something another task must provide before this part can finish |

## Java and K-Means

| Word | Meaning here |
|---|---|
| JDK | Tools to compile/run Java; we use JDK 17 |
| Maven / wrapper | Build tool; `mvnw` / `mvnw.cmd` downloads and runs the chosen version |
| Build / verify | Compile the code, run configured tests, and package the project |
| Package / module | A grouping of related Java responsibilities in this project |
| Interface / contract | Shared method names, inputs, and outputs that members implement or call |
| Unit test | Check a small piece, such as one math function |
| Integration test | Check multiple parts working together |
| Fixture | Fixed test data with hand-checked expected answers |
| Point | A data item with `(x, y)` coordinates |
| K | Number of clusters to find |
| Centroid | A cluster's center, updated to the mean of its assigned points |
| Assignment | Choosing a nearest centroid for each point |
| Iteration | One assignment-and-update cycle |
| Convergence / tolerance | Stop when centroid movement is small enough |
| Sequential | Do assignment work in one sequence |
| Fork/Join | Java framework for splitting work into tasks and joining their results |
| Threshold | Range size at which a task stops splitting |
| Seed | Value used to make random generation/initialization repeatable |
| SSE | Sum of squared point-to-assigned-center distances: our clustering-error measure |
| Benchmark / speedup | Measure runtime; speedup is sequential time divided by parallel time |
| Snapshot / observer | Saved iteration state / optional callback that lets the UI receive it |

For exact method signatures and policies, use [Architecture](architecture.md).
