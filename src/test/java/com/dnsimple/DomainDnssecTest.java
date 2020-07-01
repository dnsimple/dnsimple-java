package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.DisableDnssecResponse;
import com.dnsimple.response.EnableDnssecResponse;
import com.dnsimple.response.GetDnssecResponse;
import org.junit.Test;

import java.io.IOException;

import static com.dnsimple.tools.CustomMatchers.property;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainDnssecTest extends DnsimpleTestBase {
    @Test
    public void testEnableDnssec() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("enableDnssec/success.http");
        EnableDnssecResponse response = client.domains.enableDnssec("1", "example.com");
        assertThat(response.getData().getEnabled(), is(true));
    }

    @Test
    public void testDisableDnssec() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("disableDnssec/success.http");
        DisableDnssecResponse response = client.domains.disableDnssec("1", "example.com");
        assertThat(response.getData(), is(nullValue()));
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
        GetDnssecResponse response = client.domains.getDnssec("1", "example.com");
        assertThat(response.getData().getEnabled(), is(true));
    }
}
