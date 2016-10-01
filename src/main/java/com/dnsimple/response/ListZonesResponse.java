package com.dnsimple.response;

import com.dnsimple.Zone;
import com.dnsimple.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListZonesResponse extends ApiResponse {
  @Key("data")
  private List<Zone> data;

  @Key("pagination")
  private Pagination pagination;

  public List<Zone> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
