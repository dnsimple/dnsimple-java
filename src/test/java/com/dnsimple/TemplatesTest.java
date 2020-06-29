package com.dnsimple;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.DELETE;
import static com.dnsimple.tools.HttpMethod.GET;
import static com.dnsimple.tools.HttpMethod.PATCH;
import static com.dnsimple.tools.HttpMethod.POST;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import com.dnsimple.data.Template;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.response.ApplyTemplateResponse;
import com.dnsimple.response.CreateTemplateResponse;
import com.dnsimple.response.DeleteTemplateResponse;
import com.dnsimple.response.ListTemplatesResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class TemplatesTest extends DnsimpleTestBase {

  @Test
  public void testListTemplatesSupportsPagination() throws DnsimpleException, IOException {
    client.templates.listTemplates("1", singletonMap("page", 1));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates?page=1"));
  }

  @Test
  public void testListTemplatesSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    client.templates.listTemplates("1", singletonMap("foo", "bar"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates?foo=bar"));
  }

  @Test
  public void testListTemplatesSupportsSorting() throws DnsimpleException, IOException {
    client.templates.listTemplates("1", singletonMap("sort", "name:asc"));
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1/templates?sort=name:asc"));
  }

  @Test
  public void testListTemplatesProducesTemplateList() throws DnsimpleException, IOException {
    server.stubFixtureAt("listTemplates/success.http");

    List<Template> templates = client.templates.listTemplates("1").getData();
    assertThat(templates, hasSize(2));
    assertThat(templates.get(0).getId(), is(1));
  }

  @Test
  public void testListTemplatesExposesPaginationInfo() throws DnsimpleException, IOException {
    server.stubFixtureAt("listTemplates/success.http");

    ListTemplatesResponse response = client.templates.listTemplates("1");
    assertThat(response.getPagination().getCurrentPage(), is(1));
  }

  @Test
  public void testGetTemplate() throws DnsimpleException, IOException {
    server.stubFixtureAt("getTemplate/success.http");

    Template template = client.templates.getTemplate("1010", "1").getData();
    assertThat(server.getRecordedRequest().getMethod(), is(GET));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1"));
    assertThat(template.getId(), is(1));
    assertThat(template.getAccountId(), is(1010));
    assertThat(template.getName(), is("Alpha"));
    assertThat(template.getSid(), is("alpha"));
    assertThat(template.getDescription(), is("An alpha template."));
    assertThat(template.getCreatedAt(), is("2016-03-22T11:08:58Z"));
    assertThat(template.getUpdatedAt(), is("2016-03-22T11:08:58Z"));
  }

  @Test
  public void testGetTemplateWhenNotFound() {
    server.stubFixtureAt("notfound-template.http");

    assertThat(() -> client.templates.getTemplate("1010", "2"),
        thrownException(is(instanceOf(ResourceNotFoundException.class))));
  }

  @Test
  public void testCreateTemplateSendsCorrectRequest() throws DnsimpleException, IOException {
    server.stubFixtureAt("createTemplate/created.http");

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("name", "A Template");
    attributes.put("short_name", "a_template");
    client.templates.createTemplate("1010", attributes);
    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates"));
    assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
  }

  @Test
  public void testCreateTemplateProducesTemplate() throws DnsimpleException, IOException {
    server.stubFixtureAt("createTemplate/created.http");

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("name", "A Template");
    attributes.put("short_name", "a_template");
    CreateTemplateResponse response = client.templates.createTemplate("1", attributes);
    assertThat(response.getData().getId(), is(1));
  }

  @Test
  public void testUpdateTemplate() throws DnsimpleException, IOException {
    server.stubFixtureAt("updateTemplate/success.http");

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("name", "A Template");
    attributes.put("short_name", "a_template");
    Template template = client.templates.updateTemplate("1010", "1", attributes).getData();
    assertThat(server.getRecordedRequest().getMethod(), is(PATCH));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1"));
    assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    assertThat(template.getId(), is(1));
  }

  @Test
  public void testDeleteTemplate() throws DnsimpleException, IOException {
    server.stubFixtureAt("deleteTemplate/success.http");

    DeleteTemplateResponse response = client.templates.deleteTemplate("1010", "1");
    assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/templates/1"));
    assertThat(response.getData(), is(nullValue()));
  }

  @Test
  public void testApplyTemplate() throws DnsimpleException, IOException {
    server.stubFixtureAt("applyTemplate/success.http");

    ApplyTemplateResponse response = client.templates.applyTemplate("1010", "1", "example.com");
    assertThat(server.getRecordedRequest().getMethod(), is(POST));
    assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/example.com/templates/1"));
    assertThat(response.getData(), is(nullValue()));
  }
}
