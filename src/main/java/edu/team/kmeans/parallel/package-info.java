/*
 * BEGIN TEMPORARY TEAM GUIDANCE - M3 / issue #3
 * Remove this entire comment block later; keep the package declaration/Javadoc.
 * This is a work checklist, not an implementation or evidence of completion.
 *
 * YOUR JOB: Real Java Fork/Join assignment and the parallel public KMeans wrapper.
 * START WITH: The SAME AssignmentStrategy and AssignmentFixture as M2.
 * 1. Use ForkJoinPool and recursive tasks processing point ranges [from, to).
 * 2. Split large ranges near the middle; process small ranges directly at threshold.
 * 3. Each leaf reads the same stable centers and writes ONLY its own output indices.
 * 4. Use squared distance and lowest-index ties exactly as sequential assignment does.
 * 5. Join all child tasks before assign returns; M4 must never read unfinished output.
 * 6. Validate direct inputs; honor configured threshold and workers for each fit.
 * 7. Reuse the owned pool when workers match; replace an idle pool if they change.
 * 8. Implement/document close() on the concrete owned engine and clean up safely.
 *    Never shut down the common pool or create a new pool on every iteration.
 * 9. Plug your strategy into M2's engine, using M4 math; do not copy their whole loop.
 * START NOW: Fixture assignment; full integration needs M2/M4.
 * TEST: [0,0,1,1], odd sizes, threshold boundaries, multiple workers, overwritten output,
 * unchanged inputs, joined completion, and pool lifecycle. Use real tasks in tests.
 * DO NOT spawn one task per point or let tasks update shared centroid sums/counts.
 * DONE: Race-safe assignment plus full engine integration; speedup needs M6 evidence.
 *
 * EVERY MEMBER: Run Maven verify, submit a small reviewed PR, write your own
 * unit tests, and prepare your report contribution and individual oral explanation.
 * Reference: docs/ownership.md, docs/architecture.md, and AGENTS.md.
 * END TEMPORARY TEAM GUIDANCE
 */

/** M3 future Fork/Join assignment and public engine. See docs/ownership.md and docs/architecture.md. */
package edu.team.kmeans.parallel;
