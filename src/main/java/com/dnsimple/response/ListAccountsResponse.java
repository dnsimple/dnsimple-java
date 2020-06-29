package com.dnsimple.response;

import com.dnsimple.data.Account;

import java.util.Collections;
import java.util.List;

public class ListAccountsResponse extends ApiResponse {
    private final List<Account> data;

    public ListAccountsResponse() {
        data = Collections.emptyList();
    }

    public ListAccountsResponse(List<Account> data) {
        this.data = data;
    }

    public List<Account> getData() {
        return data;
    }
}
