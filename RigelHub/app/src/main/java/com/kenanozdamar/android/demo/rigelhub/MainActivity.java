package com.kenanozdamar.android.demo.rigelhub;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kenanozdamar.android.demo.rigelhub.search_results.fragments.SearchResultsFragment;
import com.kenanozdamar.android.demo.rigelhub.web.fragments.WebFragment;

public class MainActivity extends AppCompatActivity implements MainCallbacks {

    private static final String TAG = MainActivity.class.getName();

    // region ivar(s)
    SearchView searchView;
    Toolbar toolbar;
    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().back
//        getSupportActionBar().setHomeButtonEnabled(true);
    }

    // endregion


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(createSearchQueryListener());


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    // region MainCallbacks overrides
    @Override
    public void displayWeb(String url) {
        Log.d(TAG, url);
        displayWebFragment(url);
    }

    // endregion

    // region fragment navigation
    private void displaySearchResultsFragment(String searchQuery) {
        Log.d(TAG, "Displaying search results fragment.");
        SearchResultsFragment searchResultsFragment = SearchResultsFragment.newInstance(searchQuery);
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
                displaySearchResultsFragment(query);
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
}
