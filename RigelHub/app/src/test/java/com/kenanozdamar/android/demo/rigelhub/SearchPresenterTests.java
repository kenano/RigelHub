package com.kenanozdamar.android.demo.rigelhub;

import com.kenanozdamar.android.demo.rigelhub.presenters.SearchPresenter;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResult;
import com.kenanozdamar.android.demo.rigelhub.fixtures.Fixtures;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResults;
import com.kenanozdamar.android.demo.services.githubclient.models.ClientResults;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SearchPresenterTests {

    private Fixtures fixtures = new Fixtures();
    private ClientResults validResults;
    private ClientResults emptyResults;
    private ClientResults threeResults;
    private ClientResults twoResults;
    private ClientResults nullOwner;
    private ClientResults nullLogin;
    private ClientResults emptyHtmlUrl;
    private ClientResults invalidInteger;
    private SearchPresenter systemUnderTest;

    @Before
    public void setup() {
        emptyResults = fixtures.emptyResults();
        validResults = fixtures.validResults();
        threeResults = fixtures.threeResults();
        twoResults = fixtures.twoResults();
        nullOwner = fixtures.twoResultsNullOwner();
        nullLogin = fixtures.twoResultsNullLogin();
        emptyHtmlUrl = fixtures.twoResultsEmptyHtmlUrl();
        invalidInteger = fixtures.twoResultsInvalidInteger();
        systemUnderTest = new SearchPresenter();
    }

    @Test
    public void createSearchResults_nullResults() {
        SearchResults results = systemUnderTest.createSearchResults(null);

        assertNull(results);
    }

    @Test
    public void createSearchResults_emptyResults() {
        List<SearchResult> list = systemUnderTest.createSearchResults(emptyResults).getSearchResults();

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void createSearchResults_threeResults() {
        List<SearchResult> list = systemUnderTest.createSearchResults(threeResults).getSearchResults();

        assertNotNull(list);
        assertTrue(!list.isEmpty());
        assertThat(list.size(), is(3));
    }

    @Test
    public void createSearchResults_twoResults() {
        List<SearchResult> list = systemUnderTest.createSearchResults(twoResults).getSearchResults();

        assertNotNull(list);
        assertTrue(!list.isEmpty());
        assertThat(list.size(), is(2));
    }

    @Test
    public void createSearchResults_NullOwner() {
        List<SearchResult> list = systemUnderTest.createSearchResults(nullOwner).getSearchResults();

        assertNotNull(list);
        assertTrue(!list.isEmpty());
        assertThat(list.size(), is(1));
    }

    @Test
    public void createSearchResults_NullLogin() {
        List<SearchResult> list = systemUnderTest.createSearchResults(nullLogin).getSearchResults();

        assertNotNull(list);
        assertTrue(!list.isEmpty());
        assertThat(list.size(), is(1));
    }

    @Test
    public void createSearchResults_EmptyHtmlUrl() {
        List<SearchResult> list = systemUnderTest.createSearchResults(emptyHtmlUrl).getSearchResults();

        assertNotNull(list);
        assertTrue(!list.isEmpty());
        assertThat(list.size(), is(1));
    }

    @Test
    public void createSearchResults_InvalidInteger() {

        SearchResults results = systemUnderTest.createSearchResults(invalidInteger);

        // if integer cant be parsed from json the entire paring fails and no ClientResults obj is created.
        assertNull(results);
    }

    @Test
    public void createSearchResults_fullResults() {
        List<SearchResult> list = systemUnderTest.createSearchResults(validResults).getSearchResults();

        assertNotNull(list);
        assertTrue(!list.isEmpty());
        assertThat(list.size(), is(3));
    }
}
