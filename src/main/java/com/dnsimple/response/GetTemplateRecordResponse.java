package com.dnsimple.response;

import com.dnsimple.data.TemplateRecord;



public class GetTemplateRecordResponse extends ApiResponse {

  private TemplateRecord data;

    public TemplateRecord getData() {
        return data;
    }
}
