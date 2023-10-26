package com.dnsimple.endpoints;

import com.dnsimple.data.Charge;
import com.dnsimple.data.ChargeItem;
import com.dnsimple.exception.BadRequestException;
import com.dnsimple.request.ListOptions;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class BillingTest extends DnsimpleTestBase {
    @Test
    public void testListChargesProducesChargeList() {
        server.stubFixtureAt("listCharges/success.http");
        List<Charge> charges = client.billing.listCharges(1010, ListOptions.empty()).getData();
        assertThat(charges, hasSize(3));
        assertThat(charges.get(0), is(new Charge("2023-08-17T05:53:36Z", "14.50", "0.00", "1-2", "collected", List.of(new ChargeItem("Register bubble-registered.com", "14.50", 1L, "domain-registration", "bubble-registered.com")))));
        assertThat(charges.get(1), is(new Charge("2023-08-17T05:57:53Z", "14.50", "0.00", "2-2", "refunded", List.of(new ChargeItem("Register example.com", "14.50", 2L, "domain-registration", "example.com")))));
        assertThat(charges.get(2), is(new Charge("2023-10-24T07:49:05Z", "1099999.99", "0.00", "4-2", "collected", List.of(new ChargeItem("Test Line Item 1", "99999.99", null, "manual", null), new ChargeItem("Test Line Item 2", "1000000.00", null, "manual", null)))));
    }

    @Test
    public void testListChargesFail400BadFilter() {
        server.stubFixtureAt("listCharges/fail-400-bad-filter.http");
        boolean caught = false;
        try {
            client.billing.listCharges(1010, ListOptions.empty());
        } catch (BadRequestException e) {
            assertThat(e.getBody(), is(Map.of("message", "Invalid date format must be ISO8601 (YYYY-MM-DD)")));
            caught = true;
        }
        assertThat(caught, is(true));
    }

    @Test
    public void testListChargesFail403() {
        server.stubFixtureAt("listCharges/fail-403.http");
        boolean caught = false;
        try {
            client.billing.listCharges(1010, ListOptions.empty());
        } catch (BadRequestException e) {
            assertThat(e.getBody(), is(Map.of("message", "Permission Denied. Required Scope: billing:*:read")));
            caught = true;
        }
        assertThat(caught, is(true));
    }
}
