package com.dnsimple.endpoints;

import com.dnsimple.data.Domain;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.time.ZoneOffset.UTC;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainsTest extends DnsimpleTestBase {
    @Test
    public void testListDomainsSupportsPagination() {
        client.domains.listDomains(1, new ListOptions.Builder().page(1).build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?page=1"));
    }

    @Test
    public void testListDomainsSupportsExtraRequestOptions() {
        client.domains.listDomains(1, new ListOptions.Builder().filter("foo", "bar").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?foo=bar"));
    }

    @Test
    public void testListDomainsSupportsSorting() {
        client.domains.listDomains(1, new ListOptions.Builder().sortAsc("expiration").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?sort=expiration%3Aasc"));
    }

    @Test
    public void testListDomainsSupportsFiltering() {
        client.domains.listDomains(1, new ListOptions.Builder().filter("name_like", "example").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?name_like=example"));
    }

    @Test
    public void testListDomainsProducesDomainList() {
        server.stubFixtureAt("listDomains/success.http");
        List<Domain> domains = client.domains.listDomains(1).getData();
        assertThat(domains, hasSize(2));
        assertThat(domains.get(0).getId(), is(181984L));
    }

    @Test
    public void testListDomainsExposesPaginationInfo() {
        server.stubFixtureAt("listDomains/success.http");
        PaginatedResponse<Domain> response = client.domains.listDomains(1);
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetDomain() {
        server.stubFixtureAt("getDomain/success.http");
        Domain domain = client.domains.getDomain(1, "example.com").getData();
        assertThat(domain.getId(), is(181984L));
        assertThat(domain.getAccountId(), is(1385L));
        assertThat(domain.getRegistrantId(), is(2715L));
        assertThat(domain.getName(), is("example-alpha.com"));
        assertThat(domain.getUnicodeName(), is("example-alpha.com"));
        assertThat(domain.getToken(), is(nullValue()));
        assertThat(domain.getState(), is("registered"));
        assertThat(domain.hasAutoRenew(), is(false));
        assertThat(domain.hasPrivateWhois(), is(false));
        assertThat(domain.getExpiresAt(), is(OffsetDateTime.of(2021, 6, 5, 2, 15, 0, 0, UTC)));
        assertThat(domain.getCreatedAt(), is(OffsetDateTime.of(2020, 6, 4, 19, 15, 14, 0, UTC)));
        assertThat(domain.getUpdatedAt(), is(OffsetDateTime.of(2020, 6, 4, 19, 15, 21, 0, UTC)));
    }

    @Test
    public void testGetDomainWhenDomainNotFound() {
        server.stubFixtureAt("notfound-domain.http");
        assertThat(() -> client.domains.getDomain(1, "example.com"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateDomainSendsCorrectRequest() {
        server.stubFixtureAt("createDomain/created.http");
        Map<String, Object> attributes = singletonMap("name", "example.com");
        client.domains.createDomain(1010, attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test
    public void testCreateDomainProducesDomain() {
        server.stubFixtureAt("createDomain/created.http");
        SimpleResponse<Domain> response = client.domains.createDomain(1, singletonMap("name", "example.com"));
        assertThat(response.getData().getId(), is(181985L));
    }

    @Test
    public void testDeleteDomain() {
        server.stubFixtureAt("deleteDomain/success.http");
        client.domains.deleteDomain(1, "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com"));
    }

    @Test
    public void testResetDomainToken() {
        server.stubFixtureAt("resetDomainToken/success.http");
        SimpleResponse<Domain> response = client.domains.resetDomainToken(1, "example.com");
        assertThat(response.getData().getId(), is(1L));
    }
}
