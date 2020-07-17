package com.dnsimple.request;

public class TemplateRecordOptions {
    private final String name;
    private final String type;
    private final String content;
    private final Integer ttl;
    private final Integer priority;

    private TemplateRecordOptions(String name, String type, String content, Integer ttl, Integer priority) {
        this.name = name;
        this.type = type;
        this.content = content;
        this.ttl = ttl;
        this.priority = priority;
    }

    /**
     * @param name    the name of the record. Use an empty string to create a record for the apex.
     * @param type    the type of the record
     * @param content the content of the record
     */
    public static TemplateRecordOptions of(String name, String type, String content) {
        return new TemplateRecordOptions(name, type, content, null, null);
    }

    /**
     * Set a TTL (time to live)
     */
    public TemplateRecordOptions ttl(int ttl) {
        return new TemplateRecordOptions(name, type, content, ttl, priority);
    }

    /**
     * Set a priority
     */
    public TemplateRecordOptions priority(int priority) {
        return new TemplateRecordOptions(name, type, content, ttl, priority);
    }
}
