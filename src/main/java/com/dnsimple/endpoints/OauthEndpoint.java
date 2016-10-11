package com.dnsimple.endpoints;

import com.dnsimple.Dnsimple;
import com.dnsimple.Oauth;
import com.dnsimple.data.OauthToken;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.JsonParser;

import io.mikael.urlbuilder.UrlBuilder;

import java.io.InputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry.*;

public class OauthEndpoint implements Oauth {
  private static final String CODE_RESPONSE_TYPE = "code";

  private HttpEndpointClient client;

  public OauthEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret) throws DnsimpleException, IOException {
    return exchangeAuthorizationForToken(code, clientId, clientSecret, new HashMap<String, Object>());
  }

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

  public String authorizeUrl(String clientId) {
    return authorizeUrl(clientId, Collections.emptyMap());
  }

  public String authorizeUrl(String clientId, Map<Object, Object> options) {
    UrlBuilder urlBuilder = UrlBuilder.fromString(Dnsimple.getApiBase().replaceFirst("api\\.", "") + "/oauth/authorize")
      .addParameter("client_id", clientId)
      .addParameter("response_type", CODE_RESPONSE_TYPE);

    for (Map.Entry<Object,Object> entry : options.entrySet()) {
      urlBuilder = urlBuilder.addParameter(entry.getKey().toString(), entry.getValue().toString());
    }

    return urlBuilder.toString();
  }
}
