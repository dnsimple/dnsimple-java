package com.dnsimple.endpoints;

import com.dnsimple.data.Service;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static com.dnsimple.http.HttpMethod.GET;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ServicesTest extends DnsimpleTestBase {
    @Test
    public void testListServicesSupportsPagination() {
        client.services.listServices(new ListOptions.Builder().page(1).build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/services?page=1"));
    }

    @Test
    public void testListServicesSupportsExtraRequestOptions() {
        client.services.listServices(new ListOptions.Builder().filter("foo", "bar").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/services?foo=bar"));
    }

    @Test
    public void testListServicesSupportsSorting() {
        client.services.listServices(new ListOptions.Builder().sortAsc("name").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/services?sort=name%3Aasc"));
    }

    @Test
    public void testListServicesProducesServiceList() {
        server.stubFixtureAt("listServices/success.http");
        List<Service> services = client.services.listServices().getData();
        assertThat(services, hasSize(2));
        assertThat(services.get(0).getId(), is(1L));
    }

    @Test
    public void testGetService() {
        server.stubFixtureAt("getService/success.http");
        SimpleResponse<Service> response = client.services.getService("1");
        Service service = response.getData();
        assertThat(service.getId(), is(1L));
        assertThat(service.getName(), is("Service 1"));
        assertThat(service.getSid(), is("service1"));
        assertThat(service.getDescription(), is("First service example."));
        assertThat(service.getSetupDescription(), isEmptyOrNullString());
        assertThat(service.requiresSetup(), is(true));
        assertThat(service.getDefaultSubdomain(), isEmptyOrNullString());
        assertThat(service.getCreatedAt(), is(OffsetDateTime.of(2014, 2, 14, 19, 15, 19, 0, UTC)));
        assertThat(service.getUpdatedAt(), is(OffsetDateTime.of(2016, 3, 4, 9, 23, 27, 0, UTC)));
        assertThat(service.getSettings(), hasSize(1));
    }
}

