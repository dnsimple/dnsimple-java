package com.dnsimple.endpoints;

import com.dnsimple.data.VanityNameServer;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static com.dnsimple.http.HttpMethod.*;
import static java.time.ZoneOffset.UTC;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class RegistrarDelegationTest extends DnsimpleTestBase {
    @Test
    public void testGetDomainDelegation() {
        server.stubFixtureAt("getDomainDelegation/success.http");
        List<String> delegatedTo = client.registrar.getDomainDelegation(1010, "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/delegation"));
        assertThat(delegatedTo, hasSize(4));
        assertThat(delegatedTo.get(0), is("ns1.dnsimple.com"));
        assertThat(delegatedTo.get(1), is("ns2.dnsimple.com"));
        assertThat(delegatedTo.get(2), is("ns3.dnsimple.com"));
        assertThat(delegatedTo.get(3), is("ns4.dnsimple.com"));
    }

    @Test
    public void testChangeDomainDelegation() {
        server.stubFixtureAt("changeDomainDelegation/success.http");
        List<String> nameServerNames = singletonList("ns1.example.com");
        List<String> delegatedTo = client.registrar.changeDomainDelegation(1010, "example.com", nameServerNames).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/delegation"));
        assertThat(server.getRecordedRequest().getJsonArrayPayload(), is(nameServerNames));
        assertThat(delegatedTo.get(0), is("ns1.dnsimple.com"));
        assertThat(delegatedTo.get(1), is("ns2.dnsimple.com"));
        assertThat(delegatedTo.get(2), is("ns3.dnsimple.com"));
        assertThat(delegatedTo.get(3), is("ns4.dnsimple.com"));
    }

    @Test
    public void testChangeDomainDelegationToVanity() {
        server.stubFixtureAt("changeDomainDelegationToVanity/success.http");
        List<String> nameServerNames = Arrays.asList("ns1.example.com", "ns2.example.com");
        List<VanityNameServer> delegatedTo = client.registrar.changeDomainDelegationToVanity(1010, "example.com", nameServerNames).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/delegation/vanity"));
        assertThat(server.getRecordedRequest().getJsonArrayPayload(), is(nameServerNames));
        assertThat(delegatedTo, hasSize(2));
        VanityNameServer vanityNameServer = delegatedTo.get(0);
        assertThat(vanityNameServer.getName(), is("ns1.example.com"));
        assertThat(vanityNameServer.getIpv4(), is("127.0.0.1"));
        assertThat(vanityNameServer.getIpv6(), is("::1"));
        assertThat(vanityNameServer.getCreatedAt(), is(OffsetDateTime.of(2016, 7, 11, 9, 40, 19, 0, UTC)));
        assertThat(vanityNameServer.getUpdatedAt(), is(OffsetDateTime.of(2016, 7, 11, 9, 40, 19, 0, UTC)));
    }

    @Test
    public void testChangeDomainDelegationFromVanity() {
        server.stubFixtureAt("changeDomainDelegationFromVanity/success.http");
        client.registrar.changeDomainDelegationFromVanity(1010, "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/delegation/vanity"));
    }
}
