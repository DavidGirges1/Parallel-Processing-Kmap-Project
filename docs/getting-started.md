# First-time setup: follow these steps in order

This guide gets the existing project building on your computer. You are not expected to implement K-Means during setup.

**Windows/macOS:** use the GitHub Desktop steps below.

**Linux:** follow steps 1–2, use [the command-line guide](command-line.md) to clone, then return at step 5. GitHub Desktop does not have an official Linux version.

## Step 1 — Get access

1. Create an account at [github.com](https://github.com/) if you do not have one.
2. Send your **GitHub username** to the team leader.
3. Ask which role, **M1–M7**, you own.
4. Accept the repository invitation in your email or GitHub notifications.
5. Open [our repository](https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project).

**Ready when:** you know your role and have accepted the invitation. The repository is public, so being able to read files alone does not mean you can upload work.

## Step 2 — Install the tools

| Install | Choose | Purpose |
|---|---|---|
| [Temurin JDK](https://adoptium.net/temurin/releases/?version=17) | **JDK 17**, matching your operating system and CPU | Compile and run Java |
| [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) | Its free Java features are sufficient | Open and edit the project |
| [GitHub Desktop](https://desktop.github.com/download/) | Windows or macOS version | Download/upload work without Git commands |

1. Install JDK 17. Enable **Add to PATH** / **Set JAVA_HOME** if offered by the installer.
2. Install your Java editor. If you already use Eclipse or VS Code for Java, you can keep using it.
3. Install GitHub Desktop on Windows/macOS.
4. Restart your editor and terminal windows after installing Java.

You do **not** need a separate Maven installation. The project's Maven Wrapper downloads the correct version on the first build.

**macOS/Linux:** also ensure `unzip` and either `curl` or `wget` are installed. The checksum-pinned wrapper needs `unzip` for its ZIP download. On Debian/Ubuntu:

```sh
sudo apt-get install unzip curl
```

Use your distribution's package manager on other Linux systems. Ask the leader if you are unsure.

## Step 3 — Sign in to GitHub Desktop

1. Open GitHub Desktop.
2. Choose **Sign in to GitHub.com** and complete the browser sign-in.
3. Use the same account you gave to the leader.
4. If asked for a commit name/email, use your own name and an email associated with your GitHub account. The private `noreply` email in your GitHub account settings is also an option.

**Ready when:** Desktop is signed into your account.

## Step 4 — Clone the repository

**Clone** means download a working copy connected to GitHub.

1. Choose **File → Clone repository** in GitHub Desktop.
2. Select the **URL** tab.
3. Paste this URL:

   ```text
   https://github.com/DavidGirges1/Parallel-Processing-Kmap-Project.git
   ```

4. Choose a local folder you can find again, such as a folder inside `Documents`.
5. Click **Clone**.
6. Check that **Current branch** is `main`.

**Ready when:** the downloaded folder contains `README.md`, `pom.xml`, `mvnw`, `mvnw.cmd`, and `src`.

Use Clone rather than **Download ZIP**: the ZIP is not connected for uploading changes. You do not need a separate **fork** of this team repository.

## Step 5 — Open the project in your editor

In IntelliJ IDEA:

1. Choose **Open** and select the downloaded folder containing `pom.xml`.
2. If prompted, open/import it as a **Maven project**.
3. Trust the project after checking it is our team's repository.
4. Open **File → Project Structure → Project** and choose **JDK 17** as the Project SDK. Add your installed JDK, or choose **Download JDK → version 17**, if needed.
5. Wait for Maven to load dependencies. First use needs internet access.
6. Find `src/main/java/edu/team/kmeans` in the project panel.

**Ready when:** the packages are visible and the project uses JDK 17.

## Step 6 — Check Java in the terminal

1. Open the editor's **Terminal** tab: the area where you type commands and press Enter.
2. Make sure it is in the project folder containing `pom.xml`.
3. Run:

   ```sh
   java -version
   ```

4. Check that the version starts with **17**.

If the terminal reports another version, ask for help setting `JAVA_HOME` and `PATH` to JDK 17. These computer settings tell tools where Java is installed. An editor's Java setting and a terminal's Java setting can differ.

## Step 7 — Build and run the existing tests

Use **one** command for your operating system, from the project folder.

**Windows PowerShell:**

```powershell
.\mvnw.cmd --batch-mode --no-transfer-progress verify
```

**macOS/Linux:**

```sh
./mvnw --batch-mode --no-transfer-progress verify
```

1. Wait for downloads and the build to finish. The first run takes longer.
2. Look near the end for **BUILD SUCCESS**.
3. Check that test failures and errors are both **0**.

**Ready when:** the build succeeds. The foundation originally contains 23 test cases; the number will grow as members add tests.

There is no clustering app to launch yet. This command compiles the project and runs tests; it does not mean the full assignment is complete.

## Step 8 — Find your first coding task

1. Open [member checklists](ownership.md).
2. Find your M1–M7 section.
3. Read **Do this first**, **What you receive and provide**, and **Done when**.
4. Open your linked issue and confirm the scope with the leader.
5. Follow [Contributing](../CONTRIBUTING.md) to create a branch before editing.

**Setup complete:** you have access, a successful build, a confirmed role, and a clear first task.

## If you get stuck

| Problem | Next step |
|---|---|
| `java` not recognized / command not found | Install JDK 17 and restart the terminal. Ask for help with PATH/JAVA_HOME if needed. |
| Invalid `JAVA_HOME` | Point it to the JDK 17 folder, not its `bin` subfolder or a JRE. |
| `mvnw` or `pom.xml` not found | Open the terminal in the downloaded project folder; use the Windows command on Windows. |
| `Permission denied` for `./mvnw` | On macOS/Linux, run `chmod +x mvnw`, then retry. |
| Maven checksum error | Check `unzip` is installed on macOS/Linux, then ask for help. Do not disable checksum verification. |
| Download/certificate/network error | Check the connection and campus proxy settings with the leader. Do not turn off certificate verification. |
| Publish/Push refused | Confirm your invitation was accepted and Desktop uses the invited account. Ask the leader to check write access. |
| Tests fail | Share the failing test name and error output. Do not delete or disable tests to make a build pass. |

When asking for help, include your operating system, role, the command/button you used, and the full error text. Never include passwords or access tokens.
