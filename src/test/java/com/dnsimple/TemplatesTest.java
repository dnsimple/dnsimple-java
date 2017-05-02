package com.dnsimple;

import com.dnsimple.data.Template;
import com.dnsimple.data.Pagination;
import com.dnsimple.response.ListTemplatesResponse;
import com.dnsimple.response.GetTemplateResponse;
import com.dnsimple.response.CreateTemplateResponse;
import com.dnsimple.response.UpdateTemplateResponse;
import com.dnsimple.response.DeleteTemplateResponse;
import com.dnsimple.response.ApplyTemplateResponse;
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

public class TemplatesTest extends DnsimpleTestBase {

  @Test
  public void testListTemplatesSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/templates?page=1");

    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();

    options.put("page", 1);
    client.templates.listTemplates(accountId, options);
  }

  @Test
  public void testListTemplatesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/templates?foo=bar");

    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");

    client.templates.listTemplates(accountId, options);
  }

  @Test
  public void testListTemplatesSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/templates?sort=name%3Aasc");

    String accountId = "1";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "name:asc");

    client.templates.listTemplates(accountId, options);
  }

  @Test
  public void testListTemplatesProducesTemplateList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listTemplates/success.http"));

    String accountId = "1";

    ListTemplatesResponse response = client.templates.listTemplates(accountId);

    List<Template> templates = response.getData();
    assertEquals(2, templates.size());
    assertEquals(1, templates.get(0).getId().intValue());
  }

  @Test
  public void testListTemplatesExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listTemplates/success.http"));

    String accountId = "1";

    ListTemplatesResponse response = client.templates.listTemplates(accountId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testGetTemplate() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/templates/1", HttpMethods.GET, new HttpHeaders(), null, resource("getTemplate/success.http"));

    String accountId = "1010";
    String templateId = "1";

    GetTemplateResponse response = client.templates.getTemplate(accountId, templateId);

    Template template = response.getData();
    assertEquals(1, template.getId().intValue());
    assertEquals(1010, template.getAccountId().intValue());
    assertEquals("Alpha", template.getName());
    assertEquals("alpha", template.getShortName());
    assertEquals("An alpha template.", template.getDescription());
    assertEquals("2016-03-22T11:08:58.262Z", template.getCreatedAt());
    assertEquals("2016-03-22T11:08:58.262Z", template.getUpdatedAt());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetTemplateWhenNotFound() throws DnsimpleException, IOException {
    Client client = mockClient(resource("notfound-template.http"));

    String accountId = "1010";
    String templateId = "2";

    client.templates.getTemplate(accountId, templateId);
  }

  @Test
  public void testCreateTemplateSendsCorrectRequest() throws DnsimpleException, IOException {
    String accountId = "1010";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "A Template");
    attributes.put("short_name", "a_template");

    Client client = expectClient("https://api.dnsimple.com/v2/1010/templates", HttpMethods.POST, new HashMap<String, Object>(), attributes);

    client.templates.createTemplate(accountId, attributes);
  }

  @Test
  public void testCreateTemplateProducesTemplate() throws DnsimpleException, IOException {
    Client client = mockClient(resource("createTemplate/created.http"));

    String accountId = "1";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "A Template");
    attributes.put("short_name", "a_template");

    CreateTemplateResponse response = client.templates.createTemplate(accountId, attributes);
    Template template = response.getData();
    assertEquals(1, template.getId().intValue());
  }

  @Test
  public void testUpdateTemplate() throws DnsimpleException, IOException {
    String accountId = "1010";
    String templateId = "1";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("name", "A Template");
    attributes.put("short_name", "a_template");

    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/templates/1", HttpMethods.PATCH, new HttpHeaders(), attributes, resource("updateTemplate/success.http"));

    UpdateTemplateResponse response = client.templates.updateTemplate(accountId, templateId, attributes);
    Template template = response.getData();
    assertEquals(1, template.getId().intValue());
  }

  @Test
  public void testDeleteTemplate() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/templates/1", HttpMethods.DELETE, resource("deleteTemplate/success.http"));

    String accountId = "1010";
    String templateId = "1";

    DeleteTemplateResponse response = client.templates.deleteTemplate(accountId, templateId);
    assertEquals(null, response.getData());
  }

  @Test
  public void testApplyTemplate() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1010/domains/example.com/templates/1", HttpMethods.POST, resource("applyTemplate/success.http"));

    String accountId = "1010";
    String templateId = "1";
    String domainId = "example.com";

    ApplyTemplateResponse response = client.templates.applyTemplate(accountId, templateId, domainId);
    assertEquals(null, response.getData());
  }
}
