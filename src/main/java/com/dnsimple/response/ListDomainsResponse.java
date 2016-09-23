package com.dnsimple.response;

import com.dnsimple.Domain;
import com.dnsimple.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListDomainsResponse implements ApiResponse {
  @Key("data")
  private List<Domain> data;
  @Key("pagination")
  private Pagination pagination;

  public List<Domain> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
