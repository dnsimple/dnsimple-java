package com.dnsimple.endpoints.http;

import com.dnsimple.Contacts;
import com.dnsimple.data.Contact;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static com.dnsimple.endpoints.http.java11.HttpMethod.GET;

public class ContactsEndpoint implements Contacts {
    private final HttpEndpointClient client;

    public ContactsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Contact> listContacts(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return listContacts(accountId, null);
    }

    public PaginatedResponse<Contact> listContacts(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.page(GET, accountId + "/contacts", options, Contact.class);
    }

    public SimpleResponse<Contact> getContact(String accountId, String contactId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/contacts/" + contactId, null, Collections.emptyMap(), Contact.class);
    }

    public SimpleResponse<Contact> createContact(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/contacts", attributes, null, Contact.class);
    }

    public SimpleResponse<Contact> updateContact(String accountId, String contactId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.patchSimple(accountId + "/contacts/" + contactId, null, attributes, Contact.class);
    }

    public EmptyResponse deleteContact(String accountId, String contactId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/contacts/" + contactId, null);
    }
}
