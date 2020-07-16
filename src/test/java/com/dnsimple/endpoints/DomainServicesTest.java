package com.dnsimple.endpoints;

import com.dnsimple.data.Service;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.util.List;

import static com.dnsimple.http.HttpMethod.*;
import static java.util.Collections.emptyMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainServicesTest extends DnsimpleTestBase {
    @Test
    public void testAppliedServicesSupportsPagination() {
        client.services.appliedServices(1, "example.com", new ListOptions.Builder().page(1).build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?page=1"));
    }

    @Test
    public void testAppliedServicesSupportsExtraRequestOptions() {
        client.services.appliedServices(1, "example.com", new ListOptions.Builder().filter("foo", "bar").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?foo=bar"));
    }

    @Test
    public void testAppliedServicesSupportsSorting() {
        client.services.appliedServices(1, "example.com", new ListOptions.Builder().sortAsc("name").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?sort=name%3Aasc"));
    }

    @Test
    public void testAppliedServicesProducesServiceList() {
        server.stubFixtureAt("appliedServices/success.http");
        List<Service> services = client.services.appliedServices(1, "example.com").getData();
        assertThat(services, hasSize(1));
        assertThat(services.get(0).getId(), is(1L));
    }

    @Test
    public void testAppliedServicesExposesPaginationInfo() {
        server.stubFixtureAt("appliedServices/success.http");
        PaginatedResponse<Service> response = client.services.appliedServices(1, "example.com");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testApplyService() {
        server.stubFixtureAt("applyService/success.http");
        SimpleResponse<Service> response = client.services.applyService(1010, "example.com", "2", emptyMap());
        assertThat(response.getData(), is(nullValue()));
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/example.com/services/2"));
    }

    @Test
    public void testUnapplyService() {
        server.stubFixtureAt("unapplyService/success.http");
        SimpleResponse<Service> response = client.services.unapplyService(1010, "example.com", "2");
        assertThat(response.getData(), is(nullValue()));
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/example.com/services/2"));
    }
}
