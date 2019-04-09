package com.kenanozdamar.android.demo.rigelhub;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kenanozdamar.android.demo.rigelhub.dialogs.AlertDialogManager;
import com.kenanozdamar.android.demo.rigelhub.error.ErrorType;
import com.kenanozdamar.android.demo.rigelhub.presenters.SearchPresenter;
import com.kenanozdamar.android.demo.rigelhub.search_results.SearchResultsView;
import com.kenanozdamar.android.demo.rigelhub.search_results.fragments.SearchResultsFragment;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResults;
import com.kenanozdamar.android.demo.rigelhub.web.fragments.WebFragment;
import com.kenanozdamar.android.demo.rigelhub.welcome.fragments.WelcomeFragment;
import com.kenanozdamar.android.demo.services.network.exceptions.NetworkException;

public class MainActivity extends AppCompatActivity implements MainCallbacks, SearchResultsView {

    private static final String TAG = MainActivity.class.getName();

    // region ivar(s)
    private SearchView searchView;
    private Toolbar toolbar;
    private SearchPresenter presenter;
    private View progressSpinner;
    // endregion

    // region Activity overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        progressSpinner = findViewById(R.id.progress_spinner);
        setSupportActionBar(toolbar);
        presenter = new SearchPresenter();
        presenter.register(this);
        if (savedInstanceState == null) displayWelcomeFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(createSearchQueryListener());


        return super.onCreateOptionsMenu(menu);
    }
    // endregion

    // region MainCallbacks overrides
    @Override
    public void displayWeb(String url) {
        Log.d(TAG, url);
        displayWebFragment(url);
    }

    // endregion

    // region fragment navigation
    private void displayWelcomeFragment() {
        Log.d(TAG, "Displaying welcome fragment.");
        WelcomeFragment welcomeFragment = WelcomeFragment.newInstance();
        pushStateless(R.id.main_frame, welcomeFragment, WelcomeFragment.class.getSimpleName(), false);

    }

    private void displaySearchResultsFragment(SearchResults searchResults) {
        Log.d(TAG, "Displaying search results fragment.");
        SearchResultsFragment searchResultsFragment = SearchResultsFragment.newInstance(searchResults);
        pushStateless(R.id.main_frame, searchResultsFragment, SearchResultsFragment.class.getSimpleName(), true);

    }

    private void displayWebFragment(@NonNull String url) {
        WebFragment webFragment = WebFragment.newInstance(url);
        pushStateless(R.id.main_frame, webFragment, WebFragment.class.getSimpleName(), true);
    }
    // endregion

    // region fragment helpers
    private void pushStateless(@IdRes int res,
                                 @NonNull Fragment fragment,
                                 @Nullable String tag,
                                 @NonNull Boolean addToStack) {
        commit(genTransition(res, fragment, tag, addToStack), true);
    }

    private void commit(@NonNull FragmentTransaction transaction, boolean stateless) {
        if (stateless) {
            transaction.commitAllowingStateLoss();
            return;
        }
        transaction.commit();
    }

    private FragmentTransaction genTransition(
            @IdRes int res,
            @NonNull Fragment fragment,
            @Nullable String tag,
            @NonNull Boolean addToStack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(res, fragment, tag);
        if (addToStack && !TextUtils.isEmpty(tag)) {
            transaction.addToBackStack(tag);
        }
        return transaction;
    }
    // endregion


    // region helpers
    private SearchView.OnQueryTextListener createSearchQueryListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "Query submitted.");
                resetSearchView();
                progressSpinner.setVisibility(View.VISIBLE);
                presenter.request(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        };
    }

    private void resetSearchView() {
        searchView.setQuery("", false);
        searchView.setIconified(true);
        searchView.clearFocus();
        toolbar.collapseActionView();
    }
    // endregion

    // region SearchResultsView overrides
    @Override
    public void onError(Throwable exc) {
        progressSpinner.setVisibility(View.GONE);
        if (exc instanceof NetworkException) {
            NetworkException networkException = (NetworkException) exc;
            if (networkException.getCode() == 422) {
                AlertDialogManager.displayErrorAlert(this, ErrorType.EmptySearchResult);

            }
        }
    }

    @Override
    public void showResults(SearchResults results) {
        Log.d(TAG, results.toString());
        progressSpinner.setVisibility(View.GONE);
        if( results != null && results.getSearchResults().size() > 0) displaySearchResultsFragment(results);
    }
    // endregion
}
