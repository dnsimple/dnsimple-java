package com.dnsimple.endpoints.http;

import com.dnsimple.Contacts;
import com.dnsimple.data.Contact;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

public class ContactsEndpoint implements Contacts {
    private final HttpEndpointClient client;

    public ContactsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Contact> listContacts(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return listContacts(accountId, null);
    }

    public PaginatedResponse<Contact> listContacts(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.getPage(accountId + "/contacts", options, Contact.class);
    }

    public SimpleResponse<Contact> getContact(String accountId, String contactId) throws DnsimpleException, IOException, InterruptedException {
        return client.getSimple(accountId + "/contacts/" + contactId, null, Contact.class);
    }

    public SimpleResponse<Contact> createContact(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/contacts", attributes, null, Contact.class);
    }

    public SimpleResponse<Contact> updateContact(String accountId, String contactId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.patchSimple(accountId + "/contacts/" + contactId, attributes, null, Contact.class);
    }

    public EmptyResponse deleteContact(String accountId, String contactId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/contacts/" + contactId, null);
    }
}
