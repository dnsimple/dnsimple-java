package com.dnsimple.endpoints;

import com.dnsimple.data.EmailForward;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;

import static com.dnsimple.http.HttpMethod.DELETE;
import static com.dnsimple.http.HttpMethod.GET;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainEmailForwardsTest extends DnsimpleTestBase {
    @Test
    public void testListEmailForwardsSupportsPagination() {
        client.domains.listEmailForwards(1, "example.com", ListOptions.empty().page(1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/email_forwards?page=1"));
    }

    @Test
    public void testListEmailForwardsSupportsExtraRequestOptions() {
        client.domains.listEmailForwards(1, "example.com", ListOptions.empty().filter("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/email_forwards?foo=bar"));
    }

    @Test
    public void testListEmailForwardsSupportsSorting() {
        client.domains.listEmailForwards(1, "example.com", ListOptions.empty().sortAsc("from"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/email_forwards?sort=from%3Aasc"));
    }

    @Test
    public void testListEmailForwardsProducesDomainList() {
        server.stubFixtureAt("listEmailForwards/success.http");
        PaginatedResponse<EmailForward> response = client.domains.listEmailForwards(1, "example.com");
        assertThat(response.getData(), hasSize(2));
        assertThat(response.getData().get(0).getId(), is(17702L));
    }

    @Test
    public void testListEmailForwardsExposesPaginationInfo() {
        server.stubFixtureAt("listEmailForwards/success.http");
        PaginatedResponse<EmailForward> response = client.domains.listEmailForwards(1, "example.com");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetEmailForward() {
        server.stubFixtureAt("getEmailForward/success.http");
        EmailForward emailForward = client.domains.getEmailForward(1, "example.com", 17706).getData();
        assertThat(emailForward.getId(), is(17706L));
        assertThat(emailForward.getDomainId(), is(228963L));
        assertThat(emailForward.getTo(), is("jim@another.com"));
        assertThat(emailForward.getFrom(), is("jim@a-domain.com"));
        assertThat(emailForward.getCreatedAt(), is(OffsetDateTime.of(2016, 2, 4, 14, 26, 50, 0, UTC)));
        assertThat(emailForward.getUpdatedAt(), is(OffsetDateTime.of(2016, 2, 4, 14, 26, 50, 0, UTC)));
    }

    @Test
    public void testGetEmailForwardWhenDomainNotFound() {
        server.stubFixtureAt("notfound-domain.http");
        assertThat(() -> client.domains.getEmailForward(1, "0", 17706),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testGetEmailForwardWhenEmailForwardNotFound() {
        server.stubFixtureAt("notfound-emailforward.http");
        assertThat(() -> client.domains.getEmailForward(1, "example.com", 0),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateEmailForwardProducesEmailForward() {
        server.stubFixtureAt("createEmailForward/created.http");
        SimpleResponse<EmailForward> response = client.domains.createEmailForward(1, "example.com", "john@example.com", "john@another.com");
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), allOf(
                hasEntry("from", "john@example.com"),
                hasEntry("to", "john@another.com")
        ));
        assertThat(response.getData().getId(), is(17706L));
    }

    @Test
    public void testDeleteEmailForward() {
        server.stubFixtureAt("deleteEmailForward/success.http");
        client.domains.deleteEmailForward(1, "example.com", 2);
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/email_forwards/2"));
    }
}
