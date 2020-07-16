package com.dnsimple.endpoints;

import com.dnsimple.data.Zone;
import com.dnsimple.data.ZoneDistribution;
import com.dnsimple.data.ZoneFile;
import com.dnsimple.data.ZoneRecord;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;

/**
 * Provides access to the DNSimple Zones API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/zones">https://developer.dnsimple.com/v2/zones</a>
 */
public class Zones {
    private final HttpEndpointClient client;

    public Zones(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists the zones in the account.
     *
     * @param account The account ID
     * @return The list zones response
     * @see <a href="https://developer.dnsimple.com/v2/zones/#list">https://developer.dnsimple.com/v2/zones/#list</a>
     */
    public PaginatedResponse<Zone> listZones(Number account) {
        return client.page(GET, account + "/zones", ListOptions.empty(), null, Zone.class);
    }

    /**
     * Lists the zones in the account.
     *
     * @param account The account ID
     * @param options The options for the list request
     * @return The list zones response
     * @see <a href="https://developer.dnsimple.com/v2/zones/#list">https://developer.dnsimple.com/v2/zones/#list</a>
     */
    public PaginatedResponse<Zone> listZones(Number account, ListOptions options) {
        return client.page(GET, account + "/zones", options, null, Zone.class);
    }

    /**
     * Get a specific zone associated to an account using the zone's name or ID.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @return The get zone response
     * @see <a href="https://developer.dnsimple.com/v2/zones/#get">https://developer.dnsimple.com/v2/zones/#get</a>
     */
    public SimpleResponse<Zone> getZone(Number account, String zone) {
        return client.simple(GET, account + "/zones/" + zone, ListOptions.empty(), null, Zone.class);
    }

    /**
     * Get the zone file associated to an account using the zone's name or ID.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @return The get zone file response
     * @see <a href="https://developer.dnsimple.com/v2/zones/#get-file">https://developer.dnsimple.com/v2/zones/#get-file</a>
     */
    public SimpleResponse<ZoneFile> getZoneFile(Number account, String zone) {
        return client.simple(GET, account + "/zones/" + zone + "/file", ListOptions.empty(), null, ZoneFile.class);
    }

    /**
     * Checks if a zone change is fully distributed to all our nameservers across the globe.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @return The result of the check
     * @see <a href="https://developer.dnsimple.com/v2/zones/#checkZoneDistribution">https://developer.dnsimple.com/v2/zones/#checkZoneDistribution</a>
     */
    public SimpleResponse<ZoneDistribution> checkZoneDistribution(Number account, String zone) {
        return client.simple(GET, account + "/zones/" + zone + "/distribution", ListOptions.empty(), null, ZoneDistribution.class);
    }

    /**
     * Checks if a zone record change is fully distributed to all our nameservers of our regions.
     *
     * @param account  The account ID
     * @param zone     The zone name
     * @param record The zone record ID
     * @return The result of the check
     * @see <a href="https://developer.dnsimple.com/v2/zones/#checkZoneRecordDistribution">https://developer.dnsimple.com/v2/zones/#checkZoneRecordDistribution</a>
     */
    public SimpleResponse<ZoneDistribution> checkZoneRecordDistribution(Number account, String zone, Number record) {
        return client.simple(GET, account + "/zones/" + zone + "/records/" + record + "/distribution", ListOptions.empty(), null, ZoneDistribution.class);
    }

    /**
     * Lists the records in the zone.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @return The list zone records response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#list">https://developer.dnsimple.com/v2/zones/records/#list</a>
     */
    public PaginatedResponse<ZoneRecord> listZoneRecords(Number account, String zone) {
        return client.page(GET, account + "/zones/" + zone + "/records", ListOptions.empty(), null, ZoneRecord.class);
    }

    /**
     * Lists the records in the zone.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @param options The options for the list request
     * @return The list zone records response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#list">https://developer.dnsimple.com/v2/zones/records/#list</a>
     */
    public PaginatedResponse<ZoneRecord> listZoneRecords(Number account, String zone, ListOptions options) {
        return client.page(GET, account + "/zones/" + zone + "/records", options, null, ZoneRecord.class);
    }

    /**
     * Get a specific record associated to a zone using the zone's name or ID.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @param record  The zone record ID
     * @return The get zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#get">https://developer.dnsimple.com/v2/zones/records/#get</a>
     */
    public SimpleResponse<ZoneRecord> getZoneRecord(Number account, String zone, Number record) {
        return client.simple(GET, account + "/zones/" + zone + "/records/" + record, ListOptions.empty(), null, ZoneRecord.class);
    }

    /**
     * Create a record in a zone.
     *
     * @param account    The account ID
     * @param zone       The zone name
     * @param attributes The zone attributes
     * @return The create zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#create">https://developer.dnsimple.com/v2/zones/records/#create</a>
     */
    public SimpleResponse<ZoneRecord> createZoneRecord(Number account, String zone, Map<String, Object> attributes) {
        return client.simple(POST, account + "/zones/" + zone + "/records", ListOptions.empty(), attributes, ZoneRecord.class);
    }

    /**
     * Update a record in a zone.
     *
     * @param account    The account ID
     * @param zone       The zone name
     * @param record     The zone record ID
     * @param attributes The zone attributes
     * @return The update zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#update">https://developer.dnsimple.com/v2/zones/records/#update</a>
     */
    public SimpleResponse<ZoneRecord> updateZoneRecord(Number account, String zone, Number record, Map<String, Object> attributes) {
        return client.simple(PATCH, account + "/zones/" + zone + "/records/" + record, ListOptions.empty(), attributes, ZoneRecord.class);
    }

    /**
     * Delete a record from a zone.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @param record  The zone record ID
     * @return The delete zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#delete">https://developer.dnsimple.com/v2/zones/records/#delete</a>
     */
    public EmptyResponse deleteZoneRecord(Number account, String zone, Number record) {
        return client.empty(DELETE, account + "/zones/" + zone + "/records/" + record, ListOptions.empty(), null);
    }
}
