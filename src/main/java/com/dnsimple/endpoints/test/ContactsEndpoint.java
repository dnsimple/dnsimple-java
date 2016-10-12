package com.dnsimple.endpoints.test;

import com.dnsimple.Contacts;
import com.dnsimple.response.ListContactsResponse;
import com.dnsimple.response.GetContactResponse;
import com.dnsimple.response.CreateContactResponse;
import com.dnsimple.response.UpdateContactResponse;
import com.dnsimple.response.DeleteContactResponse;

import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ContactsEndpoint implements Contacts {

  public ListContactsResponse listContacts(String accountId) throws DnsimpleException, IOException {
    return new ListContactsResponse();
  }

  public ListContactsResponse listContacts(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListContactsResponse();
  }

  public GetContactResponse getContact(String accountId, String contactId) throws DnsimpleException, IOException {
    return new GetContactResponse();
  }

  public CreateContactResponse createContact(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new CreateContactResponse();
  }

  public UpdateContactResponse updateContact(String accountId, String contactId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new UpdateContactResponse();
  }

  public DeleteContactResponse deleteContact(String accountId, String contactId) throws DnsimpleException, IOException {
    return new DeleteContactResponse();
  }

}

