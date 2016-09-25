package com.dnsimple.response;

import com.dnsimple.Push;
import com.dnsimple.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListPushesResponse extends ApiResponse {
  @Key("data")
  private List<Push> data;
  @Key("pagination")
  private Pagination pagination;

  public List<Push> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
