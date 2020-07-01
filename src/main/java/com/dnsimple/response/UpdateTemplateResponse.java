package com.dnsimple.response;

import com.dnsimple.data.Template;

public class UpdateTemplateResponse extends ApiResponse {
    private Template data;

    public Template getData() {
        return data;
    }
}
