package com.dnsimple.endpoints.http;

import com.dnsimple.Certificates;
import com.dnsimple.data.Certificate;
import com.dnsimple.data.CertificateBundle;
import com.dnsimple.data.CertificatePurchase;
import com.dnsimple.data.CertificateRenewal;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

public class CertificatesEndpoint implements Certificates {
    private final HttpEndpointClient client;

    public CertificatesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Certificate> listCertificates(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return listCertificates(accountId, domainId, null);
    }

    public PaginatedResponse<Certificate> listCertificates(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.getPage(accountId + "/domains/" + domainId + "/certificates", options, Certificate.class);
    }

    public SimpleResponse<Certificate> getCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/domains/" + domainId + "/certificates/" + certificateId, null, Certificate.class);
    }

    public SimpleResponse<CertificateBundle> downloadCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/domains/" + domainId + "/certificates/" + certificateId + "/download", null, CertificateBundle.class);
    }

    public SimpleResponse<CertificateBundle> getCertificatePrivateKey(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/domains/" + domainId + "/certificates/" + certificateId + "/private_key", null, CertificateBundle.class);
    }

    public SimpleResponse<CertificatePurchase> purchaseLetsencryptCertificate(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/domains/" + domainId + "/certificates/letsencrypt", attributes, null, CertificatePurchase.class);
    }

    public SimpleResponse<Certificate> issueLetsencryptCertificate(String accountId, String domainId, String certificatePurchaseId) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificatePurchaseId + "/issue", null, null, Certificate.class);
    }

    public SimpleResponse<CertificateRenewal> purchaseLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificateId + "/renewals", attributes, null, CertificateRenewal.class);
    }

    public SimpleResponse<Certificate> issueLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, String certificateRenewalId) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificateId + "/renewals/" + certificateRenewalId + "/issue", null, null, Certificate.class);
    }
}
