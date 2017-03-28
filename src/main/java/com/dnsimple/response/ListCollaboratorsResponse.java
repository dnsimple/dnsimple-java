package com.dnsimple.response;

import com.dnsimple.data.Collaborator;
import com.dnsimple.data.Pagination;

import java.util.List;

import com.google.api.client.util.Key;

public class ListCollaboratorsResponse extends ApiResponse {
  @Key("data")
  private List<Collaborator> data;
  @Key("pagination")
  private Pagination pagination;

  public List<Collaborator> getData() {
    return data;
  }

  public Pagination getPagination() {
    return pagination;
  }
}

