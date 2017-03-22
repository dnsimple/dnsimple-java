package com.dnsimple.response;

import com.dnsimple.data.Service;
import com.dnsimple.data.Pagination;

import java.util.List;
import java.util.ArrayList;

import com.google.api.client.util.Key;

public class AppliedServicesResponse extends ApiResponse {
  @Key("data")
  private List<Service> data;

  @Key("pagination")
  private Pagination pagination;

  public AppliedServicesResponse() {
    this(new ArrayList<Service>());
  }

  public AppliedServicesResponse(List<Service> data) {
    this(data, new Pagination());
  }

  public AppliedServicesResponse(List<Service> data, Pagination pagination) {
    this.data = data;
    this.pagination = pagination;
  }

  public List<Service> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }

}
