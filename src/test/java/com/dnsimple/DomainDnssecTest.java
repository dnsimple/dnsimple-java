package com.dnsimple;

import com.dnsimple.data.Dnssec;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.exception.DnsimpleException;
import org.junit.Test;

import java.io.IOException;

import static com.dnsimple.tools.CustomMatchers.property;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.DELETE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainDnssecTest extends DnsimpleTestBase {
    @Test
    public void testEnableDnssec() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("enableDnssec/success.http");
        SimpleResponse<Dnssec> response = client.domains.enableDnssec("1", "example.com");
        assertThat(response.getData().getEnabled(), is(true));
    }

    @Test
    public void testDisableDnssec() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("disableDnssec/success.http");
        client.domains.disableDnssec("1", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/dnssec"));
    }

    @Test
    public void testDisableDnssecWhenNotEnabled() {
        server.stubFixtureAt("disableDnssec/not-enabled.http");
        assertThat(() -> client.domains.disableDnssec("1", "example.com"), allOf(
                thrownException(is(instanceOf(DnsimpleException.class))),
                thrownException(property(DnsimpleException::getStatusCode, is(428)))
        ));
    }

    @Test
    public void testGetDnssec() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getDnssec/success.http");
        SimpleResponse<Dnssec> response = client.domains.getDnssec("1", "example.com");
        assertThat(response.getData().getEnabled(), is(true));
    }
}
