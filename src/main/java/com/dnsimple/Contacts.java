package com.dnsimple;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.dnsimple.response.ListContactsResponse;
import com.dnsimple.response.GetContactResponse;
import com.dnsimple.response.CreateContactResponse;
import com.dnsimple.response.UpdateContactResponse;
import com.dnsimple.response.DeleteContactResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

/**
 * Provides access to the DNSimple Contacts API.
 *
 * @see https://developer.dnsimple.com/v2/contacts
 */
public class Contacts {
  private Client client;

  protected Contacts(Client client) {
    this.client = client;
  }

  /**
   * Lists the contacts in the account.
   *
   * @see https://developer.dnsimple.com/v2/contacts/#list
   *
   * @param accountId The account ID
   * @return The list contacts response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListContactsResponse listContacts(String accountId) throws DnsimpleException, IOException {
    return listContacts(accountId, null);
  }

  /**
   * Lists the contacts in the account.
   *
   * @see https://developer.dnsimple.com/v2/contacts/#list
   *
   * @param accountId The account ID
   * @param options A Map of options to pass to the contacts API
   * @return The list contacts response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListContactsResponse listContacts(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/contacts", options);
    return (ListContactsResponse)client.parseResponse(response, ListContactsResponse.class);
  }

  /**
   * Get a specific contact associated to an account using the contacts's ID.
   *
   * @see https://developer.dnsimple.com/v2/contacts/#get
   *
   * @param accountId The account ID
   * @param contactId The contact ID
   * @return The get contact response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetContactResponse getContact(String accountId, String contactId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/contacts/" + contactId);
    return (GetContactResponse)client.parseResponse(response, GetContactResponse.class);
  }

  public CreateContactResponse createContact(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/contacts", attributes);
    return (CreateContactResponse)client.parseResponse(response, CreateContactResponse.class);
  }

  public UpdateContactResponse updateContact(String accountId, String contactId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.patch(accountId + "/contacts/" + contactId, attributes);
    return (UpdateContactResponse)client.parseResponse(response, UpdateContactResponse.class);
  }

  public DeleteContactResponse deleteContact(String accountId, String contactId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/contacts/" + contactId);
    return (DeleteContactResponse)client.parseResponse(response, DeleteContactResponse.class);
  }
}
