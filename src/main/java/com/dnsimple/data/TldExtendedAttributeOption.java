package com.dnsimple.data;

public class TldExtendedAttributeOption {
    private final String title;
    private final String value;
    private final String description;

    public TldExtendedAttributeOption(String title, String value, String description) {
        this.title = title;
        this.value = value;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
