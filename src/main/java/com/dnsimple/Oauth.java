package com.dnsimple;

import java.io.InputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.JsonParser;

/**
 * Provides access to the DNSimple OAuth API.
 *
 * @see https://developer.dnsimple.com/v2/oauth
 */
public class Oauth {
  private Client client;

  protected Oauth(Client client) {
    this.client = client;
  }

  /**
   * Exchange the short-lived authorization code for an access token
   * that is used to authenticate API calls.
   *
   * @see https://developer.dnsimple.com/v2/oauth
   *
   * @param code The authorization code
   * @param clientId The client ID
   * @param clientSecret The client secret
   * @return The OauthToken instance
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret) throws DnsimpleException, IOException {
    return exchangeAuthorizationForToken(code, clientId, clientSecret, new HashMap<String, Object>());
  }

  /**
   * Exchange the short-lived authorization code for an access token
   * that is used to authenticate API calls.
   *
   * @see https://developer.dnsimple.com/v2/oauth
   *
   * @param code The authorization code
   * @param clientId The client ID
   * @param clientSecret The client secret
   * @param options Map of options
   * @return The OauthToken instance
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret, Map<String, Object> options) throws DnsimpleException, IOException {
    Map<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("code", code);
    attributes.put("client_id", clientId);
    attributes.put("client_secret", clientSecret);
    attributes.put("grant_type", "authorization_code");

    if (options.containsKey("state")) {
      attributes.put("state", options.remove("state"));
    }

    if (options.containsKey("redirect_uri")) {
      attributes.put("redirect_uri", options.remove("redirect_uri"));
    }

    HttpResponse response = client.post("oauth/access_token", attributes);
    InputStream in = response.getContent();
    if (in == null) {
      throw new DnsimpleException("Response was empty", null, response.getStatusCode());
    } else {
      try {
        JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(in);
        return jsonParser.parse(OauthToken.class);
      } finally {
        in.close();
      }
    }
  }
}
