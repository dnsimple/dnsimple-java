package com.dnsimple;

import com.dnsimple.data.WhoisPrivacy;
import com.dnsimple.data.WhoisPrivacyRenewal;
import org.junit.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RegistrarWhoisPrivacyTest extends DnsimpleTestBase {
    @Test
    public void testGetWhoisPrivacy() {
        server.stubFixtureAt("getWhoisPrivacy/success.http");
        WhoisPrivacy whoisPrivacy = client.registrar.getWhoisPrivacy("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
        assertThat(whoisPrivacy.getId(), is(1L));
        assertThat(whoisPrivacy.getDomainId(), is(2L));
        assertThat(whoisPrivacy.getExpiresOn(), is(LocalDate.of(2017, 2, 13)));
        assertThat(whoisPrivacy.isEnabled(), is(true));
        assertThat(whoisPrivacy.getCreatedAt(), is(OffsetDateTime.of(2016, 2, 13, 14, 34, 50, 0, UTC)));
        assertThat(whoisPrivacy.getUpdatedAt(), is(OffsetDateTime.of(2016, 2, 13, 14, 34, 52, 0, UTC)));
    }

    @Test
    public void testEnableWhoisPrivacyAlreadyPurchased() {
        server.stubFixtureAt("enableWhoisPrivacy/success.http");
        client.registrar.enableWhoisPrivacy("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
    }

    @Test
    public void testEnableWhoisPrivacyNewlyPurchased() {
        server.stubFixtureAt("enableWhoisPrivacy/created.http");
        WhoisPrivacy whoisPrivacy = client.registrar.enableWhoisPrivacy("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
        assertThat(whoisPrivacy.getId(), is(1L));
    }

    @Test
    public void testDisableWhoisPrivacyNewlyPurchased() {
        server.stubFixtureAt("disableWhoisPrivacy/success.http");
        WhoisPrivacy whoisPrivacy = client.registrar.disableWhoisPrivacy("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
        assertThat(whoisPrivacy.getId(), is(1L));
    }

    @Test
    public void testRenewWhoisPrivacy() {
        server.stubFixtureAt("renewWhoisPrivacy/success.http");
        WhoisPrivacyRenewal whoisPrivacyRenewal = client.registrar.renewWhoisPrivacy("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy/renewals"));
        assertThat(whoisPrivacyRenewal.getId(), is(1L));
        assertThat(whoisPrivacyRenewal.getDomainId(), is(100L));
        assertThat(whoisPrivacyRenewal.getWhoisPrivacyId(), is(999L));
        assertThat(whoisPrivacyRenewal.getState(), is("new"));
        assertThat(whoisPrivacyRenewal.isEnabled(), is(true));
        assertThat(whoisPrivacyRenewal.getExpiresOn(), is(LocalDate.of(2020, 1, 10)));
        assertThat(whoisPrivacyRenewal.getCreatedAt(), is(OffsetDateTime.of(2019, 1, 10, 12, 12, 48, 0, UTC)));
        assertThat(whoisPrivacyRenewal.getUpdatedAt(), is(OffsetDateTime.of(2019, 1, 10, 12, 12, 48, 0, UTC)));
    }
}
