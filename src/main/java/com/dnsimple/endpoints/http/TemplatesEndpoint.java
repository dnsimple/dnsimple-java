package com.dnsimple.endpoints.http;

import com.dnsimple.Templates;
import com.dnsimple.data.Template;
import com.dnsimple.data.TemplateRecord;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.util.Collections.emptyMap;

public class TemplatesEndpoint implements Templates {
    private final HttpEndpointClient client;

    public TemplatesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Template> listTemplates(String accountId) {
        return client.page(GET, accountId + "/templates", emptyMap(), null, Template.class);
    }

    public PaginatedResponse<Template> listTemplates(String accountId, Map<String, Object> options) {
        return client.page(GET, accountId + "/templates", options, null, Template.class);
    }

    public SimpleResponse<Template> getTemplate(String accountId, String templateId) {
        return client.simple(GET, accountId + "/templates/" + templateId, emptyMap(), null, Template.class);
    }

    public SimpleResponse<Template> createTemplate(String accountId, Map<String, Object> attributes) {
        return client.simple(POST, accountId + "/templates", emptyMap(), attributes, Template.class);
    }

    public SimpleResponse<Template> updateTemplate(String accountId, String templateId, Map<String, Object> attributes) {
        return client.simple(PATCH, accountId + "/templates/" + templateId, emptyMap(), attributes, Template.class);
    }

    public EmptyResponse deleteTemplate(String accountId, String templateId) {
        return client.empty(DELETE, accountId + "/templates/" + templateId, emptyMap(), null);
    }

    public EmptyResponse applyTemplate(String accountId, String templateId, String domainId) {
        return client.empty(POST, accountId + "/domains/" + domainId + "/templates/" + templateId, emptyMap(), null);
    }
    // region Template records

    public PaginatedResponse<TemplateRecord> listTemplateRecords(String accountId, String templateId) {
        return client.page(GET, accountId + "/templates/" + templateId + "/records", emptyMap(), null, TemplateRecord.class);
    }

    public PaginatedResponse<TemplateRecord> listTemplateRecords(String accountId, String templateId, Map<String, Object> options) {
        return client.page(GET, accountId + "/templates/" + templateId + "/records", options, null, TemplateRecord.class);
    }

    public SimpleResponse<TemplateRecord> getTemplateRecord(String accountId, String templateId, String recordId) {
        return client.simple(GET, accountId + "/templates/" + templateId + "/records/" + recordId, emptyMap(), null, TemplateRecord.class);
    }

    public SimpleResponse<TemplateRecord> createTemplateRecord(String accountId, String templateId, Map<String, Object> attributes) {
        return client.simple(POST, accountId + "/templates/" + templateId + "/records", emptyMap(), attributes, TemplateRecord.class);
    }

    public EmptyResponse deleteTemplateRecord(String accountId, String templateId, String recordId) {
        return client.empty(DELETE, accountId + "/templates/" + templateId + "/records/" + recordId, emptyMap(), null);
    }
    // endregion
}
