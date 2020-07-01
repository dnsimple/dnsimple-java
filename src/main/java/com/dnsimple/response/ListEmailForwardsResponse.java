package com.dnsimple.response;

import com.dnsimple.data.EmailForward;
import com.dnsimple.data.Pagination;

import java.util.List;

public class ListEmailForwardsResponse extends ApiResponse {
    private List<EmailForward> data;
    private Pagination pagination;

    public List<EmailForward> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }
}
