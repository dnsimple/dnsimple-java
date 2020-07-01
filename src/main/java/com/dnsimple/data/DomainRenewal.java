package com.dnsimple.data;

public class DomainRenewal {

  private Integer id;


  private Integer domainId;


  private Integer period;


  private String state;


  private String createdAt;


  private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public Integer getPeriod() {
        return period;
    }

    public String getState() {
        return state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
