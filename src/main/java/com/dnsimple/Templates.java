package com.dnsimple;

import com.dnsimple.data.Template;
import com.dnsimple.data.TemplateRecord;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

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
     * @see <a href="https://developer.dnsimple.com/v2/templates/#list">https://developer.dnsimple.com/v2/templates/#list</a>
     */
    PaginatedResponse<Template> listTemplates(String accountId);

    /**
     * Lists the templates in the account.
     *
     * @param accountId The account ID
     * @param options   A Map of options to pass to the templates API
     * @return The list templates response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#list">https://developer.dnsimple.com/v2/templates/#list</a>
     */
    PaginatedResponse<Template> listTemplates(String accountId, Map<String, Object> options);

    /**
     * Get a specific template associated to an account using the templates's ID.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @return The get template response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#get">https://developer.dnsimple.com/v2/templates/#list</a>
     */
    SimpleResponse<Template> getTemplate(String accountId, String templateId);

    /**
     * Create a template in the account.
     *
     * @param accountId  The account ID
     * @param attributes A map of attributes to contruct the template
     * @return The create template response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#create">https://developer.dnsimple.com/v2/templates/#create</a>
     */
    SimpleResponse<Template> createTemplate(String accountId, Map<String, Object> attributes);

    /**
     * Update a template in the account.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param attributes A map of attributes to update the template
     * @return The update template response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#update">https://developer.dnsimple.com/v2/templates/#update</a>
     */
    SimpleResponse<Template> updateTemplate(String accountId, String templateId, Map<String, Object> attributes);

    /**
     * Delete a template from the account.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @return The delete template response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#delete">https://developer.dnsimple.com/v2/templates/#delete</a>
     */
    EmptyResponse deleteTemplate(String accountId, String templateId);

    /**
     * Apply a template from the account to the domain
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param domainId   The domain ID
     * @return The apply template response
     * @see <a href="https://developer.dnsimple.com/v2/domains/templates/#apply">https://developer.dnsimple.com/v2/domains/templates/#apply</a>
     */
    EmptyResponse applyTemplate(String accountId, String templateId, String domainId);
    // Template records

    /**
     * Lists the records in the template.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @return The list template records response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#list">https://developer.dnsimple.com/v2/templates/records#list</a>
     */
    PaginatedResponse<TemplateRecord> listTemplateRecords(String accountId, String templateId);

    /**
     * Lists the records in the template.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param options    A Map of options to pass to the templates API
     * @return The list template records response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#list">https://developer.dnsimple.com/v2/templates/records#list</a>
     */
    PaginatedResponse<TemplateRecord> listTemplateRecords(String accountId, String templateId, Map<String, Object> options);

    /**
     * Get a specific record associated to a template using the record's ID.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param recordId   The record ID
     * @return The get template record response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records/#get">https://developer.dnsimple.com/v2/templates/records/#get</a>
     */
    SimpleResponse<TemplateRecord> getTemplateRecord(String accountId, String templateId, String recordId);

    /**
     * Create a record in the template.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param attributes A map of attributes to contruct the template record
     * @return The create template record response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#create">https://developer.dnsimple.com/v2/templates/records#create</a>
     */
    SimpleResponse<TemplateRecord> createTemplateRecord(String accountId, String templateId, Map<String, Object> attributes);

    /**
     * Delete a record from the template.
     *
     * @param accountId  The account ID
     * @param templateId The template ID
     * @param recordId   The record ID
     * @return The delete template record response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#delete">https://developer.dnsimple.com/v2/templates/records#delete</a>
     */
    EmptyResponse deleteTemplateRecord(String accountId, String templateId, String recordId);
}
