package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;

import java.io.IOException;

public class ClientTest extends DnsimpleTestBase {
  @Test
  public void testNewClient() {
    Client client = new Client();
  }

  @Test
  public void testAuthorizationHeader() throws DnsimpleException, IOException {
    HttpHeaders headers = getDefaultHeaders();
    headers.setAuthorization("Bearer " + TEST_ACCESS_TOKEN);

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/accounts", HttpMethods.GET, headers, null, resource("listAccounts/success-account.http"));
    client.accounts.listAccounts();
  }

  @Test
  public void testUserAgentHeader() throws DnsimpleException, IOException {
    HttpHeaders headers = getDefaultHeaders();
    headers.setUserAgent("my-user-agent dnsimple-java/0.3.0 Google-HTTP-Java-Client/1.20.0 (gzip)");

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/accounts", HttpMethods.GET, headers, null, resource("listAccounts/success-account.http"));
    client.setUserAgent("my-user-agent");
    client.accounts.listAccounts();
  }
}
