package com.dnsimple.response;

import com.dnsimple.data.Collaborator;
import com.dnsimple.data.Pagination;

import java.util.List;



public class ListCollaboratorsResponse extends ApiResponse {

  private List<Collaborator> data;

  private Pagination pagination;

    public List<Collaborator> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

