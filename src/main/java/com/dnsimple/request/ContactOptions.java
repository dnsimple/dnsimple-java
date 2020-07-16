package com.dnsimple.request;

public class ContactOptions {
    private final String label;
    private final String firstName;
    private final String lastName;
    private final String organizationName;
    private final String jobTitle;
    private final String address1;
    private final String address2;
    private final String city;
    private final String stateProvince;
    private final String postalCode;
    private final String country;
    private final String email;
    private final String phone;
    private final String fax;

    private ContactOptions(String label, String firstName, String lastName, String organizationName, String jobTitle, String address1, String address2, String city, String stateProvince, String postalCode, String country, String email, String phone, String fax) {
        this.label = label;
        this.firstName = firstName;
        this.lastName = lastName;
        this.organizationName = organizationName;
        this.jobTitle = jobTitle;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.fax = fax;
    }

    public static ContactOptions of(String firstName, String lastName, String address1, String city, String stateProvince, String postalCode, String country, String email, String phone) {
        return new ContactOptions(null, firstName, lastName, null, null, address1, null, city, stateProvince, postalCode, country, email, phone, null);
    }

    public ContactOptions label(String label) {
        return new ContactOptions(label, firstName, lastName, organizationName, jobTitle, address1, address2, city, stateProvince, postalCode, country, email, phone, fax);
    }

    public ContactOptions organizationName(String organizationName, String jobTitle) {
        return new ContactOptions(label, firstName, lastName, organizationName, jobTitle, address1, address2, city, stateProvince, postalCode, country, email, phone, fax);
    }

    public ContactOptions address2(String address2) {
        return new ContactOptions(label, firstName, lastName, organizationName, jobTitle, address1, address2, city, stateProvince, postalCode, country, email, phone, fax);
    }

    public ContactOptions fax(String fax) {
        return new ContactOptions(label, firstName, lastName, organizationName, jobTitle, address1, address2, city, stateProvince, postalCode, country, email, phone, fax);
    }
}
