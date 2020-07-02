package com.dnsimple;

import com.dnsimple.data.DomainAvailability;
import com.dnsimple.data.DomainRegistration;
import com.dnsimple.data.DomainRenewal;
import com.dnsimple.data.DomainTransfer;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.SimpleResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.dnsimple.endpoints.http.HttpMethod.*;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class RegistrarTest extends DnsimpleTestBase {
    @Test
    public void testCheckDomain() {
        server.stubFixtureAt("checkDomain/success.http");
        DomainAvailability availability = client.registrar.checkDomain("1010", "ruby.codes").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/ruby.codes/check"));
        assertThat(availability.getDomainName(), is("ruby.codes"));
        assertThat(availability.getAvailable(), is(true));
        assertThat(availability.getPremium(), is(true));
    }

    @Test
    public void testRegisterDomain() {
        server.stubFixtureAt("registerDomain/success.http");
        Map<String, Object> attributes = singletonMap("registrant_id", "10");
        SimpleResponse<DomainRegistration> response = client.registrar.registerDomain("1010", "example.com", attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/registrations"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
        DomainRegistration registration = response.getData();
        assertThat(registration.getId(), is(1));
        assertThat(registration.getDomainId(), is(999));
        assertThat(registration.getRegistrantId(), is(2));
        assertThat(registration.getState(), is("new"));
        assertThat(registration.hasAutoRenew(), is(false));
        assertThat(registration.hasWhoisPrivacy(), is(false));
        assertThat(registration.getCreatedAt(), is("2016-12-09T19:35:31Z"));
        assertThat(registration.getUpdatedAt(), is("2016-12-09T19:35:31Z"));
    }

    @Test
    public void testRenewDomain() {
        server.stubFixtureAt("renewDomain/success.http");
        Map<String, Object> attributes = singletonMap("period", "3");
        DomainRenewal domainRenewal = client.registrar.renewDomain("1010", "example.com", attributes).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/renewals"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
        assertThat(domainRenewal.getId(), is(1));
    }

    @Test(expected = DnsimpleException.class)
    public void testRenewDomainTooSoon() {
        server.stubFixtureAt("renewDomain/error-tooearly.http");
        Map<String, Object> attributes = singletonMap("period", "3");
        client.registrar.renewDomain("1010", "example.com", attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/renewals"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test
    public void testTransferDomain() {
        server.stubFixtureAt("transferDomain/success.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("registrant_id", "1");
        attributes.put("auth_info", "x1y2z3");
        SimpleResponse<DomainTransfer> response = client.registrar.transferDomain("1010", "example.com", attributes);
        DomainTransfer transfer = response.getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfers"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
        assertThat(transfer.getId(), is(1));
    }

    @Test
    public void testGetDomainTransfer() {
        server.stubFixtureAt("getDomainTransfer/success.http");
        SimpleResponse<DomainTransfer> response = client.registrar.getDomainTransfer("1010", "example.com", "361");
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfers/361"));
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        DomainTransfer transfer = response.getData();
        assertThat(transfer.getId(), is(361));
        assertThat(transfer.getDomainId(), is(182245));
        assertThat(transfer.getRegistrantId(), is(2715));
        assertThat(transfer.getState(), is("cancelled"));
        assertThat(transfer.hasAutoRenew(), is(false));
        assertThat(transfer.hasWhoisPrivacy(), is(false));
        assertThat(transfer.getStatusDescription(), is("Canceled by customer"));
        assertThat(transfer.getCreatedAt(), is("2020-06-05T18:08:00Z"));
        assertThat(transfer.getUpdatedAt(), is("2020-06-05T18:10:01Z"));
    }

    @Test
    public void testCancelDomainTransfer() {
        server.stubFixtureAt("cancelDomainTransfer/success.http");
        SimpleResponse<DomainTransfer> response = client.registrar.cancelDomainTransfer("1010", "example.com", "361");
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfers/361"));
        assertThat(server.getRecordedRequest().getMethod(), is(DELETE));
        DomainTransfer transfer = response.getData();
        assertThat(transfer.getId(), is(361));
        assertThat(transfer.getDomainId(), is(182245));
        assertThat(transfer.getRegistrantId(), is(2715));
        assertThat(transfer.getState(), is("transferring"));
        assertThat(transfer.hasAutoRenew(), is(false));
        assertThat(transfer.hasWhoisPrivacy(), is(false));
        assertThat(transfer.getStatusDescription(), isEmptyOrNullString());
        assertThat(transfer.getCreatedAt(), is("2020-06-05T18:08:00Z"));
        assertThat(transfer.getUpdatedAt(), is("2020-06-05T18:08:04Z"));
    }

    @Test(expected = DnsimpleException.class)
    public void testTransferDomainAlreadyInDnsimple() {
        server.stubFixtureAt("transferDomain/error-indnsimple.http");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("registrant_id", "1");
        attributes.put("auth_info", "x1y2z3");
        client.registrar.transferDomain("1010", "example.com", attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfers"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test(expected = DnsimpleException.class)
    public void testTransferDomainAuthInfoRequired() {
        server.stubFixtureAt("transferDomain/error-missing-authcode.http");
        Map<String, Object> attributes = singletonMap("registrant_id", "1");
        client.registrar.transferDomain("1010", "example.com", attributes);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfers"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), is(attributes));
    }

    @Test
    public void testTransferDomainOut() {
        server.stubFixtureAt("authorizeDomainTransferOut/success.http");
        client.registrar.transferDomainOut("1010", "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/authorize_transfer_out"));
    }
}
