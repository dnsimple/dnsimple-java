package com.dnsimple.response;

import com.dnsimple.data.Contact;

public class UpdateContactResponse extends ApiResponse {
    private final Contact data;

    public UpdateContactResponse() {
        data = null;
    }

    public UpdateContactResponse(Contact data) {
        this.data = data;
    }

    public Contact getData() {
        return data;
    }
}
