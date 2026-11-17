/*
 * BEGIN TEMPORARY TEAM GUIDANCE - M2 / issue #2
 * Remove this entire comment block later; keep the package declaration/Javadoc.
 * This is a work checklist, not an implementation or evidence of completion.
 *
 * YOUR JOB: Sequential nearest-centroid assignment, then the sequential public engine.
 * START WITH: api/AssignmentStrategy.java and test/support/AssignmentFixture.java.
 * 1. Implement AssignmentStrategy in a class here; choose a clear implementation name.
 * 2. For each point, compare squared Euclidean distance to EVERY current center.
 * 3. Keep the lowest center index on ties; write one valid index per input point.
 * 4. Overwrite the supplied output array completely; do not rely on its prior values.
 * 5. Validate nonempty/null inputs and output length as specified by the interface.
 * 6. Leave input lists/centers unchanged.
 * FIRST TEST: (1,1),(2,2),(8,8),(9,9), starts (1,1),(8,8) -> [0,0,1,1].
 * ALSO TEST: One center, distance ties, duplicate centers, reused output, invalid input.
 * LATER: Expose complete sequential KMeans using your engine/ loop and M4 services.
 * WAIT FOR: M4 only for the complete loop; fixed-data assignment can start now.
 * DO NOT implement centroid means/SSE here or require M5 generation for first tests.
 * DONE: Both assignment tests and the complete sequential run pass; assignment alone
 * is an intermediate milestone, not a complete K-Means implementation.
 *
 * EVERY MEMBER: Run Maven verify, submit a small reviewed PR, write your own
 * unit tests, and prepare your report contribution and individual oral explanation.
 * Reference: docs/ownership.md, docs/architecture.md, and AGENTS.md.
 * END TEMPORARY TEAM GUIDANCE
 */

/** M2 future sequential assignment and public engine. See docs/ownership.md and docs/architecture.md. */
package edu.team.kmeans.sequential;
