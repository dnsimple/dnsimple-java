package com.dnsimple.response;

import com.dnsimple.data.Collaborator;
import com.dnsimple.data.Pagination;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListCollaboratorsResponse extends ApiResponse {
    private final List<Collaborator> data;
    private final Pagination pagination;

    public ListCollaboratorsResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListCollaboratorsResponse(List<Collaborator> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Collaborator> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}

