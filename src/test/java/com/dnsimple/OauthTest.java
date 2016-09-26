package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

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

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/oauth/access_token", HttpMethods.POST, attributes, resource("oauthAccessToken/success.http"));

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

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/oauth/access_token", HttpMethods.POST, expectedAttributes, resource("oauthAccessToken/success.http"));

    OauthToken token = client.oauth.exchangeAuthorizationForToken(code, clientId, clientSecret, options);
    assertEquals("zKQ7OLqF5N1gylcJweA9WodA000BUNJD", token.getAccessToken());
    assertEquals("Bearer", token.getTokenType());
    assertTrue(Data.isNull(token.getScope()));
    assertEquals(1, token.getAccountId().intValue());
  }

  @Test
  public void testAuthorizeUrlIsCorrect() {
    Client client = new Client();
    String authorizeUrl = client.oauth.authorizeUrl("great-app");
    assertEquals("https://dnsimple.com/oauth/authorize?client_id=great-app&response_type=code", authorizeUrl);
  }

  @Test
  public void testAuthorizeUrlIncludesOptions() {
    Client client = new Client();

    HashMap<Object, Object> options = new HashMap<Object, Object>();
    options.put("secret", "1");
    options.put("redirect_uri", "http://example.com");

    String authorizeUrl = client.oauth.authorizeUrl("great-app", options);
    assertEquals("https://dnsimple.com/oauth/authorize?client_id=great-app&response_type=code&secret=1&redirect_uri=http%3A%2F%2Fexample.com", authorizeUrl);
  }
}
