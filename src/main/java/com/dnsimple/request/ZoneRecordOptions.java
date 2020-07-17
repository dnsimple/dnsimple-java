package com.dnsimple.request;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class ZoneRecordOptions {
    private final String name;
    private final String type;
    private final String content;
    private final Integer ttl;
    private final Integer priority;
    private final List<String> regions;

    private ZoneRecordOptions(String name, String type, String content, Integer ttl, Integer priority, List<String> regions) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.ttl = ttl;
        this.priority = priority;
        this.regions = regions;
    }

    /**
     * @param name    the name of the record. Use an empty string to create a record for the apex.
     * @param type    the type of the record
     * @param content the content of the record
     */
    public static ZoneRecordOptions of(String name, String type, String content) {
        return new ZoneRecordOptions(name, type, content, null, null, singletonList("global"));
    }

    /**
     * Set a TTL (time to live)
     */
    public ZoneRecordOptions ttl(int ttl) {
        return new ZoneRecordOptions(name, type, content, ttl, priority, regions);
    }

    /**
     * Set a priority
     */
    public ZoneRecordOptions priority(int priority) {
        return new ZoneRecordOptions(name, type, content, ttl, priority, regions);
    }

    /**
     * Set the regions where the record will be present.
     * More info at <a href="https://developer.dnsimple.com/v2/zones/records/#zone-record-regions">https://developer.dnsimple.com/v2/zones/records/#zone-record-regions</a>
     */
    public ZoneRecordOptions regions(String... regions) {
        return new ZoneRecordOptions(name, type, content, ttl, priority, Arrays.asList(regions));
    }
}
