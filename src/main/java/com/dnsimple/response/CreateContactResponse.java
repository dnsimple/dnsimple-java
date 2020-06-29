package com.dnsimple.response;

import com.dnsimple.data.Contact;

public class CreateContactResponse extends ApiResponse {
    private final Contact data;

    public CreateContactResponse() {
        data = null;
    }

    public CreateContactResponse(Contact data) {
        this.data = data;
    }

    public Contact getData() {
        return data;
    }
}
