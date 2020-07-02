package com.dnsimple.endpoints.http;

import com.dnsimple.endpoints.http.java11.HttpMethod;
import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

public interface HttpEndpointClient {
    <T> ListResponse<T> list(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException;

    <T> PaginatedResponse<T> page(HttpMethod method, String path, Map<String, Object> queryStringParams, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException;

    <T> SimpleResponse<T> simple(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws IOException, InterruptedException, DnsimpleException;

    <T> SimpleResponse<T> postSimple(String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType) throws DnsimpleException, IOException, InterruptedException;

    <T> T raw(HttpMethod method, String path, Object body, Map<String, Object> queryStringParams, Class<T> dataType) throws DnsimpleException, IOException, InterruptedException;

    EmptyResponse empty(HttpMethod method, String path, Map<String, Object> queryStringParams, Object body) throws DnsimpleException, IOException, InterruptedException;

    <T> ListResponse<T> putList(String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws DnsimpleException, IOException, InterruptedException;

    <T> SimpleResponse<T> putSimple(String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws DnsimpleException, IOException, InterruptedException;

    EmptyResponse putEmpty(String path, Map<String, Object> queryStringParams, Object body) throws DnsimpleException, IOException, InterruptedException;

    <T> SimpleResponse<T> patchSimple(String path, Map<String, Object> queryStringParams, Object body, Class<T> dataType) throws DnsimpleException, IOException, InterruptedException;

    <T> SimpleResponse<T> deleteSimple(String path, Map<String, Object> queryStringParams, Class<T> dataType) throws DnsimpleException, IOException, InterruptedException;

    EmptyResponse deleteEmpty(String path, Map<String, Object> queryStringParams) throws DnsimpleException, IOException, InterruptedException;
}
