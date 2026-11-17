# Workflow and integration gates

Use one repository and short-lived feature branches. Track each issue through **Backlog → Ready → In Progress → In Review → Done**. A GitHub Project board can represent these states; until one exists, record status and blockers in the issue. There is no invented deadline or mandatory seven-day schedule.

1. **Gate 0 — foundation on main:** M1's contracts/build are reviewed, merged, and passing. M2–M5 agree signatures before independent coding.
2. **Assignment-first development:** M2/M3 independently use the same fixed fixture; M4 tests math on fixed points/assignments; M5 tests generation/initialization; M6 prepares tests/experiments; M7 uses labeled development mocks.
3. **Core integration:** merge M4/M5, finish M2's complete sequential loop, then wire M3's strategy into that same loop. Both public engines take identical input and initial centroids.
4. **Correctness gate:** edge cases, convergence, final assignments, SSE, and sequential/parallel consistency pass. Each owner fixes their module. Passing foundation tests alone does not reach this gate.
5. **Evidence and optional enhancements:** M3/M6 measure real headless workloads; M7 integrates real results. Optional K-Means++/restarts follow stable required work.
6. **Delivery:** the whole team completes the report, presentation, demo, and individual preparation.

## Every PR's definition of done

- Linked issue, scoped files, dependencies identified, no uncommunicated contract change.
- Behavior implemented and tested by its owner; build remains usable.
- Actual `verify` output and relevant demo evidence supplied; no invented metrics.
- Another member reviews; CI passes; conversations resolved.
- Documentation matches final behavior; credentials/generated output excluded.

For M2, assignment alone is an intermediate milestone: the owner is done only when the full sequential loop works. The same distinction applies to M3 assignment versus full parallel integration. M6 is done only with repeatable actual experiments, and M7 only with a real-result visualization.
