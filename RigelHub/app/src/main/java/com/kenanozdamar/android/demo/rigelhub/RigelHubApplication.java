package com.kenanozdamar.android.demo.rigelhub;

import android.app.Application;

import com.kenanozdamar.android.demo.services.ServicesFacade;
import com.kenanozdamar.android.demo.services.network.OkHttpClientFactory;

public class RigelHubApplication extends Application {

    // region Application Override(s).
    @Override
    public void onCreate() {
        super.onCreate();
        initializeOkHttp();
        ServicesFacade.getServiceFacade();
    }
    // endregion

    // region OkHttp Cache Initialization.
    private void initializeOkHttp() {
        OkHttpClientFactory.genClient();
    }
    // endregion
}
