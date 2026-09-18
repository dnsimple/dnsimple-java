# Releasing

This document describes the steps to release a new version of DNSimple/Java.

## Prerequisites

- You have commit access to the repository
- You have push access to the repository
- You have a GPG key configured for signing tags

## Release process

1. **Determine the new version** using [Semantic Versioning](https://semver.org/)

   ```shell
   VERSION=X.Y.Z
   ```

   - **MAJOR** version for incompatible API changes
   - **MINOR** version for backwards-compatible functionality additions
   - **PATCH** version for backwards-compatible bug fixes

   Check the `## Unreleased` section in `CHANGELOG.md` for entries prefixed with `**BREAKING**:`. Any such entry requires a **MAJOR** version, whatever the other entries are. Raising the minimum supported Java version is always a breaking change, so its changelog entry must carry this prefix.

2. **Run tests** and confirm they pass

   ```shell
   ./gradlew clean test
   ```

   (use `gradlew.bat` in Windows instead)

3. **Update the version files** with the new version

   Edit `dnsimple.java`:

   ```java
   public interface Dnsimple {
     public static final String VERSION = "$VERSION";
     //...
   }
   ```

   Edit `build.gradle` (setting the proper version):

   ```groovy
   version = '$VERSION'
   ```

   Edit `VERSION` (setting the proper version):

   ```text
   $VERSION
   ```

4. **Update the changelog** with the new version

   Finalize the `## Unreleased` section in `CHANGELOG.md` assigning the version.

5. **Commit the new version**

   ```shell
   git commit -a -m "Release $VERSION"
   ```

6. **Push the changes**

   ```shell
   git push origin main
   ```

7. **Wait for CI to complete**

8. **Create a signed tag**

   ```shell
   git tag -a v$VERSION -s -m "Release $VERSION"
   git push origin --tags
   ```

   GitHub will take the new tag and release it automatically into Maven Central.

## Post-release

- Verify the new version appears on [Maven Central](https://search.maven.org/artifact/com.dnsimple/dnsimple)
- Verify the GitHub release was created
- Announce the release if necessary
