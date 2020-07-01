package com.dnsimple.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CertificateBundle {

  private String privateKey;

  @SerializedName("server")
  private String serverCertificate;


  @SerializedName("root")
  private String rootCertificate;

  @SerializedName("chain")
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
