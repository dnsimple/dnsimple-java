package com.dnsimple.endpoints.http;

import com.dnsimple.Contacts;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.util.Map;

public class ContactsEndpoint implements Contacts {
    private HttpEndpointClient client;

    public ContactsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListContactsResponse listContacts(String accountId) throws DnsimpleException, IOException {
        return listContacts(accountId, null);
    }

    public ListContactsResponse listContacts(String accountId, Map<String, Object> options) throws DnsimpleException, IOException {
        return (ListContactsResponse) client.get(accountId + "/contacts", options, ListContactsResponse.class);
    }

    public GetContactResponse getContact(String accountId, String contactId) throws DnsimpleException, IOException {
        return (GetContactResponse) client.get(accountId + "/contacts/" + contactId, null, GetContactResponse.class);
    }

    public CreateContactResponse createContact(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException {
        return (CreateContactResponse) client.post(accountId + "/contacts", attributes, null, CreateContactResponse.class);
    }

    public UpdateContactResponse updateContact(String accountId, String contactId, Map<String, Object> attributes) throws DnsimpleException, IOException {
        return (UpdateContactResponse) client.patch(accountId + "/contacts/" + contactId, attributes, null, UpdateContactResponse.class);
    }

    public DeleteContactResponse deleteContact(String accountId, String contactId) throws DnsimpleException, IOException {
        return (DeleteContactResponse) client.delete(accountId + "/contacts/" + contactId, null, DeleteContactResponse.class);
    }
}
