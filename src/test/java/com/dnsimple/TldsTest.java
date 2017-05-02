package com.dnsimple;

import com.dnsimple.data.Tld;
import com.dnsimple.data.TldExtendedAttribute;
import com.dnsimple.data.TldExtendedAttributeOption;
import com.dnsimple.data.Pagination;
import com.dnsimple.response.ListTldsResponse;
import com.dnsimple.response.GetTldResponse;
import com.dnsimple.response.GetTldExtendedAttributesResponse;
import com.dnsimple.exception.DnsimpleException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

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

  @Test
  public void testGetTld() throws DnsimpleException, IOException {
    Client client = mockClient(resource("getTld/success.http"));

    String tldString = "com";

    GetTldResponse response = client.tlds.getTld(tldString);

    Tld tld = response.getData();
    assertEquals("com", tld.getTld());
  }

  @Test
  public void testGetTldExtendedAttributes() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/tlds/uk/extended_attributes", HttpMethods.GET, new HttpHeaders(), null, resource("getTldExtendedAttributes/success.http"));

    String tldString = "uk";

    GetTldExtendedAttributesResponse response = client.tlds.getTldExtendedAttributes(tldString);

    List<TldExtendedAttribute> extendedAttributes = response.getData();
    assertEquals(4, extendedAttributes.size());
    assertEquals("uk_legal_type", extendedAttributes.get(0).getName());
    assertEquals("Legal type of registrant contact", extendedAttributes.get(0).getDescription());
    assertEquals(false, extendedAttributes.get(0).getRequired().booleanValue());

    List<TldExtendedAttributeOption> options = extendedAttributes.get(0).getOptions();
    assertEquals(17, options.size());
    assertEquals("UK Individual", options.get(0).getTitle());
    assertEquals("IND", options.get(0).getValue());
    assertEquals("UK Individual (our default value)", options.get(0).getDescription());
  }

  @Test
  public void testGetTldExtendedAttributesWhenNone() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/tlds/com/extended_attributes", HttpMethods.GET, new HttpHeaders(), null, resource("getTldExtendedAttributes/success-noattributes.http"));

    String tldString = "com";

    GetTldExtendedAttributesResponse response = client.tlds.getTldExtendedAttributes(tldString);

    List<TldExtendedAttribute> extendedAttributes = response.getData();
    assertEquals(0, extendedAttributes.size());
  }
}
