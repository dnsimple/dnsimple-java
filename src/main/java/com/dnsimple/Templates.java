package com.dnsimple;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.*;

import java.io.IOException;
import java.util.Map;

/**
 * Provides access to the DNSimple Templates API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/templates">https://developer.dnsimple.com/v2/templates</a>
 */
public interface Templates {
    /**
     * Lists the templates in the account.
     *
     * @param accountId The account ID
     * @return The list templates response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/#list">https://developer.dnsimple.com/v2/templates/#list</a>
     */
    public ListTemplatesResponse listTemplates(String accountId) throws DnsimpleException, IOException;

    /**
     * Lists the templates in the account.
     *
     * @param accountId The account ID
     * @param options   A Map of options to pass to the templates API
     * @return The list templates response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/#list">https://developer.dnsimple.com/v2/templates/#list</a>
     */
    public ListTemplatesResponse listTemplates(String accountId, Map<String, Object> options) throws DnsimpleException, IOException;

    /**
     * Get a specific template associated to an account using the templates's ID.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @return The get template response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/#get">https://developer.dnsimple.com/v2/templates/#list</a>
     */
    public GetTemplateResponse getTemplate(String accountId, String templateId) throws DnsimpleException, IOException;

    /**
     * Create a template in the account.
     *
     * @param accountId  The account ID
     * @param attributes A map of attributes to contruct the template
     * @return The create template response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/#create">https://developer.dnsimple.com/v2/templates/#create</a>
     */
    public CreateTemplateResponse createTemplate(String accountId, Map<String, Object> attributes) throws DnsimpleException, IOException;

    /**
     * Update a template in the account.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param attributes A map of attributes to update the template
     * @return The update template response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/#update">https://developer.dnsimple.com/v2/templates/#update</a>
     */
    public UpdateTemplateResponse updateTemplate(String accountId, String templateId, Map<String, Object> attributes) throws DnsimpleException, IOException;

    /**
     * Delete a template from the account.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @return The delete template response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/#delete">https://developer.dnsimple.com/v2/templates/#delete</a>
     */
    public DeleteTemplateResponse deleteTemplate(String accountId, String templateId) throws DnsimpleException, IOException;

    /**
     * Apply a template from the account to the domain
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param domainId   The domain ID
     * @return The apply template response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/domains/templates/#apply">https://developer.dnsimple.com/v2/domains/templates/#apply</a>
     */
    public ApplyTemplateResponse applyTemplate(String accountId, String templateId, String domainId) throws DnsimpleException, IOException;
    // Template records

    /**
     * Lists the records in the template.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @return The list template records response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#list">https://developer.dnsimple.com/v2/templates/records#list</a>
     */
    public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId) throws DnsimpleException, IOException;

    /**
     * Lists the records in the template.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param options    A Map of options to pass to the templates API
     * @return The list template records response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#list">https://developer.dnsimple.com/v2/templates/records#list</a>
     */
    public ListTemplateRecordsResponse listTemplateRecords(String accountId, String templateId, Map<String, Object> options) throws DnsimpleException, IOException;

    /**
     * Get a specific record associated to a template using the record's ID.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param recordId   The record ID
     * @return The get template record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/records/#get">https://developer.dnsimple.com/v2/templates/records/#get</a>
     */
    public GetTemplateRecordResponse getTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException;

    /**
     * Create a record in the template.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param attributes A map of attributes to contruct the template record
     * @return The create template record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#create">https://developer.dnsimple.com/v2/templates/records#create</a>
     */
    public CreateTemplateRecordResponse createTemplateRecord(String accountId, String templateId, Map<String, Object> attributes) throws DnsimpleException, IOException;

    /**
     * Delete a record from the template.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param recordId   The record ID
     * @return The delete template record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#delete">https://developer.dnsimple.com/v2/templates/records#delete</a>
     */
    public DeleteTemplateRecordResponse deleteTemplateRecord(String accountId, String templateId, String recordId) throws DnsimpleException, IOException;
}
