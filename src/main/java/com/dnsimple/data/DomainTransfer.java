package com.dnsimple.data;

public class DomainTransfer {

  private Integer id;


  private Integer domainId;


  private Integer registrantId;


  private String state;


  private boolean autoRenew;


  private boolean whoisPrivacy;


  private String statusDescription;


  private String createdAt;


  private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public Integer getRegistrantId() {
        return registrantId;
    }

    public String getState() {
        return state;
    }

    public boolean hasAutoRenew() {
        return autoRenew;
    }

    public boolean hasWhoisPrivacy() {
        return whoisPrivacy;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
