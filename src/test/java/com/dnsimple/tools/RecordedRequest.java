package com.dnsimple.tools;

import com.dnsimple.endpoints.http.java11.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordedRequest {
    private static final Gson GSON = new Gson();
    private static final Type LIST_GSON_TYPE = new TypeToken<ArrayList<String>>() {
    }.getType();
    private static final Type MAP_GSON_TYPE = new TypeToken<HashMap<String, Object>>() {
    }.getType();
    private final HttpMethod method;
    private final String path;
    private final Map<String, String> headers;
    private final String jsonPayload;

    public RecordedRequest(HttpMethod method, String path, Map<String, String> headers, String jsonPayload) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.jsonPayload = jsonPayload;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, Object> getJsonObjectPayload() {
        return GSON.fromJson(jsonPayload, MAP_GSON_TYPE);
    }

    public List<String> getJsonArrayPayload() {
        return GSON.fromJson(jsonPayload, LIST_GSON_TYPE);
    }
}
