package com.dnsimple.endpoints.http;

import com.dnsimple.Certificates;
import com.dnsimple.data.Certificate;
import com.dnsimple.data.CertificateBundle;
import com.dnsimple.data.CertificatePurchase;
import com.dnsimple.data.CertificateRenewal;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static com.dnsimple.endpoints.http.HttpMethod.POST;
import static java.util.Collections.emptyMap;

public class CertificatesEndpoint implements Certificates {
    private final HttpEndpointClient client;

    public CertificatesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Certificate> listCertificates(String accountId, String domainId) {
        return client.page(GET, accountId + "/domains/" + domainId + "/certificates", emptyMap(), null, Certificate.class);
    }

    public PaginatedResponse<Certificate> listCertificates(String accountId, String domainId, Map<String, Object> options) {
        return client.page(GET, accountId + "/domains/" + domainId + "/certificates", options, null, Certificate.class);
    }

    public SimpleResponse<Certificate> getCertificate(String accountId, String domainId, String certificateId) {
        return client.simple(GET, accountId + "/domains/" + domainId + "/certificates/" + certificateId, emptyMap(), null, Certificate.class);
    }

    public SimpleResponse<CertificateBundle> downloadCertificate(String accountId, String domainId, String certificateId) {
        return client.simple(GET, accountId + "/domains/" + domainId + "/certificates/" + certificateId + "/download", emptyMap(), null, CertificateBundle.class);
    }

    public SimpleResponse<CertificateBundle> getCertificatePrivateKey(String accountId, String domainId, String certificateId) {
        return client.simple(GET, accountId + "/domains/" + domainId + "/certificates/" + certificateId + "/private_key", emptyMap(), null, CertificateBundle.class);
    }

    public SimpleResponse<CertificatePurchase> purchaseLetsencryptCertificate(String accountId, String domainId, Map<String, Object> attributes) {
        return client.simple(POST, accountId + "/domains/" + domainId + "/certificates/letsencrypt", emptyMap(), attributes, CertificatePurchase.class);
    }

    public SimpleResponse<Certificate> issueLetsencryptCertificate(String accountId, String domainId, String certificatePurchaseId) {
        return client.simple(POST, accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificatePurchaseId + "/issue", emptyMap(), null, Certificate.class);
    }

    public SimpleResponse<CertificateRenewal> purchaseLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, Map<String, Object> attributes) {
        return client.simple(POST, accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificateId + "/renewals", emptyMap(), attributes, CertificateRenewal.class);
    }

    public SimpleResponse<Certificate> issueLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, String certificateRenewalId) {
        return client.simple(POST, accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificateId + "/renewals/" + certificateRenewalId + "/issue", emptyMap(), null, Certificate.class);
    }
}
