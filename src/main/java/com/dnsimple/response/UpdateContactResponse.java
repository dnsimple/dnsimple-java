package com.dnsimple.response;

import com.dnsimple.data.Contact;

public class UpdateContactResponse extends ApiResponse {
    private Contact data;

    public Contact getData() {
        return data;
    }
}
