package com.dnsimple;

import com.dnsimple.response.ListCertificatesResponse;
import com.dnsimple.response.GetCertificateResponse;
import com.dnsimple.response.DownloadCertificateResponse;
import com.dnsimple.response.GetCertificatePrivateKeyResponse;
import com.dnsimple.response.LetsencryptPurchaseResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import java.io.IOException;
import java.util.Map;

public interface Certificates {

  /**
   * Lists the domains in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/certificates/#list">https://developer.dnsimple.com/v2/domains/certificates/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The list certificates response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCertificatesResponse listCertificates(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * Lists the certificates in the domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/certificates/#list">https://developer.dnsimple.com/v2/domains/certificates/#list</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param options A Map of options to pass to the certificates API
   * @return The list certificates response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCertificatesResponse listCertificates(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException;

  /**
   * Get a specific certificate associated to a domain using the certificate's ID.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/certificates/#get">https://developer.dnsimple.com/v2/domains/certificates/#get</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param certificateId The certificate ID
   * @return The get certificate response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetCertificateResponse getCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException;

  /**
   * Downloads certificate associated with a domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/certificates/#download">https://developer.dnsimple.com/v2/domains/certificates/#download</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param certificateId The certificate ID
   * @return The download certificate response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DownloadCertificateResponse downloadCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException;

  /**
   * Get the certificate private key associated with a domain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/certificates/#private-key">https://developer.dnsimple.com/v2/domains/certificates/#private-key</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param certificateId The certificate ID
   * @return The get certificate private key response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetCertificatePrivateKeyResponse getCertificatePrivateKey(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException;

  /**
   * Purchase a Let's Encrypt certificate.
   *
   * @see <a href=""></a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param attributes Attributes for the certificate
   * @return The letsencrypt purchase response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public LetsencryptPurchaseResponse letsencryptPurchase(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException;

}
