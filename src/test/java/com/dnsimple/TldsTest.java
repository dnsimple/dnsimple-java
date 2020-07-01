package com.dnsimple;

import com.dnsimple.data.Tld;
import com.dnsimple.data.TldExtendedAttribute;
import com.dnsimple.data.TldExtendedAttributeOption;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.exception.DnsimpleException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.dnsimple.tools.HttpMethod.GET;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TldsTest extends DnsimpleTestBase {
    @Test
    public void testListTldsSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
        client.tlds.listTlds(singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds?page=1"));
    }

    @Test
    public void testListTldsSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
        client.tlds.listTlds(singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds?foo=bar"));
    }

    @Test
    public void testListTldsSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
        client.tlds.listTlds(singletonMap("sort", "name:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds?sort=name%3Aasc"));
    }

    @Test
    public void testListTldsProducesTldList() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listTlds/success.http");
        List<Tld> tlds = client.tlds.listTlds().getData();
        assertThat(tlds, hasSize(2));
        assertThat(tlds.get(0).getTld(), is("ac"));
    }

    @Test
    public void testListTldsExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listTlds/success.http");
        PaginatedResponse<Tld> response = client.tlds.listTlds();
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetTld() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getTld/success.http");
        SimpleResponse<Tld> response = client.tlds.getTld("com");
        assertThat(response.getData().getTld(), is("com"));
    }

    @Test
    public void testGetTldExtendedAttributes() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getTldExtendedAttributes/success.http");
        ListResponse<TldExtendedAttribute> response = client.tlds.getTldExtendedAttributes("uk");
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds/uk/extended_attributes"));
        List<TldExtendedAttribute> extendedAttributes = response.getData();
        assertThat(extendedAttributes, hasSize(4));
        assertThat(extendedAttributes.get(0).getName(), is("uk_legal_type"));
        assertThat(extendedAttributes.get(0).getDescription(), is("Legal type of registrant contact"));
        assertThat(extendedAttributes.get(0).getRequired(), is(false));
        List<TldExtendedAttributeOption> options = extendedAttributes.get(0).getOptions();
        assertThat(options, hasSize(17));
        assertThat(options.get(0).getTitle(), is("UK Individual"));
        assertThat(options.get(0).getValue(), is("IND"));
        assertThat(options.get(0).getDescription(), is("UK Individual (our default value)"));
    }

    @Test
    public void testGetTldExtendedAttributesWhenNone() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getTldExtendedAttributes/success-noattributes.http");
        ListResponse<TldExtendedAttribute> response = client.tlds.getTldExtendedAttributes("com");
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/tlds/com/extended_attributes"));
        assertThat(response.getData(), is(empty()));
    }
}
