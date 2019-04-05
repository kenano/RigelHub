package com.kenanozdamar.android.demo.rigelhub.search_results;

import android.util.Log;

import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResult;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResults;
import com.kenanozdamar.android.demo.services.ServicesFacade;
import com.kenanozdamar.android.demo.services.githubclient.ClientCallback;
import com.kenanozdamar.android.demo.services.githubclient.GithubClientFacade;
import com.kenanozdamar.android.demo.services.githubclient.models.ClientResults;
import com.kenanozdamar.android.demo.services.githubclient.models.Item;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPresenter {

    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = SearchResultsPresenter.class.getSimpleName();
    // endregion

    // region constants
    private static final int MAX_DISPLAY_COUNT = 3;
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

    public void unregister() {
        view = null;
    }


    public void request(String query) {
        GithubClientFacade client = serviceFacade.getGithubClientFacade();
        client.request(query, new ClientCallback() {
            @Override
            public void onUpdate(ClientResults results) {
                Log.d(TAG, results.toString());
                view.showResults(createSearchResults(results));
            }

            @Override
            public void onError(Throwable exc) {
                Log.w(TAG, exc.getMessage());
            }
        });
    }

    // region business logic helpers
    private SearchResults createSearchResults(ClientResults apiResults) {
        List<SearchResult> resultList = new ArrayList<>();
        List<Item> topList = apiResults.getItems()
                .subList(0, (apiResults.getItems().size() >= MAX_DISPLAY_COUNT) ? MAX_DISPLAY_COUNT : apiResults.getItems().size());
        for (Item apiResult : topList) {
            SearchResult result = new SearchResult();
            result.setRepositoryName(apiResult.getName());
            result.setStarCount(Integer.parseInt(apiResult.getStargazersCount()));
            result.setWebUrl(apiResult.getUrl());
            resultList.add(result);
        }
        SearchResults searchResults = new SearchResults();
        searchResults.setSearchResults(resultList);
        return searchResults;
    }
    // endregion
}
