package com.dnsimple.data;

import com.google.api.client.util.Key;

public class CertificatePurchase {
  @Key("id")
  private Integer id;

  @Key("certificate_id")
  private Integer certificateId;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;


  public Integer getId() {
    return id;
  }

  public Integer getCertificateId() {
    return certificateId;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}
