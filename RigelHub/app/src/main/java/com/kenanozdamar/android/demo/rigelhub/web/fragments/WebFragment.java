package com.kenanozdamar.android.demo.rigelhub.web.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kenanozdamar.android.demo.rigelhub.R;
import com.kenanozdamar.android.demo.rigelhub.web.client.AppWebViewClient;

public class WebFragment extends Fragment {

    // region TAG.
    @SuppressWarnings("unused")
    private static final String TAG = WebFragment.class.getSimpleName();
    // endregion

    // region constants
    private static final String ARGUMENT_URL = "ARGUMENT_QUERY";
    // endregion

    // region ivar(s)
    private WebView webView;
    private String url;
    private View progressSpinner;
    // endregion

    // region fragment generator
    public static WebFragment newInstance(@NonNull String url){
        WebFragment fragment = new WebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }
    // endregion

    // region fragment overrides

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.web_fragment, container, false);
        webView = rootView.findViewById(R.id.web_view);
        progressSpinner = rootView.findViewById(R.id.progress_spinner);
        webView.setWebViewClient(new AppWebViewClient(progressSpinner));
        getFragmentArguments();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressSpinner.setVisibility(View.VISIBLE);

        if (savedInstanceState == null) {
            webView.loadUrl(url);
        } else {
            webView.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
    // endregion

    // region fragment helpers
    private void getFragmentArguments() {
        Bundle args = getArguments();
        if (args == null) {
            Log.w(TAG, "Fragment args are null.");
            return;
        }

        if (!(args.containsKey(ARGUMENT_URL))) {
            Log.w(TAG, "correct argument not in bundle.");
            return;
        }
        url = args.getString(ARGUMENT_URL);
    }
    // endregion
}
