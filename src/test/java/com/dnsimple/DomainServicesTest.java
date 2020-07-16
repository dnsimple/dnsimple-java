package com.dnsimple;

import com.dnsimple.data.Service;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import org.junit.Test;

import java.util.List;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainServicesTest extends DnsimpleTestBase {
    @Test
    public void testAppliedServicesSupportsPagination() {
        client.services.appliedServices("1", "example.com", singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?page=1"));
    }

    @Test
    public void testAppliedServicesSupportsExtraRequestOptions() {
        client.services.appliedServices("1", "example.com", singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?foo=bar"));
    }

    @Test
    public void testAppliedServicesSupportsSorting() {
        client.services.appliedServices("1", "example.com", singletonMap("sort", "name:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?sort=name%3Aasc"));
    }

    @Test
    public void testAppliedServicesProducesServiceList() {
        server.stubFixtureAt("appliedServices/success.http");
        List<Service> services = client.services.appliedServices("1", "example.com").getData();
        assertThat(services, hasSize(1));
        assertThat(services.get(0).getId(), is(1L));
    }

    @Test
    public void testAppliedServicesExposesPaginationInfo() {
        server.stubFixtureAt("appliedServices/success.http");
        PaginatedResponse<Service> response = client.services.appliedServices("1", "example.com");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testApplyService() {
        server.stubFixtureAt("applyService/success.http");
        SimpleResponse<Service> response = client.services.applyService("1010", "example.com", "2", emptyMap());
        assertThat(response.getData(), is(nullValue()));
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/example.com/services/2"));
    }

    @Test
    public void testUnapplyService() {
        server.stubFixtureAt("unapplyService/success.http");
        SimpleResponse<Service> response = client.services.unapplyService("1010", "example.com", "2");
        assertThat(response.getData(), is(nullValue()));
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/example.com/services/2"));
    }
}
