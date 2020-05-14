package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.google.api.client.http.HttpHeaders;
import java.io.IOException;
import org.junit.Test;

public class ClientTest extends DnsimpleTestBase {
  private static final String TEST_ACCESS_TOKEN = "test-access-token";

  @Test
  public void testAuthorizationHeader() throws DnsimpleException, IOException {
    HttpHeaders headers = getDefaultHeaders();
    headers.setAuthorization("Bearer " + TEST_ACCESS_TOKEN);

    server.expectGet("/v2/accounts");
    server.expectHeaders(headers);
    server.stubFixtureAt("listAccounts/success-account.http");
    Client client = new Client();
    client.setAccessToken(TEST_ACCESS_TOKEN);
    client.accounts.listAccounts();
  }

  @Test
  public void testUserAgentHeader() throws DnsimpleException, IOException {
    HttpHeaders headers = getDefaultHeaders();
    headers.setUserAgent("my-user-agent dnsimple-java/" + Dnsimple.VERSION + " Google-HTTP-Java-Client/1.35.0 (gzip)");

    server.expectGet("/v2/accounts");
    server.expectHeaders(headers);
    server.stubFixtureAt("listAccounts/success-account.http");
    Client client = new Client();
    client.setUserAgent("my-user-agent");
    client.accounts.listAccounts();
  }

  private HttpHeaders getDefaultHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept("application/json");
    headers.setUserAgent("dnsimple-java/" + Dnsimple.VERSION + " Google-HTTP-Java-Client/1.35.0 (gzip)");
    return headers;
  }
}
