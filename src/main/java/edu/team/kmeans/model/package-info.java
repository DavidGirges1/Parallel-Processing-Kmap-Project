/*
 * BEGIN TEMPORARY TEAM GUIDANCE - M1 / issue #1
 * Remove this entire comment block later; keep the package declaration/Javadoc.
 * This is a work checklist, not an implementation or evidence of completion.
 *
 * STATUS: Point, KMeansConfig, KMeansResult, and IterationSnapshot are implemented.
 * 1. Reuse these types. Do not create different Point/config/result types per module.
 * 2. Preserve finite coordinates and positive config fields; zero tolerance is valid.
 * 3. Keep centroids immutable and copy assignment arrays on input AND access.
 * 4. Dataset-dependent K checks belong to M5/fit integration, not this config record.
 * 5. Results use final assignments against returned centers. Snapshots describe
 *    an update cycle and may have different assignments from final reassignment.
 * TEST: Existing ModelTest checks invalid values and caller-mutation protection.
 * When changing a model, add a behavior test and check every affected caller.
 * DONE: Model/API changes compile, tests pass, and affected owners agree.
 * Do not add algorithm math, timing measurements, or UI state to these classes.
 *
 * EVERY MEMBER: Run Maven verify, submit a small reviewed PR, write your own
 * unit tests, and prepare your report contribution and individual oral explanation.
 * Reference: docs/ownership.md, docs/architecture.md, and AGENTS.md.
 * END TEMPORARY TEAM GUIDANCE
 */

/** M1 immutable domain and configuration models. See docs/ownership.md and docs/architecture.md. */
package edu.team.kmeans.model;
