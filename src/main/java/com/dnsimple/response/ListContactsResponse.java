package com.dnsimple.response;

import com.dnsimple.data.Contact;
import com.dnsimple.data.Pagination;

import java.util.List;
import java.util.ArrayList;



public class ListContactsResponse extends ApiResponse {

  private List<Contact> data;


  private Pagination pagination;

    public ListContactsResponse() {
    this(new ArrayList<Contact>());
  }

  public ListContactsResponse(List<Contact> data) {
    this(data, new Pagination());
    }

    public ListContactsResponse(List<Contact> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Contact> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
