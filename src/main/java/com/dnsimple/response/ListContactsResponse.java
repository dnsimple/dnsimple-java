package com.dnsimple.response;

import com.dnsimple.data.Contact;
import com.dnsimple.data.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListContactsResponse extends ApiResponse {
  @Key("data")
  private List<Contact> data;

  @Key("pagination")
  private Pagination pagination;

  public List<Contact> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}
