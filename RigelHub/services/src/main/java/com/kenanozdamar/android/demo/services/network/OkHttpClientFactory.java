package com.kenanozdamar.android.demo.services.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpClientFactory {

    private static final String NULL_FACTORY = "Need to generate the OKHTTP factory.";
    public static final long CONNECTION_TIMEOUT_MS = 30000;
    public static final long WRITE_TIMEOUT_MS = 10000;
    public static final long READ_TIMEOUT_MS = 10000;

    private static OkHttpClientFactory okHttpClientFactory;
    private final OkHttpClient okHttpClient;


    public static OkHttpClient genClient() {
        if (okHttpClientFactory == null) okHttpClientFactory = new OkHttpClientFactory();

        return okHttpClientFactory.okHttpClient;
    }

    private OkHttpClientFactory() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        configureTimeouts(okHttpClientBuilder);
        okHttpClient = okHttpClientBuilder.build();
    }

    // region Timeout.
    private void configureTimeouts(OkHttpClient.Builder builder) {
        builder.connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        builder.writeTimeout(WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        builder.readTimeout(READ_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }
    // endregion
}
