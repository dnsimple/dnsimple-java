package com.dnsimple.endpoints;

import com.dnsimple.data.Contact;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.time.ZoneOffset.UTC;
import static java.util.Collections.emptyMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContactsTest extends DnsimpleTestBase {
    @Test
    public void testListContactsSupportsPagination() {
        client.contacts.listContacts(1, new ListOptions.Builder().page(1).build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/contacts?page=1"));
    }

    @Test
    public void testListContactsSupportsExtraRequestOptions() {
        client.contacts.listContacts(1, new ListOptions.Builder().filter("foo", "bar").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/contacts?foo=bar"));
    }

    @Test
    public void testListContactsSupportsSorting() {
        client.contacts.listContacts(1, new ListOptions.Builder().sortAsc("last_name").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/contacts?sort=last_name%3Aasc"));
    }

    @Test
    public void testListContactsProducesContactList() {
        server.stubFixtureAt("listContacts/success.http");
        List<Contact> contacts = client.contacts.listContacts(1).getData();
        assertThat(contacts, hasSize(2));
        assertThat(contacts.get(0).getId(), is(1L));
    }

    @Test
    public void testListContactsExposesPaginationInfo() {
        server.stubFixtureAt("listContacts/success.http");
        assertThat(client.contacts.listContacts(1).getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetContact() {
        server.stubFixtureAt("getContact/success.http");
        SimpleResponse<Contact> response = client.contacts.getContact(1010, 1);
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/contacts/1"));
        Contact contact = response.getData();
        assertThat(contact.getId(), is(1L));
        assertThat(contact.getAccountId(), is(1010L));
        assertThat(contact.getLabel(), is("Default"));
        assertThat(contact.getFirstName(), is("First"));
        assertThat(contact.getLastName(), is("User"));
        assertThat(contact.getJobTitle(), is("CEO"));
        assertThat(contact.getOrganization(), is("Awesome Company"));
        assertThat(contact.getEmail(), is("first@example.com"));
        assertThat(contact.getPhone(), is("+18001234567"));
        assertThat(contact.getFax(), is("+18011234567"));
        assertThat(contact.getAddress1(), is("Italian Street, 10"));
        assertThat(contact.getAddress2(), is(""));
        assertThat(contact.getCity(), is("Roma"));
        assertThat(contact.getStateProvince(), is("RM"));
        assertThat(contact.getPostalCode(), is("00100"));
        assertThat(contact.getCountry(), is("IT"));
        assertThat(contact.getCreatedAt(), is(OffsetDateTime.of(2016, 1, 19, 20, 50, 26, 0, UTC)));
        assertThat(contact.getUpdatedAt(), is(OffsetDateTime.of(2016, 1, 19, 20, 50, 26, 0, UTC)));
    }

    @Test
    public void testGetContactWhenNotFound() {
        server.stubFixtureAt("notfound-contact.http");
        assertThat(() -> client.contacts.getContact(1010, 2),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateContactSendsCorrectRequest() {
        server.stubFixtureAt("createContact/created.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("first_name", "John");
        attributes.put("last_name", "Smith");
        client.contacts.createContact(1010, attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/contacts"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test
    public void testCreateContactProducesContact() {
        server.stubFixtureAt("createContact/created.http");
        Contact contact = client.contacts.createContact(1, emptyMap()).getData();
        assertThat(contact.getId(), is(1L));
    }

    @Test
    public void testUpdateContact() {
        server.stubFixtureAt("updateContact/success.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("first_name", "John");
        attributes.put("last_name", "Smith");
        SimpleResponse<Contact> response = client.contacts.updateContact(1010, 1, attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(PATCH));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/contacts/1"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
        assertThat(response.getData().getId(), is(1L));
    }

    @Test
    public void testDeleteContact() {
        server.stubFixtureAt("deleteContact/success.http");
        client.contacts.deleteContact(1010, 1);
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/contacts/1"));
    }
}
