package com.dnsimple.request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class ZoneRecordUpdateOptions {
    private final String name;
    private final String content;
    private final Integer ttl;
    private final Integer priority;
    private final List<String> regions;

    private ZoneRecordUpdateOptions(String name, String content, Integer ttl, Integer priority, List<String> regions) {
        this.name = name;
        this.content = content;
        this.ttl = ttl;
        this.priority = priority;
        this.regions = regions;
    }

    public static ZoneRecordUpdateOptions empty() {
        return new ZoneRecordUpdateOptions(null, null, null, null, emptyList());
    }

    /**
     * Set a new name
     */
    public ZoneRecordUpdateOptions name(String name) {
        return new ZoneRecordUpdateOptions(name, content, ttl, priority, regions);
    }

    /**
     * Set a new content
     */
    public ZoneRecordUpdateOptions content(String content) {
        return new ZoneRecordUpdateOptions(name, content, ttl, priority, regions);
    }

    /**
     * Set a new TTL (time to live)
     */
    public ZoneRecordUpdateOptions ttl(int ttl) {
        return new ZoneRecordUpdateOptions(name, content, ttl, priority, regions);
    }

    /**
     * Set a new priority
     */
    public ZoneRecordUpdateOptions priority(int priority) {
        return new ZoneRecordUpdateOptions(name, content, ttl, priority, regions);
    }

    /**
     * Set new regions where the record will be present.
     * More info at <a href="https://developer.dnsimple.com/v2/zones/records/#zone-record-regions">https://developer.dnsimple.com/v2/zones/records/#zone-record-regions</a>
     */
    public ZoneRecordUpdateOptions regions(String... regions) {
        return new ZoneRecordUpdateOptions(name, content, ttl, priority, Arrays.asList(regions));
    }

    /**
     * @return a map with this object's attributes and prunes the `regions` attribute if it's empty
     */
    public Map<String, Object> asPayload() {
        var map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("content", content);
        map.put("ttl", ttl);
        map.put("priority", priority);
        if (!regions.isEmpty())
            map.put("regions", regions);
        return map;
    }
}
