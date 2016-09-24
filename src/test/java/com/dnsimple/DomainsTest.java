package com.dnsimple;

import com.dnsimple.request.Filter;
import com.dnsimple.response.ListDomainsResponse;
import com.dnsimple.response.GetDomainResponse;
import com.dnsimple.response.CreateDomainResponse;
import com.dnsimple.response.DeleteDomainResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Data;

public class DomainsTest extends DnsimpleTestBase {

  @Test
  public void testListDomainsSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains?page=1");
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.domains.listDomains(accountId, options);
  }

  @Test
  public void testListDomainsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains?foo=bar");
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.domains.listDomains(accountId, options);
  }

  @Test
  public void testListDomainsSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains?sort=expires_on%3Aasc");
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "expires_on:asc");
    client.domains.listDomains(accountId, options);
  }

  @Test
  public void testListDomainsSupportsFiltering() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains?name_like=example");
    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("filter", new Filter("name_like", "example"));
    client.domains.listDomains(accountId, options);
  }

  @Test
  public void testListDomainsProductsDomainList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listDomains/success.http"));

    String accountId = "1";

    ListDomainsResponse response = client.domains.listDomains(accountId);

    List<Domain> domains = response.getData();
    assertEquals(2, domains.size());
    assertEquals(1, domains.get(0).getId().intValue());
  }

  @Test
  public void testListDomainsExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listDomains/success.http"));

    String accountId = "1";

    ListDomainsResponse response = client.domains.listDomains(accountId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testGetDomain() throws DnsimpleException, IOException {
    Client client = mockClient(resource("getDomain/success.http"));

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
    assertEquals("2014-12-06T15:56:55.573Z", domain.getCreatedAt());
    assertEquals("2015-12-09T00:20:56.056Z", domain.getUpdatedAt());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetDomainWhenDomainNotFound() throws DnsimpleException, IOException {
    Client client = mockClient(resource("notfound-domain.http"));

    String accountId = "1";
    String domainId = "example.com";

    client.domains.getDomain(accountId, domainId);
  }

  @Test
  public void testCreateDomainSendsCorrectRequest() throws DnsimpleException, IOException {
    String accountId = "1010";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "example.com");

    Client client = expectClient("https://api.dnsimple.com/v2/1010/domains", HttpMethods.POST, attributes);

    client.domains.createDomain(accountId, attributes);
  }

  @Test
  public void testCreateDomainProducesDomain() throws DnsimpleException, IOException {
    Client client = mockClient(resource("createDomain/created.http"));

    String accountId = "1";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "example.com");

    CreateDomainResponse response = client.domains.createDomain(accountId, attributes);
    Domain domain = response.getData();
    assertEquals(1, domain.getId().intValue());
  }

  @Test
  public void testDeleteDomain() throws DnsimpleException, IOException {
    Client client = mockClient(resource("deleteDomain/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    DeleteDomainResponse response = client.domains.deleteDomain(accountId, domainId);
    assert(response.getData() == null);
  }
}
