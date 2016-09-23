package com.dnsimple;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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

import io.mikael.urlbuilder.UrlBuilder;

/**
 * Instances of the Client handle low-level HTTP calls to the API.
 */
public class Client {

  private static final String API_VERSION_PATH = "/v2/";

  public final Accounts accounts;
  public final Domains domains;
  public final Identity identity;

  private HttpTransport transport;

  public Client() {
    this.accounts = new Accounts(this);
    this.domains = new Domains(this);
    this.identity = new Identity(this);
    this.transport = new NetHttpTransport();
  }

  /**
   * Set the underlying transport mechanism.
   *
   * This method is primarily used to specify a mocked transport layer during testing.
   */
  public void setTransport(HttpTransport transport) {
    this.transport = transport;
  }


  protected HttpResponse get(String path) throws IOException {
    return get(path, null);
  }

  protected HttpResponse get(String path, Map<String, Object> options) throws IOException {
    return request(HttpMethods.GET, versionedPath(path), null, options);
  }

  protected HttpResponse post(String path, Map<String, Object> attributes) throws IOException {
    return post(path, attributes, null);
  }

  protected HttpResponse post(String path, Map<String, Object> attributes, Map<String, Object> options) throws IOException {
    return request(HttpMethods.POST, versionedPath(path), attributes, null);
  }

  protected HttpResponse delete(String path) throws IOException {
    return delete(path, null);
  }

  protected HttpResponse delete(String path, Map<String, Object> options) throws IOException {
    return request(HttpMethods.DELETE, versionedPath(path), null, null);
  }


  /**
   * Parse the response from the HTTP call into an instance of the given class.
   *
   * @param response The HTTPResponse instance
   * @param c The class to instantiate and use to build the response object
   * @throws IOException Any IO errors
   */
  protected ApiResponse parseResponse(HttpResponse response, Class<?> c) throws IOException {
    ApiResponse res = null;
    InputStream in = response.getContent();

    if (in == null) {
      try {
        return (ApiResponse)c.newInstance();
      } catch(ReflectiveOperationException e) {
        throw new RuntimeException("Cannot instantiate " + c, e);
      }
    }

    try {
      JsonParser jsonParser = GsonFactory.getDefaultInstance().createJsonParser(in);
      res = (ApiResponse)jsonParser.parse(c);
    } finally {
      in.close();
    }
    return res;
  }

  protected HttpResponse request(String method, String url, Object data, Map<String, Object> options) throws IOException {
    HttpContent content = null;
    if (data != null) {
       content = new JsonHttpContent(new GsonFactory(), data);
    }

    HttpRequest request = transport.createRequestFactory().buildRequest(method, buildUrl(url, options), content);

    return request.execute();
  }


  private String versionedPath(String path) {
     return Dnsimple.getApiBase() + API_VERSION_PATH + path;
  }

  private GenericUrl buildUrl(String url, Map<String, Object> options) {
    UrlBuilder urlBuilder = UrlBuilder.fromString(url);
    if (options != null && options.containsKey("page")) {
      urlBuilder = urlBuilder.addParameter("page", options.get("page").toString());
    }

    return new GenericUrl(urlBuilder.toString());
  }

}
