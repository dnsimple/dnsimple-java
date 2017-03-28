package com.dnsimple;

import com.dnsimple.data.DelegationSignerRecord;
import com.dnsimple.data.Pagination;
import com.dnsimple.response.ListDelegationSignerRecordsResponse;
import com.dnsimple.response.GetDelegationSignerRecordResponse;
import com.dnsimple.response.CreateDelegationSignerRecordResponse;
import com.dnsimple.response.DeleteDelegationSignerRecordResponse;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.exception.ResourceNotFoundException;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Data;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

public class DomainDelegationSignerRecordsTest extends DnsimpleTestBase {

  @Test
  public void testListDelegationSignerRecordsSupportsPagination() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/1010/ds_records?page=1");
    String accountId = "1";
    String domainId = "1010";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("page", 1);
    client.domains.listDelegationSignerRecords(accountId, domainId, options);
  }

  @Test
  public void testListDelegationSignerRecordsSupportsExtraRequestOptions() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/1010/ds_records?foo=bar");
    String accountId = "1";
    String domainId = "1010";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("foo", "bar");
    client.domains.listDelegationSignerRecords(accountId, domainId, options);
  }

  @Test
  public void testListDelegationSignerRecordsSupportsSorting() throws DnsimpleException, IOException {
    Client client = expectClient("https://api.dnsimple.com/v2/1/domains/1010/ds_records?sort=created_at%3Aasc");
    String accountId = "1";
    String domainId = "1010";
    HashMap<String, Object> options = new HashMap<String, Object>();
    options.put("sort", "created_at:asc");
    client.domains.listDelegationSignerRecords(accountId, domainId, options);
  }

  @Test
  public void testListDelegationSignerRecordsProducesDelegationSignerRecordList() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listDelegationSignerRecords/success.http"));

    String accountId = "1";
    String domainId = "1010";

    ListDelegationSignerRecordsResponse response = client.domains.listDelegationSignerRecords(accountId, domainId);

    List<DelegationSignerRecord> dsRecords = response.getData();
    assertEquals(1, dsRecords.size());
    assertEquals(24, dsRecords.get(0).getId().intValue());
  }

  @Test
  public void testListDelegationSignerRecordsExposesPaginationInfo() throws DnsimpleException, IOException {
    Client client = mockClient(resource("listDelegationSignerRecords/success.http"));

    String accountId = "1";
    String domainId = "1010";

    ListDelegationSignerRecordsResponse response = client.domains.listDelegationSignerRecords(accountId, domainId);

    Pagination pagination = response.getPagination();
    assertEquals(1, pagination.getCurrentPage().intValue());
  }

  @Test
  public void testGetDelegationSignerRecord() throws DnsimpleException, IOException {
    Client client = mockClient(resource("getDelegationSignerRecord/success.http"));

    String accountId = "1";
    String domainId = "example.com";
    String dsRecordId = "24";

    GetDelegationSignerRecordResponse response = client.domains.getDelegationSignerRecord(accountId, domainId, dsRecordId);

    DelegationSignerRecord dsRecord = response.getData();
    assertEquals(24, dsRecord.getId().intValue());
    assertEquals(1010, dsRecord.getDomainId().intValue());
    assertEquals("8", dsRecord.getAlgorithm());
    assertEquals("C1F6E04A5A61FBF65BF9DC8294C363CF11C89E802D926BDAB79C55D27BEFA94F", dsRecord.getDigest());
    assertEquals("2", dsRecord.getDigestType());
    assertEquals("44620", dsRecord.getKeytag());
    assertEquals("2017-03-03T13:49:58Z", dsRecord.getCreatedAt());
    assertEquals("2017-03-03T13:49:58Z", dsRecord.getUpdatedAt());
  }

  @Test(expected=ResourceNotFoundException.class)
  public void testGetDelegationSignerRecordWhenNotFound() throws DnsimpleException, IOException {
    Client client = mockClient(resource("notfound-delegationSignerRecord.http"));

    String accountId = "1";
    String domainId = "example.com";
    String dsRecordId = "0";

    client.domains.getDelegationSignerRecord(accountId, domainId, dsRecordId);
  }

  @Test
  public void testCreateDelegationSignerRecordProducesDelegationSignerRecord() throws DnsimpleException, IOException {
    Client client = mockClient(resource("createDelegationSignerRecord/created.http"));

    String accountId = "1";
    String domainId = "example.com";
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    attributes.put("algorithm", "13");
    attributes.put("digest", "684a1f049d7d082b7f98691657da5a65764913df7f065f6f8c36edf62d66ca03");
    attributes.put("digest_type", "2");
    attributes.put("keytag", "2371");

    CreateDelegationSignerRecordResponse response = client.domains.createDelegationSignerRecord(accountId, domainId, attributes);
    DelegationSignerRecord dsRecord = response.getData();
    assertEquals(2, dsRecord.getId().intValue());
  }

  @Test
  public void testDeleteDelegationSignerRecord() throws DnsimpleException, IOException {
    Client client = mockAndExpectClient("https://api.dnsimple.com/v2/1/domains/example.com/ds_records/24", HttpMethods.DELETE, resource("deleteDelegationSignerRecord/success.http"));

    String accountId = "1";
    String domainId = "example.com";
    String dsRecordId = "24";

    DeleteDelegationSignerRecordResponse response = client.domains.deleteDelegationSignerRecord(accountId, domainId, dsRecordId);
    assertEquals(null, response.getData());
  }

}
