package com.dnsimple.endpoints;

import com.dnsimple.data.Template;
import com.dnsimple.data.TemplateRecord;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.request.TemplateOptions;
import com.dnsimple.request.TemplateRecordOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import static com.dnsimple.http.HttpMethod.*;

/**
 * Provides access to the DNSimple Templates API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/templates">https://developer.dnsimple.com/v2/templates</a>
 */
public class Templates {
    private final HttpEndpointClient client;

    public Templates(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists the templates in the account.
     *
     * @param account The account ID
     * @return The list templates response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#listTemplates">https://developer.dnsimple.com/v2/templates/#listTemplates</a>
     */
    public PaginatedResponse<Template> listTemplates(Number account) {
        return client.page(GET, account + "/templates", ListOptions.empty(), null, Template.class);
    }

    /**
     * Lists the templates in the account.
     *
     * @param account The account ID
     * @param options The options for the list request
     * @return The list templates response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#listTemplates">https://developer.dnsimple.com/v2/templates/#listTemplates</a>
     */
    public PaginatedResponse<Template> listTemplates(Number account, ListOptions options) {
        return client.page(GET, account + "/templates", options, null, Template.class);
    }

    /**
     * Create a template in the account.
     *
     * @param account The account ID
     * @param options The template options
     * @return The create template response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#createTemplate">https://developer.dnsimple.com/v2/templates/#createTemplate</a>
     */
    public SimpleResponse<Template> createTemplate(Number account, TemplateOptions options) {
        return client.simple(POST, account + "/templates", ListOptions.empty(), options, Template.class);
    }

    /**
     * Get a specific template associated to an account using the templates's ID.
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @return The get template response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#getTemplate">https://developer.dnsimple.com/v2/templates/#getTemplate</a>
     */
    public SimpleResponse<Template> getTemplate(Number account, String template) {
        return client.simple(GET, account + "/templates/" + template, ListOptions.empty(), null, Template.class);
    }

    /**
     * Update a template in the account.
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @param options  The template options
     * @return The update template response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#updateTemplate">https://developer.dnsimple.com/v2/templates/#updateTemplate</a>
     */
    public SimpleResponse<Template> updateTemplate(Number account, String template, TemplateOptions options) {
        return client.simple(PATCH, account + "/templates/" + template, ListOptions.empty(), options, Template.class);
    }

    /**
     * Delete a template from the account.
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @return The delete template response
     * @see <a href="https://developer.dnsimple.com/v2/templates/#deleteTemplate">https://developer.dnsimple.com/v2/templates/#deleteTemplate</a>
     */
    public EmptyResponse deleteTemplate(Number account, String template) {
        return client.empty(DELETE, account + "/templates/" + template, ListOptions.empty(), null);
    }

    /**
     * Apply a template from the account to the domain
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @param domain   The domain ID or name
     * @return The apply template response
     * @see <a href="https://developer.dnsimple.com/v2/domains/templates/#applyTemplateToDomain">https://developer.dnsimple.com/v2/domains/templates/#applyTemplateToDomain</a>
     */
    public EmptyResponse applyTemplate(Number account, String template, String domain) {
        return client.empty(POST, account + "/domains/" + domain + "/templates/" + template, ListOptions.empty(), null);
    }

    /**
     * Lists the records in the template.
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @return The list template records response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#listTemplateRecords">https://developer.dnsimple.com/v2/templates/records#listTemplateRecords</a>
     */
    public PaginatedResponse<TemplateRecord> listTemplateRecords(Number account, String template) {
        return client.page(GET, account + "/templates/" + template + "/records", ListOptions.empty(), null, TemplateRecord.class);
    }

    /**
     * Lists the records in the template.
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @param options  The options for the list request
     * @return The list template records response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#listTemplateRecords">https://developer.dnsimple.com/v2/templates/records#listTemplateRecords</a>
     */
    public PaginatedResponse<TemplateRecord> listTemplateRecords(Number account, String template, ListOptions options) {
        return client.page(GET, account + "/templates/" + template + "/records", options, null, TemplateRecord.class);
    }

    /**
     * Create a record in the template.
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @param options  The template record options
     * @return The create template record response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#createTemplateRecord">https://developer.dnsimple.com/v2/templates/records#createTemplateRecord</a>
     */
    public SimpleResponse<TemplateRecord> createTemplateRecord(Number account, String template, TemplateRecordOptions options) {
        return client.simple(POST, account + "/templates/" + template + "/records", ListOptions.empty(), options, TemplateRecord.class);
    }

    /**
     * Get a specific record associated to a template using the record's ID.
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @param record   The record ID
     * @return The get template record response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records/#getTemplateRecord">https://developer.dnsimple.com/v2/templates/records/#getTemplateRecord</a>
     */
    public SimpleResponse<TemplateRecord> getTemplateRecord(Number account, String template, Number record) {
        return client.simple(GET, account + "/templates/" + template + "/records/" + record, ListOptions.empty(), null, TemplateRecord.class);
    }

    /**
     * Delete a record from the template.
     *
     * @param account  The account ID
     * @param template The template short name or ID
     * @param record   The record ID
     * @return The delete template record response
     * @see <a href="https://developer.dnsimple.com/v2/templates/records#deleteTemplateRecord">https://developer.dnsimple.com/v2/templates/records#deleteTemplateRecord</a>
     */
    public EmptyResponse deleteTemplateRecord(Number account, String template, Number record) {
        return client.empty(DELETE, account + "/templates/" + template + "/records/" + record, ListOptions.empty(), null);
    }
}
