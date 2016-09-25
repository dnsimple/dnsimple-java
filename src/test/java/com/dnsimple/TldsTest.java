package com.dnsimple;

import com.dnsimple.request.Filter;
import com.dnsimple.response.ListTldsResponse;
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

public class TldsTest extends DnsimpleTestBase {

  @Test
  public void testListTldsSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/tlds?page=1");
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.tlds.listTlds(options);
  }

  @Test
  public void testListTldsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/tlds?foo=bar");
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.tlds.listTlds(options);
  }

  @Test
  public void testListTldsSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/tlds?sort=name%3Aasc");
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "name:asc");
    client.tlds.listTlds(options);
  }

  @Test
  public void testListTldsProducesTldList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listTlds/success.http"));

    ListTldsResponse response = client.tlds.listTlds();

    List<Tld> tlds = response.getData();
    assertEquals(2, tlds.size());
    assertEquals("ac", tlds.get(0).getTld());
  }

  @Test
  public void testListTldsExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listTlds/success.http"));

    String accountId = "1";

    ListTldsResponse response = client.tlds.listTlds();

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }
}
