package com.dnsimple.endpoints.http;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ApiResponse;
import com.google.api.client.http.HttpResponse;
import java.io.IOException;
import java.util.Map;

public interface HttpEndpointClient {

  HttpResponse get(String path) throws DnsimpleException, IOException;

  HttpResponse get(String path, Map<String, Object> options) throws DnsimpleException, IOException;

  Object get(String path, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

  HttpResponse post(String path) throws DnsimpleException, IOException;

  HttpResponse post(String path, Map<String, Object> attributes) throws DnsimpleException, IOException;

  HttpResponse post(String path, Map<String, Object> attributes, Map<String, Object> options) throws DnsimpleException, IOException;

  Object post(String path, Map<String, Object> attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

  HttpResponse put(String path) throws DnsimpleException, IOException;

  HttpResponse put(String path, Object attributes) throws DnsimpleException, IOException;

  HttpResponse put(String path, Object attributes, Map<String, Object> options) throws DnsimpleException, IOException;

  Object put(String path, Object attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

  HttpResponse patch(String path, Object attributes) throws DnsimpleException, IOException;

  HttpResponse patch(String path, Object attributes, Map<String, Object> options) throws DnsimpleException, IOException;

  Object patch(String path, Object attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

  HttpResponse delete(String path) throws DnsimpleException, IOException;

  HttpResponse delete(String path, Map<String, Object> options) throws DnsimpleException, IOException;

  Object delete(String path, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

  ApiResponse parseResponse(HttpResponse response, Class<?> c) throws IOException;

  Object parse(HttpResponse response, Class<?> c) throws IOException;
}
