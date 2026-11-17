# Deliverables and individual preparation

The supplied `AGENTS.md` preserves Project 4 requirements from the two course sources. Only Project 4 is in scope.

- [ ] Java sequential K-Means and actual Fork/Join point assignment, modular OOP and configurable parameters.
- [ ] Correct centroid updates, stopping, empty clusters, validation, and SSE.
- [ ] Fair sequential/parallel comparison across different N and K, with actual total/per-iteration timing, iterations, SSE, speedup, and repeatable setup.
- [ ] Approximately **3–5 page report**: objective/algorithm, OOP and Fork/Join safety, experiment method/results, interpretation/tradeoffs.
- [ ] **5–8 minute team presentation**: design, actual Java demo on the same dataset, timings, results, lessons.
- [ ] Every member explains their own contribution and the whole algorithm.

## Individual oral (5 of the base 20 marks)

Rehearse the official sample questions together:

1. What does K-Means optimize, and why can it converge to local minima?
2. How are empty clusters handled, and why do they occur?
3. Which step is parallelized, and why is it the bottleneck?
4. How is shared-state contention avoided when accumulating centroid sums?

Also explain stopping criteria, task thresholds, SSE, and speedup. Individual grades may differ even within the same team.

## Optional bonus

Core correctness and evidence come first. M7's integrated 2D visualizer and M5's optional initialization/restart improvements follow. Source A describes up to +3 visualization / +2 extras; source B v2 describes qualitative +0–5 tiers, including a standout visualization. Preserve both interpretations and confirm with the instructor if scope depends on the difference. No feature guarantees bonus marks; extras must work, be integrated, and be demonstrated.
