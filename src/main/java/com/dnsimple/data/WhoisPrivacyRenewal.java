package com.dnsimple.data;

public class WhoisPrivacyRenewal {

  private Integer id;


  private Integer domainId;


  private Integer whoisPrivacyId;


  private String state;


  private Boolean enabled;


  private String expiresOn;


  private String createdAt;


  private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public Integer getWhoisPrivacyId() {
        return whoisPrivacyId;
    }

    public String getState() {
        return state;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getExpiresOn() {
        return expiresOn;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
