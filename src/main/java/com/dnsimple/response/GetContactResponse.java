package com.dnsimple.response;

import com.dnsimple.data.Contact;

public class GetContactResponse extends ApiResponse {
    private Contact data;

    public Contact getData() {
        return data;
    }
}
