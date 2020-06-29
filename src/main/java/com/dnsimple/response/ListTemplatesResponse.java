package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.Template;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListTemplatesResponse extends ApiResponse {
    private final List<Template> data;
    private final Pagination pagination;

    public ListTemplatesResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListTemplatesResponse(List<Template> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<Template> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
