# How to submit your work

Complete [first-time setup](docs/getting-started.md) first. These steps use **GitHub Desktop** on Windows/macOS. Prefer commands or use Linux? Use [the command-line guide](docs/command-line.md).

**The flow:** task → branch → edit and test → commit → push → pull request → review → merge.

## Step 1 — Choose one small task

1. Read [your member checklist](docs/ownership.md) and linked issue. An **issue** is a page describing work to do.
2. Agree the first small piece with the leader. One module may take several pull requests.
3. Identify dependencies. You can often write a test or fixture while another member's part is unfinished.

## Step 2 — Get the latest shared code

1. Open this repository in GitHub Desktop.
2. Handle unfinished changes before switching branches. If Desktop asks what to do and you are unsure, ask for help; do not discard them.
3. Choose **Current branch → main**.
4. Click **Fetch origin**, then **Pull origin** if it appears.

`main` is the team's shared version. Fetch checks for updates; Pull brings them into your copy.

## Step 3 — Create a task branch

1. With updated `main` selected, choose **Current branch → New branch**.
2. Name it for the task, such as `feat/2-sequential-assignment` for issue #2.
3. Click **Create branch**.
4. Check the branch name before editing.

A **branch** gives your task a separate place to grow until review. Use a new branch for each small task, rather than one permanent branch per person.

## Step 4 — Edit and test your module

1. Open the project in your Java editor.
2. Edit the folders listed for your role.
3. Write tests for the behavior you implement. Name test files `SomethingTest.java` so the current build finds them.
4. Discuss changes to another member's files or shared interfaces with that member and M1 first.

M6 tests the whole system; each member still tests their own code. Do not copy another member's algorithm/math as a temporary workaround.

## Step 5 — Run the checks

Use the editor terminal in the project folder.

**Windows PowerShell:**

```powershell
.\mvnw.cmd --batch-mode --no-transfer-progress verify
```

**macOS/Linux:**

```sh
./mvnw --batch-mode --no-transfer-progress verify
```

Wait for **BUILD SUCCESS** and zero failures/errors. Fix or discuss failures before asking to merge. You may open a **draft** PR for help with unfinished work; say what still fails.

## Step 6 — Commit your changes

1. Open Desktop's **Changes** tab.
2. Review each changed file and check that it belongs to your task.
3. Leave credentials, generated `target/` files, and unrelated edits out of the commit. Do not discard unfamiliar changes.
4. Write a **Summary**, such as `Add sequential point assignment`.
5. Click **Commit to [your branch]**.

A **commit** saves a checkpoint on your computer. It does not upload it yet. Several small commits on one task branch are fine.

## Step 7 — Push your branch

1. Click **Publish branch** the first time you share it.
2. Use **Push origin** for later commits on that branch.

**Push** uploads your work to GitHub. Do not Force Push or send your task directly to `main`.

## Step 8 — Open a pull request

1. Use Desktop's **Preview Pull Request** / **Create Pull Request** when offered, or GitHub's **Pull requests → New pull request**.
2. Check **base: main** and **compare: your task branch**.
3. Give it a short title explaining the change.
4. Fill in the template: issue, changed behavior, dependencies, and actual test results.
5. Write **Related to #2**, for example, when only part of a module is finished. Use **Closes #2** only when the whole issue is complete: merging then closes it automatically.
6. Request another member's review. Include M1 and affected owners for shared contract changes.

A **pull request (PR)** is the review page; creating it does not change `main` yet.

## Step 9 — Respond to review and checks

1. Open the PR's **Checks** tab. Our `build-and-test` check builds/tests the project on GitHub. This is continuous integration (**CI**).
2. Wait for success. If it fails, open the log to find the error.
3. Read review comments, make corrections on the **same task branch**, test, commit, and push again.
4. GitHub updates the existing PR automatically; do not make a new PR for every correction.

A **merge conflict** means changes overlap. Ask the affected teammate/leader to help reconcile them. Do not overwrite someone else's work just to clear the warning.

## Step 10 — Merge and prepare for the next task

1. Confirm another member approved, `build-and-test` passed, and comments were addressed.
2. Coordinate with the leader about who clicks **Squash and merge**.
3. Delete the merged task branch on GitHub when appropriate; keep unfinished branches.
4. Switch to `main` in Desktop and Fetch/Pull again.
5. Create a fresh branch from updated `main` for the next task.

**This task is done when:** its reviewed changes are merged, checks pass, and its issue accurately shows any work remaining.

Every member also contributes to the report/presentation and prepares their own explanation. See [deliverables](docs/deliverables.md).
