package com.dnsimple;

import com.dnsimple.data.Domain;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.Filter;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainsTest extends DnsimpleTestBase {
    @Test
    public void testListDomainsSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listDomains("1", singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?page=1"));
    }

    @Test
    public void testListDomainsSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listDomains("1", singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?foo=bar"));
    }

    @Test
    public void testListDomainsSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listDomains("1", singletonMap("sort", "expiration:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?sort=expiration%3Aasc"));
    }

    @Test
    public void testListDomainsSupportsFiltering() throws DnsimpleException, IOException, InterruptedException {
        Map<String, Object> options = new HashMap<>();
        options.put("filter", new Filter("name_like", "example"));
        client.domains.listDomains("1", options);
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?name_like=example"));
    }

    @Test
    public void testListDomainsProducesDomainList() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listDomains/success.http");
        List<Domain> domains = client.domains.listDomains("1").getData();
        assertThat(domains, hasSize(2));
        assertThat(domains.get(0).getId(), is(181984));
    }

    @Test
    public void testListDomainsExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listDomains/success.http");
        PaginatedResponse<Domain> response = client.domains.listDomains("1");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetDomain() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getDomain/success.http");
        Domain domain = client.domains.getDomain("1", "example.com").getData();
        assertThat(domain.getId(), is(181984));
        assertThat(domain.getAccountId(), is(1385));
        assertThat(domain.getRegistrantId(), is(2715));
        assertThat(domain.getName(), is("example-alpha.com"));
        assertThat(domain.getUnicodeName(), is("example-alpha.com"));
        assertThat(domain.getState(), is("registered"));
        assertThat(domain.getAutoRenew(), is(false));
        assertThat(domain.getPrivateWhois(), is(false));
        assertThat(domain.getExpiresAt(), is("2021-06-05T02:15:00Z"));
        assertThat(domain.getCreatedAt(), is("2020-06-04T19:15:14Z"));
        assertThat(domain.getUpdatedAt(), is("2020-06-04T19:15:21Z"));
    }

    @Test
    public void testGetDomainWhenDomainNotFound() {
        server.stubFixtureAt("notfound-domain.http");
        assertThat(() -> client.domains.getDomain("1", "example.com"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateDomainSendsCorrectRequest() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("createDomain/created.http");
        Map<String, Object> attributes = singletonMap("name", "example.com");
        client.domains.createDomain("1010", attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test
    public void testCreateDomainProducesDomain() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("createDomain/created.http");
        SimpleResponse<Domain> response = client.domains.createDomain("1", singletonMap("name", "example.com"));
        assertThat(response.getData().getId(), is(181985));
    }

    @Test
    public void testDeleteDomain() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("deleteDomain/success.http");
        client.domains.deleteDomain("1", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com"));
    }

    @Test
    public void testResetDomainToken() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("resetDomainToken/success.http");
        SimpleResponse<Domain> response = client.domains.resetDomainToken("1", "example.com");
        assertThat(response.getData().getId(), is(1));
    }
}
