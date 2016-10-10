package com.dnsimple;

import com.dnsimple.data.Service;
import com.dnsimple.data.Pagination;
import com.dnsimple.request.Filter;
import com.dnsimple.response.AppliedServicesResponse;
import com.dnsimple.response.ApplyServiceResponse;
import com.dnsimple.response.UnapplyServiceResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class DomainServicesTest extends DnsimpleTestBase {

  @Test
  public void testAppliedServicesSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/services?page=1");
    String accountId = "1";
    String domainId = "example.com";

    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.services.appliedServices(accountId, domainId, options);
  }

  @Test
  public void testAppliedServicesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/services?foo=bar");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.services.appliedServices(accountId, domainId, options);
  }

  @Test
  public void testAppliedServicesSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/services?sort=name%3Aasc");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "name:asc");
    client.services.appliedServices(accountId, domainId, options);
  }

  @Test
  public void testAppliedServicesProducesServiceList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("appliedServices/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    AppliedServicesResponse response = client.services.appliedServices(accountId, domainId);

    List<Service> services = response.getData();
    assertEquals(1, services.size());
    assertEquals(1, services.get(0).getId().intValue());
  }

  @Test
  public void testAppliedServicesExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("appliedServices/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    AppliedServicesResponse response = client.services.appliedServices(accountId, domainId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testApplyService() throws DnsimpleException, IOException {
    HashMap<String, Object> settings = new HashMap<String, Object>();

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/example.com/services/2", HttpMethods.POST, settings, resource("applyService/success.http"));

    String accountId = "1010";
    String domainId = "example.com";
    String serviceId = "2";

    ApplyServiceResponse response = client.services.applyService(accountId, domainId, serviceId, settings);
    assertEquals(null, response.getData());
  }

  @Test
  public void testUnapplyService() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/example.com/services/2", HttpMethods.DELETE, null, resource("unapplyService/success.http"));

    String accountId = "1010";
    String domainId = "example.com";
    String serviceId = "2";

    UnapplyServiceResponse response = client.services.unapplyService(accountId, domainId, serviceId);
    assertEquals(null, response.getData());
  }
}
