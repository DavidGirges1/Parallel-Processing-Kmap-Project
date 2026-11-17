# Who starts when, and how work moves forward

Foundation PR [#8](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/pull/8) is merged into `main`, and its checks passed. The shared types/build are available. Algorithms, data generation, measurements, and the UI are still team work.

## Step 1 — Confirm roles and shared methods

1. M1 maps actual teammates to M1–M7 and confirms repository access.
2. Everyone runs the [setup guide](getting-started.md).
3. M2–M5 review the shared methods with M1 and raise questions before coding.
4. Each member chooses a small first task from [their checklist](ownership.md).

## Step 2 — Start these pieces at the same time

| Member | Start now | What waits for teammates? |
|---|---|---|
| M1 | Access, contract questions, integration preparation | Real app wiring needs core modules |
| M2 | Sequential assignment with the fixed fixture | Full loop needs M4 math |
| M3 | Fork/Join assignment with the same fixture | Full engine needs M2's loop and M4 math |
| M4 | Means, convergence, SSE using fixed inputs | Final integration with engines |
| M5 | Seeded data and initial centers | Final integration with callers |
| M6 | Fixtures, test plan, experiment harness | Real comparisons need complete engines |
| M7 | Agree toolkit; draw clearly labeled mock data | Real demo needs Java outputs |

An **assignment step** is only choosing a center for each point. A **complete K-Means run** also updates centers repeatedly, stops correctly, and returns SSE. M2/M3 can finish the first step independently, but that alone does not complete their modules.

## Step 3 — Connect the core pieces

1. Merge reviewed/tested M4 math and M5 data.
2. M2 connects its assignment to M4's math in the shared loop; finish the sequential reference first.
3. M3 plugs its parallel assignment into that same loop.
4. Check both engines using the exact same data and starting centers.

M1 coordinates the connections. M2/M3 must not create separate copies of M4 math or duplicate the complete loop.

## Step 4 — Prove correctness, then measure

1. Each owner supplies unit tests; M6 checks the assembled system.
2. Fix empty-cluster, invalid-input, convergence, assignment, and SSE issues.
3. Once correctness is established, M3/M6 run repeatable measurements.
4. M7 connects real results to the UI. Optional initialization extras follow stable core work.

Do not present performance claims before correct integrated runs exist. A passing foundation build is not evidence of a complete algorithm.

## Step 5 — Prepare the submission together

1. Write the **3–5 page report** with actual results.
2. Prepare the **5–8 minute presentation** and reproducible Java demo.
3. Rehearse the individual oral questions together.

Use [Deliverables](deliverables.md) for details. No official deadline has been supplied in the project context.

## Keep your issue status understandable

| Status | Meaning |
|---|---|
| Backlog | Work is identified but not selected yet |
| Ready | Scope is clear and its needed inputs are available |
| In Progress | Someone is actively working on it |
| In Review | A PR is open and awaiting feedback/checks |
| Done | Full agreed task is reviewed, merged, and verified |
| Blocked | A particular piece needs another input; name it and link its owner/task |

These are descriptions to use on issues or a future Project board; they are not a claim that labels/board automation are configured. One module may have independent work in progress while another part is blocked.

Before merging any PR: another member reviews, automated checks pass, comments are addressed, and the issue accurately describes what remains. See [Contributing](../CONTRIBUTING.md) for the click-by-click flow.
