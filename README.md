# Parallel K-Means Clustering with Fork/Join

Parallel Processing · Fall 2026 · Project 4 · Seven team members

Implement K-Means in Java, parallelize point assignment with Fork/Join, and compare runtime and SSE using identical datasets and initial centroids.

**Status: collaboration foundation.** Shared models, service contracts, tests, build tooling, and contribution guides are provided. Sequential/parallel algorithms, dataset generation, benchmark results, and visualization are upcoming member work. There is no runnable clustering demo yet.

## Start here

Install **JDK 17** (not only a JRE) and Git. On Linux/macOS also ensure `unzip` and either `curl` or `wget` are available; on Debian/Ubuntu, install them with `sudo apt-get install unzip curl`. The checksum-pinned wrapper requires `unzip` on Unix so it downloads the ZIP archive matching the configured checksum. Set `JAVA_HOME` to your JDK if needed. Maven is downloaded automatically by the committed Maven Wrapper; first use requires internet access. No database, Node.js, or GUI toolkit is needed for the foundation.

```sh
git clone https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project.git
cd Parallel-Processing-Kmap-Project
java -version
./mvnw --batch-mode --no-transfer-progress verify
```

Windows PowerShell: use `.\mvnw.cmd --batch-mode --no-transfer-progress verify` for the last command. IntelliJ IDEA, Eclipse, or VS Code users should import the root `pom.xml`, select JDK 17, and use the Maven Wrapper.

The build compiles the foundation, runs JUnit tests, and creates `target/parallel-kmeans-0.1.0-SNAPSHOT.jar`. This is a library artifact with no application entry point yet. A passing foundation build does **not** prove K-Means correctness or speedup.

If the setup PR is still open, its files are on `chore/team-foundation`; wait for its merge before branching for member work. To review it locally, run `git fetch origin` and `git switch --track origin/chore/team-foundation` after cloning. The default branch only becomes the development baseline after the setup PR is merged.

## Team ownership

| Member | Primary packages | Responsibility |
|---|---|---|
| M1 · leader | `api`, `model`, `app` | Contracts, foundation, integration |
| M2 | `engine`, `sequential` | Shared iteration loop and sequential assignment |
| M3 | `parallel` | Fork/Join assignment and parallel public engine |
| M4 | `math` | Means, convergence, SSE, empty clusters |
| M5 | `data` | Synthetic data, validation, initialization |
| M6 | `benchmark`, integration tests | Correctness comparisons and repeatable measurements |
| M7 | `ui` | Visualization of real Java results |

Names and GitHub assignments are pending. Every member owns unit tests and their report/presentation contribution. See [ownership and starter tasks](docs/ownership.md).

## Working together

Read [AGENTS.md](AGENTS.md), then [CONTRIBUTING.md](CONTRIBUTING.md). Use one issue → one short-lived branch → a small PR → another member's review plus passing CI → merge. Protect `main`; review shared API changes with M1 and affected owners.

- [Architecture and exact shared contracts](docs/architecture.md)
- [Workflow, dependencies, and integration gates](docs/workflow.md)
- [Correctness and experiment protocol](docs/experiments.md)
- [Report, presentation, and individual oral](docs/deliverables.md)
- [Leader's GitHub settings and teammate onboarding](docs/github-setup.md)

The course requires Java and actual Fork/Join. Java 17, Maven, JUnit, and this package layout follow the team's chosen tooling. The source requirements and bonus-source distinction are preserved in [AGENTS.md](AGENTS.md). No team deadline has been supplied.
