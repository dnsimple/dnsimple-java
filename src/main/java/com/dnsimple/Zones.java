package com.dnsimple;

import com.dnsimple.data.Zone;
import com.dnsimple.data.ZoneDistribution;
import com.dnsimple.data.ZoneFile;
import com.dnsimple.data.ZoneRecord;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.Map;

/**
 * Provides access to the DNSimple Zones API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/zones">https://developer.dnsimple.com/v2/zones</a>
 */
public interface Zones {
    /**
     * Lists the zones in the account.
     *
     * @param accountId The account ID
     * @return The list zones response
     * @see <a href="https://developer.dnsimple.com/v2/zones/#list">https://developer.dnsimple.com/v2/zones/#list</a>
     */
    PaginatedResponse<Zone> listZones(String accountId);

    /**
     * Lists the zones in the account.
     *
     * @param accountId The account ID
     * @param options   A Map of options to pass to the zones API
     * @return The list zones response
     * @see <a href="https://developer.dnsimple.com/v2/zones/#list">https://developer.dnsimple.com/v2/zones/#list</a>
     */
    PaginatedResponse<Zone> listZones(String accountId, Map<String, Object> options);

    /**
     * Get a specific zone associated to an account using the zone's name or ID.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @return The get zone response
     * @see <a href="https://developer.dnsimple.com/v2/zones/#get">https://developer.dnsimple.com/v2/zones/#get</a>
     */
    SimpleResponse<Zone> getZone(String accountId, String zoneId);

    /**
     * Get the zone file associated to an account using the zone's name or ID.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @return The get zone file response
     * @see <a href="https://developer.dnsimple.com/v2/zones/#get-file">https://developer.dnsimple.com/v2/zones/#get-file</a>
     */
    SimpleResponse<ZoneFile> getZoneFile(String accountId, String zoneId);

    /**
     * Checks if a zone change is fully distributed to all our nameservers across the globe.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @return The result of the check
     * @see <a href="https://developer.dnsimple.com/v2/zones/#checkZoneDistribution">https://developer.dnsimple.com/v2/zones/#checkZoneDistribution</a>
     */
    SimpleResponse<ZoneDistribution> checkZoneDistribution(String accountId, String zoneId);

    /**
     * Checks if a zone record change is fully distributed to all our nameservers of our regions.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @param recordId  The zone record ID
     * @return The result of the check
     * @see <a href="https://developer.dnsimple.com/v2/zones/#checkZoneRecordDistribution">https://developer.dnsimple.com/v2/zones/#checkZoneRecordDistribution</a>
     */
    SimpleResponse<ZoneDistribution> checkZoneRecordDistribution(String accountId, String zoneId, String recordId);

    /**
     * Lists the records in the zone.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @return The list zone records response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#list">https://developer.dnsimple.com/v2/zones/records/#list</a>
     */
    PaginatedResponse<ZoneRecord> listZoneRecords(String accountId, String zoneId);

    /**
     * Lists the records in the zone.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @param options   A Map of options to pass to the zones API
     * @return The list zone records response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#list">https://developer.dnsimple.com/v2/zones/records/#list</a>
     */
    PaginatedResponse<ZoneRecord> listZoneRecords(String accountId, String zoneId, Map<String, Object> options);

    /**
     * Get a specific record associated to a zone using the zone's name or ID.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @param recordId  The zone record ID
     * @return The get zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#get">https://developer.dnsimple.com/v2/zones/records/#get</a>
     */
    SimpleResponse<ZoneRecord> getZoneRecord(String accountId, String zoneId, String recordId);

    /**
     * Create a record in a zone.
     *
     * @param accountId  The account ID
     * @param zoneId     The zone name or ID
     * @param attributes The zone attributes
     * @return The create zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#create">https://developer.dnsimple.com/v2/zones/records/#create</a>
     */
    SimpleResponse<ZoneRecord> createZoneRecord(String accountId, String zoneId, Map<String, Object> attributes);

    /**
     * Update a record in a zone.
     *
     * @param accountId  The account ID
     * @param zoneId     The zone name or ID
     * @param recordId   The zone record ID
     * @param attributes The zone attributes
     * @return The update zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#update">https://developer.dnsimple.com/v2/zones/records/#update</a>
     */
    SimpleResponse<ZoneRecord> updateZoneRecord(String accountId, String zoneId, String recordId, Map<String, Object> attributes);

    /**
     * Delete a record from a zone.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @param recordId  The zone record ID
     * @return The delete zone record response
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#delete">https://developer.dnsimple.com/v2/zones/records/#delete</a>
     */
    EmptyResponse deleteZoneRecord(String accountId, String zoneId, String recordId);
}
