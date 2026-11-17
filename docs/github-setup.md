# Leader's GitHub setup checklist

The setup creates repository files, a reviewable foundation PR, and seven unassigned work items. It does not automatically merge the PR, invite unknown people, or claim administration settings were changed.

## Complete onboarding

1. Collect the six teammates' actual GitHub usernames and map everyone to M1–M7 in `ownership.md`.
2. Repository **Settings → Collaborators → Add people**: invite the confirmed users with write access. They must accept before pushing branches.
3. Assign the seven module issues, confirm public API signatures with M2–M5, and have each member clone and run `verify`.
4. Add `.github/CODEOWNERS` using confirmed accounts with repository access, matching the ownership table. Include both source/test paths and review coverage for shared API/build changes. Do not use invented or unconfirmed usernames.

## Protect the shared branch

After `build-and-test` has appeared in the first PR check run, use **Settings → Rules → Rulesets** (or **Branches → Branch protection rules**, depending on GitHub UI): target `main`, require a pull request, at least one approving review, resolved conversations, and successful `build-and-test`. Block force pushes and branch deletion. Review bypass settings deliberately; avoid blocking setup before a check has ever run.

Under **Settings → General**, ensure Issues are enabled, prefer squash merging, and enable automatic deletion of merged head branches. A Project board with Backlog/Ready/In Progress/In Review/Done is optional; issue status notes work until it exists.

The connected GitHub tools expose repository files, branches, PRs, and issues but do not expose administration or invitation writes. The authenticated CLI also returned `403 Resource not accessible by integration` when attempting repository settings. These settings remain owner steps; none is claimed as configured. Issues and squash merging were observed enabled; automatic deletion of merged branches was observed disabled. The repository being public does not grant teammates write access.

## Review the foundation

Review the setup PR and its passing CI, then merge it when satisfied. Gate 0 is reached only when the full foundation is on `main` and verified. Team feature branches should start from that merged `main`. Algorithms and benchmark evidence remain open work.
