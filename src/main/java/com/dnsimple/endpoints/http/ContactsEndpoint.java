package com.dnsimple.endpoints.http;

import com.dnsimple.Contacts;
import com.dnsimple.response.ListContactsResponse;
import com.dnsimple.response.GetContactResponse;
import com.dnsimple.response.CreateContactResponse;
import com.dnsimple.response.UpdateContactResponse;
import com.dnsimple.response.DeleteContactResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ContactsEndpoint implements Contacts {
  private HttpEndpointClient client;

  public ContactsEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListContactsResponse listContacts(String accountId) throws DnsimpleException, IOException {
    return listContacts(accountId, null);
  }

  public ListContactsResponse listContacts(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/contacts", options);
    return (ListContactsResponse)client.parseResponse(response, ListContactsResponse.class);
  }

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
