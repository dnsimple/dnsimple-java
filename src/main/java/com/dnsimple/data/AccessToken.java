package com.dnsimple.data;

public class AccessToken {
    private final String accessToken;
    private final String tokenType;
    private final Integer accountId;

    public AccessToken(String accessToken, String tokenType, Integer accountId) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.accountId = accountId;
    }

    public String getToken() {
        return accessToken;
    }

    public String getType() {
        return tokenType;
    }

    public Integer getAccountId() {
        return accountId;
    }
}
