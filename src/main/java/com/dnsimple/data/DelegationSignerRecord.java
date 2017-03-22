package com.dnsimple.data;

import com.google.api.client.util.Key;

public class DelegationSignerRecord {
  @Key("id")
  private Integer id;

  @Key("domain_id")
  private Integer domainId;

  @Key("algorithm")
  private String algorithm;

  @Key("digest")
  private String digest;

  @Key("digest_type")
  private String digestType;

  @Key("keytag")
  private String keytag;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  public Integer getId() {
    return id;
  }

  public Integer getDomainId() {
    return domainId;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public String getDigest() {
    return digest;
  }

  public String getDigestType() {
    return digestType;
  }

  public String getKeytag() {
    return keytag;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }
}
