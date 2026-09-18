package com.dnsimple.endpoints;

import com.dnsimple.data.DnsAnalyticsEntry;
import com.dnsimple.data.DnsAnalyticsQuery;
import com.dnsimple.request.ListOptions;
import com.dnsimple.response.PaginatedResponseWithQuery;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static com.dnsimple.http.HttpMethod.GET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class DnsAnalyticsTest extends DnsimpleTestBase {
    @Test
    public void testQuery() {
        server.stubFixtureAt("dnsAnalytics/success.http");
        client.dnsAnalytics.query(1, ListOptions.empty());
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/dns_analytics"));
    }

    @Test
    public void testQuerySupportsFilteringSortingAndPagination() {
        server.stubFixtureAt("dnsAnalytics/success.http");
        ListOptions options = ListOptions.empty()
                .page(0, 100)
                .filter("start_date", "2023-12-08")
                .filter("end_date", "2024-01-08")
                .filter("groupings", "zone_name,date")
                .sortAsc("zone_name")
                .sortAsc("date");
        client.dnsAnalytics.query(1, options);
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/dns_analytics?page=0&per_page=100&start_date=2023-12-08&end_date=2024-01-08&groupings=zone_name%2Cdate&sort=zone_name%3Aasc,date%3Aasc"));
    }

    @Test
    public void testQueryProducesEntryList() {
        server.stubFixtureAt("dnsAnalytics/success.http");
        List<DnsAnalyticsEntry> entries = client.dnsAnalytics.query(1, ListOptions.empty()).getData();
        assertThat(entries, hasSize(12));
        assertThat(entries.get(0).getZoneName(), is("bar.com"));
        assertThat(entries.get(0).getDate(), is(LocalDate.of(2023, 12, 8)));
        assertThat(entries.get(0).getVolume(), is(1200L));
        assertThat(entries.get(11).getZoneName(), is("foo.com"));
        assertThat(entries.get(11).getDate(), is(LocalDate.of(2024, 1, 8)));
        assertThat(entries.get(11).getVolume(), is(1200L));
    }

    @Test
    public void testQueryExposesPaginationInfo() {
        server.stubFixtureAt("dnsAnalytics/success.http");
        PaginatedResponseWithQuery<DnsAnalyticsEntry, DnsAnalyticsQuery> response = client.dnsAnalytics.query(1, ListOptions.empty());
        assertThat(response.getPagination().getCurrentPage(), is(0));
        assertThat(response.getPagination().getPerPage(), is(100));
        assertThat(response.getPagination().getTotalEntries(), is(93));
        assertThat(response.getPagination().getTotalPages(), is(1));
    }

    @Test
    public void testQueryExposesQuery() {
        server.stubFixtureAt("dnsAnalytics/success.http");
        DnsAnalyticsQuery query = client.dnsAnalytics.query(1, ListOptions.empty()).getQuery();
        assertThat(query.getAccountId(), is(1L));
        assertThat(query.getStartDate(), is(LocalDate.of(2023, 12, 8)));
        assertThat(query.getEndDate(), is(LocalDate.of(2024, 1, 8)));
        assertThat(query.getSort(), is("zone_name:asc,date:asc"));
        assertThat(query.getPage(), is(0));
        assertThat(query.getPerPage(), is(100));
        assertThat(query.getGroupings(), is("zone_name,date"));
    }
}
