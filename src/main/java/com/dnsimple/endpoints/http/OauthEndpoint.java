package com.dnsimple.endpoints.http;

import com.dnsimple.Dnsimple;
import com.dnsimple.Oauth;
import com.dnsimple.data.OauthToken;
import com.dnsimple.exception.DnsimpleException;
import io.mikael.urlbuilder.UrlBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OauthEndpoint implements Oauth {
    private static final String CODE_RESPONSE_TYPE = "code";
    private HttpEndpointClient client;

    public OauthEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret) throws DnsimpleException, IOException, InterruptedException {
        return exchangeAuthorizationForToken(code, clientId, clientSecret, new HashMap<String, Object>());
    }

    public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("code", code);
        attributes.put("client_id", clientId);
        attributes.put("client_secret", clientSecret);
        attributes.put("grant_type", "authorization_code");
        if (options.containsKey("state")) {
            attributes.put("state", options.remove("state"));
        }
        if (options.containsKey("redirect_uri")) {
            attributes.put("redirect_uri", options.remove("redirect_uri"));
        }
        return (OauthToken) client.post("oauth/access_token", attributes, null, OauthToken.class);
    }

    public String authorizeUrl(String clientId) {
        return authorizeUrl(clientId, Collections.emptyMap());
    }

    public String authorizeUrl(String clientId, Map<Object, Object> options) {
        UrlBuilder urlBuilder = UrlBuilder.fromString(Dnsimple.getApiBase().replaceFirst("api\\.", "") + "/oauth/authorize")
                .addParameter("client_id", clientId)
                .addParameter("response_type", CODE_RESPONSE_TYPE);
        for (Map.Entry<Object, Object> entry : options.entrySet()) {
            urlBuilder = urlBuilder.addParameter(entry.getKey().toString(), entry.getValue().toString());
        }
        return urlBuilder.toString();
    }
}
