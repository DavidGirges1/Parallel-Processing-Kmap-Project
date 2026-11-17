/*
 * BEGIN TEMPORARY TEAM GUIDANCE - M4 / issue #4
 * Remove this entire comment block later; keep the package declaration/Javadoc.
 * This is a work checklist, not an implementation or evidence of completion.
 *
 * YOUR JOB: Implement CentroidUpdater, ConvergenceChecker, and SSECalculator here.
 * START NOW: Fixed points/assignment arrays; no engine or generator is needed.
 * 1. Read each api/ method's input rules. Do not change caller arrays/lists.
 * 2. For each cluster, sum assigned coordinates and divide by its assigned count.
 * 3. If count is zero, retain that cluster's previous center; do not divide by zero.
 * 4. Convergence: compare maximum corresponding-center Euclidean movement <= tolerance.
 *    Zero tolerance is valid; mismatched/empty center lists are invalid.
 * 5. SSE: sum squared point-to-assigned-center distances using the SUPPLIED pair.
 *    Do not silently reassign points when calculating this metric.
 * 6. Reject invalid lengths/indices and non-finite computed coordinates/SSE clearly.
 * HAND-CHECKED TEST: Points (1,1),(2,2),(8,8),(9,9), assignments [0,0,1,1].
 * Means -> (1.5,1.5),(8.5,8.5). SSE at these means -> 2.0; at starts (1,1),(8,8) -> 4.0.
 * ALSO TEST: Empty clusters, one cluster, duplicates, zero movement, tolerance boundary,
 * null/invalid inputs, and overflow that cannot produce a finite result.
 * PROVIDE TO: M2's loop, used by both engines. M6 verifies complete runs later.
 * DO NOT generate datasets or implement the full iteration loop here.
 * DONE: Three tested services with the same policies for both execution modes.
 *
 * EVERY MEMBER: Run Maven verify, submit a small reviewed PR, write your own
 * unit tests, and prepare your report contribution and individual oral explanation.
 * Reference: docs/ownership.md, docs/architecture.md, and AGENTS.md.
 * END TEMPORARY TEAM GUIDANCE
 */

/** M4 future centroid, convergence, and SSE implementations. See docs/ownership.md and docs/architecture.md. */
package edu.team.kmeans.math;
