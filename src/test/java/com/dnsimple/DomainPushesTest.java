package com.dnsimple;

import com.dnsimple.data.DomainPush;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.dnsimple.endpoints.http.HttpMethod.DELETE;
import static com.dnsimple.endpoints.http.HttpMethod.POST;
import static java.time.ZoneOffset.UTC;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainPushesTest extends DnsimpleTestBase {
    @Test
    public void testInitiatePushProducesPush() {
        server.stubFixtureAt("initiatePush/success.http");
        DomainPush domainPush = client.domains.initiatePush("1", "example.com", singletonMap("new_account_email", "jim@example.com")).getData();
        assertThat(domainPush.getId(), is(1L));
        assertThat(domainPush.getDomainId(), is(100L));
        assertThat(domainPush.getContactId(), is(nullValue()));
        assertThat(domainPush.getAccountId(), is(2020L));
        assertThat(domainPush.getCreatedAt(), is(OffsetDateTime.of(2016, 8, 11, 10, 16, 3, 0, UTC)));
        assertThat(domainPush.getUpdatedAt(), is(OffsetDateTime.of(2016, 8, 11, 10, 16, 3, 0, UTC)));
        assertThat(domainPush.getAcceptedAt(), is(nullValue()));
    }

    @Test
    public void testListPushesProducesPushList() {
        server.stubFixtureAt("listPushes/success.http");
        List<DomainPush> domainPushes = client.domains.listPushes("1", "example.com").getData();
        assertThat(domainPushes, hasSize(2));
        assertThat(domainPushes.get(0).getId(), is(1L));
    }

    @Test
    public void testAcceptPush() {
        server.stubFixtureAt("acceptPush/success.http");
        client.domains.acceptPush("1010", "200", singletonMap("contact_id", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/pushes/200"));
    }

    @Test
    public void testRejectPush() {
        server.stubFixtureAt("rejectPush/success.http");
        client.domains.rejectPush("1010", "200");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/pushes/200"));
    }
}
