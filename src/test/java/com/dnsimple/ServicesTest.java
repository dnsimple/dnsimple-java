package com.dnsimple;

import com.dnsimple.data.Service;
import com.dnsimple.response.ListServicesResponse;
import com.dnsimple.response.GetServiceResponse;
import com.dnsimple.exception.DnsimpleException;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class ServicesTest extends DnsimpleTestBase {

  @Test
  public void testListServicesSupportsPagination() throws DnsimpleException, IOException {
    server.expectGet("/v2/services?page=1");
    Client client = new Client();

    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.services.listServices(options);
  }

  @Test
  public void testListServicesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    server.expectGet("/v2/services?foo=bar");
    Client client = new Client();
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.services.listServices(options);
  }

  @Test
  public void testListServicesSupportsSorting() throws DnsimpleException, IOException {
    server.expectGet("/v2/services?sort=name%3Aasc");
    Client client = new Client();
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "name:asc");
    client.services.listServices(options);
  }

  @Test
  public void testListServicesProducesServiceList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listServices/success.http");
    Client client = new Client();

    ListServicesResponse response = client.services.listServices();

    List<Service> services = response.getData();
    assertEquals(2, services.size());
    assertEquals(1, services.get(0).getId().intValue());
  }

  @Test
  public void testGetService() throws DnsimpleException, IOException {
    server.stubFixtureAt("getService/success.http");
    Client client = new Client();

    String serviceId = "1";

    GetServiceResponse response = client.services.getService(serviceId);

    Service service = response.getData();
    assertEquals(1, service.getId().intValue());
    assertEquals("Service 1", service.getName());
    assertEquals("service1", service.getShortName());
    assertEquals("First service example.", service.getDescription());
    assertTrue(Data.isNull(service.getSetupDescription()));
    assertTrue(service.getRequiresSetup());
    assertTrue(Data.isNull(service.getDefaultSubdomain()));
    assertEquals("2014-02-14T19:15:19Z", service.getCreatedAt());
    assertEquals("2016-03-04T09:23:27Z", service.getUpdatedAt());
    assertEquals(1, service.getSettings().size());
  }

}

