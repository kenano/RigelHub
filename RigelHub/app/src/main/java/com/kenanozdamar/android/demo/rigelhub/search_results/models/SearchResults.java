package com.kenanozdamar.android.demo.rigelhub.search_results.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchResults implements Serializable {

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
