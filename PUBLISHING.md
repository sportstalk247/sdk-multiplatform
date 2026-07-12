# Publishing runbook

How to publish the Sportstalk247 KMP SDK (`:shared:core`, `:shared:model`) to **Maven Central** via the
**Sonatype Central Portal**.

- Coordinates: `io.github.sportstalk247.sdk-multiplatform:core:<version>` and `:model:<version>`
- Tooling: [Vanniktech Maven Publish plugin](https://vanniktech.github.io/gradle-maven-publish-plugin/) → Central Portal
- Version is set in [`build.gradle.kts`](build.gradle.kts) (`packageVersion`).

> Background: the legacy OSSRH service (`oss.sonatype.org` / `s01.oss.sonatype.org`) was shut down on
> June 30, 2025. All namespaces were migrated to <https://central.sonatype.com>. This runbook uses the
> new Portal only.

---

## One-time setup

### 1. Access the namespace on the Central Portal
Sign in to <https://central.sonatype.com> with your **original OSSRH username/password account**
(**not** "Sign in with GitHub/Google" — social logins do not see migrated OSSRH namespaces).
Confirm `io.github.sportstalk247` is listed and **Verified** at
<https://central.sonatype.com/publishing/namespaces>.

If it's missing / shows "Namespace exists" when you try to register it, you're on the wrong account —
recover the original one, or email **central-support@sonatype.com** to transfer the namespace (offer to
prove ownership of `github.com/sportstalk247`, since `io.github.*` maps to that GitHub identity).

### 2. Generate a Portal user token
Account menu (top-right) → **Generate User Token**. Copy the token username + password.
(Old OSSRH tokens return `401`.)

### 3. Publish your GPG public key to a keyserver
Central validates the `.asc` signatures against public keyservers:
```bash
gpg --list-keys                                    # find <KEY_ID>
gpg --keyserver keyserver.ubuntu.com --send-keys <KEY_ID>
gpg --keyserver keys.openpgp.org      --send-keys <KEY_ID>
```

### 4. Put credentials + signing key in `~/.gradle/gradle.properties`
**Global file, outside the repo — never commit.** The plugin reads these Gradle properties (not `local.properties`):
```properties
# Central Portal user token from step 2
mavenCentralUsername=<token-username>
mavenCentralPassword=<token-password>

# In-memory PGP signing key — armored private key from `gpg --export-secret-keys --armor <KEY_ID>`
signingInMemoryKey=<armored-secret-key>
signingInMemoryKeyId=<last-8-hex-chars-of-key-id>
signingInMemoryKeyPassword=<key-passphrase>
```
Mapping from the old `local.properties` values: `signing.key → signingInMemoryKey`,
`signing.keyId → signingInMemoryKeyId`, `signing.password → signingInMemoryKeyPassword`.
The old `ossrh*` / `signing.*` entries in `local.properties` are no longer read and can be removed
(keep `sdk.dir`).

---

## Release a version

1. Set the release version in [`build.gradle.kts`](build.gradle.kts) (`packageVersion`).
   Use a plain version (e.g. `2.0.0-beta08`) for a release; a `-SNAPSHOT` suffix routes to the
   snapshot repo instead.
2. Publish and auto-release:
   ```bash
   ./gradlew publishAndReleaseToMavenCentral --no-configuration-cache
   ```
   Or stage only, then click **Publish** at <https://central.sonatype.com/publishing/deployments>:
   ```bash
   ./gradlew publishToMavenCentral --no-configuration-cache
   ```
3. Track validation/publish at <https://central.sonatype.com/publishing/deployments>.
4. Confirm it went public (10–30 min to sync):
   `https://repo1.maven.org/maven2/io/github/sportstalk247/sdk-multiplatform/core/<version>/`

---

## Verify locally before releasing

```bash
./gradlew clean publishToMavenLocal
ls -R ~/.m2/repository/io/github/sportstalk247/sdk-multiplatform/
```
Expect, per module (`core`, `model`): root `*.module` + `.pom`, `-android` AAR, `-iosarm64` /
`-iossimulatorarm64` klibs, a `-javadoc.jar`, a `-sources.jar`, and `.asc` signatures.
Inspect a `.pom` without keys via, e.g.,
`./gradlew :shared:core:generatePomFileForKotlinMultiplatformPublication` →
`shared/core/build/publications/kotlinMultiplatform/pom-default.xml`.

---

## Notes

- **Consumers are unaffected** — released artifacts stay on `mavenCentral()` with the same coordinates.
  Only SNAPSHOT consumers use the new repo `https://central.sonatype.com/repository/maven-snapshots/`.
- **iOS distribution** (the `Sportstalk247Kit` CocoaPods/SPM framework) is produced by `:shared:kmmbridge`
  and is a separate pipeline from Maven Central.

## Troubleshooting

| Symptom | Fix |
|---|---|
| `401` on publish | Regenerate a Portal user token (step 2); OSSRH tokens no longer work. |
| Deployment rejected: missing POM fields | POM must include `scm.connection`/`developerConnection` (already set in `build.gradle.kts`). |
| Deployment rejected: signature invalid / key not found | Ensure the **public** key is on a keyserver (step 3) and `signingInMemory*` are set. |
| "Namespace exists" / no namespaces | You're on the wrong Portal account — see setup step 1. |
