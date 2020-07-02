package com.dnsimple.endpoints.http.java11;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static java.net.http.HttpResponse.BodySubscribers.mapping;

class Java11RawResponseHandler<DATA_TYPE> implements HttpResponse.BodyHandler<Supplier<DATA_TYPE>> {
    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
    private final Class<DATA_TYPE> dataType;

    public Java11RawResponseHandler(Class<DATA_TYPE> dataType) {
        this.dataType = dataType;
    }

    @Override
    public HttpResponse.BodySubscriber<Supplier<DATA_TYPE>> apply(HttpResponse.ResponseInfo responseInfo) {
        var upstream = HttpResponse.BodySubscribers.ofInputStream();
        return responseInfo.statusCode() != 204
                ? mapping(upstream, is -> () -> deserialize(is, dataType))
                : mapping(upstream, __ -> () -> null);
    }

    private static <DATA_TYPE> DATA_TYPE deserialize(InputStream inputStream, Class<DATA_TYPE> dataType) {
        try (InputStream stream = inputStream;
             InputStreamReader isr = new InputStreamReader(stream);
             BufferedReader br = new BufferedReader(isr)) {
            return gson.fromJson(br, dataType);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
