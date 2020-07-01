package com.dnsimple.data;

public class Contact {
    private Integer id;
    private Integer accountId;
    private String label;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String organizationName;
    private String email;
    private String phone;
    private String fax;
    private String address1;
    private String address2;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String createdAt;
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
        return stateProvince;
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
