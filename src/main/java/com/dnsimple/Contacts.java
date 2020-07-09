package com.dnsimple;

import com.dnsimple.data.Contact;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

/**
 * Provides access to the DNSimple Contacts API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/contacts">https://developer.dnsimple.com/v2/contacts</a>
 */
public interface Contacts {
    /**
     * Lists the contacts in the account.
     *
     * @param accountId The account ID
     * @return The list contacts response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#list">https://developer.dnsimple.com/v2/contacts/#list</a>
     */
    PaginatedResponse<Contact> listContacts(String accountId);

    /**
     * Lists the contacts in the account.
     *
     * @param accountId The account ID
     * @param options   A Map of options to pass to the contacts API
     * @return The list contacts response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#list">https://developer.dnsimple.com/v2/contacts/#list</a>
     */
    PaginatedResponse<Contact> listContacts(String accountId, Map<String, Object> options);

    /**
     * Get a specific contact associated to an account using the contacts's ID.
     *
     * @param accountId The account ID
     * @param contactId The contact ID
     * @return The get contact response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#get">https://developer.dnsimple.com/v2/contacts/#get</a>
     */
    SimpleResponse<Contact> getContact(String accountId, String contactId);

    /**
     * Create a contact in the account.
     *
     * @param accountId  The account ID
     * @param attributes A map of attributes to contruct the contact
     * @return The create contact response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#create">https://developer.dnsimple.com/v2/contacts/#create</a>
     */
    SimpleResponse<Contact> createContact(String accountId, Map<String, Object> attributes);

    /**
     * Update a contact in the account.
     *
     * @param accountId  The account ID
     * @param contactId  The contact ID
     * @param attributes A map of attributes to update the contact
     * @return The update contact response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#update">https://developer.dnsimple.com/v2/contacts/#update</a>
     */
    SimpleResponse<Contact> updateContact(String accountId, String contactId, Map<String, Object> attributes);

    /**
     * Delete a contact from the account.
     *
     * @param accountId The account ID
     * @param contactId The contact ID
     * @return The delete contact response
     * @see <a href="https://developer.dnsimple.com/v2/contacts/#delete">https://developer.dnsimple.com/v2/contacts/#delete</a>
     */
    EmptyResponse deleteContact(String accountId, String contactId);
}
