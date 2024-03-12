package com.dnsimple.endpoints;

import com.dnsimple.data.*;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.CertificatePurchaseOptions;
import com.dnsimple.request.CertificateRenewalPurchaseOptions;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import static com.dnsimple.http.HttpMethod.GET;
import static com.dnsimple.http.HttpMethod.POST;

/**
 * Provides access to the DNSimple Certificates API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/certificates">https://developer.dnsimple.com/v2/certificates</a>
 */
public class Certificates {
    private final HttpEndpointClient client;

    public Certificates(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * List certificates for a domain in the account.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @return The list certificates response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#listCertificates">https://developer.dnsimple.com/v2/certificates/#listCertificates</a>
     */
    public PaginatedResponse<Certificate> listCertificates(Number account, String domain) {
        return client.page(GET, account + "/domains/" + domain + "/certificates", ListOptions.empty(), null, Certificate.class);
    }

    /**
     * List certificates for a domain in the account.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options for the list request
     * @return The list certificates response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#listCertificates">https://developer.dnsimple.com/v2/certificates/#listCertificates</a>
     */
    public PaginatedResponse<Certificate> listCertificates(Number account, String domain, ListOptions options) {
        return client.page(GET, account + "/domains/" + domain + "/certificates", options, null, Certificate.class);
    }

    /**
     * Get the details of a certificate.
     *
     * @param account       The account ID
     * @param domain        The domain name or ID
     * @param certificateId The certificate ID
     * @return The get certificate response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#getCertificate">https://developer.dnsimple.com/v2/certificates/#getCertificate</a>
     */
    public SimpleResponse<Certificate> getCertificate(Number account, String domain, Number certificateId) {
        return client.simple(GET, account + "/domains/" + domain + "/certificates/" + certificateId, ListOptions.empty(), null, Certificate.class);
    }

    /**
     * Get the PEM-encoded certificate, aNumber with the root certificate and intermediate chain.
     *
     * @param account       The account ID
     * @param domain        The domain name or ID
     * @param certificateId The certificate ID
     * @return The download certificate response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#downloadCertificate">https://developer.dnsimple.com/v2/certificates/#downloadCertificate</a>
     */
    public SimpleResponse<CertificateBundle> downloadCertificate(Number account, String domain, Number certificateId) {
        return client.simple(GET, account + "/domains/" + domain + "/certificates/" + certificateId + "/download", ListOptions.empty(), null, CertificateBundle.class);
    }

    /**
     * Get the PEM-encoded certificate private key.
     *
     * @param account       The account ID
     * @param domain        The domain name or ID
     * @param certificateId The certificate ID
     * @return The get certificate private key response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#getCertificatePrivateKey">https://developer.dnsimple.com/v2/certificates/#getCertificatePrivateKey</a>
     */
    public SimpleResponse<CertificatePrivateKey> getCertificatePrivateKey(Number account, String domain, Number certificateId) {
        return client.simple(GET, account + "/domains/" + domain + "/certificates/" + certificateId + "/private_key", ListOptions.empty(), null, CertificatePrivateKey.class);
    }

    /**
     * Purchase a Let's Encrypt certificate.
     * <p>
     * This method creates a new purchase order. The order ID should be used to request the issuance of the certificate
     * using `#purchase_letsencrypt_certificate`.
     *
     * @param account The account ID
     * @param domain  The domain name or ID
     * @param options The options for the certificate purchase
     * @return The Let's Encrypt purchase response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#purchaseLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#purchaseLetsencryptCertificate</a>
     */
    public SimpleResponse<CertificatePurchase> purchaseLetsencryptCertificate(Number account, String domain, CertificatePurchaseOptions options) {
        return client.simple(POST, account + "/domains/" + domain + "/certificates/letsencrypt", ListOptions.empty(), options, CertificatePurchase.class);
    }

    /**
     * Issue a pending Let's Encrypt certificate order.
     * <p>
     * Note that the issuance process is async. A successful response means the issuance
     * request has been successfully acknowledged and queued for processing.
     *
     * @param account               The account ID
     * @param domain                The domain name or ID
     * @param certificatePurchaseId The ID of the purchase order returned by purchaseLetsencryptCertificate
     * @return The Let's Encrypt issue response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#issueLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#issueLetsencryptCertificate</a>
     */
    public SimpleResponse<Certificate> issueLetsencryptCertificate(Number account, String domain, Number certificatePurchaseId) {
        return client.simple(POST, account + "/domains/" + domain + "/certificates/letsencrypt/" + certificatePurchaseId + "/issue", ListOptions.empty(), null, Certificate.class);
    }

    /**
     * Purchase a Let's Encrypt certificate renewal.
     * <p>
     * This method creates a new renewal order. The order ID should be used to request the issuance of the certificate
     * using `#issue_letsencrypt_certificate_renewal`.
     *
     * @param account       The account ID
     * @param domain        The domain name or ID
     * @param certificateId The certificate ID
     * @param options       The options for the certificate renewal
     * @return The Let's Encrypt purchase renewal response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#purchaseRenewalLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#purchaseRenewalLetsencryptCertificate</a>
     */
    public SimpleResponse<CertificateRenewal> purchaseLetsencryptCertificateRenewal(Number account, String domain, Number certificateId, CertificateRenewalPurchaseOptions options) {
        return client.simple(POST, account + "/domains/" + domain + "/certificates/letsencrypt/" + certificateId + "/renewals", ListOptions.empty(), options, CertificateRenewal.class);
    }

    /**
     * Issue a pending Let's Encrypt certificate renewal order.
     * <p>
     * Note that the issuance process is async. A successful response means the issuance
     * request has been successfully acknowledged and queued for processing.
     *
     * @param account              The account ID
     * @param domain               The domain name or ID
     * @param certificateId        The domain name or ID
     * @param certificateRenewalId The ID of the purchase order returned by purchaseLetsencryptCertificateRenewal
     * @return The Let's Encrypt issue response
     * @see <a href="https://developer.dnsimple.com/v2/certificates/#issueRenewalLetsencryptCertificate">https://developer.dnsimple.com/v2/certificates/#issueRenewalLetsencryptCertificate</a>
     */
    public SimpleResponse<Certificate> issueLetsencryptCertificateRenewal(Number account, String domain, Number certificateId, Number certificateRenewalId) {
        return client.simple(POST, account + "/domains/" + domain + "/certificates/letsencrypt/" + certificateId + "/renewals/" + certificateRenewalId + "/issue", ListOptions.empty(), null, Certificate.class);
    }
}
