package com.dnsimple.endpoints;

import com.dnsimple.data.Template;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.thrownException;
import static java.time.ZoneOffset.UTC;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TemplatesTest extends DnsimpleTestBase {
    @Test
    public void testListTemplatesSupportsPagination() {
        client.templates.listTemplates(1, new ListOptions.Builder().page(1).build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates?page=1"));
    }

    @Test
    public void testListTemplatesSupportsExtraRequestOptions() {
        client.templates.listTemplates(1, new ListOptions.Builder().filter("foo", "bar").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates?foo=bar"));
    }

    @Test
    public void testListTemplatesSupportsSorting() {
        client.templates.listTemplates(1, new ListOptions.Builder().sortAsc("name").build());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates?sort=name%3Aasc"));
    }

    @Test
    public void testListTemplatesProducesTemplateList() {
        server.stubFixtureAt("listTemplates/success.http");
        List<Template> templates = client.templates.listTemplates(1).getData();
        assertThat(templates, hasSize(2));
        assertThat(templates.get(0).getId(), is(1L));
    }

    @Test
    public void testListTemplatesExposesPaginationInfo() {
        server.stubFixtureAt("listTemplates/success.http");
        PaginatedResponse<Template> response = client.templates.listTemplates(1);
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetTemplate() {
        server.stubFixtureAt("getTemplate/success.http");
        Template template = client.templates.getTemplate(1010, "1").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1"));
        assertThat(template.getId(), is(1L));
        assertThat(template.getAccountId(), is(1010L));
        assertThat(template.getName(), is("Alpha"));
        assertThat(template.getSid(), is("alpha"));
        assertThat(template.getDescription(), is("An alpha template."));
        assertThat(template.getCreatedAt(), is(OffsetDateTime.of(2016, 3, 22, 11, 8, 58, 0, UTC)));
        assertThat(template.getUpdatedAt(), is(OffsetDateTime.of(2016, 3, 22, 11, 8, 58, 0, UTC)));
    }

    @Test
    public void testGetTemplateWhenNotFound() {
        server.stubFixtureAt("notfound-template.http");
        assertThat(() -> client.templates.getTemplate(1010, "2"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateTemplateSendsCorrectRequest() {
        server.stubFixtureAt("createTemplate/created.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "A Template");
        attributes.put("short_name", "a_template");
        client.templates.createTemplate(1010, attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test
    public void testCreateTemplateProducesTemplate() {
        server.stubFixtureAt("createTemplate/created.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "A Template");
        attributes.put("short_name", "a_template");
        SimpleResponse<Template> response = client.templates.createTemplate(1, attributes);
        assertThat(response.getData().getId(), is(1L));
    }

    @Test
    public void testUpdateTemplate() {
        server.stubFixtureAt("updateTemplate/success.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", "A Template");
        attributes.put("short_name", "a_template");
        Template template = client.templates.updateTemplate(1010, "1", attributes).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(PATCH));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
        assertThat(template.getId(), is(1L));
    }

    @Test
    public void testDeleteTemplate() {
        server.stubFixtureAt("deleteTemplate/success.http");
        client.templates.deleteTemplate(1010, "1");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1"));
    }

    @Test
    public void testApplyTemplate() {
        server.stubFixtureAt("applyTemplate/success.http");
        client.templates.applyTemplate(1010, "1", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/example.com/templates/1"));
    }
}
