package com.dnsimple.endpoints.http.java11;

import com.dnsimple.exception.DnsimpleException;

import java.io.IOException;
import java.util.Map;

interface Request<CONTAINER, DATA_TYPE> {
    CONTAINER execute(String path, Object body, Map<String, Object> queryStringParams, Class<DATA_TYPE> dataType, String method) throws IOException, InterruptedException, DnsimpleException;
}
