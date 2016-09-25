package com.dnsimple;

import com.google.api.client.util.Key;

public class Tld {
  @Key("tld")
  private String tld;

  @Key("tld_type")
  private Integer tldType;

  @Key("whois_privacy")
  private Boolean whoisPrivacy;

  @Key("autorenew_only")
  private Boolean autorenewOnly;

  @Key("idn")
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
