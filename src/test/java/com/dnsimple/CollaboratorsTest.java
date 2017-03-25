package com.dnsimple;

import com.dnsimple.data.Collaborator;
import com.dnsimple.data.Pagination;
import com.dnsimple.response.ListCollaboratorsResponse;
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

public class CollaboratorsTest extends DnsimpleTestBase {

  @Test
  public void testListCollaboratorsSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/collaborators?page=1");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.collaborators.listCollaborators(accountId, domainId, options);
  }

  @Test
  public void testListCollaboratorsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/collaborators?foo=bar");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.collaborators.listCollaborators(accountId, domainId, options);
  }

  @Test
  public void testCollaboratorsSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/collaborators?sort=created_at%3Aasc");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "created_at:asc");
    client.collaborators.listCollaborators(accountId, domainId, options);
  }

  @Test
  public void testCollaboratorsProducesDelegationSignerRecordList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listCollaborators/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    ListCollaboratorsResponse response = client.collaborators.listCollaborators(accountId, domainId);

    List<Collaborator> collaborators = response.getData();
    assertEquals(2, collaborators.size());
    assertEquals(100, collaborators.get(0).getId().intValue());
  }

  @Test
  public void testListCollaboratorsExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listCollaborators/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    ListCollaboratorsResponse response = client.collaborators.listCollaborators(accountId, domainId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

}
