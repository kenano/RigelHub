package com.kenanozdamar.android.demo.services.githubclient;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenanozdamar.android.demo.services.githubclient.models.ClientResults;
import com.kenanozdamar.android.demo.services.network.NetworkFacade;

import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class GithubClientFacade {

    private static final String BASE_URL = "https://api.github.com/search/repositories?q=+org:%1$s&sort=stars&order=desc";

    private NetworkFacade networkFacade;

    // region Setter(s).
    public void setNetworkFacade(NetworkFacade networkFacade) {
        this.networkFacade = networkFacade;
    }
    // endregion

    // region Request.
    public void request(@NonNull String searchParameter, @NonNull ClientCallback listener) {
        Disposable disposable = makeRequest(buildUrl(searchParameter))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((dataAsString) -> {
                    Log.d(TAG, dataAsString);
                    return parse(dataAsString);
                })
                .subscribe(
                        (searchResults) -> onNext(searchResults, listener),
                        (error) -> onError(error, listener)
        );
    }
    // endregion


    // region Internal Request.
    private Observable<String> makeRequest(@NonNull final String url) {
        return networkFacade.request(url);
    }
    // endregion


    private Observable<ClientResults> parse(@NonNull String dataAsString) {
        return Observable.create((observer) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ClientResults searchResults = mapper.readValue(dataAsString, ClientResults.class);
                observer.onNext(searchResults);
                observer.onComplete();

            } catch (Throwable ex) {
                observer.onError(ex);
            }
        });
    }

    // region url helpers
    private String buildUrl(String query) {
        return String.format(
                Locale.getDefault(),
                BASE_URL,
                query
        );
    }
    // endregion

    private void onNext(ClientResults results, ClientCallback listener) {
        if (listener != null) {
            listener.onUpdate(results);
        }
//        compositeDisposable.dispose();
    }

    private void onError(Throwable ex, ClientCallback listener) {
        if (listener != null) {
            listener.onError(ex);
        }
    }
}
