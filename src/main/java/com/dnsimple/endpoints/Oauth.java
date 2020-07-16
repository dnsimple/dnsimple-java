package com.dnsimple.endpoints;

import com.dnsimple.data.AccessToken;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.request.OauthExtraOptions;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.POST;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Provides access to the DNSimple OAuth API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
 */
public class Oauth {
    private final HttpEndpointClient client;

    public Oauth(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Exchange the short-lived authorization code for an access token
     * that is used to authenticate API calls.
     *
     * @param code         The authorization code
     * @param clientId     The client ID
     * @param clientSecret The client secret
     * @return The OauthToken instance
     * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
     */
    public AccessToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret) {
        return exchangeAuthorizationForToken(code, clientId, clientSecret, OauthExtraOptions.empty());
    }

    /**
     * Exchange the short-lived authorization code for an access token
     * that is used to authenticate API calls.
     *
     * @param code         The authorization code
     * @param clientId     The client ID
     * @param clientSecret The client secret
     * @param options      Remaining optional params for the access token request
     * @return The OauthToken instance
     * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
     */
    public AccessToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret, OauthExtraOptions options) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("code", code);
        attributes.put("client_id", clientId);
        attributes.put("client_secret", clientSecret);
        attributes.put("grant_type", "authorization_code");
        options.ifState(state -> attributes.put("state", state));
        options.ifRedirectUri(uri -> attributes.put("redirect_uri", uri.toString()));
        return client.raw(POST, "oauth/access_token", ListOptions.empty(), attributes, AccessToken.class);
    }

    /**
     * Gets the URL to authorize a user for an application via the OAuth2 flow.
     *
     * @param clientId The client ID of the OAuth app in DNSimple
     * @return The authorize URL String
     * @see <a href="https://developer.dnsimple.com/v2/oauth/">https://developer.dnsimple.com/v2/oauth/</a>
     */
    public String authorizeUrl(String clientId) {
        return authorizeUrl(clientId, OauthExtraOptions.empty());
    }

    /**
     * Gets the URL to authorize a user for an application via the OAuth2 flow.
     *
     * @param clientId The client ID of the OAuth app in DNSimple
     * @param options  Remaining optional params for the authorize URL
     * @return The authorize URL string
     * @see <a href="https://developer.dnsimple.com/v2/oauth/">https://developer.dnsimple.com/v2/oauth/</a>
     */
    public String authorizeUrl(String clientId, OauthExtraOptions options) {
        String baseUrl = client.getApiBase().toString().replaceFirst("api\\.", "") + "/oauth/authorize";
        List<String> qsParams = new ArrayList<>();
        qsParams.add("client_id=" + clientId);
        qsParams.add("response_type=code");
        options.ifState(state -> qsParams.add("state=" + state));
        options.ifRedirectUri(uri -> qsParams.add("redirect_uri=" + URLEncoder.encode(uri.toString(), UTF_8)));
        return baseUrl + "?" + String.join("&", qsParams);
    }
}
