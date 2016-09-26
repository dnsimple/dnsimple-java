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
}
