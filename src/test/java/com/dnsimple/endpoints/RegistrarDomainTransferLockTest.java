package com.dnsimple.endpoints;

import com.dnsimple.data.DomainTransferLock;
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

public class RegistrarDomainTransferLockTest extends DnsimpleTestBase {
    @Test
    public void testGetDomainDelegation() {
        server.stubFixtureAt("getDomainTransferLock/success.http");
        DomainTransferLock l = client.registrar.getDomainTransferLock(1010, "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfer_lock"));
        assertThat(l, is(new DomainTransferLock(true)));
    }

    @Test
    public void testEnableDomainDelegation() {
        server.stubFixtureAt("enableDomainTransferLock/success.http");
        DomainTransferLock l = client.registrar.enableDomainTransferLock(1010, "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfer_lock"));
        assertThat(l, is(new DomainTransferLock(true)));
    }

    @Test
    public void testDisableDomainDelegation() {
        server.stubFixtureAt("disableDomainTransferLock/success.http");
        DomainTransferLock l = client.registrar.disableDomainTransferLock(1010, "example.com").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfer_lock"));
        assertThat(l, is(new DomainTransferLock(false)));
    }
}
