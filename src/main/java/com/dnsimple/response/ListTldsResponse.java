package com.dnsimple.response;

import com.dnsimple.data.Tld;
import com.dnsimple.data.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListTldsResponse extends ApiResponse {
  @Key("data")
  private List<Tld> data;

  @Key("pagination")
  private Pagination pagination;

  public List<Tld> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
