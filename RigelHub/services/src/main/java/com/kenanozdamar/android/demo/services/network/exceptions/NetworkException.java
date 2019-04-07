package com.kenanozdamar.android.demo.services.network.exceptions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class NetworkException extends Throwable {
    private String url;
    private int code;
    private String message;
    private Throwable parent;

    public NetworkException(
            @NonNull String url,
            int code,
            @NonNull String message,
            @Nullable Throwable parent
    ) {
        this.url = url;
        this.code = code;
        this.message = message;
        this.parent = parent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getParent() {
        return parent;
    }

    public void setParent(Throwable parent) {
        this.parent = parent;
    }
}
