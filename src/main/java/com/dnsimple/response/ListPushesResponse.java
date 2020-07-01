package com.dnsimple.response;

import com.dnsimple.data.Push;
import com.dnsimple.data.Pagination;

import java.util.List;



public class ListPushesResponse extends ApiResponse {

  private List<Push> data;

  private Pagination pagination;

    public List<Push> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
