package com.dnsimple;

import java.io.InputStream;
import java.io.IOException;

import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.gson.GsonFactory;

public class Identity {
  private Client client;
  
  public Identity(Client client) {
    this.client = client;
  }

  public DnsimpleResponse whoami() throws DnsimpleException, IOException {
    HttpResponse response = client.get("whoami");
    System.out.println(response);
    System.out.println("Content type: " + response.getContentType());

    DnsimpleResponse dnsimpleResponse = null;
    InputStream in = response.getContent();
    try {
      JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(in);
      dnsimpleResponse = jsonParser.parse(DnsimpleResponse.class);
      System.out.println(dnsimpleResponse);
    } finally {
      in.close();
    }

    return dnsimpleResponse;
  }
}
