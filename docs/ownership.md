# Ownership and starter tasks

Seven members total; M1 is included. Real names/usernames and issue assignments remain pending. Every owner writes unit tests, supplies a report section, and prepares for the individual oral.

| Owner | Scope under `src/main/java/edu/team/kmeans/` | First deliverable | Later dependency |
|---|---|---|---|
| M1 | `api/`, `model/`, `app/`, build/integration | Tested shared foundation and contracts | Final application wiring needs all core modules |
| M2 | `engine/`, `sequential/` | Sequential assignment using fixed fixture | Shared complete loop needs M4; dataset wiring uses M5 |
| M3 | `parallel/` | Fork/Join assignment using same fixture | Full wrapper needs M2 engine and M4 math |
| M4 | `math/` | Means, convergence, SSE, empty-cluster policy | Shared model/API contracts |
| M5 | `data/` | Seeded synthetic data and explicit initial centroids | Shared model/API contracts |
| M6 | `benchmark/`, integration tests | Fixtures, validation plan, benchmark harness | Actual comparisons need both full engines |
| M7 | `ui/` | Clearly labeled development visualization | Real integrated demo needs Java results/snapshots |

## GitHub work items

- [M1 starter task #1](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/1)
- [M2 starter task #2](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/2)
- [M3 starter task #3](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/3)
- [M4 starter task #4](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/4)
- [M5 starter task #5](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/5)
- [M6 starter task #6](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/6)
- [M7 starter task #7](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/issues/7)

Assignees remain unset until real GitHub usernames are supplied. The M1 foundation work item remains open for review/integration; its creation is not evidence of completed application integration.

## Review responsibilities

M1 reviews shared contracts and architecture; affected owners also review changes that alter their API. Teammates can review module-local work without routing every PR through M1. Do not edit another owner's implementation without coordination. Follow [workflow gates](workflow.md) before claiming full implementation or starting performance claims.

CODEOWNERS will be added after member usernames and repository access are confirmed. A placeholder account would misdirect reviews; none is committed.
