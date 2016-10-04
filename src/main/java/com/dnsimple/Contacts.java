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
 * @see <a href="https://developer.dnsimple.com/v2/contacts">https://developer.dnsimple.com/v2/contacts</a>
 */
public class Contacts {
  private Client client;

  protected Contacts(Client client) {
    this.client = client;
  }

  /**
   * Lists the contacts in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/contacts/#list">https://developer.dnsimple.com/v2/contacts/#list</a>
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
   * @see <a href="https://developer.dnsimple.com/v2/contacts/#list">https://developer.dnsimple.com/v2/contacts/#list</a>
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
   * @see <a href="https://developer.dnsimple.com/v2/contacts/#get">https://developer.dnsimple.com/v2/contacts/#get</a>
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

  /**
   * Create a contact in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/contacts/#create">https://developer.dnsimple.com/v2/contacts/#create</a>
   *
   * @param accountId The account ID
   * @param attributes A map of attributes to contruct the contact
   * @return The create contact response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public CreateContactResponse createContact(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/contacts", attributes);
    return (CreateContactResponse)client.parseResponse(response, CreateContactResponse.class);
  }

  /**
   * Update a contact in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/contacts/#update">https://developer.dnsimple.com/v2/contacts/#update</a>
   *
   * @param accountId The account ID
   * @param contactId The contact ID
   * @param attributes A map of attributes to update the contact
   * @return The update contact response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public UpdateContactResponse updateContact(String accountId, String contactId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.patch(accountId + "/contacts/" + contactId, attributes);
    return (UpdateContactResponse)client.parseResponse(response, UpdateContactResponse.class);
  }

  /**
   * Delete a contact from the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/contacts/#delete">https://developer.dnsimple.com/v2/contacts/#delete</a>
   *
   * @param accountId The account ID
   * @param contactId The contact ID
   * @return The delete contact response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DeleteContactResponse deleteContact(String accountId, String contactId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/contacts/" + contactId);
    return (DeleteContactResponse)client.parseResponse(response, DeleteContactResponse.class);
  }
}
