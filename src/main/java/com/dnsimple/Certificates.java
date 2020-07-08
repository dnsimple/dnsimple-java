package com.dnsimple;

import com.dnsimple.response.ListCertificatesResponse;
import com.dnsimple.response.GetCertificateResponse;
import com.dnsimple.response.DownloadCertificateResponse;
import com.dnsimple.response.GetCertificatePrivateKeyResponse;
import com.dnsimple.response.PurchaseLetsencryptResponse;
import com.dnsimple.response.IssueLetsencryptResponse;
import com.dnsimple.response.PurchaseLetsencryptRenewalResponse;
import com.dnsimple.response.IssueLetsencryptRenewalResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

public interface Certificates {

  /**
   * List certificates for a domain in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#listCertificates">https://developer.dnsimple.com/v2/certificates/#listCertificates</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The list certificates response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCertificatesResponse listCertificates(String accountId, String domainId) throws DnsimpleException, IOException;

  /**
   * List certificates for a domain in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#listCertificates">https://developer.dnsimple.com/v2/certificates/#listCertificates</a>
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
   * Get the details of a certificate.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#getCertificate">https://developer.dnsimple.com/v2/certificates/#getCertificate</a>
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
   * Get the PEM-encoded certificate, along with the root certificate and intermediate chain.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#downloadCertificate">https://developer.dnsimple.com/v2/certificates/#downloadCertificate</a>
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
   * Get the PEM-encoded certificate private key.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#getCertificatePrivateKey">https://developer.dnsimple.com/v2/certificates/#getCertificatePrivateKey</a>
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
   * This method creates a new purchase order. The order ID should be used to request the issuance of the certificate
   * using `#purchase_letsencrypt_certificate`.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#purchaseLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#purchaseLetsencryptCertificate</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param attributes Attributes for the certificate
   * @return The Let's Encrypt purchase response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public PurchaseLetsencryptResponse purchaseLetsencryptCertificate(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Issue a pending Let's Encrypt certificate order.
   *
   * Note that the issuance process is async. A successful response means the issuance
   * request has been successfully acknowledged and queued for processing.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#issueLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#issueLetsencryptCertificate</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param certificatePurchaseId The ID of the purchase order returned by purchaseLetsencryptCertificate
   * @return The Let's Encrypt issue response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public IssueLetsencryptResponse issueLetsencryptCertificate(String accountId, String domainId, String certificatePurchaseId) throws DnsimpleException, IOException;

  /**
   * Purchase a Let's Encrypt certificate renewal.
   *
   * This method creates a new renewal order. The order ID should be used to request the issuance of the certificate
   * using `#issue_letsencrypt_certificate_renewal`.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#purchaseRenewalLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#purchaseRenewalLetsencryptCertificate</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param certificateId The certificate ID
   * @param attributes Attributes for the certificate
   * @return The Let's Encrypt purchase renewal response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public PurchaseLetsencryptRenewalResponse purchaseLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Issue a pending Let's Encrypt certificate renewal order.
   *
   * Note that the issuance process is async. A successful response means the issuance
   * request has been successfully acknowledged and queued for processing.
   *
   * @see <a href="https://developer.dnsimple.com/v2/certificates/#issueRenewalLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#issueRenewalLetsencryptCertificate</a>
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param certificateId The domain name or ID
   * @param certificateRenewalId The ID of the purchase order returned by purchaseLetsencryptCertificateRenewal
   * @return The Let's Encrypt issue response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public IssueLetsencryptRenewalResponse issueLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, String certificateRenewalId) throws DnsimpleException, IOException;

}
