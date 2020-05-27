package com.dnsimple;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.DELETE;
import static com.dnsimple.tools.HttpMethod.GET;
import static com.dnsimple.tools.HttpMethod.POST;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;

import com.dnsimple.data.Domain;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.Filter;
import com.dnsimple.response.CreateDomainResponse;
import com.dnsimple.response.DeleteDomainResponse;
import com.dnsimple.response.ListDomainsResponse;
import com.dnsimple.response.ResetDomainTokenResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class DomainsTest extends DnsimpleTestBase {

  @Test
  public void testListDomainsSupportsPagination() throws DnsimpleException, IOException {
    client.domains.listDomains("1", singletonMap("page", 1));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?page=1"));
  }

  @Test
  public void testListDomainsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    client.domains.listDomains("1", singletonMap("foo", "bar"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?foo=bar"));
  }

  @Test
  public void testListDomainsSupportsSorting() throws DnsimpleException, IOException {
    client.domains.listDomains("1", singletonMap("sort", "expires_on:asc"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?sort=expires_on:asc"));
  }

  @Test
  public void testListDomainsSupportsFiltering() throws DnsimpleException, IOException {
    Map<String, Object> options = new HashMap<>();
    options.put("filter", new Filter("name_like", "example"));
    client.domains.listDomains("1", options);
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains?name_like=example"));
  }

  @Test
  public void testListDomainsProducesDomainList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listDomains/success.http");

    List<Domain> domains = client.domains.listDomains("1").getData();
    assertThat(domains, hasSize(2));
    assertThat(domains.get(0).getId(), is(1));
  }

  @Test
  public void testListDomainsExposesPaginationInfo() throws DnsimpleException, IOException {
    server.stubFixtureAt("listDomains/success.http");

    ListDomainsResponse response = client.domains.listDomains("1");
    assertThat(response.getPagination().getCurrentPage(), is(1));
  }

  @Test
  public void testGetDomain() throws DnsimpleException, IOException {
    server.stubFixtureAt("getDomain/success.http");

    Domain domain = client.domains.getDomain("1", "example.com").getData();
    assertThat(domain.getId(), is(1));
    assertThat(domain.getAccountId(), is(1010));
    assertThat(domain.getRegistrantId(), is(0));
    assertThat(domain.getName(), is("example-alpha.com"));
    assertThat(domain.getUnicodeName(), is("example-alpha.com"));
    assertThat(domain.getToken(), is("domain-token"));
    assertThat(domain.getState(), is("hosted"));
    assertThat(domain.getAutoRenew(), is(false));
    assertThat(domain.getPrivateWhois(), is(false));
    assertThat(domain.getExpiresOn(), isEmptyOrNullString());
    assertThat(domain.getCreatedAt(), is("2014-12-06T15:56:55Z"));
    assertThat(domain.getUpdatedAt(), is("2015-12-09T00:20:56Z"));
  }

  @Test
  public void testGetDomainWhenDomainNotFound() {
    server.stubFixtureAt("notfound-domain.http");

    assertThat(() -> client.domains.getDomain("1", "example.com"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }

  @Test
  public void testCreateDomainSendsCorrectRequest() throws DnsimpleException, IOException {
    server.stubFixtureAt("createDomain/created.http");

    Map<String, Object> attributes = singletonMap("name", "example.com");
    client.domains.createDomain("1010", attributes);

    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains"));
    assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
  }

  @Test
  public void testCreateDomainProducesDomain() throws DnsimpleException, IOException {
    server.stubFixtureAt("createDomain/created.http");

    CreateDomainResponse response = client.domains.createDomain("1", singletonMap("name", "example.com"));
    assertThat(response.getData().getId(), is(1));
  }

  @Test
  public void testDeleteDomain() throws DnsimpleException, IOException {
    server.stubFixtureAt("deleteDomain/success.http");

    DeleteDomainResponse response = client.domains.deleteDomain("1", "example.com");
    assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com"));
    assertThat(response.getData(), is(nullValue()));
  }

  @Test
  public void testResetDomainToken() throws DnsimpleException, IOException {
    server.stubFixtureAt("resetDomainToken/success.http");

    ResetDomainTokenResponse response = client.domains.resetDomainToken("1", "example.com");
    assertThat(response.getData().getId(), is(1));
  }
}
