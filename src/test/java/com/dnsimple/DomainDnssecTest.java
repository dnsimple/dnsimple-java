package com.dnsimple;

import com.dnsimple.data.Dnssec;
import com.dnsimple.exception.BadRequestException;
import com.dnsimple.response.SimpleResponse;
import org.junit.Test;

import static com.dnsimple.endpoints.http.HttpMethod.DELETE;
import static com.dnsimple.tools.CustomMatchers.property;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainDnssecTest extends DnsimpleTestBase {
    @Test
    public void testEnableDnssec() {
        server.stubFixtureAt("enableDnssec/success.http");
        SimpleResponse<Dnssec> response = client.domains.enableDnssec("1", "example.com");
        assertThat(response.getData().getEnabled(), is(true));
    }

    @Test
    public void testDisableDnssec() {
        server.stubFixtureAt("disableDnssec/success.http");
        client.domains.disableDnssec("1", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/dnssec"));
    }

    @Test
    public void testDisableDnssecWhenNotEnabled() {
        server.stubFixtureAt("disableDnssec/not-enabled.http");
        assertThat(() -> client.domains.disableDnssec("1", "example.com"), allOf(
                thrownException(is(instanceOf(BadRequestException.class))),
                thrownException(property(BadRequestException::getStatusCode, is(428)))
        ));
    }

    @Test
    public void testGetDnssec() {
        server.stubFixtureAt("getDnssec/success.http");
        SimpleResponse<Dnssec> response = client.domains.getDnssec("1", "example.com");
        assertThat(response.getData().getEnabled(), is(true));
    }
}
