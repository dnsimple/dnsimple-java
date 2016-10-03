package com.dnsimple.response;

import com.dnsimple.Contact;

import com.google.api.client.util.Key;

public class CreateContactResponse extends ApiResponse {
  @Key("data")
  private Contact data;

  public Contact getData() {
    return data;
  }
}
