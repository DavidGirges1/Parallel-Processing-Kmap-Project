# Contributing

1. Accept your repository invitation. Public visibility lets you clone; it does not let you push a branch. The leader must grant repository write access.
2. Read `AGENTS.md`, your module's issue, and [the contracts](docs/architecture.md). Agree shared signatures with affected members before coding.
3. Start from an updated `main` after the foundation PR is merged:

   ```sh
   git switch main
   git pull --ff-only origin main
   git switch -c feat/ISSUE_NUMBER-short-description
   ```

4. Keep changes inside your module and its tests. Discuss cross-module changes in the issue/PR first. Add tests for your behavior; M6 does not write everyone's unit tests.
5. Run `./mvnw --batch-mode --no-transfer-progress verify` (PowerShell: `.\mvnw.cmd --batch-mode --no-transfer-progress verify`). Tests under any package, including `integration`, should use `*Test.java` names so this command executes them. New `*IT` tests need a separately agreed Failsafe configuration.
6. Review `git diff`, stage only intended files, commit, then push your branch:

   ```sh
   git add path/to/your/files
   git commit -m "feat: describe the observable change"
   git push -u origin HEAD
   ```

7. Open a PR to `main` using the template. Link the issue, explain the change, provide actual test output/evidence, and request another member's review. M1 and affected owners review shared contract changes. Request changes or approve based on code and evidence, not ownership alone.
8. Merge only after review, passing `build-and-test`, and resolved conversations. Prefer squash merging, then delete the merged feature branch. Do not force push `main`, overwrite others' work, or merge unrelated unfinished work.

Keep PRs small and buildable. Mark work blocked and identify the dependency. Never commit credentials, IDE-local files, generated build output, mock benchmark results, or fabricated performance claims. Do not add a license on behalf of teammates without agreement.

For conflicts, fetch `origin`, merge `origin/main` into your feature branch, resolve with the affected owner, and rerun tests. Avoid rewriting a shared feature branch's history.
