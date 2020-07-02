package com.dnsimple;

import com.dnsimple.data.Push;
import com.dnsimple.exception.DnsimpleException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.dnsimple.endpoints.http.java11.HttpMethod.DELETE;
import static com.dnsimple.endpoints.http.java11.HttpMethod.POST;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainPushesTest extends DnsimpleTestBase {
    @Test
    public void testInitiatePushProducesPush() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("initiatePush/success.http");
        Push push = client.domains.initiatePush("1", "example.com", singletonMap("new_account_email", "jim@example.com")).getData();
        assertThat(push.getId(), is(1));
        assertThat(push.getDomainId(), is(100));
        assertThat(push.getContactId(), is(nullValue()));
        assertThat(push.getCreatedAt(), is("2016-08-11T10:16:03Z"));
        assertThat(push.getUpdatedAt(), is("2016-08-11T10:16:03Z"));
        assertThat(push.getAcceptedAt(), isEmptyOrNullString());
    }

    @Test
    public void testListPushesProducesPushList() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listPushes/success.http");
        List<Push> pushes = client.domains.listPushes("1", "example.com").getData();
        assertThat(pushes, hasSize(2));
        assertThat(pushes.get(0).getId(), is(1));
    }

    @Test
    public void testAcceptPush() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("acceptPush/success.http");
        client.domains.acceptPush("1010", "200", singletonMap("contact_id", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/pushes/200"));
    }

    @Test
    public void testRejectPush() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("rejectPush/success.http");
        client.domains.rejectPush("1010", "200");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/pushes/200"));
    }
}
