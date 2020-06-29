package com.dnsimple.endpoints.http;

import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

public interface HttpEndpointClient {
    Object get(String path, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

    Object post(String path, Map<String, Object> attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

    Object put(String path, Object attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

    Object patch(String path, Object attributes, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;

    Object delete(String path, Map<String, Object> options, Class<?> c) throws DnsimpleException, IOException;
}
