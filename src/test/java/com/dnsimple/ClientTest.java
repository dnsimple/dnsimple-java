package com.dnsimple;

import static com.dnsimple.http.HttpMethod.GET;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

import com.dnsimple.exception.BadRequestException;
import com.dnsimple.request.ContactOptions;
import com.dnsimple.tools.DnsimpleTestBase;
import java.util.List;
import org.junit.Test;

public class ClientTest extends DnsimpleTestBase {
    @Test
    public void testAuthorizationHeader() {
        server.stubFixtureAt("listAccounts/success-account.http");
        client.accounts.listAccounts();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
        assertThat(server.getRecordedRequest().getHeaders(), hasEntry("Authorization", "Bearer " + TEST_ACCESS_TOKEN));
    }

    @Test
    public void testUserAgentHeader() {
        server.stubFixtureAt("listAccounts/success-account.http");
        client.accounts.listAccounts();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/accounts"));
        assertThat(server.getRecordedRequest().getHeaders(), hasEntry("User-Agent", TEST_USER_AGENT + " dnsimple-java/" + Dnsimple.VERSION));
    }

    @Test
    public void testBadResponseErrorIncludesParsedValidationErrors() {
        server.stubFixtureAt("createContact/error-validation-errors.http");

        try {
            client.contacts.createContact(1, ContactOptions.of("First name", "Last name", "Address 1", "City", "state", "postal", "country", "hello@example.com", "phone"));
        } catch (BadRequestException e) {
            assertThat(e.getBody().get("message"), is("Validation failed"));
            assertThat(e.getAttributeErrors().get("address1"), is(singletonList("can't be blank")));
            assertThat(e.getAttributeErrors().get("city"), is(singletonList("can't be blank")));
            assertThat(e.getAttributeErrors().get("country"), is(singletonList("can't be blank")));
            assertThat(e.getAttributeErrors().get("email"), is(List.of("can't be blank", "is an invalid email address")));
            assertThat(e.getAttributeErrors().get("first_name"), is(singletonList("can't be blank")));
            assertThat(e.getAttributeErrors().get("last_name"), is(singletonList("can't be blank")));
            assertThat(e.getAttributeErrors().get("phone"), is(List.of("can't be blank", "is probably not a phone number")));
            assertThat(e.getAttributeErrors().get("postal_code"), is(singletonList("can't be blank")));
            assertThat(e.getAttributeErrors().get("state_province"), is(singletonList("can't be blank")));
        }
    }
}
