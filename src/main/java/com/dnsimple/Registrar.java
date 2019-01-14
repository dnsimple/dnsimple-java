package com.dnsimple;

import com.dnsimple.response.CheckDomainResponse;
import com.dnsimple.response.RegisterDomainResponse;
import com.dnsimple.response.RenewDomainResponse;
import com.dnsimple.response.TransferDomainResponse;
import com.dnsimple.response.TransferDomainOutResponse;
import com.dnsimple.response.EnableAutoRenewalResponse;
import com.dnsimple.response.DisableAutoRenewalResponse;
import com.dnsimple.response.GetWhoisPrivacyResponse;
import com.dnsimple.response.EnableWhoisPrivacyResponse;
import com.dnsimple.response.DisableWhoisPrivacyResponse;
import com.dnsimple.response.RenewWhoisPrivacyResponse;
import com.dnsimple.response.GetDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationResponse;
import com.dnsimple.response.ChangeDomainDelegationToVanityResponse;
import com.dnsimple.response.ChangeDomainDelegationFromVanityResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Provides access to the DNSimple Registrar API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/registrar">https://developer.dnsimple.com/v2/registrar</a>
 */
public interface Registrar {

  /**
   * Checks whether a domain is available for registration.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/#check">https://developer.dnsimple.com/v2/registrar/#check</a>
   *
   * @param accountId The account ID
   * @param domainName The domain to check
   * @return The check domain response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public CheckDomainResponse checkDomain(String accountId, String domainName) throws DnsimpleException, IOException;

  /**
   * Registers a domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/#register">https://developer.dnsimple.com/v2/registrar/#register</a>
   *
   * @param accountId The account ID
   * @param domainName The domain to register
   * @param attributes Attributes to use for the registration
   * @return The register domain response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public RegisterDomainResponse registerDomain(String accountId, String domainName, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Renews a domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/#renew">https://developer.dnsimple.com/v2/registrar/#renew</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param attributes Attributes to use for the renewal
   * @return The renew domain response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public RenewDomainResponse renewDomain(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Starts the transfer of a domain to DNSimple.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/#transfer">https://developer.dnsimple.com/v2/registrar/#transfer</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param attributes Attributes to use for the transfer
   * @return The transfer domain response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public TransferDomainResponse transferDomain(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Requests the transfer of a domain out of DNSimple.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/#transfer-out">https://developer.dnsimple.com/v2/registrar/#transfer-out</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The transfer domain out response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public TransferDomainOutResponse transferDomainOut(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Enable auto renewal for the domain in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/auto-renewal/#enable">https://developer.dnsimple.com/v2/registrar/auto-renewal/#enable</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The enable auto renewal response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public EnableAutoRenewalResponse enableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Disable auto renewal for the domain in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/auto-renewal/#disable">https://developer.dnsimple.com/v2/registrar/auto-renewal/#disable</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The disable auto renewal response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public DisableAutoRenewalResponse disableAutoRenewal(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Gets the whois privacy for the domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#get">https://developer.dnsimple.com/v2/registrar/whois-privacy/#get</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The get whois privacy response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public GetWhoisPrivacyResponse getWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException;

   /**
   * Enable whois privacy for the domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#enable">https://developer.dnsimple.com/v2/registrar/whois-privacy/#enable</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The enable whois privacy response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public EnableWhoisPrivacyResponse enableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Disable whois privacy for the domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#disable">https://developer.dnsimple.com/v2/registrar/whois-privacy/#disable</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The disable whois privacy response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public DisableWhoisPrivacyResponse disableWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Renew whois privacy for the domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/whois-privacy/#renew">https://developer.dnsimple.com/v2/registrar/whois-privacy/#renew</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The disable whois privacy response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public RenewWhoisPrivacyResponse renewWhoisPrivacy(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Lists name servers the domain is delegating to.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#list">https://developer.dnsimple.com/v2/registrar/delegation/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The get domain delegation response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public GetDomainDelegationResponse getDomainDelegation(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Change name servers the domain is delegating to.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#update">https://developer.dnsimple.com/v2/registrar/delegation/#update</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @param nameServerNames The name server names to change the delegation to
   * @return The change domain delegation response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public ChangeDomainDelegationResponse changeDomainDelegation(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException;

  /**
   * Change the domain delegation to the specified vanity name servers.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#delegateToVanity">https://developer.dnsimple.com/v2/registrar/delegation/#delegateToVanity</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @param nameServerNames The vanity name server names
   * @return The change domain delegation to vanity response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public ChangeDomainDelegationToVanityResponse changeDomainDelegationToVanity(String accountId, String domainId, List<String> nameServerNames) throws DnsimpleException, IOException;

  /**
   * Change the domain delegation back to the standard DNSimple name servers.
   *
   * @see <a href="https://developer.dnsimple.com/v2/registrar/delegation/#delegateFromVanity">https://developer.dnsimple.com/v2/registrar/delegation/#delegateFromVanity</a>
   *
   * @param accountId The account ID
   * @param domainId The domain ID
   * @return The change domain delegation from vanity response
   * @throws DnsimpleException Any API error
   * @throws IOException Any IO error
   */
  public ChangeDomainDelegationFromVanityResponse changeDomainDelegationFromVanity(String accountId, String domainId) throws DnsimpleException, IOException;

}
