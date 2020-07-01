package com.dnsimple.data;

public class ServiceSetting {
    private String name;
    private String label;
    private String append;
    private String description;
    private String example;
    private Boolean password;

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
