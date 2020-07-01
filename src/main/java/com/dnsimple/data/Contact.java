package com.dnsimple.data;

public class Contact {
    private final Integer id;
    private final Integer accountId;
    private final String label;
    private final String firstName;
    private final String lastName;
    private final String jobTitle;
    private final String organizationName;
    private final String email;
    private final String phone;
    private final String fax;
    private final String address1;
    private final String address2;
    private final String city;
    private final String stateProvince;
    private final String postalCode;
    private final String country;
    private final String createdAt;
    private final String updatedAt;

    public Contact(Integer id, Integer accountId, String label, String firstName, String lastName, String jobTitle, String organizationName, String email, String phone, String fax, String address1, String address2, String city, String stateProvince, String postalCode, String country, String createdAt, String updatedAt) {
        this.id = id;
        this.accountId = accountId;
        this.label = label;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.organizationName = organizationName;
        this.email = email;
        this.phone = phone;
        this.fax = fax;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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
