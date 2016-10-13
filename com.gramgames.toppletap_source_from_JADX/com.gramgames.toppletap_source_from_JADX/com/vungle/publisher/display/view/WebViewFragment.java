package com.vungle.publisher.display.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.display.controller.AdWebViewClient;
import com.vungle.publisher.ek;
import com.vungle.publisher.util.ViewUtils;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class WebViewFragment extends AdFragment {
    protected String c;
    protected WebView d;
    @Inject
    public ek e;
    @Inject
    public AdWebViewClient f;
    @Inject
    public WebViewFactory g;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.c = savedInstanceState.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Logger.v(Logger.AD_TAG, "creating webview");
        Context activity = getActivity();
        View webView = new WebView(this.g.a);
        this.d = webView;
        this.e.a((WebView) webView);
        webView.setWebViewClient(this.f);
        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        settings.setUseWideViewPort(false);
        webView.setBackgroundColor(0);
        webView.setBackgroundResource(0);
        webView.loadUrl(this.c);
        ViewUtils.a(webView);
        View frameLayout = new FrameLayout(activity);
        frameLayout.addView(webView);
        LayoutParams layoutParams = webView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        return frameLayout;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, this.c);
    }
}
