package com.dnsimple.endpoints.http;

import com.dnsimple.Templates;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.util.Map;

public class TemplatesEndpoint implements Templates {
    private HttpEndpointClient client;

    public TemplatesEndpoint(HttpEndpointClient client) {
        this.client = client;
    }

    public ListTemplatesResponse listTemplates(String accountId) throws DnsimpleException, IOException, InterruptedException {
        return listTemplates(accountId, null);
    }

    public ListTemplatesResponse listTemplates(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListTemplatesResponse) client.get(accountId + "/templates", options, ListTemplatesResponse.class);
    }

    public GetTemplateResponse getTemplate(String accountId, String templateId) throws DnsimpleException, IOException, InterruptedException {
        return (GetTemplateResponse) client.get(accountId + "/templates/" + templateId, null, GetTemplateResponse.class);
    }

    public CreateTemplateResponse createTemplate(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (CreateTemplateResponse) client.post(accountId + "/templates", attributes, null, CreateTemplateResponse.class);
    }

    public UpdateTemplateResponse updateTemplate(String accountId, String templateId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (UpdateTemplateResponse) client.patch(accountId + "/templates/" + templateId, attributes, null, UpdateTemplateResponse.class);
    }

    public DeleteTemplateResponse deleteTemplate(String accountId, String templateId) throws DnsimpleException, IOException, InterruptedException {
        return (DeleteTemplateResponse) client.delete(accountId + "/templates/" + templateId, null, DeleteTemplateResponse.class);
    }

    public ApplyTemplateResponse applyTemplate(String accountId, String templateId, String domainId) throws DnsimpleException, IOException, InterruptedException {
        return (ApplyTemplateResponse) client.post(accountId + "/domains/" + domainId + "/templates/" + templateId, null, null, ApplyTemplateResponse.class);
    }
    // region Template records

    public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId) throws DnsimpleException, IOException, InterruptedException {
        return listTemplateRecords(accountId, templateId, null);
    }

    public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException {
        return (ListTemplateRecordsResponse) client.get(accountId + "/templates/" + templateId + "/records", options, ListTemplateRecordsResponse.class);
    }

    public GetTemplateRecordResponse getTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException, InterruptedException {
        return (GetTemplateRecordResponse) client.get(accountId + "/templates/" + templateId + "/records/" + recordId, null, GetTemplateRecordResponse.class);
    }

    public CreateTemplateRecordResponse createTemplateRecord(String accountId, String templateId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException {
        return (CreateTemplateRecordResponse) client.post(accountId + "/templates/" + templateId + "/records", attributes, null, CreateTemplateRecordResponse.class);
    }

    public DeleteTemplateRecordResponse deleteTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException, InterruptedException {
        return (DeleteTemplateRecordResponse) client.delete(accountId + "/templates/" + templateId + "/records/" + recordId, null, DeleteTemplateRecordResponse.class);
    }
    // endregion
}
