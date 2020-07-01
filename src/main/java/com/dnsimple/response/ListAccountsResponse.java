package com.dnsimple.response;

import com.dnsimple.data.Account;

import java.util.ArrayList;
import java.util.List;

public class ListAccountsResponse extends ApiResponse {
    private List<Account> data;

    public ListAccountsResponse() {
        this(new ArrayList<Account>());
    }

    public ListAccountsResponse(List<Account> data) {
        this.data = data;
    }

    public List<Account> getData() {
        return data;
    }
}
