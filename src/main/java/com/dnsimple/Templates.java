package com.dnsimple;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

/**
 * Provides access to the DNSimple Templates API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/templates">https://developer.dnsimple.com/v2/templates</a>
 */
public interface Templates {

  /**
   * Lists the templates in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/#list">https://developer.dnsimple.com/v2/templates/#list</a>
   *
   * @param accountId The account ID
   * @return The list templates response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListTemplatesResponse listTemplates(String accountId) throws DnsimpleException, IOException;

  /**
   * Lists the templates in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/#list">https://developer.dnsimple.com/v2/templates/#list</a>
   *
   * @param accountId The account ID
   * @param options A Map of options to pass to the templates API
   * @return The list templates response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListTemplatesResponse listTemplates(String accountId, Map<String,Object> options) throws DnsimpleException, IOException;

  /**
   * Get a specific template associated to an account using the templates's ID.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/#get">https://developer.dnsimple.com/v2/templates/#list</a>
   *
   * @param accountId The account ID
   * @param templateId The template ID
   * @return The get template response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetTemplateResponse getTemplate(String accountId, String templateId) throws DnsimpleException, IOException;

  /**
   * Create a template in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/#create">https://developer.dnsimple.com/v2/templates/#create</a>
   *
   * @param accountId The account ID
   * @param attributes A map of attributes to contruct the template
   * @return The create template response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public CreateTemplateResponse createTemplate(String accountId, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Update a template in the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/#update">https://developer.dnsimple.com/v2/templates/#update</a>
   *
   * @param accountId The account ID
   * @param templateId The template ID
   * @param attributes A map of attributes to update the template
   * @return The update template response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public UpdateTemplateResponse updateTemplate(String accountId, String templateId, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Delete a template from the account.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/#delete">https://developer.dnsimple.com/v2/templates/#delete</a>
   *
   * @param accountId The account ID
   * @param templateId The template ID
   * @return The delete template response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DeleteTemplateResponse deleteTemplate(String accountId, String templateId) throws DnsimpleException, IOException;

  /**
   * Apply a template from the account to the domain
   *
   * @see <a href="https://developer.dnsimple.com/v2/domains/templates/#apply">https://developer.dnsimple.com/v2/domains/templates/#apply</a>
   * @param accountId The account ID
   * @param templateId The template ID
   * @param domainId The domain ID
   * @return The apply template response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ApplyTemplateResponse applyTemplate(String accountId, String templateId, String domainId) throws DnsimpleException, IOException;

  // Template records

  /**
   * Lists the records in the template.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/records#list">https://developer.dnsimple.com/v2/templates/records#list</a>
   *
   * @param accountId The account ID
   * @param templateId The template ID
   * @return The list template records response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId) throws DnsimpleException, IOException;

  /**
   * Lists the records in the template.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/records#list">https://developer.dnsimple.com/v2/templates/records#list</a>
   *
   * @param accountId The account ID
   * @param templateId The template ID
   * @param options A Map of options to pass to the templates API
   * @return The list template records response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId, Map<String,Object> options) throws DnsimpleException, IOException;

  /**
   * Get a specific record associated to a template using the record's ID.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/records/#get">https://developer.dnsimple.com/v2/templates/records/#get</a>
   *
   * @param accountId The account ID
   * @param templateId The template ID
   * @param recordId The record ID
   * @return The get template record response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public GetTemplateRecordResponse getTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException;

  /**
   * Create a record in the template.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/records#create">https://developer.dnsimple.com/v2/templates/records#create</a>
   *
   * @param accountId The account ID
   * @param templateId The template ID
   * @param attributes A map of attributes to contruct the template record
   * @return The create template record response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public CreateTemplateRecordResponse createTemplateRecord(String accountId, String templateId, Map<String,Object> attributes) throws DnsimpleException, IOException;

  /**
   * Delete a record from the template.
   *
   * @see <a href="https://developer.dnsimple.com/v2/templates/records#delete">https://developer.dnsimple.com/v2/templates/records#delete</a>
   *
   * @param accountId The account ID
   * @param templateId The template ID
   * @param recordId The record ID
   * @return The delete template record response
   * @throws DnsimpleException Any API errors
   * @throws IOException Any IO errors
   */
  public DeleteTemplateRecordResponse deleteTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException;

}
