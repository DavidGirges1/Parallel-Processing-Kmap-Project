/*
 * BEGIN TEMPORARY TEAM GUIDANCE - M1 / issue #1
 * Remove this entire comment block later; keep the package declaration/Javadoc.
 * This is a work checklist, not an implementation or evidence of completion.
 *
 * STATUS: Shared interfaces already exist. These are contracts, not algorithms.
 * 1. Read every method signature and its permanent Javadoc before implementing.
 * 2. Keep these signatures stable so M2-M7 can work independently.
 * 3. If a change is necessary, discuss it with affected owners and update callers/tests.
 * 4. Keep the six services separate: assignment, update, convergence, SSE,
 *    initialization, and the public fit operation. Do not put implementations here.
 * 5. Keep IterationObserver optional. NONE means no snapshot-copy work in the engine.
 * HANDOFF: M2/M3 implement assignment/public engines; M4 implements math;
 * M5 implements initialization; M7 consumes results/snapshots.
 * CHECK: All modules compile against the same types; no duplicated contracts.
 * LATER: Add other CODEOWNERS only after real accounts/access are confirmed.
 *
 * EVERY MEMBER: Run Maven verify, submit a small reviewed PR, write your own
 * unit tests, and prepare your report contribution and individual oral explanation.
 * Reference: docs/ownership.md, docs/architecture.md, and AGENTS.md.
 * END TEMPORARY TEAM GUIDANCE
 */

/** M1 shared service contracts. See docs/ownership.md and docs/architecture.md. */
package edu.team.kmeans.api;
