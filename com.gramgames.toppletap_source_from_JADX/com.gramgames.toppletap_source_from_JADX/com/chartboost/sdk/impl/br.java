package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.Map;

public class br extends WebView {
    private bq a;
    private boolean b;

    public br(Context context) {
        super(context);
        this.b = false;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void setWebChromeClient(WebChromeClient client) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        if (VERSION.SDK_INT >= 11) {
            settings.setAllowContentAccess(true);
        }
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 11) {
            settings.setBuiltInZoomControls(false);
            settings.setDisplayZoomControls(false);
        }
        if (VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        if (client instanceof bq) {
            this.a = (bq) client;
        }
        super.setWebChromeClient(client);
    }

    public void loadData(String data, String mimeType, String encoding) {
        a();
        super.loadData(data, mimeType, encoding);
    }

    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        a();
        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    public void loadUrl(String url) {
        a();
        super.loadUrl(url);
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        a();
        super.loadUrl(url, additionalHttpHeaders);
    }

    private void a() {
        if (!this.b) {
            this.b = true;
        }
    }
}
