package com.dnsimple;

import com.dnsimple.response.ListCollaboratorsResponse;
import com.dnsimple.response.AddCollaboratorResponse;
import com.dnsimple.response.RemoveCollaboratorResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

/**
 * Provides access to the DNSimple Domains API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/domains">https://developer.dnsimple.com/v2/domains</a>
 */
public interface Collaborators {

  // Domains

  /**
   * Lists the collaborators in the domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#list">https://developer.dnsimple.com/v2/domains/collaborators/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @return The list collaborators response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCollaboratorsResponse listCollaborators(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Lists the collaborators in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#list">https://developer.dnsimple.com/v2/domains/collaborators/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @param options A Map of options to pass to the collaborators API
   * @return The list collaborators response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCollaboratorsResponse listCollaborators(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException;

  /**
   * Add a collaborator to a domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/collaborators/#add">https://developer.dnsimple.com/v2/domains/collaborators/#create</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @param attributes A Map of attributes for constructing the domain
   * @return The add collaborator response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public AddCollaboratorResponse addCollaborator(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException;

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
  public RemoveCollaboratorResponse removeCollaborator(String accountId, String domainId, String collaboratorId) throws DnsimpleException, IOException;

}
