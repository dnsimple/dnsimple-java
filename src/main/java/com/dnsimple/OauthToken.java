package com.dnsimple;

import com.google.api.client.util.Key;

public class OauthToken {
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
