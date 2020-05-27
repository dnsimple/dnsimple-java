package com.dnsimple;

import static com.dnsimple.tools.HttpMethod.*;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

import com.dnsimple.data.Service;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.tools.HttpMethod;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class ServicesTest extends DnsimpleTestBase {

  @Test
  public void testListServicesSupportsPagination() throws DnsimpleException, IOException {
    client.services.listServices(singletonMap("page", 1));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/services?page=1"));
  }

  @Test
  public void testListServicesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    client.services.listServices(singletonMap("foo", "bar"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/services?foo=bar"));
  }

  @Test
  public void testListServicesSupportsSorting() throws DnsimpleException, IOException {
    client.services.listServices(singletonMap("sort", "name:asc"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/services?sort=name:asc"));
  }

  @Test
  public void testListServicesProducesServiceList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listServices/success.http");

    List<Service> services = client.services.listServices().getData();
    assertThat(services, hasSize(2));
    assertThat(services.get(0).getId(), is(1));
  }

  @Test
  public void testGetService() throws DnsimpleException, IOException {
    server.stubFixtureAt("getService/success.http");

    Service service = client.services.getService("1").getData();
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

