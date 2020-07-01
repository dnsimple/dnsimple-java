package com.dnsimple.data;

public class CertificateRenewal {

  private Integer id;


  private Integer oldCertificateId;


  private Integer newCertificateId;


  private String createdAt;


  private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getOldCertificateId() {
        return oldCertificateId;
    }

    public Integer getNewCertificateId() {
        return newCertificateId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
