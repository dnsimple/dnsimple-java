package com.dnsimple.response;

import com.dnsimple.data.Template;

public class CreateTemplateResponse extends ApiResponse {
    private Template data;

    public Template getData() {
        return data;
    }
}
