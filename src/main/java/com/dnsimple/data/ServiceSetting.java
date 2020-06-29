package com.dnsimple.data;

public class ServiceSetting {
    private final String name;
    private final String label;
    private final String append;
    private final String description;
    private final String example;
    private final Boolean password;

    public ServiceSetting(String name, String label, String append, String description, String example, Boolean password) {
        this.name = name;
        this.label = label;
        this.append = append;
        this.description = description;
        this.example = example;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getAppend() {
        return append;
    }

    public String getDescription() {
        return description;
    }

    public String getExample() {
        return example;
    }

    public Boolean getPassword() {
        return password;
    }
}
