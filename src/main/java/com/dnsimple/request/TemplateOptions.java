package com.dnsimple.request;

public class TemplateOptions {
    private final String name;
    private final String sid;
    private final String description;

    private TemplateOptions(String name, String sid, String description) {
        this.name = name;
        this.sid = sid;
        this.description = description;
    }

    /**
     * @param name        The name of the template
     * @param sid         The short name of the template
     * @param description The description of the template
     */
    public static TemplateOptions of(String name, String sid, String description) {
        return new TemplateOptions(name, sid, description);
    }
}
