package com.dnsimple;

import com.dnsimple.data.EmailForward;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.endpoints.http.java11.HttpMethod.DELETE;
import static com.dnsimple.endpoints.http.java11.HttpMethod.GET;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainEmailForwardsTest extends DnsimpleTestBase {
    @Test
    public void testListEmailForwardsSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listEmailForwards("1", "example.com", singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/email_forwards?page=1"));
    }

    @Test
    public void testListEmailForwardsSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listEmailForwards("1", "example.com", singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/email_forwards?foo=bar"));
    }

    @Test
    public void testListEmailForwardsSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listEmailForwards("1", "example.com", singletonMap("sort", "from:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/email_forwards?sort=from%3Aasc"));
    }

    @Test
    public void testListEmailForwardsProducesDomainList() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listEmailForwards/success.http");
        PaginatedResponse<EmailForward> response = client.domains.listEmailForwards("1", "example.com");
        assertThat(response.getData(), hasSize(2));
        assertThat(response.getData().get(0).getId(), is(17702));
    }

    @Test
    public void testListEmailForwardsExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listEmailForwards/success.http");
        PaginatedResponse<EmailForward> response = client.domains.listEmailForwards("1", "example.com");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetEmailForward() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getEmailForward/success.http");
        EmailForward emailForward = client.domains.getEmailForward("1", "example.com", "17706").getData();
        assertThat(emailForward.getId(), is(17706));
        assertThat(emailForward.getDomainId(), is(228963));
    }

    @Test
    public void testGetEmailForwardWhenDomainNotFound() {
        server.stubFixtureAt("notfound-domain.http");
        assertThat(() -> client.domains.getEmailForward("1", "0", "17706"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testGetEmailForwardWhenEmailForwardNotFound() {
        server.stubFixtureAt("notfound-emailforward.http");
        assertThat(() -> client.domains.getEmailForward("1", "example.com", "0"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateEmailForwardProducesEmailForward() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("createEmailForward/created.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("from", "john");
        attributes.put("to", "john@another.com");
        SimpleResponse<EmailForward> response = client.domains.createEmailForward("1", "example.com", attributes);
        assertThat(response.getData().getId(), is(17706));
    }

    @Test
    public void testDeleteEmailForward() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("deleteEmailForward/success.http");
        client.domains.deleteEmailForward("1", "example.com", "2");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/email_forwards/2"));
    }
}
