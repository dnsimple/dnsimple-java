package com.dnsimple;

import com.google.api.client.util.Key;

public class Contact {
  @Key("id")
  private Integer id;

  @Key("account_id")
  private Integer accountId;

  @Key("label")
  private String label;

  @Key("first_name")
  private String firstName;

  @Key("last_name")
  private String lastName;

  @Key("job_title")
  private String jobTitle;

  @Key("organization_name")
  private String organizationName;

  @Key("email")
  private String email;

  @Key("phone")
  private String phone;

  @Key("fax")
  private String fax;

  @Key("address1")
  private String address1;

  @Key("address2")
  private String address2;

  @Key("city")
  private String city;

  @Key("state_province")
  private String stateOrProvince;

  @Key("postal_code")
  private String postalCode;

  @Key("country")
  private String country;

  @Key("created_at")
  private String createdAt;

  @Key("updated_at")
  private String updatedAt;

  
  public Integer getId() {
    return id;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public String getLabel() {
    return label;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getFax() {
    return fax;
  }

  public String getAddress1() {
    return address1;
  }

  public String getAddress2() {
    return address2;
  }

  public String getCity() {
    return city;
  }

  public String getStateOrProvince() {
    return stateOrProvince;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCountry() {
    return country;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

}
