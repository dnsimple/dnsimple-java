package com.dnsimple.response;

import com.dnsimple.data.Domain;
import com.dnsimple.data.Pagination;

import java.util.List;
import java.util.ArrayList;



public class ListDomainsResponse extends ApiResponse {

  private List<Domain> data;


  private Pagination pagination;

    public ListDomainsResponse() {
    this(new ArrayList<Domain>());
  }

  public ListDomainsResponse(List<Domain> data) {
    this(data, new Pagination());
    }

    public ListDomainsResponse(List<Domain> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Domain> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
