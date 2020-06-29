package com.dnsimple.data;

import com.dnsimple.response.ApiResponse;
import com.google.api.client.util.Key;

public class OauthToken extends ApiResponse {
  @Key("access_token")
  private String accessToken;

  @Key("token_type")
  private String tokenType;

  @Key("scope")
  private String scope;

  @Key("account_id")
  private Integer accountId;

  public String getAccessToken() {
    return accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getScope() {
    return scope;
  }

  public Integer getAccountId() {
    return accountId;
  }
}
