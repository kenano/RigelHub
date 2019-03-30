package com.kenanozdamar.android.demo.rigelhub.search_results;

import com.kenanozdamar.android.demo.services.ServicesFacade;
import com.kenanozdamar.android.demo.services.githubclient.GithubClientFacade;

public class SearchResultsPresenter {

    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = SearchResultsPresenter.class.getSimpleName();
    // endregion

    // region ivar(s)
    ServicesFacade serviceFacade;
    SearchResultsView view;
    //endregion


    public SearchResultsPresenter() {
        serviceFacade = ServicesFacade.getServiceFacade();
    }

    public void register(SearchResultsView view) {
        this.view = view;
    }


    public void request(String query) {
        GithubClientFacade client = serviceFacade.getGithubClientFacade();
        client.request(query);
    }
}
