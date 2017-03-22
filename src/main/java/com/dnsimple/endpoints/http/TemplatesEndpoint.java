package com.dnsimple.endpoints.http;

import com.dnsimple.Templates;
import com.dnsimple.response.ListTemplatesResponse;
import com.dnsimple.response.GetTemplateResponse;
import com.dnsimple.response.CreateTemplateResponse;
import com.dnsimple.response.UpdateTemplateResponse;
import com.dnsimple.response.DeleteTemplateResponse;
import com.dnsimple.response.ApplyTemplateResponse;

import com.dnsimple.response.ListTemplateRecordsResponse;
import com.dnsimple.response.GetTemplateRecordResponse;
import com.dnsimple.response.CreateTemplateRecordResponse;
import com.dnsimple.response.DeleteTemplateRecordResponse;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TemplatesEndpoint implements Templates {
  private HttpEndpointClient client;

  public TemplatesEndpoint(HttpEndpointClient client) {
    this.client = client;
  }

  public ListTemplatesResponse listTemplates(String accountId) throws DnsimpleException, IOException {
    return listTemplates(accountId, null);
  }

  public ListTemplatesResponse listTemplates(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/templates", options);
    return (ListTemplatesResponse)client.parseResponse(response, ListTemplatesResponse.class);
  }

  public GetTemplateResponse getTemplate(String accountId, String templateId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/templates/" + templateId);
    return (GetTemplateResponse)client.parseResponse(response, GetTemplateResponse.class);
  }

  public CreateTemplateResponse createTemplate(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/templates", attributes);
    return (CreateTemplateResponse)client.parseResponse(response, CreateTemplateResponse.class);
  }

  public UpdateTemplateResponse updateTemplate(String accountId, String templateId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.patch(accountId + "/templates/" + templateId, attributes);
    return (UpdateTemplateResponse)client.parseResponse(response, UpdateTemplateResponse.class);
  }

  public DeleteTemplateResponse deleteTemplate(String accountId, String templateId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/templates/" + templateId);
    return (DeleteTemplateResponse)client.parseResponse(response, DeleteTemplateResponse.class);
  }

  public ApplyTemplateResponse applyTemplate(String accountId, String templateId, String domainId) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/domains/" + domainId + "/templates/" + templateId);
    return (ApplyTemplateResponse)client.parseResponse(response, ApplyTemplateResponse.class);
  }


  // Template records

  public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId) throws DnsimpleException, IOException {
    return listTemplateRecords(accountId, templateId, null);
  }

  public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId, Map<String,Object> options) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/templates/" + templateId + "/records", options);
    return (ListTemplateRecordsResponse)client.parseResponse(response, ListTemplateRecordsResponse.class);
  }

  public GetTemplateRecordResponse getTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException {
    HttpResponse response = client.get(accountId + "/templates/" + templateId + "/records/" + recordId);
    return (GetTemplateRecordResponse)client.parseResponse(response, GetTemplateRecordResponse.class);
  }

  public CreateTemplateRecordResponse createTemplateRecord(String accountId, String templateId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    HttpResponse response = client.post(accountId + "/templates/" + templateId + "/records", attributes);
    return (CreateTemplateRecordResponse)client.parseResponse(response, CreateTemplateRecordResponse.class);
  }

  public DeleteTemplateRecordResponse deleteTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException {
    HttpResponse response = client.delete(accountId + "/templates/" + templateId + "/records/" + recordId);
    return (DeleteTemplateRecordResponse)client.parseResponse(response, DeleteTemplateRecordResponse.class);
  }
}
