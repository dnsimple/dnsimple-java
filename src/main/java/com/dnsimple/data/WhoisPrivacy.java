package com.dnsimple.data;

public class WhoisPrivacy {

  private Integer id;


  private Integer domainId;


  private String expiresOn;


  private Boolean enabled;


  private String createdAt;


  private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public String getExpiresOn() {
        return expiresOn;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
