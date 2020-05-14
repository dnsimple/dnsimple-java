package com.dnsimple;

import com.dnsimple.data.Domain;
import com.dnsimple.data.Pagination;
import com.dnsimple.request.Filter;
import com.dnsimple.response.ListDomainsResponse;
import com.dnsimple.response.GetDomainResponse;
import com.dnsimple.response.CreateDomainResponse;
import com.dnsimple.response.DeleteDomainResponse;
import com.dnsimple.response.ResetDomainTokenResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.dnsimple.tools.HttpMethod;
import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class DomainsTest extends DnsimpleTestBase {

  @Test
  public void testListDomainsSupportsPagination() throws DnsimpleException, IOException {
    server.expectGet("/v2/1/domains?page=1");
    Client client = new Client();
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.domains.listDomains(accountId, options);
  }

  @Test
  public void testListDomainsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    server.expectGet("/v2/1/domains?foo=bar");
    Client client = new Client();
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.domains.listDomains(accountId, options);
  }

  @Test
  public void testListDomainsSupportsSorting() throws DnsimpleException, IOException {
    server.expectGet("/v2/1/domains?sort=expires_on%3Aasc");
    Client client = new Client();
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "expires_on:asc");
    client.domains.listDomains(accountId, options);
  }

  @Test
  public void testListDomainsSupportsFiltering() throws DnsimpleException, IOException {
    server.expectGet("/v2/1/domains?name_like=example");
    Client client = new Client();
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("filter", new Filter("name_like", "example"));
    client.domains.listDomains(accountId, options);
  }

  @Test
  public void testListDomainsProducesDomainList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listDomains/success.http");
    Client client = new Client();

    String accountId = "1";

    ListDomainsResponse response = client.domains.listDomains(accountId);

    List<Domain> domains = response.getData();
    assertEquals(2, domains.size());
    assertEquals(1, domains.get(0).getId().intValue());
  }

  @Test
  public void testListDomainsExposesPaginationInfo() throws DnsimpleException, IOException {
    server.stubFixtureAt("listDomains/success.http");
    Client client = new Client();

    String accountId = "1";

    ListDomainsResponse response = client.domains.listDomains(accountId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testGetDomain() throws DnsimpleException, IOException {
    server.stubFixtureAt("getDomain/success.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    GetDomainResponse response = client.domains.getDomain(accountId, domainId);

    Domain domain = response.getData();
    assertEquals(1, domain.getId().intValue());
    assertEquals(1010, domain.getAccountId().intValue());
    assertTrue(Data.isNull(domain.getRegistrantId()));
    assertEquals("example-alpha.com", domain.getName());
    assertEquals("example-alpha.com", domain.getUnicodeName());
    assertEquals("domain-token", domain.getToken());
    assertEquals("hosted", domain.getState());
    assertFalse(domain.getAutoRenew());
    assertFalse(domain.getPrivateWhois());
    assertTrue(Data.isNull(domain.getExpiresOn()));
    assertEquals("2014-12-06T15:56:55Z", domain.getCreatedAt());
    assertEquals("2015-12-09T00:20:56Z", domain.getUpdatedAt());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetDomainWhenDomainNotFound() throws DnsimpleException, IOException {
    server.stubFixtureAt("notfound-domain.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    client.domains.getDomain(accountId, domainId);
  }

  @Test
  public void testCreateDomainSendsCorrectRequest() throws DnsimpleException, IOException {
    String accountId = "1010";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "example.com");

    server.expectPost("/v2/1010/domains");
    server.expectJsonPayload(attributes);
    server.stubFixtureAt("createDomain/created.http");
    Client client = new Client();

    client.domains.createDomain(accountId, attributes);
  }

  @Test
  public void testCreateDomainProducesDomain() throws DnsimpleException, IOException {
    server.stubFixtureAt("createDomain/created.http");
    Client client = new Client();

    String accountId = "1";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "example.com");

    CreateDomainResponse response = client.domains.createDomain(accountId, attributes);
    Domain domain = response.getData();
    assertEquals(1, domain.getId().intValue());
  }

  @Test
  public void testDeleteDomain() throws DnsimpleException, IOException {
    server.expectDelete("/v2/1/domains/example.com");
    server.stubFixtureAt("deleteDomain/success.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    DeleteDomainResponse response = client.domains.deleteDomain(accountId, domainId);
    assertEquals(null, response.getData());
  }

  @Test
  public void testResetDomainToken() throws DnsimpleException, IOException {
    server.stubFixtureAt("resetDomainToken/success.http");
    Client client = new Client();

    String accountId = "1";
    String domainId = "example.com";

    ResetDomainTokenResponse response = client.domains.resetDomainToken(accountId, domainId);
    Domain domain = response.getData();
    assertEquals(1, domain.getId().intValue());
  }
}
