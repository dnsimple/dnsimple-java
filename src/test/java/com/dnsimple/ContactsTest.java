package com.dnsimple;

import com.dnsimple.data.Contact;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.response.DeleteContactResponse;
import com.dnsimple.response.GetContactResponse;
import com.dnsimple.response.UpdateContactResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.*;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContactsTest extends DnsimpleTestBase {
    @Test
    public void testListContactsSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
        client.contacts.listContacts("1", singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/contacts?page=1"));
    }

    @Test
    public void testListContactsSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
        client.contacts.listContacts("1", singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/contacts?foo=bar"));
    }

    @Test
    public void testListContactsSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
        client.contacts.listContacts("1", singletonMap("sort", "last_name:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/contacts?sort=last_name%3Aasc"));
    }

    @Test
    public void testListContactsProducesContactList() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listContacts/success.http");
        List<Contact> contacts = client.contacts.listContacts("1").getData();
        assertThat(contacts, hasSize(2));
        assertThat(contacts.get(0).getId(), is(1));
    }

    @Test
    public void testListContactsExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listContacts/success.http");
        assertThat(client.contacts.listContacts("1").getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetContact() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getContact/success.http");
        GetContactResponse response = client.contacts.getContact("1010", "1");
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/contacts/1"));
        Contact contact = response.getData();
        assertThat(contact.getId(), is(1));
        assertThat(contact.getAccountId(), is(1010));
        assertThat(contact.getLabel(), is("Default"));
        assertThat(contact.getFirstName(), is("First"));
        assertThat(contact.getLastName(), is("User"));
        assertThat(contact.getJobTitle(), is("CEO"));
        assertThat(contact.getOrganizationName(), is("Awesome Company"));
        assertThat(contact.getEmail(), is("first@example.com"));
        assertThat(contact.getPhone(), is("+18001234567"));
        assertThat(contact.getFax(), is("+18011234567"));
        assertThat(contact.getAddress1(), is("Italian Street, 10"));
        assertThat(contact.getAddress2(), is(""));
        assertThat(contact.getCity(), is("Roma"));
        assertThat(contact.getStateOrProvince(), is("RM"));
        assertThat(contact.getPostalCode(), is("00100"));
        assertThat(contact.getCountry(), is("IT"));
        assertThat(contact.getCreatedAt(), is("2016-01-19T20:50:26Z"));
        assertThat(contact.getUpdatedAt(), is("2016-01-19T20:50:26Z"));
    }

    @Test
    public void testGetContactWhenNotFound() {
        server.stubFixtureAt("notfound-contact.http");
        assertThat(() -> client.contacts.getContact("1010", "2"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateContactSendsCorrectRequest() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("createContact/created.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("first_name", "John");
        attributes.put("last_name", "Smith");
        client.contacts.createContact("1010", attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/contacts"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test
    public void testCreateContactProducesContact() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("createContact/created.http");
        Contact contact = client.contacts.createContact("1", emptyMap()).getData();
        assertThat(contact.getId(), is(1));
    }

    @Test
    public void testUpdateContact() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("updateContact/success.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("first_name", "John");
        attributes.put("last_name", "Smith");
        UpdateContactResponse response = client.contacts.updateContact("1010", "1", attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(PATCH));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/contacts/1"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
        assertThat(response.getData().getId(), is(1));
    }

    @Test
    public void testDeleteContact() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("deleteContact/success.http");
        DeleteContactResponse response = client.contacts.deleteContact("1010", "1");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/contacts/1"));
        assertThat(response.getData(), is(nullValue()));
    }
}
