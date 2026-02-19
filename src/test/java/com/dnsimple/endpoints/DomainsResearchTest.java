package com.dnsimple.endpoints;

import com.dnsimple.data.DomainResearchStatus;
import com.dnsimple.response.SimpleResponse;
import com.dnsimple.tools.DnsimpleTestBase;
import org.junit.Test;

import java.util.List;

import static com.dnsimple.http.HttpMethod.GET;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests for the domain research status endpoint.
 */
public class DomainsResearchTest extends DnsimpleTestBase {

    @Test
    public void testDomainResearchStatusBuildsCorrectRequest() {
        server.stubFixtureAt("getDomainsResearchStatus/success-unavailable.http");
        client.domains.domainResearchStatus(1010, "taken.com");

        assertThat(server.getRecordedRequest().getMethod(), is(GET));
        assertThat(server.getRecordedRequest().getPath(), is("/v2/1010/domains/research/status?domain=taken.com"));
    }

    @Test
    public void testDomainResearchStatusReturnsDomainResearchStatus() {
        server.stubFixtureAt("getDomainsResearchStatus/success-unavailable.http");

        SimpleResponse<DomainResearchStatus> response = client.domains.domainResearchStatus(1010, "taken.com");

        assertThat(response, is(notNullValue()));
        DomainResearchStatus data = response.getData();
        assertThat(data, is(notNullValue()));
        assertThat(data.getRequestId(), is("25dd77cb-2f71-48b9-b6be-1dacd2881418"));
        assertThat(data.getDomain(), is("taken.com"));
        assertThat(data.getAvailability(), is("unavailable"));
        assertThat(data.getErrors(), is(empty()));
    }

    @Test
    public void testDomainResearchStatusWithAvailableDomain() {
        server.stubFixtureAt("getDomainsResearchStatus/success-available.http");

        SimpleResponse<DomainResearchStatus> response = client.domains.domainResearchStatus(1010, "available.com");

        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getData().getAvailability(), is("available"));
        assertThat(response.getData().getDomain(), is("available.com"));
    }

    @Test
    public void testDomainResearchStatusWithUnsupportedTld() {
        server.stubFixtureAt("getDomainsResearchStatus/success-unsupported-tld.http");

        SimpleResponse<DomainResearchStatus> response = client.domains.domainResearchStatus(1010, "example.unsupported");

        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getData().getErrors(), is(not(empty())));
    }
}
