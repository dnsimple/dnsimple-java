package com.dnsimple.endpoints.test;

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

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TemplatesEndpoint implements Templates {

  public ListTemplatesResponse listTemplates(String accountId) throws DnsimpleException, IOException {
    return new ListTemplatesResponse();
  }

  public ListTemplatesResponse listTemplates(String accountId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListTemplatesResponse();
  }

  public GetTemplateResponse getTemplate(String accountId, String templateId) throws DnsimpleException, IOException {
    return new GetTemplateResponse();
  }

  public CreateTemplateResponse createTemplate(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new CreateTemplateResponse();
  }

  public UpdateTemplateResponse updateTemplate(String accountId, String templateId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new UpdateTemplateResponse();
  }

  public DeleteTemplateResponse deleteTemplate(String accountId, String templateId) throws DnsimpleException, IOException {
    return new DeleteTemplateResponse();
  }

  public ApplyTemplateResponse applyTemplate(String accountId, String templateId, String domainId) throws DnsimpleException, IOException {
    return new ApplyTemplateResponse();
  }

  public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId) throws DnsimpleException, IOException {
    return new ListTemplateRecordsResponse();
  }

  public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId, Map<String,Object> options) throws DnsimpleException, IOException {
    return new ListTemplateRecordsResponse();
  }

  public GetTemplateRecordResponse getTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException {
    return new GetTemplateRecordResponse();
  }

  public CreateTemplateRecordResponse createTemplateRecord(String accountId, String templateId, Map<String,Object> attributes) throws DnsimpleException, IOException {
    return new CreateTemplateRecordResponse();
  }

  public DeleteTemplateRecordResponse deleteTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException {
    return new DeleteTemplateRecordResponse();
  }

}
