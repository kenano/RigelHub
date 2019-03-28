package com.kenanozdamar.android.demo.services.network;

import android.support.annotation.NonNull;

import com.kenanozdamar.android.demo.services.network.interfaces.NetworkFetcher;

import io.reactivex.Observable;

public class OkHttpFetcher implements NetworkFetcher {

    @Override
    public Observable<String> request(@NonNull String endpoint) {
        ObservableNetworkRequest networkRequest = new ObservableNetworkRequest();
        return networkRequest.get(endpoint);
    }
}
