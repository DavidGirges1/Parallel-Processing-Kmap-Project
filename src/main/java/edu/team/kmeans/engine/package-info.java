/*
 * BEGIN TEMPORARY TEAM GUIDANCE - M2 / issue #2
 * Remove this entire comment block later; keep the package declaration/Javadoc.
 * This is a work checklist, not an implementation or evidence of completion.
 *
 * YOUR JOB: Implement the ONE iteration loop shared by sequential and parallel modes.
 * INPUTS: List<Point>, explicit initial centers, KMeansConfig; read-only caller data.
 * COLLABORATORS: AssignmentStrategy, CentroidUpdater, ConvergenceChecker, SSECalculator.
 * 1. Agree constructor wiring with M3. Accept collaborators; no no-arg constructor needed.
 * 2. Validate fit input using M5 validation when available; initial centers count must be K.
 * 3. Keep current centers as a stable snapshot while assignment tasks run.
 * 4. Call assignment.assign(points, centers, assignments); it completes before returning.
 * 5. Call M4 updater; increment completed assignment/update cycle count.
 * 6. Optionally notify IterationObserver with updated centers and cycle assignments.
 *    For NONE, skip snapshot creation completely. Observer failures propagate.
 * 7. Stop when M4 reports movement <= tolerance OR maxIterations is reached.
 * 8. Reassign points against the returned centers, then use M4 to calculate final SSE.
 * 9. Return KMeansResult; final reassignment is not another update-cycle iteration.
 * START NOW: Sequential assignment/fixtures in sequential/. Full loop needs M4.
 * TEST: Both stop rules, observer opt-in/NONE, final assignments/SSE, read-only inputs.
 * DO NOT copy M4 math or write a second loop for M3. Do not invent temporary math.
 * DONE: Sequential and parallel entry points can reuse this tested engine.
 *
 * EVERY MEMBER: Run Maven verify, submit a small reviewed PR, write your own
 * unit tests, and prepare your report contribution and individual oral explanation.
 * Reference: docs/ownership.md, docs/architecture.md, and AGENTS.md.
 * END TEMPORARY TEAM GUIDANCE
 */

/** M2 future shared K-Means iteration loop. See docs/ownership.md and docs/architecture.md. */
package edu.team.kmeans.engine;
