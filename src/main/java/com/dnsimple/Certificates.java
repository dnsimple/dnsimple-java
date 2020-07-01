package com.dnsimple;

import com.dnsimple.data.Certificate;
import com.dnsimple.data.CertificateBundle;
import com.dnsimple.data.CertificatePurchase;
import com.dnsimple.data.CertificateRenewal;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

public interface Certificates {
    /**
     * List certificates for a domain in the account.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @return The list certificates response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#listCertificates">https://developer.dnsimple.com/v2/certificates/#listCertificates</a>
     */
    public PaginatedResponse<Certificate> listCertificates(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * List certificates for a domain in the account.
     *
     * @param accountId The account ID
     * @param domainId  The domain name or ID
     * @param options   A Map of options to pass to the certificates API
     * @return The list certificates response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#listCertificates">https://developer.dnsimple.com/v2/certificates/#listCertificates</a>
     */
    public PaginatedResponse<Certificate> listCertificates(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get the details of a certificate.
     *
     * @param accountId     The account ID
     * @param domainId      The domain name or ID
     * @param certificateId The certificate ID
     * @return The get certificate response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#getCertificate">https://developer.dnsimple.com/v2/certificates/#getCertificate</a>
     */
    public SimpleResponse<Certificate> getCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get the PEM-encoded certificate, along with the root certificate and intermediate chain.
     *
     * @param accountId     The account ID
     * @param domainId      The domain name or ID
     * @param certificateId The certificate ID
     * @return The download certificate response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#downloadCertificate">https://developer.dnsimple.com/v2/certificates/#downloadCertificate</a>
     */
    public SimpleResponse<CertificateBundle> downloadCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get the PEM-encoded certificate private key.
     *
     * @param accountId     The account ID
     * @param domainId      The domain name or ID
     * @param certificateId The certificate ID
     * @return The get certificate private key response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#getCertificatePrivateKey">https://developer.dnsimple.com/v2/certificates/#getCertificatePrivateKey</a>
     */
    public SimpleResponse<CertificateBundle> getCertificatePrivateKey(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Purchase a Let's Encrypt certificate.
     * <p>
     * This method creates a new purchase order. The order ID should be used to request the issuance of the certificate
     * using `#purchase_letsencrypt_certificate`.
     *
     * @param accountId  The account ID
     * @param domainId   The domain name or ID
     * @param attributes Attributes for the certificate
     * @return The Let's Encrypt purchase response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#purchaseLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#purchaseLetsencryptCertificate</a>
     */
    public SimpleResponse<CertificatePurchase> purchaseLetsencryptCertificate(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Issue a pending Let's Encrypt certificate order.
     * <p>
     * Note that the issuance process is async. A successful response means the issuance
     * request has been successfully acknowledged and queued for processing.
     *
     * @param accountId             The account ID
     * @param domainId              The domain name or ID
     * @param certificatePurchaseId The ID of the purchase order returned by purchaseLetsencryptCertificate
     * @return The Let's Encrypt issue response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#issueLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#issueLetsencryptCertificate</a>
     */
    public SimpleResponse<Certificate> issueLetsencryptCertificate(String accountId, String domainId, String certificatePurchaseId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Purchase a Let's Encrypt certificate renewal.
     * <p>
     * This method creates a new renewal order. The order ID should be used to request the issuance of the certificate
     * using `#issue_letsencrypt_certificate_renewal`.
     *
     * @param accountId     The account ID
     * @param domainId      The domain name or ID
     * @param certificateId The certificate ID
     * @param attributes    Attributes for the certificate
     * @return The Let's Encrypt purchase renewal response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#purchaseRenewalLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#purchaseRenewalLetsencryptCertificate</a>
     */
    public SimpleResponse<CertificateRenewal> purchaseLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Issue a pending Let's Encrypt certificate renewal order.
     * <p>
     * Note that the issuance process is async. A successful response means the issuance
     * request has been successfully acknowledged and queued for processing.
     *
     * @param accountId            The account ID
     * @param domainId             The domain name or ID
     * @param certificateId        The domain name or ID
     * @param certificateRenewalId The ID of the purchase order returned by purchaseLetsencryptCertificateRenewal
     * @return The Let's Encrypt issue response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#issueRenewalLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#issueRenewalLetsencryptCertificate</a>
     */
    public SimpleResponse<Certificate> issueLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, String certificateRenewalId) throws DnsimpleException, IOException, InterruptedException;
}
