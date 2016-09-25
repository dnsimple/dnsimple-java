package com.dnsimple;

import com.dnsimple.request.Filter;
import com.dnsimple.response.ListEmailForwardsResponse;
import com.dnsimple.response.GetEmailForwardResponse;
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

public class DomainEmailForwardsTest extends DnsimpleTestBase {

  @Test
  public void testListEmailForwardsSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/email_forwards?page=1");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.domains.listEmailForwards(accountId, domainId, options);
  }

   @Test
  public void testListEmailForwardsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/email_forwards?foo=bar");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.domains.listEmailForwards(accountId, domainId, options);
  }

  @Test
  public void testListEmailForwardsSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/email_forwards?sort=from%3Aasc");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "from:asc");
    client.domains.listEmailForwards(accountId, domainId, options);
  }

  @Test
  public void testListEmailForwardsProducesDomainList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listEmailForwards/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    ListEmailForwardsResponse response = client.domains.listEmailForwards(accountId, domainId);

    List<EmailForward> emailForwards = response.getData();
    assertEquals(2, emailForwards.size());
    assertEquals(17702, emailForwards.get(0).getId().intValue());
  }

  @Test
  public void testListEmailForwardsExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listEmailForwards/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    ListEmailForwardsResponse response = client.domains.listEmailForwards(accountId, domainId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testGetEmailForward() throws DnsimpleException, IOException {
    Client client = mockClient(resource("getEmailForward/success.http"));

    String accountId = "1";
    String domainId = "example.com";
    String emailForwardId = "17706";

    GetEmailForwardResponse response = client.domains.getEmailForward(accountId, domainId, emailForwardId);

    EmailForward emailForward = response.getData();
    assertEquals(17706, emailForward.getId().intValue());
    assertEquals(228963, emailForward.getDomainId().intValue());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetEmailForwardWhenDomainNotFound() throws DnsimpleException, IOException {
    Client client = mockClient(resource("notfound-domain.http"));

    String accountId = "1";
    String domainId = "0";
    String emailForwardId = "17706";

    client.domains.getEmailForward(accountId, domainId, emailForwardId);
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetEmailForwardWhenEmailForwardNotFound() throws DnsimpleException, IOException {
    Client client = mockClient(resource("notfound-emailforward.http"));

    String accountId = "1";
    String domainId = "example.com";
    String emailForwardId = "0";

    client.domains.getEmailForward(accountId, domainId, emailForwardId);
  }
}
