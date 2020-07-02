package com.dnsimple.endpoints.http;

import com.dnsimple.Templates;
import com.dnsimple.data.Template;
import com.dnsimple.data.TemplateRecord;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static com.dnsimple.endpoints.http.java11.HttpMethod.GET;
import static com.dnsimple.endpoints.http.java11.HttpMethod.POST;

public class TemplatesEndpoint implements Templates {
    private final HttpEndpointClient client;

    public TemplatesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public PaginatedResponse<Template> listTemplates(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return listTemplates(accountId, null);
    }

    public PaginatedResponse<Template> listTemplates(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.page(GET, accountId + "/templates", options, Template.class);
    }

    public SimpleResponse<Template> getTemplate(String accountId, String templateId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/templates/" + templateId, null, Collections.emptyMap(), Template.class);
    }

    public SimpleResponse<Template> createTemplate(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/templates", attributes, null, Template.class);
    }

    public SimpleResponse<Template> updateTemplate(String accountId, String templateId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.patchSimple(accountId + "/templates/" + templateId, null, attributes, Template.class);
    }

    public EmptyResponse deleteTemplate(String accountId, String templateId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/templates/" + templateId, null);
    }

    public EmptyResponse applyTemplate(String accountId, String templateId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return client.empty(POST, accountId + "/domains/" + domainId + "/templates/" + templateId, null, null);
    }
    // region Template records

    public PaginatedResponse<TemplateRecord> listTemplateRecords(String accountId, String templateId) throws DnsimpleException, IOException, InterruptedException {
        return listTemplateRecords(accountId, templateId, null);
    }

    public PaginatedResponse<TemplateRecord> listTemplateRecords(String accountId, String templateId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return client.page(GET, accountId + "/templates/" + templateId + "/records", options, TemplateRecord.class);
    }

    public SimpleResponse<TemplateRecord> getTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException, InterruptedException {
        return client.simple(GET, accountId + "/templates/" + templateId + "/records/" + recordId, null, Collections.emptyMap(), TemplateRecord.class);
    }

    public SimpleResponse<TemplateRecord> createTemplateRecord(String accountId, String templateId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return client.postSimple(accountId + "/templates/" + templateId + "/records", attributes, null, TemplateRecord.class);
    }

    public EmptyResponse deleteTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException, InterruptedException {
        return client.deleteEmpty(accountId + "/templates/" + templateId + "/records/" + recordId, null);
    }
    // endregion
}
