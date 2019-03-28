package com.kenanozdamar.android.demo.services.network;

import android.support.annotation.NonNull;

import com.kenanozdamar.android.demo.services.network.interfaces.NetworkFetcher;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NetworkFacade {

    // region ivar(s)
    public final NetworkFetcher fetcher;
    // endregion

    // region constructor
    public NetworkFacade(NetworkFetcher networkFetcher) {
        this.fetcher = networkFetcher;
    }
    // endregion

    // region request
    public Observable<String> request(@NonNull String url) {
        return fetcher.request(url).subscribeOn(Schedulers.io());
    }
    // endregion
}
