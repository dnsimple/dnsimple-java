# Contributing to DNSimple/Java

## Getting started

#### 1. Clone the repository

Clone the repository and move into it:

```shell
git clone git@github.com:dnsimple/dnsimple-java.git
cd dnsimple-java
```

#### 2. Install external tools

The project includes an [ASDF](https://github.com/asdf-vm/asdf) `.tool-versions` file to set up the JVM required to work on the project.

You can install the required version of Java executing `asdf install` at the project's root directory.

#### 3. Build and test

[Run the test suite](#testing) to check everything works as expected.

#### 4. Deploy locally for testing

You can install this project into your local Maven repository by running:

```shell
./gradlew clean publishToMavenLocal
```
(use `gradlew.bat` in Windows instead)

You can then import it into any project as if it were published to the Maven Central repo. 

## Testing

To run the test suite:

```shell
./gradlew clean test
```
(use `gradlew.bat` in Windows instead)

## Releasing

This project uses [Semantic Versioning](https://semver.org/). The following instructions use `<VERSION>` as a placeholder, where `$VERSION` is a `MAJOR.MINOR.PATCH` release, such as `1.2.0`.

1. [Run the test suite](#testing) and ensure all the tests pass.
1. Set the version in `dnsimple.java`:
    ```java
    public interface Dnsimple {
      public static final String VERSION = "<VERSION>";
      //...
    }
    ```
1. Set the version in `build.gradle`:
    ```groovy
    version = '<VERSION>'
    ```
1. Finalize the `## main` section in `CHANGELOG.md` assigning the version.
1. Commit and push the changes
    ```shell
    git commit -a -m "Release $VERSION"
    git push origin main
    ```
1. Wait for CI to complete.
1. Create a signed tag.
    ```shell
    git tag -a v$VERSION -s -m "Release $VERSION"
    git push origin --tags
    ```
1. Publish the signed JAR to OSSRH (be sure to go through the [Release configuration](#release-configuration) section first).
    ```shell
    ./gradlew clean publish
    ```
    (use `gradlew.bat` in Windows instead)

### Release configuration

You need to create a `gradle.properties` file in the root directory of the project. Start by copy and pasting the example `gradle.properties.example` file on this repo.

Fill in your signing and OSSRH settings:
- If you have no GPG setup, you can get your signing `keyId`, `password`, and `secretKeyRingFile` values following the guide at https://central.sonatype.org/pages/working-with-pgp-signatures.html
- If you don't have an OSSRH account, you can get it following the "Initial Setup" section of the guide at https://central.sonatype.org/pages/ossrh-guide.html

## Requirements for contributing code to this repo

Submit unit tests for your changes. You can test your changes on your machine by [running the test suite](#testing).

When you submit a PR, tests will also be run on the [continuous integration environment via Travis](https://travis-ci.com/dnsimple/dnsimple-java).
