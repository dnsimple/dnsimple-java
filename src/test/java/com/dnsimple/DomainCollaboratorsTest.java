package com.dnsimple;

import com.dnsimple.data.Collaborator;
import com.dnsimple.data.Pagination;
import com.dnsimple.response.ListCollaboratorsResponse;
import com.dnsimple.response.AddCollaboratorResponse;
import com.dnsimple.response.RemoveCollaboratorResponse;
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

public class DomainCollaboratorsTest extends DnsimpleTestBase {

  @Test
  public void testListCollaboratorsSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/collaborators?page=1");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.domains.listCollaborators(accountId, domainId, options);
  }

  @Test
  public void testListCollaboratorsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/collaborators?foo=bar");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.domains.listCollaborators(accountId, domainId, options);
  }

  @Test
  public void testCollaboratorsSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/example.com/collaborators?sort=created_at%3Aasc");
    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "created_at:asc");
    client.domains.listCollaborators(accountId, domainId, options);
  }

  @Test
  public void testCollaboratorsProducesDelegationSignerRecordList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listCollaborators/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    ListCollaboratorsResponse response = client.domains.listCollaborators(accountId, domainId);

    List<Collaborator> collaborators = response.getData();
    assertEquals(2, collaborators.size());
    assertEquals(100, collaborators.get(0).getId().intValue());
  }

  @Test
  public void testListCollaboratorsExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listCollaborators/success.http"));

    String accountId = "1";
    String domainId = "example.com";

    ListCollaboratorsResponse response = client.domains.listCollaborators(accountId, domainId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testAddColaboratorProducersInvitedUserCollaborator() throws DnsimpleException, IOException {
    Client client = mockClient(resource("addCollaborator/invite-success.http"));

    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("email", "invited-user@example.com");
    AddCollaboratorResponse response = client.domains.addCollaborator(accountId, domainId, attributes);
    Collaborator collaborator = response.getData();
    assertEquals(101, collaborator.getId().intValue());
    assertEquals(1, collaborator.getDomainId().intValue());
    assertEquals("example.com", collaborator.getDomainName());
    assertEquals(0, collaborator.getUserId().intValue());
    assertEquals("invited-user@example.com", collaborator.getUserEmail());
    assertEquals(true, collaborator.getInvitation().booleanValue());
  }

  @Test
  public void testAddColaboratorProducersExistingUserCollaborator() throws DnsimpleException, IOException {
    Client client = mockClient(resource("addCollaborator/success.http"));

    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("email", "invited-user@example.com");
    AddCollaboratorResponse response = client.domains.addCollaborator(accountId, domainId, attributes);
    Collaborator collaborator = response.getData();
    assertEquals(100, collaborator.getId().intValue());
    assertEquals(1, collaborator.getDomainId().intValue());
    assertEquals("example.com", collaborator.getDomainName());
    assertEquals(999, collaborator.getUserId().intValue());
    assertEquals("existing-user@example.com", collaborator.getUserEmail());
    assertEquals(false, collaborator.getInvitation().booleanValue());
  }

  @Test
  public void testRemoveCollaborator() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1/domains/example.com/collaborators/100", HttpMethods.DELETE, resource("removeCollaborator/success.http"));

    String accountId = "1";
    String domainId = "example.com";
    String collaboratorId = "100";

    RemoveCollaboratorResponse response = client.domains.removeCollaborator(accountId, domainId, collaboratorId);
    assertEquals(null, response.getData());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testRemovecollaboratorWhenNotFound() throws DnsimpleException, IOException {
    Client client = mockClient(resource("notfound-collaborator.http"));

    String accountId = "1";
    String domainId = "example.com";
    String collaboratorId = "0";

    client.domains.removeCollaborator(accountId, domainId, collaboratorId);
  }

}
