package com.amazon.device.ads;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;

class AndroidTargetUtils {
    public static final AndroidClassAdapter defaultAndroidClassAdapter;

    public static class AndroidClassAdapter {
        private final AndroidBuildInfo androidBuildInfo;

        public class WebSettingsAdapter {
            private final WebSettings webSettings;

            public WebSettingsAdapter(WebSettings webSettings) {
                this.webSettings = webSettings;
            }

            public void setMediaPlaybackRequiresUserGesture(boolean require) {
                if (AndroidClassAdapter.this.isAtLeastAndroidAPI(17)) {
                    JellyBeanMR1TargetUtils.setMediaPlaybackRequiresUserGesture(this.webSettings, require);
                }
            }
        }

        public AndroidClassAdapter(AndroidBuildInfo androidBuildInfo) {
            this.androidBuildInfo = androidBuildInfo;
        }

        public WebSettingsAdapter withWebSettings(WebSettings webSettings) {
            return new WebSettingsAdapter(webSettings);
        }

        private boolean isAtLeastAndroidAPI(int api) {
            return AndroidTargetUtils.isAtLeastAndroidAPI(this.androidBuildInfo, api);
        }
    }

    @TargetApi(11)
    private static class HoneycombTargetUtils {
        private HoneycombTargetUtils() {
        }

        public static final void disableHardwareAcceleration(View view) {
            view.setLayerType(1, null);
        }

        protected static final <T> void executeAsyncTaskWithThreadPooling(MobileAdsAsyncTask<T, ?, ?> task, T... params) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        }

        protected static void hideActionBar(Activity activity) {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }

        protected static void removeJavascriptInterface(WebView webView, String name) {
            webView.removeJavascriptInterface(name);
        }

        protected static void enableHardwareAcceleration(Window window) {
            window.setFlags(ViewCompat.MEASURED_STATE_TOO_SMALL, ViewCompat.MEASURED_STATE_TOO_SMALL);
        }
    }

    @TargetApi(17)
    private static class JellyBeanMR1TargetUtils {
        private JellyBeanMR1TargetUtils() {
        }

        public static void setMediaPlaybackRequiresUserGesture(WebSettings webSettings, boolean require) {
            webSettings.setMediaPlaybackRequiresUserGesture(require);
        }
    }

    @TargetApi(16)
    private static class JellyBeanTargetUtils {
        private JellyBeanTargetUtils() {
        }

        public static void setBackgroundForLinerLayout(View view, Drawable background) {
            view.setBackground(background);
        }

        protected static void setImageButtonAlpha(ImageButton imageButton, int alpha) {
            imageButton.setImageAlpha(alpha);
        }

        public static void hideStatusBar(Activity activity) {
            activity.getWindow().getDecorView().setSystemUiVisibility(4);
        }
    }

    @TargetApi(19)
    private static class KitKatTargetUtils {

        static class 1 implements Runnable {
            final /* synthetic */ boolean val$enable;

            1(boolean z) {
                this.val$enable = z;
            }

            public void run() {
                WebView.setWebContentsDebuggingEnabled(this.val$enable);
            }
        }

        private KitKatTargetUtils() {
        }

        public static void enableWebViewDebugging(boolean enable) {
            ThreadUtils.executeOnMainThread(new 1(enable));
        }
    }

    static {
        defaultAndroidClassAdapter = new AndroidClassAdapter(new AndroidBuildInfo());
    }

    private AndroidTargetUtils() {
    }

    public static AndroidClassAdapter getDefaultAndroidClassAdapter() {
        return defaultAndroidClassAdapter;
    }

    public static boolean isAtLeastAndroidAPI(int api) {
        return VERSION.SDK_INT >= api;
    }

    public static boolean isAtLeastAndroidAPI(AndroidBuildInfo androidBuildInfo, int api) {
        return androidBuildInfo.getSDKInt() >= api;
    }

    public static boolean isAtOrBelowAndroidAPI(int api) {
        return VERSION.SDK_INT <= api;
    }

    public static boolean isAtOrBelowAndroidAPI(AndroidBuildInfo androidBuildInfo, int api) {
        return androidBuildInfo.getSDKInt() <= api;
    }

    public static boolean isAndroidAPI(int api) {
        return VERSION.SDK_INT == api;
    }

    public static boolean isAndroidAPI(AndroidBuildInfo androidBuildInfo, int api) {
        return androidBuildInfo.getSDKInt() == api;
    }

    public static boolean isBetweenAndroidAPIs(AndroidBuildInfo androidBuildInfo, int lowerApi, int upperApi) {
        return isAtLeastAndroidAPI(androidBuildInfo, lowerApi) && isAtOrBelowAndroidAPI(androidBuildInfo, upperApi);
    }

    public static final void disableHardwareAcceleration(View view) {
        HoneycombTargetUtils.disableHardwareAcceleration(view);
    }

    public static void setImageButtonAlpha(ImageButton imageButton, int alpha) {
        if (isAtLeastAndroidAPI(16)) {
            JellyBeanTargetUtils.setImageButtonAlpha(imageButton, alpha);
        } else {
            imageButton.setAlpha(alpha);
        }
    }

    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (isAtLeastAndroidAPI(16)) {
            JellyBeanTargetUtils.setBackgroundForLinerLayout(view, drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static <T> void executeAsyncTask(MobileAdsAsyncTask<T, ?, ?> task, T... params) {
        if (isAtLeastAndroidAPI(11)) {
            HoneycombTargetUtils.executeAsyncTaskWithThreadPooling(task, params);
        } else {
            task.execute(params);
        }
    }

    public static void hideActionAndStatusBars(AndroidBuildInfo buildInfo, Activity activity) {
        if (isAtLeastAndroidAPI(buildInfo, 11)) {
            HoneycombTargetUtils.hideActionBar(activity);
        }
        if (isAtLeastAndroidAPI(buildInfo, 16)) {
            JellyBeanTargetUtils.hideStatusBar(activity);
        }
    }

    public static void removeJavascriptInterface(WebView webView, String interfaceName) {
        HoneycombTargetUtils.removeJavascriptInterface(webView, interfaceName);
    }

    public static void enableWebViewDebugging(boolean enable) {
        if (isAtLeastAndroidAPI(19)) {
            KitKatTargetUtils.enableWebViewDebugging(enable);
        }
    }

    public static void enableHardwareAcceleration(AndroidBuildInfo buildInfo, Window window) {
        if (isAtLeastAndroidAPI(buildInfo, 11)) {
            HoneycombTargetUtils.enableHardwareAcceleration(window);
        }
    }

    @TargetApi(11)
    public static boolean isAdTransparent(View adView) {
        if (isAtLeastAndroidAPI(11) && adView.getAlpha() == 0.0f) {
            return true;
        }
        return false;
    }
}
