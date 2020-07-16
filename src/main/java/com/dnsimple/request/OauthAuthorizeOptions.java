package com.dnsimple.request;

import java.util.HashMap;
import java.util.Map;

public class OauthAuthorizeOptions {
    private final String clientId;
    private final String state;
    private final String redirectUri;

    private OauthAuthorizeOptions(String clientId, String state, String redirectUri) {
        this.clientId = clientId;
        this.state = state;
        this.redirectUri = redirectUri;
    }

    /**
     * @param clientId The client ID you received from DNSimple when you registered the application.
     * @param state    An unguessable random string. It is used to protect against cross-site request forgery attacks and it will be passed back to your redirect URI.
     */
    public static OauthAuthorizeOptions of(String clientId, String state) {
        return new OauthAuthorizeOptions(clientId, state, null);
    }

    /**
     * Set where to redirect the user after authorization has completed. This must be the exact URI registered or a subdirectory.
     */
    public OauthAuthorizeOptions redirectUri(String redirectUri) {
        return new OauthAuthorizeOptions(clientId, state, redirectUri);
    }

    public Map<String, String> asMap() {
        Map<String, String> options = new HashMap<>();
        options.put("client_id", clientId);
        options.put("state", state);
        if (redirectUri != null)
            options.put("redirect_uri", redirectUri);
        return options;
    }
}
