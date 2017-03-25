package com.dnsimple;

import com.dnsimple.response.ListCollaboratorsResponse;
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
   * Lists the collaboratorsa in the domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/collaborators/#list">https://developer.dnsimple.com/v2/domains/#list</a>
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
   * @see <a href="https://developer.dnsimple.com/v2/collaborators/#list">https://developer.dnsimple.com/v2/collaborators/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @param options A Map of options to pass to the collaborators API
   * @return The list collaborators response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCollaboratorsResponse listCollaborators(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException;
}
