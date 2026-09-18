package com.dnsimple.endpoints;

import com.dnsimple.data.DnsAnalyticsEntry;
import com.dnsimple.data.DnsAnalyticsQuery;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponseWithQuery;
import com.dnsimple.response.Pagination;

import java.time.LocalDate;
import java.util.List;

import static com.dnsimple.http.HttpMethod.GET;
import static java.util.stream.Collectors.toList;

/**
 * Provides access to the DNSimple DNS Analytics API.
 *
 * @see <a href="https://developer.dnsimple.com/v2/dns-analytics">https://developer.dnsimple.com/v2/dns-analytics</a>
 */
public class DnsAnalytics {
    private final HttpEndpointClient client;

    public DnsAnalytics(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Queries DNS Analytics data for the account. This API is in Public Beta.
     *
     * @param account The account ID
     * @param options The options for the query request
     * @return The DNS Analytics query response. An entry field is null when the query does not group by it.
     * @see <a href="https://developer.dnsimple.com/v2/dns-analytics/#queryDnsAnalytics">https://developer.dnsimple.com/v2/dns-analytics/#queryDnsAnalytics</a>
     */
    public PaginatedResponseWithQuery<DnsAnalyticsEntry, DnsAnalyticsQuery> query(Number account, ListOptions options) {
        QueryResponse response = client.raw(GET, account + "/dns_analytics", options, null, QueryResponse.class);
        List<DnsAnalyticsEntry> entries = response.data.rows.stream()
                .map(row -> toEntry(response.data.headers, row))
                .collect(toList());
        return new PaginatedResponseWithQuery<>(entries, response.pagination, response.query);
    }

    private static DnsAnalyticsEntry toEntry(List<String> headers, List<Object> row) {
        String date = (String) valueOf("date", headers, row);
        Number volume = (Number) valueOf("volume", headers, row);
        return new DnsAnalyticsEntry(
                (String) valueOf("zone_name", headers, row),
                date == null ? null : LocalDate.parse(date),
                volume == null ? null : volume.longValue()
        );
    }

    private static Object valueOf(String header, List<String> headers, List<Object> row) {
        int index = headers.indexOf(header);
        return index < 0 ? null : row.get(index);
    }

    private static class QueryResponse {
        private QueryData data;
        private DnsAnalyticsQuery query;
        private Pagination pagination;
    }

    private static class QueryData {
        private List<String> headers;
        private List<List<Object>> rows;
    }
}
