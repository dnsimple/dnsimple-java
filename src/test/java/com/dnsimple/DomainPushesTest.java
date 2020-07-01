package com.dnsimple;

import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;

import com.dnsimple.data.Push;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.AcceptPushResponse;
import com.dnsimple.response.RejectPushResponse;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

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

    AcceptPushResponse response = client.domains.acceptPush("1010", "200", singletonMap("contact_id", 1));
    assertThat(response.getData(), is(nullValue()));
  }

  @Test
  public void testRejectPush() throws DnsimpleException, IOException, InterruptedException {
    server.stubFixtureAt("rejectPush/success.http");

    RejectPushResponse response = client.domains.rejectPush("1010", "200");
    assertThat(response.getData(), is(nullValue()));
  }
}
