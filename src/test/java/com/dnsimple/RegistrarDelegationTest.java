package com.dnsimple;

import com.dnsimple.data.NameServer;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ChangeDomainDelegationFromVanityResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.dnsimple.tools.HttpMethod.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegistrarDelegationTest extends DnsimpleTestBase {
    @Test
    public void testGetDomainDelegation() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getDomainDelegation/success.http");
        List<String> delegatedTo = client.registrar.getDomainDelegation("1010", "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/delegation"));
        assertThat(delegatedTo, hasSize(4));
        assertThat(delegatedTo.get(0), is("ns1.dnsimple.com"));
        assertThat(delegatedTo.get(1), is("ns2.dnsimple.com"));
        assertThat(delegatedTo.get(2), is("ns3.dnsimple.com"));
        assertThat(delegatedTo.get(3), is("ns4.dnsimple.com"));
    }

    @Test
    public void testChangeDomainDelegation() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("changeDomainDelegation/success.http");
        List<String> nameServerNames = singletonList("ns1.example.com");
        List<String> delegatedTo = client.registrar.changeDomainDelegation("1010", "example.com", nameServerNames).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/delegation"));
        assertThat(server.getRecordedRequest().getJsonArrayPayload(), is(nameServerNames));
        assertThat(delegatedTo.get(0), is("ns1.dnsimple.com"));
        assertThat(delegatedTo.get(1), is("ns2.dnsimple.com"));
        assertThat(delegatedTo.get(2), is("ns3.dnsimple.com"));
        assertThat(delegatedTo.get(3), is("ns4.dnsimple.com"));
    }

    @Test
    public void testChangeDomainDelegationToVanity() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("changeDomainDelegationToVanity/success.http");
        List<String> nameServerNames = Arrays.asList("ns1.example.com", "ns2.example.com");
        List<NameServer> delegatedTo = client.registrar.changeDomainDelegationToVanity("1010", "example.com", nameServerNames).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PUT));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/delegation/vanity"));
        assertThat(server.getRecordedRequest().getJsonArrayPayload(), is(nameServerNames));
        assertThat(delegatedTo, hasSize(2));
        NameServer nameServer = delegatedTo.get(0);
        assertThat(nameServer.getName(), is("ns1.example.com"));
        assertThat(nameServer.getIpv4(), is("127.0.0.1"));
        assertThat(nameServer.getIpv6(), is("::1"));
        assertThat(nameServer.getCreatedAt(), is("2016-07-11T09:40:19Z"));
        assertThat(nameServer.getUpdatedAt(), is("2016-07-11T09:40:19Z"));
    }

    @Test
    public void testChangeDomainDelegationFromVanity() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("changeDomainDelegationFromVanity/success.http");
        ChangeDomainDelegationFromVanityResponse response = client.registrar.changeDomainDelegationFromVanity("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/delegation/vanity"));
        assertThat(response.getData(), is(nullValue()));
    }
}
