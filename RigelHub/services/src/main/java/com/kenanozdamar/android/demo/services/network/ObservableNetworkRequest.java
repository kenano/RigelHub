package com.kenanozdamar.android.demo.services.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ObservableNetworkRequest {
    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = ObservableNetworkRequest.class.getSimpleName();
    // endregion

    // region get
    public Observable<String> get(@NonNull String url) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .get();

        Request request = requestBuilder.build();
        return makeRequest(request);
    }
    // endregion

    // region makeRequest
    private Observable<String> makeRequest(@NonNull Request request) {
        return Observable.create((observer) -> {
            final OkHttpClient client = OkHttpClientFactory.genClient();
                    try {
                        final Response response = client.newCall(request).execute();
                        if (!response.isSuccessful()) {
                            handleError(response.message());
                            response.close();
                            return;
                        }

                        final ResponseBody body = response.body();

                        //test for headers
                        Log.d(TAG, "Headers: " + response.headers().toString());

                        if (body == null) {
                            handleError(response.message());
                            response.close();
                            return;
                        }

                        if (response.cacheResponse() != null) {
                            Log.d(TAG, "Cached response found for: " + request.url());
                        }

                        final String contents = body.string();
                        observer.onNext(contents);
//                        observer.onNext(new String[]{"", ""});
//                        response.close();
                        observer.onComplete();

                    } catch (SocketTimeoutException ex) {
                        handleError("SocketTimeout Exception");
                    } catch (Throwable throwable) {
                        handleError(throwable.getMessage());
                    }
        });
    }
    // endregion

    // region error handling.
    private void handleError(String s) {
        Log.e(TAG, "There was a network error: " + s);
    }
    // endregion

}
