package com.dnsimple.endpoints;

import com.dnsimple.data.AccessToken;
import com.dnsimple.request.OauthExtraOptions;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

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
        OauthExtraOptions options = new OauthExtraOptions.Builder()
                .state("some-state")
                .redirectUri("some-redirect-uri")
                .build();
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
        OauthExtraOptions options = new OauthExtraOptions.Builder()
                .redirectUri("https://example.com")
                .build();
        assertThat(client.oauth.authorizeUrl("great-app", options),
                endsWith("/oauth/authorize?client_id=great-app&response_type=code&redirect_uri=https%3A%2F%2Fexample.com"));
    }
}
