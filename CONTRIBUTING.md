# Contributing to DNSimple/Java

## Getting started

### 1. Clone the repository

Clone the repository and move into it:

```shell
git clone git@github.com:dnsimple/dnsimple-java.git
cd dnsimple-java
```

### 2. Install external tools

- Eclipse Temurin JDK 21.0.5+11-:TS

    From [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)

### 3. Build and test

[Run the test suite](#testing) to check everything works as expected.

### 4. Deploy locally for testing

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

GitHub will take the new tag and release it automatically into Maven Central

## Requirements for contributing code to this repo

Submit unit tests for your changes. You can test your changes on your machine by [running the test suite](#testing).

When you submit a PR, tests will also be run on the [continuous integration environment via GitHub Actions](https://github.com/dnsimple/dnsimple-java/actions).
