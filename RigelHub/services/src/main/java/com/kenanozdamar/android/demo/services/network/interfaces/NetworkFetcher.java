package com.kenanozdamar.android.demo.services.network.interfaces;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

public interface NetworkFetcher {
    Observable<String> request(@NonNull String endpoint);
}
