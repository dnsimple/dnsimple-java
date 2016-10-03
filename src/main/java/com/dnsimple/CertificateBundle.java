package com.dnsimple;

import java.util.List;

import com.google.api.client.util.Key;

public class CertificateBundle {

  @Key("private_key")
  private String privateKey;

  @Key("server")
  private String serverCertificate;

  @Key("root")
  private String rootCertificate;

  @Key("chain")
  private List<String> intermediateCertificates;

  public String getPrivateKey() {
    return privateKey;
  }

  public String getServerCertificate() {
    return serverCertificate;
  }

  public String getRootCertificate() {
    return rootCertificate;
  }

  public List<String> getIntermediateCertificates() {
    return intermediateCertificates;
  }

}
