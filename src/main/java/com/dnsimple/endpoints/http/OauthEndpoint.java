package com.dnsimple.endpoints.http;

import com.dnsimple.Dnsimple;
import com.dnsimple.Oauth;
import com.dnsimple.data.OauthToken;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dnsimple.endpoints.http.java11.HttpMethod.POST;
import static java.util.Collections.emptyMap;

public class OauthEndpoint implements Oauth {
    private final HttpEndpointClient client;

    public OauthEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret) throws DnsimpleException, IOException, InterruptedException {
        return exchangeAuthorizationForToken(code, clientId, clientSecret, emptyMap());
    }

    public OauthToken exchangeAuthorizationForToken(String code, String clientId, String clientSecret, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        Map<String, Object> attributes = new HashMap<>();
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
        return client.raw(POST, "oauth/access_token", attributes, null, OauthToken.class);
    }

    public String authorizeUrl(String clientId) {
        return authorizeUrl(clientId, emptyMap());
    }

    public String authorizeUrl(String clientId, Map<Object, Object> options) {
        String baseUrl = Dnsimple.getApiBase().replaceFirst("api\\.", "") + "/oauth/authorize";
        String queryString = String.format("client_id=%s&response_type=code", clientId);
        queryString += options.isEmpty() ? "" : "&" + options.entrySet().stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue().toString(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        return baseUrl + "?" + queryString;
    }
}
