package com.mopub.mobileads.util;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection.MethodBuilder;
import gs.gram.mopub.BuildConfig;

public class WebViews {

    static class 1 extends WebChromeClient {
        1() {
        }

        public boolean onJsAlert(@NonNull WebView view, @NonNull String url, @NonNull String message, @NonNull JsResult result) {
            MoPubLog.d(message);
            result.confirm();
            return true;
        }

        public boolean onJsConfirm(@NonNull WebView view, @NonNull String url, @NonNull String message, @NonNull JsResult result) {
            MoPubLog.d(message);
            result.confirm();
            return true;
        }

        public boolean onJsPrompt(@NonNull WebView view, @NonNull String url, @NonNull String message, @NonNull String defaultValue, @NonNull JsPromptResult result) {
            MoPubLog.d(message);
            result.confirm();
            return true;
        }

        public boolean onJsBeforeUnload(@NonNull WebView view, @NonNull String url, @NonNull String message, @NonNull JsResult result) {
            MoPubLog.d(message);
            result.confirm();
            return true;
        }
    }

    @TargetApi(11)
    public static void onResume(@NonNull WebView webView) {
        if (VERSION.SDK_INT >= 11) {
            webView.onResume();
            return;
        }
        try {
            new MethodBuilder(webView, "onResume").setAccessible().execute();
        } catch (Exception e) {
        }
    }

    @TargetApi(11)
    public static void onPause(@NonNull WebView webView, boolean isFinishing) {
        if (isFinishing) {
            webView.stopLoading();
            webView.loadUrl(BuildConfig.FLAVOR);
        }
        if (VERSION.SDK_INT >= 11) {
            webView.onPause();
            return;
        }
        try {
            new MethodBuilder(webView, "onPause").setAccessible().execute();
        } catch (Exception e) {
        }
    }

    public static void setDisableJSChromeClient(@NonNull WebView webView) {
        webView.setWebChromeClient(new 1());
    }
}
