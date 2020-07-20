package com.dnsimple.endpoints;

import com.dnsimple.data.AccessToken;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.request.OauthAuthorizeOptions;
import com.dnsimple.request.OauthExchangeOptions;

import java.util.Map;

import static com.dnsimple.http.HttpMethod.POST;
import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

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
     * Gets the URL to authorize a user for an application via the OAuth2 flow.
     *
     * @param options The options for the OAuth authorization URL
     * @return The authorize URL String
     * @see <a href="https://developer.dnsimple.com/v2/oauth/">https://developer.dnsimple.com/v2/oauth/</a>
     */
    public String authorizeUrl(OauthAuthorizeOptions options) {
        String baseUrl = client.getApiBase().toString().replaceFirst("api\\.", "") + "/oauth/authorize";
        Map<String, String> optionsAsMap = options.asMap();
        optionsAsMap.put("response_type", "code");
        String queryStringParams = optionsAsMap.entrySet().stream().map(e -> e.getKey() + "=" + encode(e.getValue(), UTF_8)).collect(joining("&"));
        return baseUrl + "?" + queryStringParams;
    }

    /**
     * Exchange the short-lived authorization code for an access token
     * that is used to authenticate API calls.
     *
     * @param options The options for the OAuth exchange call
     * @return The AccessToken instance
     * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
     */
    public AccessToken exchangeAuthorizationForToken(OauthExchangeOptions options) {
        Map<String, String> optionsAsMap = options.asMap();
        optionsAsMap.put("grant_type", "authorization_code");
        return client.raw(POST, "oauth/access_token", ListOptions.empty(), optionsAsMap, AccessToken.class);
    }
}
