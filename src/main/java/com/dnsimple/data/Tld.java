package com.dnsimple.data;

public class Tld {

  private String tld;


  private Integer tldType;


  private Boolean whoisPrivacy;


  private Boolean autorenewOnly;


  private Boolean idn;

    public String getTld() {
        return tld;
    }

    public Integer getTldType() {
        return tldType;
    }

    public Boolean getWhoisPrivacy() {
        return whoisPrivacy;
    }

    public Boolean getAutorenewOnly() {
        return autorenewOnly;
    }

    public Boolean getIdn() {
        return idn;
    }
}
