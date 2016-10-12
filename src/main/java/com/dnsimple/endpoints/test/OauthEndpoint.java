package com.dnsimple.endpoints.test;

import com.dnsimple.Oauth;
import com.dnsimple.data.OauthToken;
import com.dnsimple.exception.DnsimpleException;

import io.mikael.urlbuilder.UrlBuilder;

import java.io.IOException;
import java.util.Map;

public class OauthEndpoint implements Oauth {

  public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret) throws DnsimpleException, IOException {
    return new OauthToken();
  }

  public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret, Map<String, Object> options) throws DnsimpleException, IOException {
    return new OauthToken();
  }

  public String authorizeUrl(String clientId) {
    return "";
  }

  public String authorizeUrl(String clientId, Map<Object, Object> options) {
    return "";
  }

}

