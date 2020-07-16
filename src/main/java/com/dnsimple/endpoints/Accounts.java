package com.dnsimple.endpoints;

import com.dnsimple.data.Account;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.ListResponse;

import static com.dnsimple.http.HttpMethod.GET;

/**
 * Provides access to the DNSimple Accounts API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/accounts">https://developer.dnsimple.com/v2/accounts</a>
 */
public final class Accounts {
    private final HttpEndpointClient client;

    public Accounts(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists the accounts the authenticated entity has access to.
     *
     * @return The list accounts response
     * @see <a href="https://developer.dnsimple.com/v2/accounts#list">https://developer.dnsimple.com/v2/accounts#list</a>
     */
    public ListResponse<Account> listAccounts() {
        return client.list(GET, "accounts", ListOptions.empty(), null, Account.class);
    }
}
