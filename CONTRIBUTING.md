# Contributing to DNSimple/Java

## Getting started

#### 1. Clone the repository

Clone the repository and move into it:

```shell
git clone git@github.com:dnsimple/dnsimple-java.git
cd dnsimple-java
```

#### 2. Install external tools

The project includes an [ASDF](https://github.com/asdf-vm/asdf) `.tool-versions` file to set up your JVM and Gradle dependencies in your system to work on the project.

You can install the required version of Java executing `asdf install` at the root directory of the project.

#### 3. Build and test

[Run the test suite](#testing) to check everything works as expected.

## Testing

To run the test suite:

```shell
./gradlew clean test
```
(use `gradlew.bat` in Windows instead)

## Releasing

This project uses [Semantic Versioning](https://semver.org/). The following instructions uses `<VERSION>` as a placeholder, where `$VERSION` is a `MAJOR.MINOR.BUGFIX` release such as `1.2.0`.

1. Set the version in `dnsimple.java`:
    ```java
    public abstract class Dnsimple {
      public static final String VERSION = "<VERSION>";
      //...
    }
    ```
1. Set the version in `build.gradle`:
    ```groovy
    version = '<VERSION>'
    ```
1. Run the test suite and ensure all the tests pass.
1. Finalize the `## master` section in `CHANGELOG.md` assigning the version.
1. Commit and push the changes
    ```shell
    git commit -a -m "Release $VERSION"
    git push origin master
    ```
1. Wait for CI to complete.
1. Create a signed tag.
    ```shell
    git tag -a v$VERSION -s -m "Release $VERSION"
    git push origin --tags
    ```
1. Release.
    _TBD_

## Requirements for contributing code to this repo

Submit unit tests for your changes. You can test your changes on your machine by [running the test suite](#testing).

When you submit a PR, tests will also be run on the [continuous integration environment via Travis](https://travis-ci.org/dnsimple/dnsimple-java).
