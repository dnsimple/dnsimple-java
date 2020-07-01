# Java DNSimple API Wrapper

A Java client for the [DNSimple API v2](https://developer.dnsimple.com/v2/).

[![Build Status](https://travis-ci.org/dnsimple/dnsimple-java.svg?branch=master)](https://travis-ci.org/dnsimple/dnsimple-java)

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
  <version>X.X.X</version>
</dependency>
```

## Usage

This library is a Java client you can use to interact with the [DNSimple API v2](https://developer.dnsimple.com/v2/).

The examples below demonstrate basic usage.

```java
package myapp;

import com.dnsimple.Client;
import com.dnsimple.data.Account;
import com.dnsimple.response.WhoamiResponse;

public class MyApp {
  public static void main(String[] args) {
    Client client = new Client();
    client.setAccessToken("YOUR-ACCESS-TOKEN");
    client.setUserAgent("your-user-agent");
    WhoamiResponse response = client.identity.whoami();
    Account account = response.getData().getAccount();
    System.out.println("Account: " + account);
  }
}
```

The user agent value will be prepended to additional user-agent information that is set by default in this library. While it is not strictly necessary to set the user agent, it is often helpful for the team at DNSimple when debugging, so please consider setting it.

## Sandbox Usage

If you would like to test in the [DNSimple sandbox environment](https://developer.dnsimple.com/sandbox/) then set the base URL:

```java
package myapp;

import com.dnsimple.Dnsimple;
import com.dnsimple.Client;

public class MyApp {
  public static void main(String[] args) {
    Dnsimple.setBaseUrl("https://api.sandbox.dnsimple.com");
    Client client = new Client();
    client.setAccessToken("YOUR-ACCESS-TOKEN");
    // ...
  }
}
```

You will need to ensure you are using an access token created in the sandbox environment. Production tokens will *not* work in the sandbox environment.

## Stub for Testing

When developing unit tests for your application, you should stub responses from this client to avoid making any network calls.

You can use any mocking library you choose, however the examples below use Mockito.

Here is an example of a test demonstrating how to do mock the endpoint adapter:

```java
public class MyTest {
  @Test
  public void testSomething() throws DnsimpleException, IOException, InterruptedException {
    Account account = new Account(1);

    EndpointAdapter adapter = mock(EndpointAdapter.class);
    when(adapter.identity()).thenReturn(mock(Identity.class));
    when(adapter.identity().whoami()).thenReturn(new WhoamiResponse(new Whoami(account)));

    List<Domain> domains = new ArrayList<Domain>();
    domains.add(new Domain());
    when(adapter.domains()).thenReturn(mock(Domains.class));
    when(adapter.domains().listDomains(account.getId().toString())).thenReturn(new ListDomainsResponse(domains));

    Client client = new Client(adapter);
    // ... your test
  }
}
```

## License

Copyright (c) 2016-2020 DNSimple Corporation. This is Free Software distributed under the MIT license.
