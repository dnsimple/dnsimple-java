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
import static com.dnsimple.request.DomainCheckPremiumPriceAction.REGISTRATION;
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
    public void testCheckDomainPremiumPrice() {
        server.stubFixtureAt("checkDomainPremiumPrice/success.http");
        DomainPremiumPriceCheck premiumPrice = client.registrar.getDomainPremiumPrice(1010, "cocoo.co", REGISTRATION).getData();
        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/registrar/domains/cocoo.co/premium_price?action=registration"));
        assertThat(premiumPrice.getPremiumPrice(), is("2640.00"));
        assertThat(premiumPrice.getAction(), is("registration"));
    }

    @Test
    public void testCheckDomainPremiumPrice_400_notAPremiumDomain() {
        server.stubFixtureAt("checkDomainPremiumPrice/error_400_not_a_premium_domain.http");
        assertThat(
                () -> client.registrar.getDomainPremiumPrice(1010, "cocotero.love", REGISTRATION),
                thrownException(allOf(
                        is(instanceOf(BadRequestException.class)),
                        property(
                                BadRequestException::getBody,
                                equalTo(singletonMap("message", "`cocotero.love` is not a premium domain for registration"))
                        )
                ))
        );
    }

    @Test
    public void testCheckDomainPremiumPrice_400_tld_not_supported() {
        server.stubFixtureAt("checkDomainPremiumPrice/error_400_tld_not_supported.http");
        assertThat(
                () -> client.registrar.getDomainPremiumPrice(1010, "cocotero.love", REGISTRATION),
                thrownException(allOf(
                        is(instanceOf(BadRequestException.class)),
                        property(
                                BadRequestException::getBody,
                                equalTo(singletonMap("message", "TLD .LOVE is not supported"))
                        )
                ))
        );
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
