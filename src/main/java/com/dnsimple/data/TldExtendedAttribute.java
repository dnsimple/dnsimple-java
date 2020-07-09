package com.dnsimple.data;

import java.util.List;

public class TldExtendedAttribute {
    private final String name;
    private final String description;
    private final Boolean required;
    private final List<TldExtendedAttributeOption> options;

    public TldExtendedAttribute(String name, String description, Boolean required, List<TldExtendedAttributeOption> options) {
        this.name = name;
        this.description = description;
        this.required = required;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getRequired() {
        return required;
    }

    public List<TldExtendedAttributeOption> getOptions() {
        return options;
    }
}
