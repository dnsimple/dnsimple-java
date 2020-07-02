package com.dnsimple;

import com.dnsimple.data.TemplateRecord;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.exception.DnsimpleException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TemplateRecordsTest extends DnsimpleTestBase {
    @Test
    public void testListTemplatesSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
        client.templates.listTemplateRecords("1", "2", singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates/2/records?page=1"));
    }

    @Test
    public void testListTemplatesSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
        client.templates.listTemplateRecords("1", "2", singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates/2/records?foo=bar"));
    }

    @Test
    public void testListTemplatesSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
        client.templates.listTemplateRecords("1", "2", singletonMap("sort", "name:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates/2/records?sort=name%3Aasc"));
    }

    @Test
    public void testListTemplateRecordsProducesTemplateRecordList() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listTemplateRecords/success.http");
        List<TemplateRecord> templateRecords = client.templates.listTemplateRecords("1", "2").getData();
        assertThat(templateRecords, hasSize(2));
        assertThat(templateRecords.get(0).getId(), is(296));
    }

    @Test
    public void testListTemplateRecordsExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listTemplateRecords/success.http");
        PaginatedResponse<TemplateRecord> response = client.templates.listTemplateRecords("1", "2");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetTemplateRecord() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getTemplateRecord/success.http");
        TemplateRecord record = client.templates.getTemplateRecord("1010", "1", "301").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1/records/301"));
        assertThat(record.getId(), is(301));
        assertThat(record.getTemplateId(), is(268));
        assertThat(record.getName(), isEmptyOrNullString());
        assertThat(record.getContent(), is("mx.example.com"));
        assertThat(record.getTtl(), is(600));
        assertThat(record.getPriority(), is(10));
        assertThat(record.getType(), is("MX"));
        assertThat(record.getCreatedAt(), is("2016-05-03T08:03:26Z"));
        assertThat(record.getUpdatedAt(), is("2016-05-03T08:03:26Z"));
    }

    @Test
    public void testCreateTemplateRecordSendsCorrectRequest() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("createTemplateRecord/created.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "www");
        attributes.put("content", "example.com");
        client.templates.createTemplateRecord("1010", "1", attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1/records"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test
    public void testCreateTemplateRecordProducesTemplateRecord() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("createTemplateRecord/created.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "www");
        attributes.put("content", "example.com");
        SimpleResponse<TemplateRecord> response = client.templates.createTemplateRecord("1", "300", attributes);
        assertThat(response.getData().getId(), is(300));
    }

    @Test
    public void testDeleteTemplateRecord() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("deleteTemplateRecord/success.http");
        client.templates.deleteTemplateRecord("1010", "1", "300");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1/records/300"));
    }
}
