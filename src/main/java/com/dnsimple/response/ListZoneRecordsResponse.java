package com.dnsimple.response;

import com.dnsimple.data.ZoneRecord;
import com.dnsimple.data.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListZoneRecordsResponse extends ApiResponse {
  @Key("data")
  private List<ZoneRecord> data;

  @Key("pagination")
  private Pagination pagination;

  public List<ZoneRecord> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
