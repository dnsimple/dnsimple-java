package com.dnsimple;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.dnsimple.data.OauthToken;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.tools.HttpMethod;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.junit.Test;

public class OauthTest extends DnsimpleTestBase {
  @Test
  public void testExchangeAuthorizationForToken() throws DnsimpleException, IOException {
    String clientId = "super-client-id";
    String clientSecret = "super-client-secret";
    String code = "super-code";

    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("code", code);
    attributes.put("client_id", clientId);
    attributes.put("client_secret", clientSecret);
    attributes.put("grant_type", "authorization_code");

    server.expectPost("/v2/oauth/access_token");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("oauthAccessToken/success.http");
    Client client = new Client();

    OauthToken token = client.oauth.exchangeAuthorizationForToken(code, clientId, clientSecret);
    assertEquals("zKQ7OLqF5N1gylcJweA9WodA000BUNJD", token.getAccessToken());
    assertEquals("Bearer", token.getTokenType());
    assertTrue(Data.isNull(token.getScope()));
    assertEquals(1, token.getAccountId().intValue());
  }

  @Test
  public void testExchangeAuthorizationForTokenWithOptions() throws DnsimpleException, IOException {
    String clientId = "super-client-id";
    String clientSecret = "super-client-secret";
    String code = "super-code";
    String state = "some-state";
    String redirectUri = "some-redirect-uri";

    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("state", state);
    options.put("redirect_uri", redirectUri);

    HashMap<String, Object> expectedAttributes = new HashMap<String, Object>();
    expectedAttributes.put("code", code);
    expectedAttributes.put("client_id", clientId);
    expectedAttributes.put("client_secret", clientSecret);
    expectedAttributes.put("grant_type", "authorization_code");
    expectedAttributes.put("state", state);
    expectedAttributes.put("redirect_uri", redirectUri);

    server.expectPost("/v2/oauth/access_token");
    server.expectJsonPayload(expectedAttributes);
    server.stubFixtureAt("oauthAccessToken/success.http");
    Client client = new Client();

    OauthToken token = client.oauth.exchangeAuthorizationForToken(code, clientId, clientSecret, options);
    assertEquals("zKQ7OLqF5N1gylcJweA9WodA000BUNJD", token.getAccessToken());
    assertEquals("Bearer", token.getTokenType());
    assertTrue(Data.isNull(token.getScope()));
    assertEquals(1, token.getAccountId().intValue());
  }

  @Test
  public void testAuthorizeUrlIsCorrect() throws MalformedURLException {
    Client client = new Client();
    URL authorizeUrl = new URL(client.oauth.authorizeUrl("great-app"));
    String fullPath = authorizeUrl.getPath() + "?" + authorizeUrl.getQuery();
    assertThat(fullPath, is("/oauth/authorize?client_id=great-app&response_type=code"));
  }

  @Test
  public void testAuthorizeUrlIncludesOptions() throws MalformedURLException {
    Client client = new Client();

    HashMap<Object, Object> options = new HashMap<Object, Object>();
    options.put("secret", "1");
    options.put("redirect_uri", "http://example.com");

    URL authorizeUrl = new URL(client.oauth.authorizeUrl("great-app", options));
    String fullPath = authorizeUrl.getPath() + "?" + authorizeUrl.getQuery();
    assertThat(fullPath, is("/oauth/authorize?client_id=great-app&response_type=code&secret=1&redirect_uri=http%3A%2F%2Fexample.com"));
  }
}
