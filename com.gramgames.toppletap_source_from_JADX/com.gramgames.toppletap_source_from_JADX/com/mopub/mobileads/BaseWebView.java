package com.mopub.mobileads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import com.google.android.exoplayer.C;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.VersionCode;
import com.mopub.common.util.Views;
import com.mopub.mobileads.util.WebViews;
import gs.gram.mopub.BuildConfig;

public class BaseWebView extends WebView {
    private static boolean sDeadlockCleared;
    protected boolean mIsDestroyed;

    static {
        sDeadlockCleared = false;
    }

    public BaseWebView(Context context) {
        super(context.getApplicationContext());
        enablePlugins(false);
        restrictDeviceContentAccess();
        WebViews.setDisableJSChromeClient(this);
        if (!sDeadlockCleared) {
            clearWebViewDeadlock(getContext());
            sDeadlockCleared = true;
        }
    }

    public void destroy() {
        this.mIsDestroyed = true;
        Views.removeFromParent(this);
        removeAllViews();
        super.destroy();
    }

    protected void enablePlugins(boolean enabled) {
        if (!VersionCode.currentApiLevel().isAtLeast(VersionCode.JELLY_BEAN_MR2)) {
            if (enabled) {
                getSettings().setPluginState(PluginState.ON);
            } else {
                getSettings().setPluginState(PluginState.OFF);
            }
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void enableJavascriptCaching() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setAppCachePath(getContext().getCacheDir().getAbsolutePath());
    }

    private void restrictDeviceContentAccess() {
        getSettings().setAllowFileAccess(false);
        if (VERSION.SDK_INT >= 11) {
            getSettings().setAllowContentAccess(false);
        }
        if (VERSION.SDK_INT >= 16) {
            getSettings().setAllowFileAccessFromFileURLs(false);
            getSettings().setAllowUniversalAccessFromFileURLs(false);
        }
    }

    private void clearWebViewDeadlock(@NonNull Context context) {
        if (VERSION.SDK_INT == 19) {
            WebView webView = new WebView(context.getApplicationContext());
            webView.setBackgroundColor(0);
            webView.loadDataWithBaseURL(null, BuildConfig.FLAVOR, WebRequest.CONTENT_TYPE_HTML, C.UTF8_NAME, null);
            LayoutParams params = new LayoutParams();
            params.width = 1;
            params.height = 1;
            params.type = 2005;
            params.flags = 16777240;
            params.format = -2;
            params.gravity = 8388659;
            ((WindowManager) context.getSystemService("window")).addView(webView, params);
        }
    }

    @Deprecated
    @VisibleForTesting
    void setIsDestroyed(boolean isDestroyed) {
        this.mIsDestroyed = isDestroyed;
    }
}
