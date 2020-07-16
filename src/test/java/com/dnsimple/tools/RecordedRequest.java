package com.dnsimple.tools;

import com.dnsimple.http.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

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
        // Compensate for wrong transformations of integers to doubles by Gson in ID fields
        return GSON.<Map<String, Object>>fromJson(jsonPayload, MAP_GSON_TYPE).entrySet().stream().collect(toMap(
                Map.Entry::getKey,
                e -> e.getKey().endsWith("_id") && e.getValue() instanceof Double
                        ? ((Double) e.getValue()).longValue()
                        : e.getValue()));
    }

    public List<String> getJsonArrayPayload() {
        return GSON.fromJson(jsonPayload, LIST_GSON_TYPE);
    }
}
