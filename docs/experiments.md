# Correctness and experiments

**No benchmark measurements have been collected by the foundation.** This file defines a protocol; it is not results evidence. M6 owns the harness, while every member owns unit tests for their module.

## Correctness before timing

Use hand-checked tiny datasets and the shared assignment fixture. Cover one cluster, duplicates, ties, empty clusters, K above unique point count, empty input, non-finite data, invalid config, maximum-iteration termination, zero/nonzero tolerance, and convergence. Verify assignment count/range, finite centroids, and SSE against returned assignments/centroids. Test Fork/Join inputs both smaller and larger than the split threshold, odd range sizes, and different worker counts.

Compare both implementations on **identical point coordinates, initial centroid coordinates, and K**. Use numerical tolerances and account for cluster-label permutation where necessary. Do not demand byte-identical floating-point output without justification.

## Measurement protocol for the later harness

1. Run headlessly; disable observation/snapshots. Exclude dataset generation and initial-centroid selection from fit timing. Reuse the configured pool and report its lifecycle separately.
2. Warm up the JVM, then make multiple measured runs. Alternate execution order to reduce order bias; report warmups, repetitions, summary statistics, and raw samples.
3. Vary N and K. An initial proposed matrix is N = 1,000 / 10,000 / 100,000 and K = 2 / 4 / 8, adjusted for the actual machine and enough unique points. These are team suggestions, not official requirements or measured workloads.
4. Vary task thresholds and optionally pool workers. Record seed and initialization method plus reproducible initial-centroid coordinates/data identity. Use identical starts for every matched sequential/parallel pair.
5. Capture Java version, OS, CPU, available cores, pool workers, and machine load context. Use monotonic `System.nanoTime()` for elapsed intervals.
6. Report total fit time (including final reassignment/SSE), each iteration's elapsed time, completed iterations, SSE, and `speedup = sequential time / parallel time`. If reporting total/iterations, label it as an average including finalization; it is not a directly measured individual iteration time. A harness may instrument cycle boundaries with lightweight timing, separately from snapshot observers.
7. Interpret task overhead and speedup below 1 for small workloads. Never imply a required numerical speedup target.

## Output evidence

Keep raw samples/configuration in `results/`, with units and execution instructions beside them. Suggested fields: run identifier, dataset identity, N, K, seed, initial centroid identity, implementation, threshold, workers, warmup/measured flag, total nanoseconds, iteration nanoseconds, iterations, SSE, paired speedup, machine/JDK. Derive plots/tables from actual samples. Screenshots alone are insufficient; exclude development mocks from recorded results.
