package com.kenanozdamar.android.demo.services.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kenanozdamar.android.demo.services.network.exceptions.NetworkException;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
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
        return Observable.create((emitter) -> {
            final OkHttpClient client = OkHttpClientFactory.genClient();
            try {
                final Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                    final String msg = response.message();
                    final int code = response.code();
                    final String url = request.url().toString();
                    handleError(emitter, url, code, msg, null);
                    response.close();
                    return;
                }

                final ResponseBody body = response.body();

                if (body == null) {
                    final String msg = response.message();
                    final int code = response.code();
                    final String url = request.url().toString();
                    handleError(emitter, url, code, msg, null);
                    response.close();
                    return;
                }

                if (response.cacheResponse() != null) {
                    Log.d(TAG, "Cached response found for: " + request.url());
                }

                final String contents = body.string();
                emitter.onNext(contents);
                emitter.onComplete();

            } catch (SocketTimeoutException ex) {
                Log.w(TAG, " Error on request to url < "
                        + request.url().toString()
                        + " > and message < "
                        + ex.getMessage()
                        + " >"
                );
            } catch (Throwable throwable) {
                Log.w(TAG, "Error on request to url < "
                        + request.url().toString()
                        + " > and message < "
                        + throwable.getMessage()
                        + " >"
                );
            }
        });
    }
    // endregion

    // region error handling.
    private void handleError(@NonNull ObservableEmitter emitter,
                             @NonNull String url,
                             @NonNull Integer networkCode,
                             @NonNull String networkMsg,
                             @Nullable Throwable exc) {

        Log.w(TAG, "Request to url < "
                + url
                + " > failed with code: < "
                + networkCode
                + " > and message < "
                + networkMsg
                + " >"
        );

        emitter.onError(
                new NetworkException(
                        url,
                        networkCode,
                        networkMsg,
                        exc
                )
        );
    }
    // endregion
}


