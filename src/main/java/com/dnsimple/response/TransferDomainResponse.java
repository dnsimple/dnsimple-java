package com.dnsimple.response;

import com.dnsimple.data.DomainTransfer;
import com.google.api.client.util.Key;

public class TransferDomainResponse extends ApiResponse {
  @Key("data")
  private DomainTransfer data;

  public DomainTransfer getData() {
    return data;
  }
}
