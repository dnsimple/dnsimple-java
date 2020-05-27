package com.dnsimple;

import static com.dnsimple.tools.HttpMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.tools.HttpMethod;
import java.io.IOException;
import org.junit.Test;

public class ClientTest extends DnsimpleTestBase {
  private static final String TEST_ACCESS_TOKEN = "test-access-token";

  @Test
  public void testAuthorizationHeader() throws DnsimpleException, IOException {
    server.stubFixtureAt("listAccounts/success-account.http");
    client.setAccessToken(TEST_ACCESS_TOKEN);

    client.accounts.listAccounts();
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
    assertThat(server.getRecordedRequest().getHeaders(), hasEntry("Authorization", "Bearer " + TEST_ACCESS_TOKEN));
  }

  @Test
  public void testUserAgentHeader() throws DnsimpleException, IOException {
    server.stubFixtureAt("listAccounts/success-account.http");
    client.setUserAgent("my-user-agent");

    client.accounts.listAccounts();
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
    assertThat(server.getRecordedRequest().getHeaders(), hasEntry("User-Agent", "my-user-agent dnsimple-java/" + Dnsimple.VERSION + " Google-HTTP-Java-Client/1.35.0 (gzip)"));
  }
}
