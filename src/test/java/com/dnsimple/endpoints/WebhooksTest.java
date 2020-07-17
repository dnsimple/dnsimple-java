package com.dnsimple.endpoints;

import com.dnsimple.data.Webhook;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.util.List;

import static com.dnsimple.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WebhooksTest extends DnsimpleTestBase {
    @Test
    public void testListWebhooksSupportsExtraRequestOptions() {
        client.webhooks.listWebhooks(1010, ListOptions.empty().filter("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/webhooks?foo=bar"));
    }

    @Test
    public void testListWebhooksProducesWebhookList() {
        server.stubFixtureAt("listWebhooks/success.http");
        List<Webhook> webhooks = client.webhooks.listWebhooks(1010).getData();
        assertThat(webhooks, hasSize(2));
        assertThat(webhooks.get(0).getId(), is(1L));
    }

    @Test
    public void testGetWebhook() {
        server.stubFixtureAt("getWebhook/success.http");
        Webhook webhook = client.webhooks.getWebhook(1010, 1).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/webhooks/1"));
        assertThat(webhook.getId(), is(1L));
        assertThat(webhook.getUrl(), is("https://webhook.test"));
    }

    @Test
    public void testGetWebhookWhenNotFound() {
        server.stubFixtureAt("notfound-webhook.http");
        assertThat(() -> client.webhooks.getWebhook(1010, 1),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateWebhook() {
        server.stubFixtureAt("createWebhook/created.http");
        Webhook webhook = client.webhooks.createWebhook(1010, "https://webhook.test").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/webhooks"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), hasEntry("url", "https://webhook.test"));
        assertThat(webhook.getId(), is(1L));
        assertThat(webhook.getUrl(), is("https://webhook.test"));
    }

    @Test
    public void testDeleteWebhook() {
        server.stubFixtureAt("deleteWebhook/success.http");
        client.webhooks.deleteWebhook(1010, 1);
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/webhooks/1"));
    }
}
