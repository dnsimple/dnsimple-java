package com.dnsimple;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;

import com.dnsimple.response.ApiResponse;
import com.dnsimple.exception.DnsimpleException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.JsonParser;

/**
 * Instances of the Client handle low-level HTTP calls to the API.
 */
public class Client {

  public final Identity identity;
  public final Domains domains;

  private HttpTransport transport;

  public Client() {
    this.identity = new Identity(this);
    this.domains = new Domains(this);
    this.transport = new NetHttpTransport();
  }

  public void setTransport(HttpTransport transport) {
    this.transport = transport;
  }

  public ApiResponse parseResponse(HttpResponse response, Class<?> c) throws IOException {
    ApiResponse res = null;
    InputStream in = response.getContent();
    try {
      JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(in);
      res = (ApiResponse)jsonParser.parse(c);
    } finally {
      in.close();
    }
    return res;
  }

  public HttpResponse get(String path) throws IOException {
    return request(HttpMethods.GET, versionedPath(path), null);
  }

  public HttpResponse post(String path, HashMap<String, Object> attributes) throws IOException {
    return request(HttpMethods.POST, versionedPath(path), attributes);
  }

  private HttpResponse request(String method, String url, Object data) throws IOException {
    HttpContent content = null;
    if (data != null) {
       content = new JsonHttpContent(new GsonFactory(), data);
    }

    HttpRequest request = transport.createRequestFactory().buildRequest(method, new GenericUrl(url), content);

    return request.execute();
  }

  private String versionedPath(String path) {
     return Dnsimple.getApiBase() + "/v2/" + path;
  }

}
