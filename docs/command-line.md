# Optional Git command route

Use this on Linux or if you prefer a terminal to GitHub Desktop. It follows the same process as [Contributing](../CONTRIBUTING.md); you do not need both workflows.

## 1. Install tools and identify your commits

Install [Git](https://git-scm.com/downloads), JDK 17, and the tools in [Getting started](getting-started.md).

If Git is not configured, set your own name/email. Replace these examples with your details. Use an email associated with GitHub or your account's private `noreply` email.

```sh
git config --global user.name "Your Name"
git config --global user.email "your-email@example.com"
```

### Sign in before your first upload

1. Install [GitHub CLI](https://cli.github.com/). On Debian/Ubuntu, use `sudo apt-get install gh`; for another system, follow the official installation instructions linked from that site.
2. Run this command and follow the browser sign-in instructions. Use the account invited to this repository:

   ```sh
   gh auth login --hostname github.com --git-protocol https --web
   ```

3. Let Git use that login, then check which account is signed in:

   ```sh
   gh auth setup-git
   gh auth status
   ```

4. Confirm the displayed account is yours. You are ready to push later in step 5.

GitHub does not accept your account password as a Git HTTPS password. If a push still asks for one, stop and ask the leader to check this setup. Do not paste access tokens into issues or shared commands.

## 2. Clone once

```sh
git clone https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project.git
cd Parallel-Processing-Kmap-Project
```

If you already cloned with Desktop, use that folder instead. Return to [Getting started](getting-started.md) at **Step 5** to open the project and check the build.

## 3. Start a task branch

Check for unfinished changes before switching. Ask before discarding unfamiliar changes. Run commands one at a time and investigate an error before continuing.

```sh
git status
git switch main
git pull --ff-only origin main
git switch -c feat/2-sequential-assignment
```

The last line is an example for issue #2. Replace the branch name with your task's issue number and description.

## 4. Edit and verify

Edit the agreed task files/tests, then run from the project folder:

```sh
./mvnw --batch-mode --no-transfer-progress verify
```

Windows PowerShell: use `.\mvnw.cmd --batch-mode --no-transfer-progress verify`. Wait for **BUILD SUCCESS** and zero failures/errors.

## 5. Commit and push

```sh
git status
git diff
```

Review the output. Replace the example path below with a file you actually changed; repeat `git add` for each intended file.

```sh
git add src/main/java/edu/team/kmeans/sequential/YourClass.java
git diff --cached
git commit -m "Add sequential point assignment"
git push -u origin HEAD
```

Leave out credentials, generated output, and unrelated files. Do not force push `main`.

## 6. Open and finish a pull request

1. Open the repository on GitHub.
2. Choose **Pull requests → New pull request**.
3. Select base `main` and compare your task branch.
4. Follow **Steps 8–10** in [Contributing](../CONTRIBUTING.md) for the description, review, checks, and merge.

If pull/push fails or Git reports conflicts, coordinate with the leader. Do not use discard, `reset --hard`, or force-push commands to dismiss an unfamiliar problem.
