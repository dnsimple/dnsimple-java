package com.dnsimple.endpoints;

import com.dnsimple.data.Contact;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ContactOptions;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import static com.dnsimple.http.HttpMethod.*;
import static java.util.Collections.emptyMap;

/**
 * Provides access to the DNSimple Contacts API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/contacts">https://developer.dnsimple.com/v2/contacts</a>
 */
public class Contacts {
    private final HttpEndpointClient client;

    public Contacts(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists the contacts in the account.
     *
     * @param account The account ID
     * @return The list contacts response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#list">https://developer.dnsimple.com/v2/contacts/#list</a>
     */
    public PaginatedResponse<Contact> listContacts(Number account) {
        return client.page(GET, account + "/contacts", ListOptions.empty(), null, Contact.class);
    }

    /**
     * Lists the contacts in the account.
     *
     * @param account The account ID
     * @param options The options for the list request
     * @return The list contacts response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#list">https://developer.dnsimple.com/v2/contacts/#list</a>
     */
    public PaginatedResponse<Contact> listContacts(Number account, ListOptions options) {
        return client.page(GET, account + "/contacts", options, null, Contact.class);
    }

    /**
     * Get a specific contact associated to an account using the contacts's ID.
     *
     * @param account   The account ID
     * @param contactId The contact ID
     * @return The get contact response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#get">https://developer.dnsimple.com/v2/contacts/#get</a>
     */
    public SimpleResponse<Contact> getContact(Number account, Number contactId) {
        return client.simple(GET, account + "/contacts/" + contactId, ListOptions.empty(), emptyMap(), Contact.class);
    }

    /**
     * Create a contact in the account.
     *
     * @param account The account ID
     * @param options options to create the new contact
     * @return The create contact response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#create">https://developer.dnsimple.com/v2/contacts/#create</a>
     */
    public SimpleResponse<Contact> createContact(Number account, ContactOptions options) {
        return client.simple(POST, account + "/contacts", ListOptions.empty(), options, Contact.class);
    }

    /**
     * Update a contact in the account.
     *
     * @param account   The account ID
     * @param contactId The contact ID
     * @param options   options to update the contact
     * @return The update contact response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#update">https://developer.dnsimple.com/v2/contacts/#update</a>
     */
    public SimpleResponse<Contact> updateContact(Number account, Number contactId, ContactOptions options) {
        return client.simple(PATCH, account + "/contacts/" + contactId, ListOptions.empty(), options, Contact.class);
    }

    /**
     * Delete a contact from the account.
     *
     * @param account   The account ID
     * @param contactId The contact ID
     * @return The delete contact response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#delete">https://developer.dnsimple.com/v2/contacts/#delete</a>
     */
    public EmptyResponse deleteContact(Number account, Number contactId) {
        return client.empty(DELETE, account + "/contacts/" + contactId, ListOptions.empty(), null);
    }
}
