package com.kenanozdamar.android.demo.rigelhub.search_results.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kenanozdamar.android.demo.rigelhub.R;
import com.kenanozdamar.android.demo.rigelhub.search_results.recycle_adapters.ResultsRecycleAdapter;

public class SearchResultsFragment extends Fragment {

    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = SearchResultsFragment.class.getSimpleName();
    // endregion

    // region ivar(s)
    private RecyclerView recyclerView;
    // endregion

    // region fragment overrides

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_results_fragment, container, true);
        setupRecyclerView(rootView);

        return super.onCreateView(inflater, container, savedInstanceState);
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
    // endregion

}
