package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import gs.gram.mopub.BuildConfig;

class WebViewFactory {
    private static WebViewFactory instance;
    private final MobileAdsCookieManager cookieManager;
    private final DebugProperties debugProperties;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLoggerFactory loggerFactory;
    private final WebViewConstructor webViewConstructor;
    private boolean webViewDebugging;

    static class MobileAdsCookieManager {
        private boolean cookieSyncManagerCreated;

        MobileAdsCookieManager() {
            this.cookieSyncManagerCreated = false;
        }

        public void createCookieSyncManager(Context context) {
            if (!this.cookieSyncManagerCreated) {
                CookieSyncManager.createInstance(context);
                this.cookieSyncManagerCreated = true;
            }
        }

        public void setCookie(String url, String cookie) {
            CookieManager.getInstance().setCookie(url, cookie);
        }

        public boolean isCookieSyncManagerCreated() {
            return this.cookieSyncManagerCreated;
        }

        public void startSync() {
            CookieSyncManager.getInstance().startSync();
        }

        public void stopSync() {
            CookieSyncManager.getInstance().stopSync();
        }
    }

    static class WebViewConstructor {
        WebViewConstructor() {
        }

        public WebView createWebView(Context context) {
            return new WebView(context);
        }
    }

    static {
        instance = new WebViewFactory();
    }

    protected WebViewFactory() {
        this(MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), DebugProperties.getInstance(), new MobileAdsCookieManager(), new WebViewConstructor());
    }

    WebViewFactory(MobileAdsInfoStore infoStore, MobileAdsLoggerFactory loggerFactory, DebugProperties debugProperties, MobileAdsCookieManager cookieManager, WebViewConstructor webViewConstructor) {
        this.webViewDebugging = false;
        this.infoStore = infoStore;
        this.loggerFactory = loggerFactory;
        this.debugProperties = debugProperties;
        this.cookieManager = cookieManager;
        this.webViewConstructor = webViewConstructor;
    }

    public static final WebViewFactory getInstance() {
        return instance;
    }

    public synchronized WebView createWebView(Context context) {
        WebView webView;
        shouldDebugWebViews();
        webView = this.webViewConstructor.createWebView(context.getApplicationContext());
        this.infoStore.getDeviceInfo().setUserAgentString(webView.getSettings().getUserAgentString());
        webView.getSettings().setUserAgentString(this.infoStore.getDeviceInfo().getUserAgentString());
        this.cookieManager.createCookieSyncManager(context);
        updateAdIdCookie();
        return webView;
    }

    private void updateAdIdCookie() {
        if (this.cookieManager.isCookieSyncManagerCreated()) {
            String adId = this.infoStore.getRegistrationInfo().getAdId();
            if (adId == null) {
                adId = BuildConfig.FLAVOR;
            }
            this.cookieManager.setCookie("http://amazon-adsystem.com", "ad-id=" + adId + "; Domain=.amazon-adsystem.com");
        }
    }

    public boolean isWebViewOk(Context context) {
        try {
            return WebViewDatabase.getInstance(context) != null;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public boolean setJavaScriptEnabledForWebView(boolean enable, WebView webView, String logtag) {
        try {
            webView.getSettings().setJavaScriptEnabled(enable);
            return true;
        } catch (NullPointerException e) {
            this.loggerFactory.createMobileAdsLogger(logtag).w("Could not set JavaScriptEnabled because a NullPointerException was encountered.");
            return false;
        }
    }

    private void shouldDebugWebViews() {
        boolean webViewDebugging = this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_WEBVIEWS, Boolean.valueOf(this.webViewDebugging)).booleanValue();
        if (webViewDebugging != this.webViewDebugging) {
            this.webViewDebugging = webViewDebugging;
            AndroidTargetUtils.enableWebViewDebugging(this.webViewDebugging);
        }
    }
}
