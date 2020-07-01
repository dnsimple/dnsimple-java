package com.dnsimple;

import com.dnsimple.data.Zone;
import com.dnsimple.data.ZoneDistribution;
import com.dnsimple.data.ZoneFile;
import com.dnsimple.data.ZoneRecord;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
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
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/#list">https://developer.dnsimple.com/v2/zones/#list</a>
     */
    public PaginatedResponse<Zone> listZones(String accountId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the zones in the account.
     *
     * @param accountId The account ID
     * @param options   A Map of options to pass to the zones API
     * @return The list zones response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/#list">https://developer.dnsimple.com/v2/zones/#list</a>
     */
    public PaginatedResponse<Zone> listZones(String accountId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get a specific zone associated to an account using the zone's name or ID.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @return The get zone response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/#get">https://developer.dnsimple.com/v2/zones/#get</a>
     */
    public SimpleResponse<Zone> getZone(String accountId, String zoneId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get the zone file associated to an account using the zone's name or ID.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @return The get zone file response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/#get-file">https://developer.dnsimple.com/v2/zones/#get-file</a>
     */
    public SimpleResponse<ZoneFile> getZoneFile(String accountId, String zoneId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Checks if a zone change is fully distributed to all our nameservers across the globe.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @return The result of the check
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/#checkZoneDistribution">https://developer.dnsimple.com/v2/zones/#checkZoneDistribution</a>
     */
    public SimpleResponse<ZoneDistribution> checkZoneDistribution(String accountId, String zoneId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Checks if a zone record change is fully distributed to all our nameservers of our regions.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @param recordId  The zone record ID
     * @return The result of the check
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/#checkZoneRecordDistribution">https://developer.dnsimple.com/v2/zones/#checkZoneRecordDistribution</a>
     */
    public SimpleResponse<ZoneDistribution> checkZoneRecordDistribution(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the records in the zone.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @return The list zone records response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#list">https://developer.dnsimple.com/v2/zones/records/#list</a>
     */
    public PaginatedResponse<ZoneRecord> listZoneRecords(String accountId, String zoneId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Lists the records in the zone.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @param options   A Map of options to pass to the zones API
     * @return The list zone records response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#list">https://developer.dnsimple.com/v2/zones/records/#list</a>
     */
    public PaginatedResponse<ZoneRecord> listZoneRecords(String accountId, String zoneId, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Get a specific record associated to a zone using the zone's name or ID.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @param recordId  The zone record ID
     * @return The get zone record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#get">https://developer.dnsimple.com/v2/zones/records/#get</a>
     */
    public SimpleResponse<ZoneRecord> getZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Create a record in a zone.
     *
     * @param accountId  The account ID
     * @param zoneId     The zone name or ID
     * @param attributes The zone attributes
     * @return The create zone record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#create">https://developer.dnsimple.com/v2/zones/records/#create</a>
     */
    public SimpleResponse<ZoneRecord> createZoneRecord(String accountId, String zoneId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Update a record in a zone.
     *
     * @param accountId  The account ID
     * @param zoneId     The zone name or ID
     * @param recordId   The zone record ID
     * @param attributes The zone attributes
     * @return The update zone record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#update">https://developer.dnsimple.com/v2/zones/records/#update</a>
     */
    public SimpleResponse<ZoneRecord> updateZoneRecord(String accountId, String zoneId, String recordId, Map<String, Object> attributes) throws DnsimpleException, IOException, InterruptedException;

    /**
     * Delete a record from a zone.
     *
     * @param accountId The account ID
     * @param zoneId    The zone name or ID
     * @param recordId  The zone record ID
     * @return The delete zone record response
     * @throws DnsimpleException Any API errors
     * @throws IOException       Any IO errors
     * @see <a href="https://developer.dnsimple.com/v2/zones/records/#delete">https://developer.dnsimple.com/v2/zones/records/#delete</a>
     */
    public EmptyResponse deleteZoneRecord(String accountId, String zoneId, String recordId) throws DnsimpleException, IOException, InterruptedException;
}
