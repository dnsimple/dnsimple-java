package com.dnsimple;

import com.dnsimple.data.Webhook;
import com.dnsimple.exception.ResourceNotFoundException;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WebhooksTest extends DnsimpleTestBase {
    @Test
    public void testListWebhooksSupportsExtraRequestOptions() {
        client.webhooks.listWebhooks("1010", singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/webhooks?foo=bar"));
    }

    @Test
    public void testListWebhooksProducesWebhookList() {
        server.stubFixtureAt("listWebhooks/success.http");
        List<Webhook> webhooks = client.webhooks.listWebhooks("1010").getData();
        assertThat(webhooks, hasSize(2));
        assertThat(webhooks.get(0).getId(), is(1));
    }

    @Test
    public void testGetWebhook() {
        server.stubFixtureAt("getWebhook/success.http");
        Webhook webhook = client.webhooks.getWebhook("1010", "1").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/webhooks/1"));
        assertThat(webhook.getId(), is(1));
        assertThat(webhook.getUrl(), is("https://webhook.test"));
    }

    @Test
    public void testGetWebhookWhenNotFound() {
        server.stubFixtureAt("notfound-webhook.http");
        assertThat(() -> client.webhooks.getWebhook("1010", "1"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateWebhook() {
        server.stubFixtureAt("createWebhook/created.http");
        Map<String, Object> attributes = singletonMap("url", "https://webhook.test");
        Webhook webhook = client.webhooks.createWebhook("1010", attributes).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/webhooks"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
        assertThat(webhook.getId(), is(1));
        assertThat(webhook.getUrl(), is("https://webhook.test"));
    }

    @Test
    public void testDeleteWebhook() {
        server.stubFixtureAt("deleteWebhook/success.http");
        client.webhooks.deleteWebhook("1010", "1");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/webhooks/1"));
    }
}
