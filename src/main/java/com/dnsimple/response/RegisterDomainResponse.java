package com.dnsimple.response;

import com.dnsimple.data.DomainRegistration;
import com.google.api.client.util.Key;

public class RegisterDomainResponse extends ApiResponse {
  @Key("data")
  private DomainRegistration data;

  public DomainRegistration getData() {
    return data;
  }
}

