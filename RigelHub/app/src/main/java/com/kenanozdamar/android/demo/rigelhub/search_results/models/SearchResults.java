package com.kenanozdamar.android.demo.rigelhub.search_results.models;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {

    // region ivar(s)
    private List<SearchResult> searchResults = new ArrayList<>();
    // endregion

    // region getters and setters

    public List<SearchResult> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
    }

    // endregion
}
