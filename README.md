# Java DNSimple API Wrapper

A Java client for the [DNSimple API v2](https://developer.dnsimple.com/v2/).

[![Build Status](https://github.com/dnsimple/dnsimple-java/actions/workflows/ci.yml/badge.svg)](https://github.com/dnsimple/dnsimple-java/actions/workflows/ci.yml)

## Requirements

This library is tested with Java 11 (AdoptOpenJDK 11.0.7 - HotSpot).

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
import com.dnsimple.data.User;
import com.dnsimple.data.WhoamiData;
import com.dnsimple.response.SimpleResponse;

public class MyApp {
    public static void main(String[] args) {
        Client client = new Client.Builder()
                .sandbox()
                .accessToken("YOUR-ACCESS-TOKEN")
                .extraUserAgent("your-user-agent")
                .build();
        SimpleResponse<WhoamiData> response = client.identity.whoami();
        Account account = response.getData().getAccount();
        User user = response.getData().getUser();
        System.out.println("Account: " + (account != null ? account.getEmail() : "N/A"));
        System.out.println("User: " + (user != null ? user.getEmail() : "N/A"));
    }
}
```

The user agent value will be prepended to additional user-agent information set by default in this library. While it is not strictly necessary to set the user agent, it is often helpful for the team at DNSimple when debugging, so please consider setting it.

### List request options

For endpoints that support it, you can set options to filter, limit, and sort the results that the API produces thanks to the `ListOptions` class.

```java
package myapp;

import com.dnsimple.Client;
import com.dnsimple.data.Certificate;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.ListResponse;

public class MyApp {
  public static void main(String[] args) {
    //...

    // Use the ListOptions.Builder class to get an create a ListOptions object
    ListOptions options = ListOptions.empty()
                    .page(2, 10) // Get the second page of 10 items
                    .sortAsc("state") // Sort by state in ascendant order
                    .filter("autoRenew", "true"); // Filter certificates with enabled auto-renew
    ListResponse<Certificate> response = client.certificates.listCertificates(1, "1", options);
    //...
  }
}
```

## Sandbox Usage

If you would like to test in the [DNSimple sandbox environment](https://developer.dnsimple.com/sandbox/) then add the `sandbox()` builder method to your client:

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

Copyright (c) 2016-2021 DNSimple Corporation. This is Free Software distributed under the MIT license.
