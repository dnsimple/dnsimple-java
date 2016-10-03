package com.dnsimple;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.dnsimple.response.ListCertificatesResponse;
import com.dnsimple.response.GetCertificateResponse;
import com.dnsimple.response.DownloadCertificateResponse;
import com.dnsimple.response.GetCertificatePrivateKeyResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

/**
 * Provides access to the DNSimple Certificates API.
 *
 * @see https://developer.dnsimple.com/v2/domains/certificates
 */
public class Certificates {
  private Client client;

  protected Certificates(Client client) {
    this.client = client;
  }

  // Domains

  /**
   * Lists the domains in the account.
   *
   * @see https://developer.dnsimple.com/v2/domains/certificates/#list
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @return The list certificates response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCertificatesResponse listCertificates(String accountId, String domainId) throws DnsimpleException, IOException {
    return listCertificates(accountId, domainId, null);
  }

  /**
   * Lists the certificates in the domain.
   *
   * @see https://developer.dnsimple.com/v2/domains/certificates/#list
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param options A Map of options to pass to the certificates API
   * @return The list certificates response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListCertificatesResponse listCertificates(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/domains/" + domainId + "/certificates", options);
    return (ListCertificatesResponse)client.parseResponse(response, ListCertificatesResponse.class);
  }

  /**
   * Get a specific certificate associated to a domain using the certificate's ID.
   *
   * @see https://developer.dnsimple.com/v2/domains/certificates/#get
   *
   * @param accountId The account ID
   * @param domainId The domain name or ID
   * @param certificateId The certificate ID
   * @return The get certificate response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
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
}
