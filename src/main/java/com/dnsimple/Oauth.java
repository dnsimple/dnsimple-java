package com.dnsimple;

import com.dnsimple.data.OauthToken;

import java.util.Map;

/**
 * Provides access to the DNSimple OAuth API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
 */
public interface Oauth {
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
    OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret);

    /**
     * Exchange the short-lived authorization code for an access token
     * that is used to authenticate API calls.
     *
     * @param code         The authorization code
     * @param clientId     The client ID
     * @param clientSecret The client secret
     * @param options      Map of options
     * @return The OauthToken instance
     * @see <a href="https://developer.dnsimple.com/v2/oauth">https://developer.dnsimple.com/v2/oauth</a>
     */
    OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret, Map<String, Object> options);

    /**
     * Gets the URL to authorize a user for an application via the OAuth2 flow.
     *
     * @param clientId The client ID of the OAuth app in DNSimple
     * @return The authorize URL String
     * @see <a href="https://developer.dnsimple.com/v2/oauth/">https://developer.dnsimple.com/v2/oauth/</a>
     */
    String authorizeUrl(String clientId);

    /**
     * Gets the URL to authorize a user for an application via the OAuth2 flow.
     *
     * @param clientId The client ID of the OAuth app in DNSimple
     * @param options  A Map of options to include as parameters in the generated URL
     * @return The authorize URL string
     * @see <a href="https://developer.dnsimple.com/v2/oauth/">https://developer.dnsimple.com/v2/oauth/</a>
     */
    String authorizeUrl(String clientId, Map<Object, Object> options);
}
