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
import android.widget.ImageView;
import android.widget.TextView;

import com.kenanozdamar.android.demo.rigelhub.MainCallbacks;
import com.kenanozdamar.android.demo.rigelhub.R;
import com.kenanozdamar.android.demo.rigelhub.dialogs.AlertDialogManager;
import com.kenanozdamar.android.demo.rigelhub.error.ErrorType;
import com.kenanozdamar.android.demo.rigelhub.search_results.SearchResultsPresenter;
import com.kenanozdamar.android.demo.rigelhub.search_results.SearchResultsView;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResult;
import com.kenanozdamar.android.demo.rigelhub.search_results.models.SearchResults;
import com.kenanozdamar.android.demo.rigelhub.search_results.recycle_adapters.ResultsRecycleAdapter;
import com.kenanozdamar.android.demo.services.network.exceptions.NetworkException;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchResultsFragment extends Fragment implements SearchResultsView, FragmentCallbacks {

    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = SearchResultsFragment.class.getSimpleName();
    // endregion

    // region constants
    private static final String ARGUMENT_QUERY = "ARGUMENT_QUERY";
    private static final String SAVE_STATE_AVATAR_URL = "AVATAR_URL";
    private static final String SAVE_STATE_ORG_NAME = "ORG_NAME";
    private static final String SAVE_STATE_DATA_LIST = "DATA_LIST";
    // endregion

    // region ivar(s)
    private ResultsRecycleAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView avatarImg;
    private TextView orgName;
    private SearchResultsPresenter presenter;
    private String query;
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
        adapter = new ResultsRecycleAdapter(getContext());
        adapter.register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_results_fragment, container, false);
        avatarImg = rootView.findViewById(R.id.image_view_org_avatar);
        orgName = rootView.findViewById(R.id.text_view_org_name);
        setupRecyclerView(rootView);
        getFragmentArguments();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.register(this);

        if (savedInstanceState == null) {
            presenter.request(query);
        } else {
            adapter.setData((ArrayList<SearchResult>) savedInstanceState.getSerializable(SAVE_STATE_DATA_LIST));
            loadImage(savedInstanceState.getString(SAVE_STATE_AVATAR_URL),
                    imgLoadListener(savedInstanceState.getString(SAVE_STATE_ORG_NAME)));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unregister();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "Save instance state");
        SearchResult firstResult = adapter.getDataItem(0);
        outState.putString(SAVE_STATE_AVATAR_URL, firstResult.getAvatarUrl());
        outState.putString(SAVE_STATE_ORG_NAME, firstResult.getOrgName());
        outState.putSerializable(SAVE_STATE_DATA_LIST, (ArrayList<SearchResult>) adapter.getData());
    }

    // endregion.

    // region helpers
    private void setupRecyclerView(View containerView) {
        recyclerView = containerView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
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

    private void loadImage(String url, Callback callback) {
        Picasso.get()
                .load(url)
                .into(avatarImg, callback);
    }
    // endregion

    // region Picasso callback
    private Callback imgLoadListener(String name) {
        return new Callback() {
            @Override
            public void onSuccess() {
                orgName.setText(name);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {
                orgName.setText(name);
                adapter.notifyDataSetChanged();
            }
        };
    }
    // endregion

    // region SearchResultsView overrides
    @Override
    public void onError(Throwable exc) {
        if (exc instanceof NetworkException) {
            NetworkException networkException = (NetworkException) exc;
            if (networkException.getCode() == 422) {
                AlertDialogManager.displayErrorAlert(getContext(), ErrorType.EmptySearchResult);

            }
        }
    }

    @Override
    public void showResults(SearchResults results) {
        Log.d(TAG, results.toString());
        SearchResult firstResult = results.getSearchResults().get(0);
        adapter.setData(results.getSearchResults());
        // todo what if results size is 0?
        loadImage(firstResult.getAvatarUrl(), imgLoadListener(firstResult.getOrgName()));
    }


    @Override
    public void onListItemSelected(int position) {
        ((MainCallbacks) getActivity()).displayWeb(adapter.getDataItem(position).getWebUrl());
    }
    // endregion
}
