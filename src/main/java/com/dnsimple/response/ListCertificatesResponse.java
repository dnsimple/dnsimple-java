package com.dnsimple.response;

import com.dnsimple.data.Certificate;
import com.dnsimple.data.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListCertificatesResponse extends ApiResponse {
  @Key("data")
  private List<Certificate> data;

  @Key("pagination")
  private Pagination pagination;

  public List<Certificate> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
