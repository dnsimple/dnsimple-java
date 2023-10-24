
package com.dnsimple.endpoints;

import com.dnsimple.data.*;
import com.dnsimple.http.HttpEndpointClient;
import com.dnsimple.request.*;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.SimpleResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dnsimple.http.HttpMethod.*;

public class Billing {
    private final HttpEndpointClient client;

    public Billing(HttpEndpointClient client) {
        this.client = client;
    }

    /**
     * Lists the billing charges for the account.
     *
     * @see <a href="https://developer.dnsimple.com/v2/billing/#listCharges">https://developer.dnsimple.com/v2/billing/#listCharges</a>
     */
    public ListResponse<Charge> listCharges(Number account, ListOptions options) {
        return client.list(
                GET,
                String.format("/%s/billing/charges", account),
                options,
                null, Charge.class
        );
    }
}