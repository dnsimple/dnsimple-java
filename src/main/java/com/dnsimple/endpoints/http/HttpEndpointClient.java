package com.dnsimple.endpoints.http;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.ApiResponse;

import java.io.IOException;
import java.util.Map;

public interface HttpEndpointClient {
    <T extends ApiResponse> T get(String path, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    <T extends ApiResponse> T post(String path, Object attributes, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    <T extends ApiResponse> T put(String path, Object attributes, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    <T extends ApiResponse> T patch(String path, Object attributes, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    <T extends ApiResponse> T delete(String path, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;
}
