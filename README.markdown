# Java DNSimple API Wrapper

A Java client for the [DNSimple API v2](https://developer.dnsimple.com/v2/).

[DNSimple](https://dnsimple.com/) provides DNS hosting and domain registration that is simple and friendly.
We provide a full API and an easy-to-use web interface so you can get your domain registered and set up with a minimal amount of effort.

## :warning: Alpha Warning

This project targets the development of the API client for the [DNSimple API v2](https://developer.dnsimple.com/v2/).

This library is currently in alpha version, the methods and the implementation should be considered a work-in-progress. Changes in the method naming, method signatures, public or internal APIs will happen during the alpha period.

## Requirements

This library is tested with Java 6 and later.

You must also have an activated DNSimple account to access the DNSimple API.

## Installation

### Maven

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.dnsimple</groupId>
  <artifactId>dnsimple-java</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Usage

This library is a Java client you can use to interact with the [DNSimple API v2](https://developer.dnsimple.com/v2/).

The examples below demonstrate basic usage.

```java
package myapp;

import com.dnsimple.Client;
import com.dnsimple.Account;
import com.dnsimple.response.WhoamiResponse;

public class Myapp {
  public static void main(String[] args) {
    Client client = new Client();
    WhoamiResponse response = client.identity.whoami();
    Account account = response.getData().getAccount();
    System.out.println("Account: " + account);
  }
}
```

## Sandbox Usage

If you would like to test in the [DNSimple sandbox environment](https://developer.dnsimple.com/sandbox/) then set the base URL:

```java
package myapp;

import com.dnsimple.Dnsimple;
import com.dnsimple.Client;

public class Myapp {
  public static void main(String[] args) {
    Dnsimple.setBaseUrl("https://api.sandbox.dnsimple.com");
    Client client = new Client();
    // ...
  }
}
```

You will need to ensure you are using an access token created in the sandbox environment. Production tokens will *not* work in the sandbox environment.
