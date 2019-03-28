package com.kenanozdamar.android.demo.services.githubclient;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kenanozdamar.android.demo.services.network.NetworkFacade;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class GithubClientFacade {
    private static final String URL = "https://api.github.com/search/repositories?q=mario+language:kotlin&sort=stars&order=desc";
//    https://api.github.com/orgs/nytimes/repos

    private NetworkFacade networkFacade;

    // region Setter(s).
    public void setNetworkFacade(NetworkFacade networkFacade) {
        this.networkFacade = networkFacade;
    }
    // endregion

    // region Request.
    public void request() {
        makeRequest(URL)
                .subscribeOn(Schedulers.io())
                .flatMap((dataAsString) -> {
                    Log.d(TAG, dataAsString);
                    return parse(dataAsString);
                })
                .subscribe();
    }
    // endregion


    // region Internal Request.
    private Observable<String> makeRequest(@NonNull final String url) {
        return networkFacade.request(url);
    }
    // endregion

    private Observable<String> parse(@NonNull String dataAsString) {
        return Observable.fromArray(new String[]{dataAsString});
    }
}
