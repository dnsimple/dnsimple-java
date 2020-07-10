package com.dnsimple.data;

import java.time.OffsetDateTime;

public class Contact {
    private final Long id;
    private final Long accountId;
    private final String label;
    private final String firstName;
    private final String lastName;
    private final String jobTitle;
    private final String organizationName;
    private final String address1;
    private final String address2;
    private final String city;
    private final String stateProvince;
    private final String postalCode;
    private final String country;
    private final String phone;
    private final String fax;
    private final String email;
    private final OffsetDateTime createdAt;
    private final OffsetDateTime updatedAt;

    public Contact(Long id, Long accountId, String label, String firstName, String lastName, String jobTitle, String organizationName, String address1, String address2, String city, String stateProvince, String postalCode, String country, String phone, String fax, String email, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.label = label;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.organizationName = organizationName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
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

    public String getOrganization() {
        return organizationName;
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

    public String getStateProvince() {
        return stateProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}
