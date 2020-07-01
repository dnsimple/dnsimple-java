package com.dnsimple.response;

import com.dnsimple.data.TemplateRecord;



public class CreateTemplateRecordResponse extends ApiResponse {

  private TemplateRecord data;

    public TemplateRecord getData() {
        return data;
    }
}
