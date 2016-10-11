package com.dnsimple;

import com.dnsimple.response.ListAccountsResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;

public interface Accounts {

  /**
   * Lists the accounts the authenticated entity has access to.
   *
   * @see <a href="https://developer.dnsimple.com/v2/accounts#list">https://developer.dnsimple.com/v2/accounts#list</a>
   *
   * @return The list accounts response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListAccountsResponse listAccounts() throws DnsimpleException, IOException;

}
