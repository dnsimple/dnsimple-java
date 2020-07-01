package com.dnsimple.data;

import java.util.List;

public class TldExtendedAttribute {

  private String name;


  private String description;


  private Boolean required;


  private List<TldExtendedAttributeOption> options;

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
