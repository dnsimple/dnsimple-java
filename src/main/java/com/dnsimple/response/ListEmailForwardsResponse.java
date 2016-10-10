package com.dnsimple.response;

import com.dnsimple.data.EmailForward;
import com.dnsimple.data.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListEmailForwardsResponse extends ApiResponse {
  @Key("data")
  private List<EmailForward> data;
  @Key("pagination")
  private Pagination pagination;

  public List<EmailForward> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
