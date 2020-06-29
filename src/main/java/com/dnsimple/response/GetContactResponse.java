package com.dnsimple.response;

import com.dnsimple.data.Contact;

public class GetContactResponse extends ApiResponse {
    private final Contact data;

    public GetContactResponse() {
        data = null;
    }

    public GetContactResponse(Contact data) {
        this.data = data;
    }

    public Contact getData() {
        return data;
    }
}
