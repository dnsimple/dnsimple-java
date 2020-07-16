package com.dnsimple.request;

import java.util.HashMap;
import java.util.Map;

public class OauthExchangeOptions {
    private final String code;
    private final String clientId;
    private final String clientSecret;
    private final String state;
    private final String redirectUri;

    private OauthExchangeOptions(String code, String clientId, String clientSecret, String state, String redirectUri) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.state = state;
        this.redirectUri = redirectUri;
    }

    /**
     * @param code         The code acquired in the previous authorization step.
     * @param clientId     The client ID you received from DNSimple when you registered the application.
     * @param clientSecret The client secret you received from DNSimple when you registered the application.
     * @param state        The state content originally passed to /oauth/authorize.
     */
    public static OauthExchangeOptions of(String code, String clientId, String clientSecret, String state) {
        return new OauthExchangeOptions(code, clientId, clientSecret, state, null);
    }

    /**
     * Only used to validate that it matches the original /oauth/authorize, not used to redirect again.
     */
    public OauthExchangeOptions redirectUri(String redirectUri) {
        return new OauthExchangeOptions(code, clientId, clientSecret, state, redirectUri);
    }

    public Map<String, String> asMap() {
        Map<String, String> options = new HashMap<>();
        options.put("code", code);
        options.put("client_id", clientId);
        options.put("client_secret", clientSecret);
        options.put("state", state);
        if (redirectUri != null)
            options.put("redirect_uri", redirectUri);
        return options;
    }
}
