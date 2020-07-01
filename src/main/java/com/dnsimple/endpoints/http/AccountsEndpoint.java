package com.dnsimple.endpoints.http;

import com.dnsimple.Accounts;
import com.dnsimple.data.Account;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ListResponse;

import java.io.IOException;

public class AccountsEndpoint implements Accounts {
    private final HttpEndpointClient client;

    public AccountsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListResponse<Account> listAccounts() throws DnsimpleException, IOException, InterruptedException {
        return client.getList("accounts", null, Account.class);
    }
}
