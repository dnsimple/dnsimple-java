package com.dnsimple.endpoints.test;

import com.dnsimple.Certificates;
import com.dnsimple.response.ListCertificatesResponse;
import com.dnsimple.response.GetCertificateResponse;
import com.dnsimple.response.DownloadCertificateResponse;
import com.dnsimple.response.GetCertificatePrivateKeyResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

public class CertificatesEndpoint implements Certificates {

  public ListCertificatesResponse listCertificates(String accountId, String domainId) throws DnsimpleException, IOException {
    return new ListCertificatesResponse();
  }

  public ListCertificatesResponse listCertificates(String accountId, String domainId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListCertificatesResponse();
  }

  public GetCertificateResponse getCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException {
    return new GetCertificateResponse();
  }

  public DownloadCertificateResponse downloadCertificate(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException {
    return new DownloadCertificateResponse();
  }

  public GetCertificatePrivateKeyResponse getCertificatePrivateKey(String accountId, String domainId, String certificateId) throws DnsimpleException, IOException {
    return new GetCertificatePrivateKeyResponse();
  }
}
