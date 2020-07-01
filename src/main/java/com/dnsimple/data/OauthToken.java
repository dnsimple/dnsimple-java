package com.dnsimple.data;

public class OauthToken {
    private String accessToken;
    private String tokenType;
    private String scope;
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
