# Contributing to DNSimple/Java


## Getting started

Using [jenv](http://www.jenv.be/) makes it possible to test on multiple versions of Java.

#### 1. Clone the repository

Clone the repository and move into it:

```shell
git clone git@github.com:aeden/dnsimple-java.git
cd dnsimple-java
```

#### 2. Install Maven

Install [Maven](https://maven.apache.org/install.html).

#### 3. Build and test

[Run the test suite](#testing) to check everything works as expected.


## Testing

To run the test suite:

```shell
mvn test
```


## Releasing

Releasing may only be done by one of the DNSimple team members (specifically Anthony as of today).

You need to update the Maven configuration file to include the ossrh server configuration and the ossrh profile with the GPG passphrase for using the private key for signing.

You should customize your Mave user-setting, you can find at `~/.m2/settings.xml`. If no setting file is present, you can create a new one from the [default Maven global settings](https://maven.apache.org/settings.html). Locate the file, copy it to `~/.m2/settings.xml` and update it accordingly.

```shell
cp /usr/local/Cellar/maven/<VERSION>/libexec/conf/settings.xml ~/.m2/settings.xml
chmod 600 ~/.m2/settings.xml
vim ~/.m2/settings.xml 
```

Edit the node `/settings/servers` and add the following configuration, using your Sonatype (ossrh) username and password:

```xml
<server>
  <id>ossrh</id>
  <username>USERNAME</username>
  <password>PASSWORD</password>
</server>
```

Edit the node `/settings/profiles` and add the following profile:

```xml
<profile>
  <id>ossrh</id>
  <activation>
    <activeByDefault>true</activeByDefault>
  </activation>
  <properties>
    <gpg.passphrase>PASSPHRASE</gpg.passphrase>
  </properties>
</profile>
```

Deployment is then done with the command:

```shell
mvn clean deploy -P release
```


## Tests

Submit unit tests for your changes. You can test your changes on your machine by [running the test suite](#testing).

When you submit a PR, tests will also be run on the [continuous integration environment via Travis](https://travis-ci.org/dnsimple/dnsimple-java).
