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

    From [https://adoptium.net/es/temurin/releases/](https://adoptium.net/es/temurin/releases/)

### 3. Build and test

[Run the test suite](#testing) to check everything works as expected.

### 4. Deploy locally for testing

You can install this project into your local Maven repository by running:

```shell
./gradlew clean publishToMavenLocal
```

(use `gradlew.bat` in Windows instead)

You can then import it into any project as if it were published to the Maven Central repo.

## Changelog

We follow the [Common Changelog](https://common-changelog.org/) format for changelog entries.

## Testing

To run the test suite:

```shell
./gradlew clean test
```

(use `gradlew.bat` in Windows instead)

## Requirements for contributing code to this repo

Submit unit tests for your changes. You can test your changes on your machine by [running the test suite](#testing).

When you submit a PR, tests will also be run on the [continuous integration environment via GitHub Actions](https://github.com/dnsimple/dnsimple-java/actions).
