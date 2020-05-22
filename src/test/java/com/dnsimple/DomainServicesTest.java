package com.dnsimple;

import static com.dnsimple.tools.HttpMethod.DELETE;
import static com.dnsimple.tools.HttpMethod.GET;
import static com.dnsimple.tools.HttpMethod.POST;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import com.dnsimple.data.Service;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.AppliedServicesResponse;
import com.dnsimple.response.ApplyServiceResponse;
import com.dnsimple.response.UnapplyServiceResponse;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

public class DomainServicesTest extends DnsimpleTestBase {

  @Test
  public void testAppliedServicesSupportsPagination() throws DnsimpleException, IOException {
    client.services.appliedServices("1", "example.com", singletonMap("page", 1));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?page=1"));
  }

  @Test
  public void testAppliedServicesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    client.services.appliedServices("1", "example.com", singletonMap("foo", "bar"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?foo=bar"));
  }

  @Test
  public void testAppliedServicesSupportsSorting() throws DnsimpleException, IOException {
    client.services.appliedServices("1", "example.com", singletonMap("sort", "name:asc"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/services?sort=name:asc"));
  }

  @Test
  public void testAppliedServicesProducesServiceList() throws DnsimpleException, IOException {
    server.stubFixtureAt("appliedServices/success.http");

    List<Service> services = client.services.appliedServices("1", "example.com").getData();
    assertThat(services, hasSize(1));
    assertThat(services.get(0).getId(), is(1));
  }

  @Test
  public void testAppliedServicesExposesPaginationInfo() throws DnsimpleException, IOException {
    server.stubFixtureAt("appliedServices/success.http");

    AppliedServicesResponse response = client.services.appliedServices("1", "example.com");
    assertThat(response.getPagination().getCurrentPage(), is(1));
  }

  @Test
  public void testApplyService() throws DnsimpleException, IOException {
    server.stubFixtureAt("applyService/success.http");

    ApplyServiceResponse response = client.services.applyService("1010", "example.com", "2", emptyMap());
    assertThat(response.getData(), is(nullValue()));
    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/example.com/services/2"));
  }

  @Test
  public void testUnapplyService() throws DnsimpleException, IOException {
    server.stubFixtureAt("unapplyService/success.http");

    UnapplyServiceResponse response = client.services.unapplyService("1010", "example.com", "2");
    assertThat(response.getData(), is(nullValue()));
    assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/example.com/services/2"));
  }
}
