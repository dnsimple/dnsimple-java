package com.dnsimple.endpoints;

import com.dnsimple.data.*;
import com.dnsimple.exception.BadRequestException;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.request.RegistrationOptions;
import com.dnsimple.request.RenewOptions;
import com.dnsimple.request.TransferOptions;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;
import static com.dnsimple.tools.CustomMatchers.*;
import static java.time.ZoneOffset.UTC;
import static java.util.Collections.singletonMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegistrarTest extends DnsimpleTestBase {
    @Test
    public void testCheckDomain() {
        server.stubFixtureAt("checkDomain/success.http");
        DomainCheck availability = client.registrar.checkDomain(1010, "ruby.codes").getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/ruby.codes/check"));
        assertThat(availability.getDomainName(), is("ruby.codes"));
        assertThat(availability.isAvailable(), is(true));
        assertThat(availability.isPremium(), is(true));
    }

    @Test
    public void testGetDomainPrices() {
        server.stubFixtureAt("getDomainPrices/success.http");

        DomainPrice prices = client.registrar.getDomainPrices(1010, "bingo.pizza").getData();

        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/bingo.pizza/prices"));
        assertThat(prices.getDomain(), is("bingo.pizza"));
        assertThat(prices.isPremium(), is(true));
        assertThat(prices.getRegistrationPrice(), is(20.0));
        assertThat(prices.getRenewalPrice(), is(20.0));
        assertThat(prices.getTransferPrice(), is(20.0));
    }

    @Test(expected = DnsimpleException.class)
    public void testGetDomainPricesWithANotSupportedTLD() {
        server.stubFixtureAt("getDomainPrices/failure.http");

        client.registrar.getDomainPrices(1010, "bing.pineapple");
    }

    @Test
    public void testGetDomainRegistration() {
        server.stubFixtureAt("getDomainRegistration/success.http");

        DomainRegistration registration = client.registrar.getDomainRegistration(1010, "bingo.pizza", 361).getData();

        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/bingo.pizza/registrations/361"));
        assertThat(registration.getId(), is(361L));
        assertThat(registration.getDomainId(), is(104040L));
        assertThat(registration.getRegistrantId(), is(2715L));
        assertThat(registration.getPeriod(), is(1));
        assertThat(registration.getState(), is("registering"));
        assertThat(registration.hasAutoRenew(), is(false));
        assertThat(registration.hasWhoisPrivacy(), is(false));
        assertThat(registration.getCreatedAt(), is(OffsetDateTime.of(2023, 1, 27, 17, 44, 32, 0, UTC)));
        assertThat(registration.getUpdatedAt(), is(OffsetDateTime.of(2023, 1, 27, 17, 44, 40, 0, UTC)));
    }

    @Test
    public void testGetDomainRenewal() {
        server.stubFixtureAt("getDomainRenewal/success.http");

        DomainRenewal renewal = client.registrar.getDomainRenewal(1010, "bingo.pizza", 1).getData();

        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/bingo.pizza/renewals/1"));
        assertThat(renewal.getId(), is(1L));
        assertThat(renewal.getDomainId(), is(999L));
        assertThat(renewal.getPeriod(), is(1));
        assertThat(renewal.getState(), is("renewed"));
        assertThat(renewal.getCreatedAt(), is(OffsetDateTime.of(2016, 12, 9, 19, 46, 45, 0, UTC)));
        assertThat(renewal.getUpdatedAt(), is(OffsetDateTime.of(2016, 12, 12, 19, 46, 45, 0, UTC)));
    }

    @Test
    public void testRegisterDomain() {
        server.stubFixtureAt("registerDomain/success.http");
        RegistrationOptions options = RegistrationOptions.of(10);
        SimpleResponse<DomainRegistration> response = client.registrar.registerDomain(1010, "example.com", options);
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/registrations"));
        assertThat(server.getRecordedRequest().getJsonObjectPayload(), hasEntry(is("registrant_id"), number(10)));
        DomainRegistration registration = response.getData();
        assertThat(registration.getId(), is(1L));
        assertThat(registration.getDomainId(), is(999L));
        assertThat(registration.getRegistrantId(), is(2L));
        assertThat(registration.getState(), is("new"));
        assertThat(registration.hasAutoRenew(), is(false));
        assertThat(registration.hasWhoisPrivacy(), is(false));
        assertThat(registration.getCreatedAt(), is(OffsetDateTime.of(2016, 12, 9, 19, 35, 31, 0, UTC)));
        assertThat(registration.getUpdatedAt(), is(OffsetDateTime.of(2016, 12, 9, 19, 35, 31, 0, UTC)));
    }

    @Test
    public void testRenewDomain() {
        server.stubFixtureAt("renewDomain/success.http");
        RenewOptions options = RenewOptions.empty().period(3);
        DomainRenewal domainRenewal = client.registrar.renewDomain(1010, "example.com", options).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/renewals"));
        Map<String, Object> payload = server.getRecordedRequest().getJsonObjectPayload();
        assertThat(payload, hasEntry(is("period"), number(3)));
        assertThat(payload, not(hasKey("premium_price")));
        assertThat(domainRenewal.getId(), is(1L));
        assertThat(domainRenewal.getDomainId(), is(999L));
        assertThat(domainRenewal.getPeriod(), is(1));
        assertThat(domainRenewal.getState(), is("new"));
        assertThat(domainRenewal.getCreatedAt(), is(OffsetDateTime.of(2016, 12, 9, 19, 46, 45, 0, UTC)));
        assertThat(domainRenewal.getUpdatedAt(), is(OffsetDateTime.of(2016, 12, 9, 19, 46, 45, 0, UTC)));
    }

    @Test(expected = DnsimpleException.class)
    public void testRenewDomainTooSoon() {
        server.stubFixtureAt("renewDomain/error-tooearly.http");
        RenewOptions options = RenewOptions.empty().period(3);
        client.registrar.renewDomain(1010, "example.com", options);
    }

    @Test
    public void testTransferDomain() {
        server.stubFixtureAt("transferDomain/success.http");
        TransferOptions options = TransferOptions.of(1).authCode("x1y2z3");
        SimpleResponse<DomainTransfer> response = client.registrar.transferDomain(1010, "example.com", options);
        DomainTransfer transfer = response.getData();
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/transfers"));
        Map<String, Object> jsonObjectPayload = server.getRecordedRequest().getJsonObjectPayload();
        assertThat(jsonObjectPayload, allOf(
                hasEntry(is("registrant_id"), number(1)),
                hasEntry("auth_code", "x1y2z3")
        ));
        assertThat(transfer.getId(), is(number(1)));
    }

    @Test
    public void testGetDomainTransfer() {
        server.stubFixtureAt("getDomainTransfer/success.http");
        SimpleResponse<DomainTransfer> response = client.registrar.getDomainTransfer(1010, "example.com", 361);
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
        SimpleResponse<DomainTransfer> response = client.registrar.cancelDomainTransfer(1010, "example.com", 361);
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
        TransferOptions options = TransferOptions.of(1).authCode("x1y2z3");
        client.registrar.transferDomain(1010, "example.com", options);
    }

    @Test(expected = DnsimpleException.class)
    public void testTransferDomainAuthCodeRequired() {
        server.stubFixtureAt("transferDomain/error-missing-authcode.http");
        TransferOptions options = TransferOptions.of(1);
        client.registrar.transferDomain(1010, "example.com", options);
    }

    @Test
    public void testTransferDomainOut() {
        server.stubFixtureAt("authorizeDomainTransferOut/success.http");
        client.registrar.authorizeTransferOut(1010, "example.com");
        assertThat(server.getRecordedRequest().getMethod(), is(POST));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/example.com/authorize_transfer_out"));
    }
}
