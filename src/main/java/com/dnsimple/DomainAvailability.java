package com.dnsimple;

import com.google.api.client.util.Key;

public class DomainAvailability {
  @Key("domain")
  private String domainName;

  @Key("available")
  private Boolean available;

  @Key("premium")
  private Boolean premium;

  public String getDomainName() {
    return domainName;
  }

  public Boolean getAvailable() {
    return available;
  }

  public Boolean getPremium() {
    return premium;
  }
}
