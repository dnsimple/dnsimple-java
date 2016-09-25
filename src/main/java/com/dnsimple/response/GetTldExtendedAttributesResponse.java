package com.dnsimple.response;

import com.dnsimple.TldExtendedAttribute;
import java.util.List;

import com.google.api.client.util.Key;

public class GetTldExtendedAttributesResponse extends ApiResponse {
  @Key("data")
  private List<TldExtendedAttribute> data;

  public List<TldExtendedAttribute> getData() {
    return data;
  }
}
