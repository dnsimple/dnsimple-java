package com.dnsimple;

import com.dnsimple.data.WhoisPrivacy;
import com.dnsimple.data.WhoisPrivacyRenewal;
import com.dnsimple.exception.DnsimpleException;
import org.junit.Test;

import java.io.IOException;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RegistrarWhoisPrivacyTest extends DnsimpleTestBase {
    @Test
    public void testGetWhoisPrivacy() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getWhoisPrivacy/success.http");
        WhoisPrivacy whoisPrivacy = client.registrar.getWhoisPrivacy("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
        assertThat(whoisPrivacy.getId(), is(1));
        assertThat(whoisPrivacy.getDomainId(), is(2));
        assertThat(whoisPrivacy.getExpiresOn(), is("2017-02-13"));
        assertThat(whoisPrivacy.getEnabled(), is(true));
        assertThat(whoisPrivacy.getCreatedAt(), is("2016-02-13T14:34:50Z"));
        assertThat(whoisPrivacy.getUpdatedAt(), is("2016-02-13T14:34:52Z"));
    }

    @Test
    public void testEnableWhoisPrivacyAlreadyPurchased() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("enableWhoisPrivacy/success.http");
        client.registrar.enableWhoisPrivacy("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
    }

    @Test
    public void testEnableWhoisPrivacyNewlyPurchased() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("enableWhoisPrivacy/created.http");
        WhoisPrivacy whoisPrivacy = client.registrar.enableWhoisPrivacy("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
        assertThat(whoisPrivacy.getId(), is(1));
    }

    @Test
    public void testDisableWhoisPrivacyNewlyPurchased() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("disableWhoisPrivacy/success.http");
        WhoisPrivacy whoisPrivacy = client.registrar.disableWhoisPrivacy("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
        assertThat(whoisPrivacy.getId(), is(1));
    }

    @Test
    public void testRenewWhoisPrivacy() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("renewWhoisPrivacy/success.http");
        WhoisPrivacyRenewal whoisPrivacyRenewal = client.registrar.renewWhoisPrivacy("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy/renewals"));
        assertThat(whoisPrivacyRenewal.getId(), is(1));
    }
}
