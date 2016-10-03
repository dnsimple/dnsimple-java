package com.dnsimple.response;

import com.dnsimple.Service;
import com.dnsimple.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class AppliedServicesResponse extends ApiResponse {
  @Key("data")
  private List<Service> data;

  @Key("pagination")
  private Pagination pagination;

  public List<Service> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }

}
