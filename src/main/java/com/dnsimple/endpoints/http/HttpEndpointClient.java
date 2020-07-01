package com.dnsimple.endpoints.http;

import com.dnsimple.exception.DnsimpleException;
import com.dnsimple.response.EmptyResponse;
import com.dnsimple.response.ListResponse;
import com.dnsimple.response.PaginatedResponse;
import com.dnsimple.response.SimpleResponse;

import java.io.IOException;
import java.util.Map;

public interface HttpEndpointClient {
    <T> ListResponse<T> getList(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException;

    <T> PaginatedResponse<T> getPage(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException;

    <T> SimpleResponse<T> getSimple(String path, Map<String, Object> options, Class<T> typeParam) throws IOException, InterruptedException, DnsimpleException;

    <T> SimpleResponse<T> postSimple(String path, Object attributes, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    <T> T postUnwrapped(String path, Object attributes, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    EmptyResponse postEmpty(String path, Object attributes, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    <T> ListResponse<T> putList(String path, Object attributes, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    <T> SimpleResponse<T> putSimple(String path, Object attributes, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    EmptyResponse putEmpty(String path, Object attributes, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;

    <T> SimpleResponse<T> patchSimple(String path, Object attributes, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    <T> SimpleResponse<T> deleteSimple(String path, Map<String, Object> options, Class<T> c) throws DnsimpleException, IOException, InterruptedException;

    EmptyResponse deleteEmpty(String path, Map<String, Object> options) throws DnsimpleException, IOException, InterruptedException;
}
