package com.dnsimple.endpoints.http;

import com.dnsimple.Certificates;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.util.Map;

public class CertificatesEndpoint implements Certificates {
    private HttpEndpointClient client;

    public CertificatesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListCertificatesResponse listCertificates(String accountId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return listCertificates(accountId, domainId, null);
    }

    public ListCertificatesResponse listCertificates(String accountId, String domainId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListCertificatesResponse) client.get(accountId + "/domains/" + domainId + "/certificates", options, ListCertificatesResponse.class);
    }

    public GetCertificateResponse getCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException {
        return (GetCertificateResponse) client.get(accountId + "/domains/" + domainId + "/certificates/" + certificateId, null, GetCertificateResponse.class);
    }

    public DownloadCertificateResponse downloadCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException {
        return (DownloadCertificateResponse) client.get(accountId + "/domains/" + domainId + "/certificates/" + certificateId + "/download", null, DownloadCertificateResponse.class);
    }

    public GetCertificatePrivateKeyResponse getCertificatePrivateKey(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException, InterruptedException {
        return (GetCertificatePrivateKeyResponse) client.get(accountId + "/domains/" + domainId + "/certificates/" + certificateId + "/private_key", null, GetCertificatePrivateKeyResponse.class);
    }

    public PurchaseLetsencryptResponse purchaseLetsencryptCertificate(String accountId, String domainId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (PurchaseLetsencryptResponse) client.post(accountId + "/domains/" + domainId + "/certificates/letsencrypt", attributes, null, PurchaseLetsencryptResponse.class);
    }

    public IssueLetsencryptResponse issueLetsencryptCertificate(String accountId, String domainId, String certificatePurchaseId) throws DnsimpleException, IOException, InterruptedException {
        return (IssueLetsencryptResponse) client.post(accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificatePurchaseId + "/issue", null, null, IssueLetsencryptResponse.class);
    }

    public PurchaseLetsencryptRenewalResponse purchaseLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (PurchaseLetsencryptRenewalResponse) client.post(accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificateId + "/renewals", attributes, null, PurchaseLetsencryptRenewalResponse.class);
    }

    public IssueLetsencryptRenewalResponse issueLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, String certificateRenewalId) throws DnsimpleException, IOException, InterruptedException {
        return (IssueLetsencryptRenewalResponse) client.post(accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificateId + "/renewals/" + certificateRenewalId + "/issue", null, null, IssueLetsencryptRenewalResponse.class);
    }
}
