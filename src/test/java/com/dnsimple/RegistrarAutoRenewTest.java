package com.dnsimple;

import com.dnsimple.exception.ResourceNotFoundException;
import org.junit.Test;

import static com.dnsimple.endpoints.http.HttpMethod.DELETE;
import static com.dnsimple.endpoints.http.HttpMethod.PUT;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class RegistrarAutoRenewTest extends DnsimpleTestBase {
    @Test
    public void testEnableAutoRenewal() {
        server.stubFixtureAt("enableDomainAutoRenewal/success.http");
        client.registrar.enableAutoRenewal("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/auto_renewal"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testEnableAutoRenewalDomainDoesNotExist() {
        server.stubFixtureAt("notfound-domain.http");
        client.registrar.enableAutoRenewal("1010", "0");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/0/auto_renewal"));
    }

    @Test
    public void testDisableAutoRenewal() {
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
