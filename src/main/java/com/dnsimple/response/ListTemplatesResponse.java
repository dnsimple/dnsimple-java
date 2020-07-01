package com.dnsimple.response;

import com.dnsimple.data.Template;
import com.dnsimple.data.Pagination;

import java.util.List;



public class ListTemplatesResponse extends ApiResponse {

  private List<Template> data;


  private Pagination pagination;

    public List<Template> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
