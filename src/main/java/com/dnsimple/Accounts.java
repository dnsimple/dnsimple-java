package com.dnsimple;

import com.dnsimple.data.Account;
import com.dnsimple.response.ListResponse;

public interface Accounts {
    /**
     * Lists the accounts the authenticated entity has access to.
     *
     * @return The list accounts response
     * @see <a href="https://developer.dnsimple.com/v2/accounts#list">https://developer.dnsimple.com/v2/accounts#list</a>
     */
    ListResponse<Account> listAccounts();
}
