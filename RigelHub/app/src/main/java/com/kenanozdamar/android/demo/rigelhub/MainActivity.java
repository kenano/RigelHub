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

import com.kenanozdamar.android.demo.rigelhub.search_results.fragments.SearchResultsFragment;
import com.kenanozdamar.android.demo.services.ServicesFacade;
import com.kenanozdamar.android.demo.services.githubclient.GithubClientFacade;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private GithubClientFacade clientFacade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ServicesFacade servicesFacade = ServicesFacade.getServiceFacade();
        if (servicesFacade != null) clientFacade = servicesFacade.getGithubClientFacade();
        displaySearchResultsFramgent();
    }

    // endregion


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(createSearchQueryListener());


        return super.onCreateOptionsMenu(menu);
    }

    // region fragment navigation
    private void displaySearchResultsFramgent() {
        Log.d(TAG, "Displaying search results fragment.");
        SearchResultsFragment searchResultsFragment = new SearchResultsFragment();
        pushStateless(R.id.main_frame, searchResultsFragment, null, false);

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
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "Query submitted.");
                if (clientFacade != null) clientFacade.request(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        };
    }
    // endregion
}
