package com.kenanozdamar.android.demo.rigelhub.web.client;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AppWebViewClient extends WebViewClient {

    // region ivar(s)
    private View progressSpinner;
    // endregion

    // region constructor
    public AppWebViewClient(View progressSpinner) {
        this.progressSpinner = progressSpinner;
    }
    // endregion

    // region WebViewClient Overrides
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        progressSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressSpinner.setVisibility(View.GONE);
    }
    // endregion
}
