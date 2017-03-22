package com.dnsimple.response;

import com.dnsimple.data.Domain;
import com.dnsimple.data.Pagination;

import java.util.List;
import java.util.ArrayList;

import com.google.api.client.util.Key;

public class ListDomainsResponse extends ApiResponse {
  @Key("data")
  private List<Domain> data;

  @Key("pagination")
  private Pagination pagination;

  public ListDomainsResponse() {
    this(new ArrayList<Domain>());
  }

  public ListDomainsResponse(List<Domain> data) {
    this(data, new Pagination());
  }

  public ListDomainsResponse(List<Domain> data, Pagination pagination) {
    this.data = data;
    this.pagination = pagination;
  }

  public List<Domain> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
