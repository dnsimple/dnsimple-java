package com.dnsimple.response;

import com.dnsimple.data.Tld;
import com.dnsimple.data.Pagination;

import java.util.List;



public class ListTldsResponse extends ApiResponse {

  private List<Tld> data;


  private Pagination pagination;

    public List<Tld> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
