package com.dnsimple.endpoints;

import com.dnsimple.data.TemplateRecord;
import com.dnsimple.request.ListOptions;
import com.dnsimple.request.TemplateRecordOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.number;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TemplateRecordsTest extends DnsimpleTestBase {
    @Test
    public void testListTemplatesSupportsPagination() {
        client.templates.listTemplateRecords(1, "2", new ListOptions.Builder().page(1).build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates/2/records?page=1"));
    }

    @Test
    public void testListTemplatesSupportsExtraRequestOptions() {
        client.templates.listTemplateRecords(1, "2", new ListOptions.Builder().filter("foo", "bar").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates/2/records?foo=bar"));
    }

    @Test
    public void testListTemplatesSupportsSorting() {
        client.templates.listTemplateRecords(1, "2", new ListOptions.Builder().sortAsc("name").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates/2/records?sort=name%3Aasc"));
    }

    @Test
    public void testListTemplateRecordsProducesTemplateRecordList() {
        server.stubFixtureAt("listTemplateRecords/success.http");
        List<TemplateRecord> templateRecords = client.templates.listTemplateRecords(1, "2").getData();
        assertThat(templateRecords, hasSize(2));
        assertThat(templateRecords.get(0).getId(), is(296L));
    }

    @Test
    public void testListTemplateRecordsExposesPaginationInfo() {
        server.stubFixtureAt("listTemplateRecords/success.http");
        PaginatedResponse<TemplateRecord> response = client.templates.listTemplateRecords(1, "2");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetTemplateRecord() {
        server.stubFixtureAt("getTemplateRecord/success.http");
        TemplateRecord record = client.templates.getTemplateRecord(1010, "1", 301).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1/records/301"));
        assertThat(record.getId(), is(301L));
        assertThat(record.getTemplateId(), is(268L));
        assertThat(record.getName(), isEmptyOrNullString());
        assertThat(record.getContent(), is("mx.example.com"));
        assertThat(record.getTtl(), is(600));
        assertThat(record.getPriority(), is(10));
        assertThat(record.getType(), is("MX"));
        assertThat(record.getCreatedAt(), is(OffsetDateTime.of(2016, 5, 3, 8, 3, 26, 0, UTC)));
        assertThat(record.getUpdatedAt(), is(OffsetDateTime.of(2016, 5, 3, 8, 3, 26, 0, UTC)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreateTemplateRecordSendsCorrectRequest() {
        server.stubFixtureAt("createTemplateRecord/created.http");
        var options = TemplateRecordOptions.of("www", "ALIAS", "example.com").ttl(3600).priority(42);
        client.templates.createTemplateRecord(1010, "1", options);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1/records"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), allOf(
                hasEntry("name", "www"),
                hasEntry("type", "ALIAS"),
                hasEntry("content", "example.com"),
                hasEntry(is("ttl"), number(3600)),
                hasEntry(is("priority"), number(42))
        ));
    }

    @Test
    public void testCreateTemplateRecordProducesTemplateRecord() {
        server.stubFixtureAt("createTemplateRecord/created.http");
        var options = TemplateRecordOptions.of("www", "ALIAS", "example.com").ttl(3600).priority(42);
        SimpleResponse<TemplateRecord> response = client.templates.createTemplateRecord(1, "300", options);
        assertThat(response.getData().getId(), is(300L));
    }

    @Test
    public void testDeleteTemplateRecord() {
        server.stubFixtureAt("deleteTemplateRecord/success.http");
        client.templates.deleteTemplateRecord(1010, "1", 300);
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1/records/300"));
    }
}
