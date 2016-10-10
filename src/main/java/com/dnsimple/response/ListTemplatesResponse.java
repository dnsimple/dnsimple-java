package com.dnsimple.response;

import com.dnsimple.data.Template;
import com.dnsimple.data.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListTemplatesResponse extends ApiResponse {
  @Key("data")
  private List<Template> data;

  @Key("pagination")
  private Pagination pagination;

  public List<Template> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
