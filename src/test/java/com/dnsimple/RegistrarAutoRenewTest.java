package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import org.junit.Test;

import java.io.IOException;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.DELETE;
import static com.dnsimple.tools.HttpMethod.PUT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class RegistrarAutoRenewTest extends DnsimpleTestBase {
    @Test
    public void testEnableAutoRenewal() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("enableDomainAutoRenewal/success.http");
        client.registrar.enableAutoRenewal("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/auto_renewal"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testEnableAutoRenewalDomainDoesNotExist() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("notfound-domain.http");
        client.registrar.enableAutoRenewal("1010", "0");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/0/auto_renewal"));
    }

    @Test
    public void testDisableAutoRenewal() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("disableDomainAutoRenewal/success.http");
        client.registrar.disableAutoRenewal("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/auto_renewal"));
    }

    @Test
    public void testDisableAutoRenewalDomainDoesNotExist() {
        server.stubFixtureAt("notfound-domain.http");
        assertThat(() -> client.registrar.disableAutoRenewal("1010", "0"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/0/auto_renewal"));
    }
}
