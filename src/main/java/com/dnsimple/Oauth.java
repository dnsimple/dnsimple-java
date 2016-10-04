package com.dnsimple;

import java.io.InputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry.*;

import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.JsonParser;

import io.mikael.urlbuilder.UrlBuilder;

/**
 * Provides access to the DNSimple OAuth API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
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
   * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
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
   * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
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

  /**
   * Gets the URL to authorize a user for an application via the OAuth2 flow.
   *
   * @see <a href="https://developer.dnsimple.com/v2/oauth/">https://developer.dnsimple.com/v2/oauth/</a>
   *
   * @param clientId The client ID of the OAuth app in DNSimple
   * @return The authorize URL String
   */
  public String authorizeUrl(String clientId) {
    return authorizeUrl(clientId, Collections.emptyMap());
  }

  /**
   * Gets the URL to authorize a user for an application via the OAuth2 flow.
   *
   * @see <a href="https://developer.dnsimple.com/v2/oauth/">https://developer.dnsimple.com/v2/oauth/</a>
   *
   * @param clientId The client ID of the OAuth app in DNSimple
   * @param options A Map of options to include as parameters in the generated URL
   * @return The authorize URL string
   */
  public String authorizeUrl(String clientId, Map<Object, Object> options) {
    UrlBuilder urlBuilder = UrlBuilder.fromString(Dnsimple.getApiBase().replaceFirst("api\\.", "") + "/oauth/authorize")
      .addParameter("client_id", clientId)
      .addParameter("response_type", "code");

    for (Map.Entry<Object,Object> entry : options.entrySet()) {
      urlBuilder = urlBuilder.addParameter(entry.getKey().toString(), entry.getValue().toString());
    }

    return urlBuilder.toString();
  }
}
