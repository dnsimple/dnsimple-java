package com.dnsimple.endpoints.http.java11;

import com.dnsimple.response.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

class Java11ContainerResponseHandler<DATA_TYPE, CONTAINER extends ApiResponse<DATA_TYPE>> implements HttpResponse.BodyHandler<Supplier<CONTAINER>> {
    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES).create();
    private final Class<DATA_TYPE> dataType;
    private final Class<CONTAINER> containerType;
    private final Supplier<CONTAINER> emptyContainerSupplier;

    Java11ContainerResponseHandler(Class<DATA_TYPE> dataType, Class<CONTAINER> containerType, Supplier<CONTAINER> emptyContainerSupplier) {
        this.dataType = dataType;
        this.containerType = containerType;
        this.emptyContainerSupplier = emptyContainerSupplier;
    }

    @Override
    public HttpResponse.BodySubscriber<Supplier<CONTAINER>> apply(HttpResponse.ResponseInfo responseInfo) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return responseInfo.statusCode() != 204
                ? HttpResponse.BodySubscribers.mapping(upstream, is -> buildSupplier(is, dataType, containerType))
                : HttpResponse.BodySubscribers.mapping(upstream, __ -> emptyContainerSupplier);
    }

    private static <CONTAINER extends ApiResponse<DATA_TYPE>, DATA_TYPE> Supplier<CONTAINER> buildSupplier(InputStream inputStream, Class<DATA_TYPE> dataType, Class<CONTAINER> containerType) {
        return () -> {
            try (InputStream stream = inputStream;
                 InputStreamReader isr = new InputStreamReader(stream);
                 BufferedReader br = new BufferedReader(isr)) {
                return gson.fromJson(br, TypeToken.getParameterized(containerType, dataType).getType());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
}
