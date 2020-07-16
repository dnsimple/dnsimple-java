package com.dnsimple;

import com.dnsimple.data.AccessToken;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OauthTest extends DnsimpleTestBase {
    @Test
    public void testExchangeAuthorizationForToken() {
        server.stubFixtureAt("oauthAccessToken/success.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("code", "super-code");
        attributes.put("client_id", "super-client-id");
        attributes.put("client_secret", "super-client-secret");
        attributes.put("grant_type", "authorization_code");
        AccessToken token = client.oauth.exchangeAuthorizationForToken("super-code", "super-client-id", "super-client-secret");
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/oauth/access_token"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
        assertThat(token.getToken(), is("zKQ7OLqF5N1gylcJweA9WodA000BUNJD"));
        assertThat(token.getType(), is("Bearer"));
        assertThat(token.getAccountId(), is(1));
    }

    @Test
    public void testExchangeAuthorizationForTokenWithOptions() {
        server.stubFixtureAt("oauthAccessToken/success.http");
        Map<String, Object> options = new HashMap<>();
        options.put("state", "some-state");
        options.put("redirect_uri", "some-redirect-uri");
        Map<String, Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put("code", "super-code");
        expectedAttributes.put("client_id", "super-client-id");
        expectedAttributes.put("client_secret", "super-client-secret");
        expectedAttributes.put("grant_type", "authorization_code");
        expectedAttributes.put("state", "some-state");
        expectedAttributes.put("redirect_uri", "some-redirect-uri");
        AccessToken token = client.oauth.exchangeAuthorizationForToken("super-code", "super-client-id", "super-client-secret", options);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/oauth/access_token"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(expectedAttributes));
        assertThat(token.getToken(), is("zKQ7OLqF5N1gylcJweA9WodA000BUNJD"));
        assertThat(token.getType(), is("Bearer"));
        assertThat(token.getAccountId(), is(1));
    }

    @Test
    public void testAuthorizeUrlIsCorrect() {
        assertThat(client.oauth.authorizeUrl("great-app"),
                endsWith("/oauth/authorize?client_id=great-app&response_type=code"));
    }

    @Test
    public void testAuthorizeUrlIncludesOptions() {
        Map<Object, Object> options = new HashMap<>();
        options.put("secret", "1");
        options.put("redirect_uri", "http://example.com");
        assertThat(client.oauth.authorizeUrl("great-app", options),
                endsWith("/oauth/authorize?client_id=great-app&response_type=code&secret=1&redirect_uri=http%3A%2F%2Fexample.com"));
    }
}
