package com.dnsimple.request;

import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SortField {
    final String field;
    final Order order;

    SortField(String field, Order order) {
        this.field = field;
        this.order = order;
    }

    public String asQueryStringParam() {
        return String.format(
                "%s%s%s",
                URLEncoder.encode(field, UTF_8),
                URLEncoder.encode(":", UTF_8),
                order.queryStringValue
        );
    }

    public enum Order {
        ASC("asc"), DESC("desc");
        final String queryStringValue;

        Order(String queryStringValue) {
            this.queryStringValue = queryStringValue;
        }
    }
}
