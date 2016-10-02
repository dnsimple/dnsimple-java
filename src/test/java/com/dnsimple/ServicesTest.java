package com.dnsimple;

import com.dnsimple.response.ListServicesResponse;
import com.dnsimple.response.GetServiceResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.util.Data;

public class ServicesTest extends DnsimpleTestBase {

  @Test
  public void testListServices() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listServices/success.http"));

    ListServicesResponse response = client.services.listServices();

    List<Service> services = response.getData();
    assertEquals(2, services.size());
  }

  @Test
  public void testGetService() throws DnsimpleException, IOException {
    Client client = mockClient(resource("getService/success.http"));

    String serviceId = "1";

    GetServiceResponse response = client.services.getService(serviceId);

    Service service = response.getData();
    assertEquals(1, service.getId().intValue());
    assertEquals("Service 1", service.getName());
    assertEquals("service1", service.getShortName());
    assertEquals("First service example.", service.getDescription());
    assertTrue(Data.isNull(service.getSetupDescription()));
    assertFalse(service.getRequiresSetup());
    assertTrue(Data.isNull(service.getDefaultSubdomain()));
    assertEquals("2014-02-14T19:15:19.953Z", service.getCreatedAt());
    assertEquals("2016-03-04T09:23:27.655Z", service.getUpdatedAt());
    assertEquals(0, service.getSettings().size());
  }

}

