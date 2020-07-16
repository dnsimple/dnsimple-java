# Java DNSimple API Wrapper

A Java client for the [DNSimple API v2](https://developer.dnsimple.com/v2/).

[![Build Status](https://travis-ci.org/dnsimple/dnsimple-java.svg?branch=master)](https://travis-ci.org/dnsimple/dnsimple-java)

[DNSimple](https://dnsimple.com/) provides DNS hosting and domain registration that is simple and friendly.
We provide a full API and an easy-to-use web interface so you can get your domain registered and set up with a minimal amount of effort.

## :warning: Alpha Warning

This project targets the development of the API client for the [DNSimple API v2](https://developer.dnsimple.com/v2/).

This library is currently in alpha version, the methods and the implementation should be considered a work-in-progress. Changes in the method naming, method signatures, public or internal APIs will happen during the alpha period.

## Requirements

This library is tested with Java 11.

You must also have an activated DNSimple account to access the DNSimple API.

## Installation

### Maven

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.dnsimple</groupId>
  <artifactId>dnsimple-java</artifactId>
  <version>X.X.X</version>
</dependency>
```

### Gradle

Add this dependency to your `build.gradle` file:

```groovy
compile 'com.dnsimple:dnsimple-java:X.X.X'
```

## Usage

This library is a Java client you can use to interact with the [DNSimple API v2](https://developer.dnsimple.com/v2/).

The examples below demonstrate basic usage.

```java
package myapp;

import com.dnsimple.Client;
import com.dnsimple.data.Account;
import com.dnsimple.response.SimpleResponse;

public class MyApp {
  public static void main(String[] args) {
    Client client = new Client.Builder()
                              .accessToken("YOUR-ACCESS-TOKEN")
                              .userAgent("your-user-agent")
                              .build();
    SimpleResponse<Account> response = client.identity.whoami();
    Account account = response.getData().getAccount();
    System.out.println("Account: " + account);
  }
}
```

The user agent value will be prepended to additional user-agent information that is set by default in this library. While it is not strictly necessary to set the user agent, it is often helpful for the team at DNSimple when debugging, so please consider setting it.

### List request options

For endpoints that support it, you can set options to filter, limit and sort the results that the API produces thanks to the `ListOptions` class.

```java
package myapp;

import com.dnsimple.Client;
import com.dnsimple.data.Certificate;
import com.dnsimple.response.ListResponse;

public class MyApp {
  public static void main(String[] args) {
    //...

    // Use the ListOptions.Builder class to get an create a ListOptions object
    ListOptions options = new ListOptions.Builder()
                                         .page(2, 10) // Get the second page of 10 items
                                         .sortAsc("state") // Sort by state in ascendant order
                                         .filter("autoRenew", "true") // Filter certificates with enabled auto-renew
                                         .build();
    ListResponse<Certificate> response = client.certificates.listCertificates("1", "1", options);
    //...
  }
}
```

## Sandbox Usage

If you would like to test in the [DNSimple sandbox environment](https://developer.dnsimple.com/sandbox/) then add the "sandbox()" builder to your client:

```java
package myapp;

import com.dnsimple.Dnsimple;
import com.dnsimple.Client;

public class MyApp {
  public static void main(String[] args) {
    Client client = new Client.Builder()
                              .sandbox()
                              .accessToken("YOUR-ACCESS-TOKEN")
                              .userAgent("your-user-agent")
                              .build();
    // ...
  }
}
```

You will need to ensure you are using an access token created in the sandbox environment. Production tokens will *not* work in the sandbox environment.

## Stub for Testing

When developing unit tests for your application, you should stub responses from this client to avoid making any network calls.

## License

Copyright (c) 2016-2020 DNSimple Corporation. This is Free Software distributed under the MIT license.
