package com.kenanozdamar.android.demo.rigelhub.search_results;

import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResults;

public interface SearchResultsView {
    void onError(Throwable exc);
    void showResults(SearchResults results);
}
