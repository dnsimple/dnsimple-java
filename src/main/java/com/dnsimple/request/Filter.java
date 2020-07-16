package com.dnsimple.request;

import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Filter {
    final String name;
    final String value;

    Filter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String asQueryStringParam() {
        return String.format(
                "%s=%s",
                URLEncoder.encode(name, UTF_8),
                URLEncoder.encode(value, UTF_8)
        );
    }
}
