package com.dnsimple.response;

import com.dnsimple.data.Contact;

import com.google.api.client.util.Key;

public class UpdateContactResponse extends ApiResponse {
  @Key("data")
  private Contact data;

  public Contact getData() {
    return data;
  }
}
