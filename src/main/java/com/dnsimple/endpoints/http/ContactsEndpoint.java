package com.dnsimple.endpoints.http;

import com.dnsimple.Contacts;
import com.dnsimple.data.Contact;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.util.Collections.emptyMap;

public class ContactsEndpoint implements Contacts {
    private final HttpEndpointClient client;

    public ContactsEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Contact> listContacts(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return client.page(GET, accountId + "/contacts", emptyMap(), null, Contact.class);
    }

    public PaginatedResponse<Contact> listContacts(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.page(GET, accountId + "/contacts", options, null, Contact.class);
    }

    public SimpleResponse<Contact> getContact(String accountId, String contactId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/contacts/" + contactId, emptyMap(), emptyMap(), Contact.class);
    }

    public SimpleResponse<Contact> createContact(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(POST, accountId + "/contacts", emptyMap(), attributes, Contact.class);
    }

    public SimpleResponse<Contact> updateContact(String accountId, String contactId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(PATCH, accountId + "/contacts/" + contactId, emptyMap(), attributes, Contact.class);
    }

    public EmptyResponse deleteContact(String accountId, String contactId) throws DnsimpleException, IOException, InterruptedException {
        return client.empty(DELETE, accountId + "/contacts/" + contactId, emptyMap(), null);
    }
}
