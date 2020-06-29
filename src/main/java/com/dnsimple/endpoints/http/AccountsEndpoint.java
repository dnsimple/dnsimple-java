package com.dnsimple.endpoints.http;

import com.dnsimple.Accounts;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ListAccountsResponse;

import java.io.IOException;

public class AccountsEndpoint implements Accounts {
    private HttpEndpointClient client;

    public AccountsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListAccountsResponse listAccounts() throws DnsimpleException, IOException {
        return (ListAccountsResponse) client.get("accounts", null, ListAccountsResponse.class);
    }
}
