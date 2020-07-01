package com.dnsimple.response;

import com.dnsimple.data.Pagination;
import com.dnsimple.data.TemplateRecord;

import java.util.List;

public class ListTemplateRecordsResponse extends ApiResponse {
    private List<TemplateRecord> data;
    private Pagination pagination;

    public List<TemplateRecord> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
