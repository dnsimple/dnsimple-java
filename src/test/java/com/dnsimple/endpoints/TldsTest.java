package com.dnsimple.endpoints;

import com.dnsimple.data.Tld;
import com.dnsimple.data.TldExtendedAttribute;
import com.dnsimple.data.TldExtendedAttributeOption;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.util.List;

import static com.dnsimple.http.HttpMethod.GET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TldsTest extends DnsimpleTestBase {
    @Test
    public void testListTldsSupportsPagination() {
        client.tlds.listTlds(ListOptions.empty().page(1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds?page=1"));
    }

    @Test
    public void testListTldsSupportsExtraRequestOptions() {
        client.tlds.listTlds(ListOptions.empty().filter("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds?foo=bar"));
    }

    @Test
    public void testListTldsSupportsSorting() {
        client.tlds.listTlds(ListOptions.empty().sortAsc("name"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds?sort=name%3Aasc"));
    }

    @Test
    public void testListTldsProducesTldList() {
        server.stubFixtureAt("listTlds/success.http");
        List<Tld> tlds = client.tlds.listTlds().getData();
        assertThat(tlds, hasSize(2));
        Tld tld = tlds.get(0);
        assertThat(tld.getTld(), is("ac"));
        assertThat(tld.getTldType(), is(2));
        assertThat(tld.supportsWhoisPrivacy(), is(false));
        assertThat(tld.isAutorenewOnly(), is(true));
        assertThat(tld.getMinimumRegistration(), is(1));
        assertThat(tld.isRegistrationEnabled(), is(true));
        assertThat(tld.isRenewalEnabled(), is(true));
        assertThat(tld.isTransferEnabled(), is(false));
        assertThat(tld.getDnssecInterfaceType(), is("ds"));
    }

    @Test
    public void testListTldsExposesPaginationInfo() {
        server.stubFixtureAt("listTlds/success.http");
        PaginatedResponse<Tld> response = client.tlds.listTlds();
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetTld() {
        server.stubFixtureAt("getTld/success.http");
        SimpleResponse<Tld> response = client.tlds.getTld("com");
        Tld tld = response.getData();
        assertThat(tld.getTld(), is("com"));
        assertThat(tld.getTldType(), is(1));
        assertThat(tld.supportsWhoisPrivacy(), is(true));
        assertThat(tld.isAutorenewOnly(), is(false));
        assertThat(tld.getMinimumRegistration(), is(1));
        assertThat(tld.isRegistrationEnabled(), is(true));
        assertThat(tld.isRenewalEnabled(), is(true));
        assertThat(tld.isTransferEnabled(), is(true));
        assertThat(tld.getDnssecInterfaceType(), is("ds"));
    }

    @Test
    public void testGetTldExtendedAttributes() {
        server.stubFixtureAt("getTldExtendedAttributes/success.http");
        ListResponse<TldExtendedAttribute> response = client.tlds.getTldExtendedAttributes("uk");
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds/uk/extended_attributes"));
        List<TldExtendedAttribute> extendedAttributes = response.getData();
        assertThat(extendedAttributes, hasSize(4));
        assertThat(extendedAttributes.get(0).getName(), is("uk_legal_type"));
        assertThat(extendedAttributes.get(0).getDescription(), is("Legal type of registrant contact"));
        assertThat(extendedAttributes.get(0).isRequired(), is(false));
        List<TldExtendedAttributeOption> options = extendedAttributes.get(0).getOptions();
        assertThat(options, hasSize(17));
        assertThat(options.get(0).getTitle(), is("UK Individual"));
        assertThat(options.get(0).getValue(), is("IND"));
        assertThat(options.get(0).getDescription(), is("UK Individual (our default value)"));
    }

    @Test
    public void testGetTldExtendedAttributesWhenNone() {
        server.stubFixtureAt("getTldExtendedAttributes/success-noattributes.http");
        ListResponse<TldExtendedAttribute> response = client.tlds.getTldExtendedAttributes("com");
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds/com/extended_attributes"));
        assertThat(response.getData(), is(empty()));
    }
}
