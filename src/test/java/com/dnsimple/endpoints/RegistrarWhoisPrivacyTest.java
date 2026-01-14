package com.dnsimple.endpoints;

import com.dnsimple.data.WhoisPrivacy;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import static com.dnsimple.http.HttpMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RegistrarWhoisPrivacyTest extends DnsimpleTestBase {
    @Test
    public void testEnableWhoisPrivacyAlreadyPurchased() {
        server.stubFixtureAt("enableWhoisPrivacy/success.http");
        client.registrar.enableWhoisPrivacy(1010, "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
    }

    @Test
    public void testEnableWhoisPrivacyNewlyPurchased() {
        server.stubFixtureAt("enableWhoisPrivacy/created.http");
        WhoisPrivacy whoisPrivacy = client.registrar.enableWhoisPrivacy(1010, "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
        assertThat(whoisPrivacy.getId(), is(1L));
    }

    @Test
    public void testDisableWhoisPrivacyNewlyPurchased() {
        server.stubFixtureAt("disableWhoisPrivacy/success.http");
        WhoisPrivacy whoisPrivacy = client.registrar.disableWhoisPrivacy(1010, "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/whois_privacy"));
        assertThat(whoisPrivacy.getId(), is(1L));
    }
}
