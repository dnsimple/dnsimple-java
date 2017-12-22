package com.dnsimple.data;

import com.google.api.client.util.Key;

public class CertificateRenewal {
  @Key("id")
  private Integer id;

  @Key("old_certificate_id")
  private Integer oldCertificateId;

  @Key("new_certificate_id")
  private Integer newCertificateId;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
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
