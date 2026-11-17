/*
 * BEGIN TEMPORARY TEAM GUIDANCE - M6 / issue #6
 * Remove this entire comment block later; keep the package declaration/Javadoc.
 * This is a work checklist, not an implementation or evidence of completion.
 *
 * YOUR JOB: Repeatable complete-engine checks and genuine performance experiments.
 * START NOW: Fixed fixtures, edge-case plan, runner configuration, and output format.
 * WAIT FOR: Integrated M2-M5 before collecting official algorithm measurements.
 * 1. Follow docs/experiments.md. Give both modes identical points/K/initial coordinates.
 * 2. Verify correctness/SSE and stopping behavior before reporting performance.
 * 3. Disable UI/snapshots; exclude generation/initialization from fit timing.
 * 4. Warm up the JVM and repeat measured runs; vary dataset sizes, K, thresholds.
 * 5. Measure total fit time INCLUDING final reassignment/SSE, individual iteration
 *    times, iteration count, SSE, and paired sequential_time / parallel_time.
 * 6. Record raw samples, units, seeds/starts, workers, thresholds, JDK and CPU details.
 * 7. Explain overhead and cases where parallel speedup is below 1.
 * 8. Save actual evidence/instructions in results/. Do not put mock values there.
 * TEST: Integration files use *Test.java names; Surefire currently does not run *IT.
 * Check invalid input, empty clusters, duplicates, termination, and both modes fairly.
 * EVERY OWNER still writes their unit tests; you are not their sole test author.
 * DONE: Repeatable real comparisons and evidence-backed tables/plots, not one timing.
 *
 * EVERY MEMBER: Run Maven verify, submit a small reviewed PR, write your own
 * unit tests, and prepare your report contribution and individual oral explanation.
 * Reference: docs/ownership.md, docs/architecture.md, and AGENTS.md.
 * END TEMPORARY TEAM GUIDANCE
 */

/** M6 future reproducible headless experiment runner. See docs/ownership.md and docs/architecture.md. */
package edu.team.kmeans.benchmark;
