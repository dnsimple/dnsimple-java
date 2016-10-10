package com.dnsimple.response;

import com.dnsimple.data.Service;

import java.util.List;

import com.google.api.client.util.Key;

public class ListServicesResponse extends ApiResponse {
  @Key("data")
  private List<Service> data;

  public List<Service> getData() {
    return data;
  }

}
