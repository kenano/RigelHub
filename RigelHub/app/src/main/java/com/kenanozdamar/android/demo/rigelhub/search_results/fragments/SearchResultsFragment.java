package com.kenanozdamar.android.demo.rigelhub.search_results.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kenanozdamar.android.demo.rigelhub.R;
import com.kenanozdamar.android.demo.rigelhub.search_results.SearchResultsPresenter;
import com.kenanozdamar.android.demo.rigelhub.search_results.SearchResultsView;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResults;
import com.kenanozdamar.android.demo.rigelhub.search_results.recycle_adapters.ResultsRecycleAdapter;

public class SearchResultsFragment extends Fragment implements SearchResultsView {

    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = SearchResultsFragment.class.getSimpleName();
    // endregion

    // region constants
    private static final String ARGUMENT_QUERY = "ARGUMENTS_QUERY";
    // endregion

    // region ivar(s)
    private RecyclerView recyclerView;
    private SearchResultsPresenter presenter;
    private String query;
    // endregion

    // region SearchResultsView overrides
    @Override
    public void onError(Throwable exc) {
        Log.w(TAG, exc.getMessage());
    }

    @Override
    public void showResults(SearchResults results) {
        Log.d(TAG, results.toString());
    }
    // endregion

    // region fragment generator
    public static SearchResultsFragment newInstance(@NonNull String searchQuery){
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_QUERY, searchQuery);
        fragment.setArguments(bundle);
        return fragment;
    }
    // endregion

    // region fragment overrides
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchResultsPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_results_fragment, container, false);
        setupRecyclerView(rootView);
        getFragmentArguments();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.request(query);
    }

    // endregion.

    // region helpers
    private void setupRecyclerView(View containerView) {
        recyclerView = containerView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new ResultsRecycleAdapter(getContext());
        recyclerView.setAdapter(adapter);
    }

    private void getFragmentArguments() {
        Bundle args = getArguments();
        if (args == null) {
            Log.w(TAG, "Fragment args are null.");
            return;
        }

        if (!(args.containsKey(ARGUMENT_QUERY))) {
            Log.w(TAG, "correct argument not in bundle.");
            return;
        }
        query = args.getString(ARGUMENT_QUERY);
    }
    // endregion

}
