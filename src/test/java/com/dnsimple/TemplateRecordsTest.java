package com.dnsimple;

import com.dnsimple.data.TemplateRecord;
import com.dnsimple.data.Pagination;
import com.dnsimple.response.ListTemplateRecordsResponse;
import com.dnsimple.response.GetTemplateRecordResponse;
import com.dnsimple.response.CreateTemplateRecordResponse;
import com.dnsimple.response.DeleteTemplateRecordResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class TemplateRecordsTest extends DnsimpleTestBase {

  @Test
  public void testListTemplatesSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/templates/2/records?page=1");

    String accountId = "1";
    String templateId = "2";
    HashMap<String, Object> options = new HashMap<String, Object>();

    options.put("page", 1);
    client.templates.listTemplateRecords(accountId, templateId, options);
  }

  @Test
  public void testListTemplatesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/templates/2/records?foo=bar");

    String accountId = "1";
    String templateId = "2";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");

    client.templates.listTemplateRecords(accountId, templateId, options);
  }

  @Test
  public void testListTemplatesSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/templates/2/records?sort=name%3Aasc");

    String accountId = "1";
    String templateId = "2";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "name:asc");

    client.templates.listTemplateRecords(accountId, templateId, options);
  }

  @Test
  public void testListTemplateRecordsProducesTemplateRecordList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listTemplateRecords/success.http"));

    String accountId = "1";
    String templateId = "2";

    ListTemplateRecordsResponse response = client.templates.listTemplateRecords(accountId, templateId);

    List<TemplateRecord> templateRecords = response.getData();
    assertEquals(2, templateRecords.size());
    assertEquals(296, templateRecords.get(0).getId().intValue());
  }

  @Test
  public void testListTemplateRecordsExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listTemplateRecords/success.http"));

    String accountId = "1";
    String templateId = "2";

    ListTemplateRecordsResponse response = client.templates.listTemplateRecords(accountId, templateId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testGetTemplateRecord() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/templates/1/records/301", HttpMethods.GET, resource("getTemplateRecord/success.http"));

    String accountId = "1010";
    String templateId = "1";
    String recordId = "301";

    GetTemplateRecordResponse response = client.templates.getTemplateRecord(accountId, templateId, recordId);

    TemplateRecord record = response.getData();
    assertEquals(301, record.getId().intValue());
    assertEquals(268, record.getTemplateId().intValue());
    assertEquals("", record.getName());
    assertEquals("mx.example.com", record.getContent());
    assertEquals(600, record.getTtl().intValue());
    assertEquals(10, record.getPriority().intValue());
    assertEquals("MX", record.getType());
    assertEquals("2016-05-03T08:03:26.444Z", record.getCreatedAt());
    assertEquals("2016-05-03T08:03:26.444Z", record.getUpdatedAt());
  }

  @Test
  public void testCreateTemplateRecordSendsCorrectRequest() throws DnsimpleException, IOException {
    String accountId = "1010";
    String templateId = "1";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "www");
    attributes.put("content", "example.com");

    Client client = expectClient("https://api.dnsimple.com/v2/1010/templates/1/records", HttpMethods.POST, new HashMap<String, Object>(), attributes);

    client.templates.createTemplateRecord(accountId, templateId, attributes);
  }

  @Test
  public void testCreateTemplateRecordProducesTemplateRecord() throws DnsimpleException, IOException {
    Client client = mockClient(resource("createTemplateRecord/created.http"));

    String accountId = "1";
    String templateId = "300";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "www");
    attributes.put("content", "example.com");

    CreateTemplateRecordResponse response = client.templates.createTemplateRecord(accountId, templateId, attributes);
    TemplateRecord record = response.getData();
    assertEquals(300, record.getId().intValue());
  }

  @Test
  public void testDeleteTemplateRecord() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/templates/1/records/300", HttpMethods.DELETE, resource("deleteTemplateRecord/success.http"));

    String accountId = "1010";
    String templateId = "1";
    String recordId = "300";

    DeleteTemplateRecordResponse response = client.templates.deleteTemplateRecord(accountId, templateId, recordId);
    assertEquals(null, response.getData());
  }
}
