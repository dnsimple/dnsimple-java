package com.dnsimple.endpoints.http;

import com.dnsimple.Collaborators;
import com.dnsimple.response.ListCollaboratorsResponse;
import com.dnsimple.response.AddCollaboratorResponse;
import com.dnsimple.response.RemoveCollaboratorResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/* Provides access to the DNSimple Collaborators API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/collaborators">https://developer.dnsimple.com/v2/collaborators</a>
 */
public class CollaboratorsEndpoint implements Collaborators {
  private HttpEndpointClient client;

  public CollaboratorsEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  // Collaborators

  /**
   * Lists the collaborators in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/collaborators/#list">https://developer.dnsimple.com/v2/collaborators/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @return The list collaborators response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCollaboratorsResponse listCollaborators(String accountId, String domainId) throws DnsimpleException, IOException {
    return listCollaborators(accountId, domainId, null);
  }

  /**
   * Lists the collaborators in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/collaborators/#list">https://developer.dnsimple.com/v2/collaborators/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @param options A Map of options to pass to the collaborators API
   * @return The list collaborators response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCollaboratorsResponse listCollaborators(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/collaborators", options);
    return (ListCollaboratorsResponse)client.parseResponse(response, ListCollaboratorsResponse.class);
  }

  /**
   * Add a collaborator to a domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#add">https://developer.dnsimple.com/v2/domains/collaborators/#add</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @param attributes A Map of attributes for adding the collaborator
   * @return The add collaborator response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public AddCollaboratorResponse addCollaborator(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId + "/collaborators", attributes);
    return (AddCollaboratorResponse)client.parseResponse(response, AddCollaboratorResponse.class);
  }

  /**
   * Remove a collaborator from a domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#remove">https://developer.dnsimple.com/v2/domains/collaborators/#remove</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @param collaboratorId The collaborator ID
   * @return The remove collaborator response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public RemoveCollaboratorResponse removeCollaborator(String accountId, String domainId, String collaboratorId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/domains/" + domainId + "/collaborators/" + collaboratorId);
    return (RemoveCollaboratorResponse)client.parseResponse(response, RemoveCollaboratorResponse.class);
  }

}
