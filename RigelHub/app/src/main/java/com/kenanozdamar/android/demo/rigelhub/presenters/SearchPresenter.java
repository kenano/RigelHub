package com.kenanozdamar.android.demo.rigelhub.presenters;

import android.util.Log;

import com.kenanozdamar.android.demo.rigelhub.StringUtil;
import com.kenanozdamar.android.demo.rigelhub.search_results.SearchResultsView;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResult;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResults;
import com.kenanozdamar.android.demo.services.ServicesFacade;
import com.kenanozdamar.android.demo.services.githubclient.ClientCallback;
import com.kenanozdamar.android.demo.services.githubclient.GithubClientFacade;
import com.kenanozdamar.android.demo.services.githubclient.models.ClientResults;
import com.kenanozdamar.android.demo.services.githubclient.models.Item;
import com.kenanozdamar.android.demo.services.githubclient.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter {

    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = SearchPresenter.class.getSimpleName();
    // endregion

    // region constants
    private static final int MAX_DISPLAY_COUNT = 3;
    // endregion

    // region ivar(s)
    ServicesFacade serviceFacade;
    SearchResultsView view;
    //endregion

    // region constructor
    public SearchPresenter() {
        serviceFacade = ServicesFacade.getServiceFacade();
    }
    // endregion

    // region public methods
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
                if (view ==  null) return;
                view.showResults(createSearchResults(results));
            }

            @Override
            public void onError(Throwable exc) {
                view.onError(exc);
            }
        });
    }
    // endregion

    // region static method(s)
    public static SearchResults createSearchResults(ClientResults apiResults) {
        List<SearchResult> resultList = new ArrayList<>();
        if (apiResults ==  null) return null;
        List<Item> topList = apiResults.getItems()
                .subList(0, (apiResults.getItems().size() >= MAX_DISPLAY_COUNT) ? MAX_DISPLAY_COUNT : apiResults.getItems().size());
        for (Item apiResult : topList) {

            if (apiResult.getOwner() == null ||
                    apiResult.getDescription().isEmpty() ||
                    StringUtil.isEmpty(apiResult.getName()) ||
                    StringUtil.isEmpty(apiResult.getUrl()) ||
                    StringUtil.isEmpty(apiResult.getDescription()) ||
                    StringUtil.isEmpty(apiResult.getStargazersCount())) continue;

            Owner owner = apiResult.getOwner();
            if (StringUtil.isEmpty(owner.getAvatarUrl()) ||
                    StringUtil.isEmpty(owner.getOrgName())) continue;

            SearchResult result = new SearchResult();
            result.setRepositoryName(apiResult.getName());
            result.setOrgName(owner.getOrgName());
            result.setWebUrl(apiResult.getUrl());
            result.setAvatarUrl(apiResult.getOwner().getAvatarUrl());
            result.setDescription(apiResult.getDescription());
            try {
                result.setStarCount(Integer.parseInt(apiResult.getStargazersCount()));
            } catch (NullPointerException exception) {
                continue;
            }
            resultList.add(result);

        }
        SearchResults searchResults = new SearchResults();
        searchResults.setSearchResults(resultList);
        return searchResults;
    }
    // endregion
}
