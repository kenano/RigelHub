package com.kenanozdamar.android.demo.rigelhub.welcome.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kenanozdamar.android.demo.rigelhub.R;


public class WelcomeFragment extends Fragment {

    // region TAG
    @SuppressWarnings("unused")
    private static final String TAG = WelcomeFragment.class.getSimpleName();
    // endregion

    // region fragment generator
    public static WelcomeFragment newInstance(){
        WelcomeFragment fragment = new WelcomeFragment();
        return fragment;
    }
    // endregion

    // region fragment overrides
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.welcome_fragment, container, false);
        return rootView;
    }

    // endregion
}
