package com.kenanozdamar.android.demo.services;

import com.kenanozdamar.android.demo.services.githubclient.GithubClientFacade;
import com.kenanozdamar.android.demo.services.network.NetworkFacade;
import com.kenanozdamar.android.demo.services.network.OkHttpFetcher;

public class ServicesFacade {

    // region ivars
    private final NetworkFacade networkFacade;
    private final GithubClientFacade githubClientFacade;
    private static ServicesFacade serviceFacade;
    // endregion

    // region constructor (private)
    private ServicesFacade() {
        networkFacade = new NetworkFacade(new OkHttpFetcher());
        githubClientFacade = new GithubClientFacade();
        githubClientFacade.setNetworkFacade(networkFacade);
    }
    // endregion

    // region  Factory getter
    public static ServicesFacade getServiceFacade() {
        if (serviceFacade == null) {
            serviceFacade = new ServicesFacade();
        }

        return serviceFacade;
    }
    // endregion

    // region getters
    public GithubClientFacade getGithubClientFacade() {
        return githubClientFacade;
    }
    // endregion
}
