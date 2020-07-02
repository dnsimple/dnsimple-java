package com.dnsimple;

import com.dnsimple.data.Service;
import com.dnsimple.response.SimpleResponse;
import org.junit.Test;

import java.util.List;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ServicesTest extends DnsimpleTestBase {
    @Test
    public void testListServicesSupportsPagination() {
        client.services.listServices(singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/services?page=1"));
    }

    @Test
    public void testListServicesSupportsExtraRequestOptions() {
        client.services.listServices(singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/services?foo=bar"));
    }

    @Test
    public void testListServicesSupportsSorting() {
        client.services.listServices(singletonMap("sort", "name:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/services?sort=name%3Aasc"));
    }

    @Test
    public void testListServicesProducesServiceList() {
        server.stubFixtureAt("listServices/success.http");
        List<Service> services = client.services.listServices().getData();
        assertThat(services, hasSize(2));
        assertThat(services.get(0).getId(), is(1));
    }

    @Test
    public void testGetService() {
        server.stubFixtureAt("getService/success.http");
        SimpleResponse<Service> response = client.services.getService("1");
        Service service = response.getData();
        assertThat(service.getId(), is(1));
        assertThat(service.getName(), is("Service 1"));
        assertThat(service.getShortName(), is("service1"));
        assertThat(service.getDescription(), is("First service example."));
        assertThat(service.getSetupDescription(), isEmptyOrNullString());
        assertThat(service.getRequiresSetup(), is(true));
        assertThat(service.getDefaultSubdomain(), isEmptyOrNullString());
        assertThat(service.getCreatedAt(), is("2014-02-14T19:15:19Z"));
        assertThat(service.getUpdatedAt(), is("2016-03-04T09:23:27Z"));
        assertThat(service.getSettings(), hasSize(1));
    }
}

