package com.dnsimple.response;

import com.dnsimple.data.Contact;
import com.dnsimple.data.Pagination;

import java.util.List;

import static java.util.Collections.emptyList;

public class ListContactsResponse extends ApiResponse {
    private final List<Contact> data;
    private final Pagination pagination;

    public ListContactsResponse() {
        data = emptyList();
        pagination = Pagination.empty();
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
