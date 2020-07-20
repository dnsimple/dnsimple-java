package com.dnsimple.endpoints;

import com.dnsimple.data.AccessToken;
import com.dnsimple.request.OauthAuthorizeOptions;
import com.dnsimple.request.OauthExchangeOptions;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.util.Arrays;

import static com.dnsimple.http.HttpMethod.POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OauthTest extends DnsimpleTestBase {
    @Test
    public void testExchangeAuthorizationForToken() {
        server.stubFixtureAt("oauthAccessToken/success.http");
        AccessToken token = client.oauth.exchangeAuthorizationForToken(OauthExchangeOptions.of("code", "client-id", "secret", "state"));
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/oauth/access_token"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), allOf(
                hasEntry("code", "code"),
                hasEntry("client_id", "client-id"),
                hasEntry("client_secret", "secret"),
                hasEntry("state", "state"),
                hasEntry("grant_type", "authorization_code")
        ));
        assertThat(token.getToken(), is("zKQ7OLqF5N1gylcJweA9WodA000BUNJD"));
        assertThat(token.getType(), is("Bearer"));
        assertThat(token.getAccountId(), is(1));
    }

    @Test
    public void testExchangeAuthorizationForTokenWithOptions() {
        server.stubFixtureAt("oauthAccessToken/success.http");
        AccessToken token = client.oauth.exchangeAuthorizationForToken(OauthExchangeOptions.of("code", "client-id", "secret", "state").redirectUri("https://example.com/callback"));
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/oauth/access_token"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), allOf(
                hasEntry("code", "code"),
                hasEntry("client_id", "client-id"),
                hasEntry("client_secret", "secret"),
                hasEntry("state", "state"),
                hasEntry("redirect_uri", "https://example.com/callback"),
                hasEntry("grant_type", "authorization_code")
        ));
        assertThat(token.getToken(), is("zKQ7OLqF5N1gylcJweA9WodA000BUNJD"));
        assertThat(token.getType(), is("Bearer"));
        assertThat(token.getAccountId(), is(1));
    }

    @Test
    public void testAuthorizeUrlIsCorrect() {
        String[] urlParts = client.oauth.authorizeUrl(OauthAuthorizeOptions.of("client-id", "state")).split("\\?");
        assertThat(urlParts[0], endsWith("/oauth/authorize"));
        assertThat(Arrays.asList(urlParts[1].split("&")), containsInAnyOrder(
                "client_id=client-id",
                "state=state",
                "response_type=code"
        ));
    }

    @Test
    public void testAuthorizeUrlIncludesOptions() {
        String[] urlParts = client.oauth.authorizeUrl(OauthAuthorizeOptions.of("client-id", "state").redirectUri("https://example.com")).split("\\?");
        assertThat(urlParts[0], endsWith("/oauth/authorize"));
        assertThat(Arrays.asList(urlParts[1].split("&")), containsInAnyOrder(
                "client_id=client-id",
                "state=state",
                "response_type=code",
                "redirect_uri=https%3A%2F%2Fexample.com"
        ));
    }
}
