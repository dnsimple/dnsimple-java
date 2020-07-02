package com.dnsimple.endpoints.http;

import com.dnsimple.Accounts;
import com.dnsimple.data.Account;
import com.dnsimple.response.ListResponse;

import static com.dnsimple.endpoints.http.HttpMethod.GET;
import static java.util.Collections.emptyMap;

public class AccountsEndpoint implements Accounts {
    private final HttpEndpointClient client;

    public AccountsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListResponse<Account> listAccounts() {
        return client.list(GET, "accounts", emptyMap(), null, Account.class);
    }
}
