package edu.team.kmeans.api;

import edu.team.kmeans.model.IterationSnapshot;

/** Optional synchronous shared-engine constructor hook for M7; not a fit parameter. */
@FunctionalInterface
public interface IterationObserver {
    /** Engines must skip snapshot creation entirely when using this sentinel. */
    IterationObserver NONE = snapshot -> { };

    /** Called after an update cycle; errors propagate. UI owners marshal threads. */
    void onIteration(IterationSnapshot snapshot);
}
