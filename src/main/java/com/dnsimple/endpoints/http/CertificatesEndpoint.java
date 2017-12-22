package com.dnsimple.endpoints.http;

import com.dnsimple.Certificates;
import com.dnsimple.response.*;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

public class CertificatesEndpoint implements Certificates {
  private HttpEndpointClient client;

  public CertificatesEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListCertificatesResponse listCertificates(String accountId, String domainId) throws DnsimpleException, IOException {
    return listCertificates(accountId, domainId, null);
  }

  public ListCertificatesResponse listCertificates(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/certificates", options);
    return (ListCertificatesResponse)client.parseResponse(response, ListCertificatesResponse.class);
  }

  public GetCertificateResponse getCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/certificates/" + certificateId);
    return (GetCertificateResponse)client.parseResponse(response, GetCertificateResponse.class);
  }

  public DownloadCertificateResponse downloadCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/certificates/" + certificateId + "/download");
    return (DownloadCertificateResponse)client.parseResponse(response, DownloadCertificateResponse.class);
  }

  public GetCertificatePrivateKeyResponse getCertificatePrivateKey(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/certificates/" + certificateId + "/private_key");
    return (GetCertificatePrivateKeyResponse)client.parseResponse(response, GetCertificatePrivateKeyResponse.class);
  }

  public PurchaseLetsencryptResponse purchaseLetsencryptCertificate(String accountId, String domainId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId + "/certificates/letsencrypt", attributes);
    return (PurchaseLetsencryptResponse)client.parseResponse(response, PurchaseLetsencryptResponse.class);
  }

  public PurchaseLetsencryptRenewalResponse purchaseLetsencryptCertificateRenewal(String accountId, String domainId, String certificateId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId + "/certificates/letsencrypt/" + certificateId + "/renewals", attributes);
    return (PurchaseLetsencryptRenewalResponse)client.parseResponse(response, PurchaseLetsencryptRenewalResponse.class);
  }
}
