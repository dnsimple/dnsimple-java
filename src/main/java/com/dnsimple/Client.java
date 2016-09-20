package com.dnsimple;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.gson.GsonFactory;

public class Client {

  public final Identity identity;

  private HttpTransport transport;

  public Client() {
    this.identity = new Identity(this);
    this.transport = new NetHttpTransport();
  }

  public void setTransport(HttpTransport transport) {
    this.transport = transport;
  }

  public HttpResponse get(String path) throws IOException {
    return request("GET", Dnsimple.getApiBase() + "/v2/" + path, null);
  }

  private HttpResponse request(String method, String url, Object data) throws IOException {
    HttpContent content = null;
    if (data != null) {
       content = new JsonHttpContent(new GsonFactory(), data);
    }

    HttpRequest request = transport.createRequestFactory().buildRequest(method, new GenericUrl(url), content);

    return request.execute();
  }

}
