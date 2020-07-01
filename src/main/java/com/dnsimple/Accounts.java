package com.dnsimple;

import com.dnsimple.data.Account;
import com.dnsimple.response.ListResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;

public interface Accounts {
    /**
     * Lists the accounts the authenticated entity has access to.
     *
     * @return The list accounts response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/accounts#list">https://developer.dnsimple.com/v2/accounts#list</a>
     */
    public ListResponse<Account> listAccounts() throws DnsimpleException, IOException, InterruptedException;
}
