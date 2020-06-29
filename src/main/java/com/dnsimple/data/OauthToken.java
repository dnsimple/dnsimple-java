package com.dnsimple.data;

import com.dnsimple.response.ApiResponse;

public class OauthToken extends ApiResponse {
    private final String accessToken;
    private final String tokenType;
    private final String scope;
    private final Integer accountId;

    public OauthToken(String accessToken, String tokenType, String scope, Integer accountId) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.scope = scope;
        this.accountId = accountId;
    }

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
