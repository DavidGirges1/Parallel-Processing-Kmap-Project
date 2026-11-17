# Leader's GitHub setup checklist

Foundation [PR #8](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project/pull/8) is merged and its checks passed. Seven starter issues are open. The leader still needs to confirm names, invite teammates, and configure the settings below.

## Complete onboarding

1. Collect the six teammates' actual GitHub usernames.
2. Map each person to M1–M7 in [the member guide](ownership.md).
3. Open repository **Settings → Collaborators → Add people** and invite the confirmed users with write access.
4. Ask each member to accept the invitation and complete [Getting started](getting-started.md).
5. Open each starter issue, use its **Assignees** section to select the member, and confirm their first small task.
6. Walk M2–M5 through the shared method signatures before they start independent coding.
7. Add `.github/CODEOWNERS` only after accounts/access are confirmed. This file tells GitHub whom to request for reviews; match both source/test paths to the member roles.

## Protect the shared branch

1. Open **Settings → Rules → Rulesets**, or **Branches → Branch protection rules**, depending on the GitHub UI.
2. Create a rule targeting `main`.
3. Require a pull request and at least one approving review.
4. Require resolved review conversations.
5. Require successful `build-and-test` (the check has already run in PR #8).
6. Block force pushes and deletion of `main`.
7. Review who can bypass the rule, then enable/save it.

Under **Settings → General**:

1. Ensure Issues are enabled.
2. Keep squash merging available for small PRs.
3. Enable automatic deletion of merged task branches if desired. This deletes a branch after merging; it does not delete `main` or its merged work.

A Project board with Backlog/Ready/In Progress/In Review/Done is optional; issue status notes work until it exists.

The connected GitHub tools expose repository files, branches, PRs, and issues but do not expose administration or invitation writes. The authenticated CLI also returned `403 Resource not accessible by integration` when attempting repository settings. These settings remain owner steps; none is claimed as configured. Issues and squash merging were observed enabled; automatic deletion of merged branches was observed disabled. The repository being public does not grant teammates write access.

## Start team work from main

The foundation is available on `main`. Ask members to update their local `main` before creating task branches. Algorithms, benchmark evidence, and the integrated demo remain open work; use [the dependency guide](workflow.md) to coordinate them.
