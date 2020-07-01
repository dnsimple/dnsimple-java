package com.dnsimple;

import com.dnsimple.data.DelegationSignerRecord;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;
import com.dnsimple.response.CreateDelegationSignerRecordResponse;
import com.dnsimple.response.DeleteDelegationSignerRecordResponse;
import com.dnsimple.response.ListDelegationSignerRecordsResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.tools.CustomMatchers.thrownException;
import static com.dnsimple.tools.HttpMethod.DELETE;
import static com.dnsimple.tools.HttpMethod.GET;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DomainDelegationSignerRecordsTest extends DnsimpleTestBase {
    @Test
    public void testListDelegationSignerRecordsSupportsPagination() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listDelegationSignerRecords("1", "1010", singletonMap("page", 1));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/1010/ds_records?page=1"));
    }

    @Test
    public void testListDelegationSignerRecordsSupportsExtraRequestOptions() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listDelegationSignerRecords("1", "1010", singletonMap("foo", "bar"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/1010/ds_records?foo=bar"));
    }

    @Test
    public void testListDelegationSignerRecordsSupportsSorting() throws DnsimpleException, IOException, InterruptedException {
        client.domains.listDelegationSignerRecords("1", "1010", singletonMap("sort", "created_at:asc"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/1010/ds_records?sort=created_at%3Aasc"));
    }

    @Test
    public void testListDelegationSignerRecordsProducesDelegationSignerRecordList() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listDelegationSignerRecords/success.http");
        List<DelegationSignerRecord> dsRecords = client.domains.listDelegationSignerRecords("1", "1010").getData();
        assertThat(dsRecords, hasSize(1));
        assertThat(dsRecords.get(0).getId(), is(24));
    }

    @Test
    public void testListDelegationSignerRecordsExposesPaginationInfo() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("listDelegationSignerRecords/success.http");
        ListDelegationSignerRecordsResponse response = client.domains.listDelegationSignerRecords("1", "1010");
        assertThat(response.getPagination().getCurrentPage(), is(1));
    }

    @Test
    public void testGetDelegationSignerRecord() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("getDelegationSignerRecord/success.http");
        DelegationSignerRecord dsRecord = client.domains.getDelegationSignerRecord("1", "example.com", "24").getData();
        assertThat(dsRecord.getId(), is(24));
        assertThat(dsRecord.getDomainId(), is(1010));
        assertThat(dsRecord.getAlgorithm(), is("8"));
        assertThat(dsRecord.getDigest(), is("C1F6E04A5A61FBF65BF9DC8294C363CF11C89E802D926BDAB79C55D27BEFA94F"));
        assertThat(dsRecord.getDigestType(), is("2"));
        assertThat(dsRecord.getKeytag(), is("44620"));
        assertThat(dsRecord.getCreatedAt(), is("2017-03-03T13:49:58Z"));
        assertThat(dsRecord.getUpdatedAt(), is("2017-03-03T13:49:58Z"));
    }

    @Test
    public void testGetDelegationSignerRecordWhenNotFound() {
        server.stubFixtureAt("notfound-delegationSignerRecord.http");
        assertThat(() -> client.domains.getDelegationSignerRecord("1", "example.com", "0"),
                thrownException(is(instanceOf(ResourceNotFoundException.class))));
    }

    @Test
    public void testCreateDelegationSignerRecordProducesDelegationSignerRecord() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("createDelegationSignerRecord/created.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("algorithm", "13");
        attributes.put("digest", "684a1f049d7d082b7f98691657da5a65764913df7f065f6f8c36edf62d66ca03");
        attributes.put("digest_type", "2");
        attributes.put("keytag", "2371");
        CreateDelegationSignerRecordResponse response = client.domains.createDelegationSignerRecord("1", "example.com", attributes);
        assertThat(response.getData().getId(), is(2));
    }

    @Test
    public void testDeleteDelegationSignerRecord() throws DnsimpleException, IOException, InterruptedException {
        server.stubFixtureAt("deleteDelegationSignerRecord/success.http");
        DeleteDelegationSignerRecordResponse response = client.domains.deleteDelegationSignerRecord("1", "example.com", "24");
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1/domains/example.com/ds_records/24"));
        assertThat(response.getData(), is(nullValue()));
    }
}
