# Contributing to DNSimple/Java

## Getting started

Clone the repository and move into it:

```
$ git clone git@github.com:aeden/dnsimple-java.git
$ cd dnsimple-java
```

Install Maven.

[Run the test suite](#testing) to check everything works as expected.

## Testing

To run the test suite:

    mvn test

## Tests

Submit unit tests for your changes. You can test your changes on your machine by [running the test suite](#testing).

## Releasing

Releasing may only be done by one of the DNSimple team members (specifically Anthony as of today).

Once `~/.m2/settings.xml` is updated to include the ossrh server configuration and the ossrh profile with the GPG passphrase for using the private key for signing, deployment is then done with the command:

    mvn clean deploy
