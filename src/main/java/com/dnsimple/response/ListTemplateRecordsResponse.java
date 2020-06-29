package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.TemplateRecord;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListTemplateRecordsResponse extends ApiResponse {
    private final List<TemplateRecord> data;
    private final Pagination pagination;

    public ListTemplateRecordsResponse() {
        data = emptyList();
        pagination = Pagination.empty();
    }

    public ListTemplateRecordsResponse(List<TemplateRecord> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<TemplateRecord> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
