package com.dnsimple.endpoints;

import com.dnsimple.data.Zone;
import com.dnsimple.data.ZoneDistribution;
import com.dnsimple.data.ZoneFile;
import com.dnsimple.data.ZoneRecord;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.request.ZoneRecordOptions;
import com.dnsimple.request.ZoneRecordUpdateOptions;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

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
     * @see <a href="https://developer.dnsimple.com/v2/zones/#listZones">https://developer.dnsimple.com/v2/zones/#listZones</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/zones/#listZones">https://developer.dnsimple.com/v2/zones/#listZones</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/zones/#getZone">https://developer.dnsimple.com/v2/zones/#getZone</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/zones/#getZoneFile">https://developer.dnsimple.com/v2/zones/#getZoneFile</a>
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
     * Lists the records in the zone.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @return The list zone records response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#listZoneRecords">https://developer.dnsimple.com/v2/zones/records/#listZoneRecords</a>
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
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#listZoneRecords">https://developer.dnsimple.com/v2/zones/records/#listZoneRecords</a>
     */
    public PaginatedResponse<ZoneRecord> listZoneRecords(Number account, String zone, ListOptions options) {
        return client.page(GET, account + "/zones/" + zone + "/records", options, null, ZoneRecord.class);
    }

    /**
     * Create a record in a zone.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @param options Options for the Zone record
     * @return The create zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#createZoneRecord">https://developer.dnsimple.com/v2/zones/records/#createZoneRecord</a>
     */
    public SimpleResponse<ZoneRecord> createZoneRecord(Number account, String zone, ZoneRecordOptions options) {
        return client.simple(POST, account + "/zones/" + zone + "/records", ListOptions.empty(), options.asPayload(), ZoneRecord.class);
    }

    /**
     * Get a specific record associated to a zone using the zone's name or ID.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @param record  The zone record ID
     * @return The get zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#getZoneRecord">https://developer.dnsimple.com/v2/zones/records/#getZoneRecord</a>
     */
    public SimpleResponse<ZoneRecord> getZoneRecord(Number account, String zone, Number record) {
        return client.simple(GET, account + "/zones/" + zone + "/records/" + record, ListOptions.empty(), null, ZoneRecord.class);
    }

    /**
     * Update a record in a zone.
     *
     * @param account    The account ID
     * @param zone       The zone name
     * @param record     The zone record ID
     * @param options The options to update the Zone record
     * @return The update zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#updateZoneRecord">https://developer.dnsimple.com/v2/zones/records/#updateZoneRecord</a>
     */
    public SimpleResponse<ZoneRecord> updateZoneRecord(Number account, String zone, Number record, ZoneRecordUpdateOptions options) {
        return client.simple(PATCH, account + "/zones/" + zone + "/records/" + record, ListOptions.empty(), options, ZoneRecord.class);
    }

    /**
     * Delete a record from a zone.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @param record  The zone record ID
     * @return The delete zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#deleteZoneRecord">https://developer.dnsimple.com/v2/zones/records/#deleteZoneRecord</a>
     */
    public EmptyResponse deleteZoneRecord(Number account, String zone, Number record) {
        return client.empty(DELETE, account + "/zones/" + zone + "/records/" + record, ListOptions.empty(), null);
    }

    /**
     * Checks if a zone record change is fully distributed to all our nameservers of our regions.
     *
     * @param account The account ID
     * @param zone    The zone name
     * @param record  The zone record ID
     * @return The result of the check
     * @see <a href="https://developer.dnsimple.com/v2/zones/#checkZoneRecordDistribution">https://developer.dnsimple.com/v2/zones/#checkZoneRecordDistribution</a>
     */
    public SimpleResponse<ZoneDistribution> checkZoneRecordDistribution(Number account, String zone, Number record) {
        return client.simple(GET, account + "/zones/" + zone + "/records/" + record + "/distribution", ListOptions.empty(), null, ZoneDistribution.class);
    }
}
