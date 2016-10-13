package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.exoplayer.C;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.AdReport;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mraid.MraidController;
import com.mopub.mraid.MraidController.MraidListener;
import com.mopub.mraid.MraidController.UseCustomCloseListener;
import com.mopub.mraid.MraidWebViewDebugListener;
import com.mopub.mraid.PlacementType;
import com.mopub.network.Networking;

public class MraidActivity extends BaseInterstitialActivity {
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @Nullable
    private MraidController mMraidController;

    static class 1 extends WebViewClient {
        final /* synthetic */ CustomEventInterstitialListener val$customEventInterstitialListener;

        1(CustomEventInterstitialListener customEventInterstitialListener) {
            this.val$customEventInterstitialListener = customEventInterstitialListener;
        }

        public void onPageFinished(WebView view, String url) {
            this.val$customEventInterstitialListener.onInterstitialLoaded();
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            this.val$customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.MRAID_LOAD_ERROR);
        }
    }

    class 2 implements MraidListener {
        2() {
        }

        public void onLoaded(View view) {
            MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getJavascript());
        }

        public void onFailedToLoad() {
            MoPubLog.d("MraidActivity failed to load. Finishing the activity");
            BaseBroadcastReceiver.broadcastAction(MraidActivity.this, MraidActivity.this.getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_FAIL);
            MraidActivity.this.finish();
        }

        public void onClose() {
            MraidActivity.this.mMraidController.loadJavascript(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getJavascript());
            MraidActivity.this.finish();
        }

        public void onExpand() {
        }

        public void onOpen() {
            BaseBroadcastReceiver.broadcastAction(MraidActivity.this, MraidActivity.this.getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_CLICK);
        }
    }

    class 3 implements UseCustomCloseListener {
        3() {
        }

        public void useCustomCloseChanged(boolean useCustomClose) {
            if (useCustomClose) {
                MraidActivity.this.hideInterstitialCloseButton();
            } else {
                MraidActivity.this.showInterstitialCloseButton();
            }
        }
    }

    public static void preRenderHtml(@NonNull Context context, @NonNull CustomEventInterstitialListener customEventInterstitialListener, @NonNull String htmlData) {
        preRenderHtml(customEventInterstitialListener, htmlData, new BaseWebView(context));
    }

    @VisibleForTesting
    static void preRenderHtml(@NonNull CustomEventInterstitialListener customEventInterstitialListener, @NonNull String htmlData, @NonNull BaseWebView dummyWebView) {
        dummyWebView.enablePlugins(false);
        dummyWebView.enableJavascriptCaching();
        dummyWebView.setWebViewClient(new 1(customEventInterstitialListener));
        dummyWebView.loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + "/", htmlData, WebRequest.CONTENT_TYPE_HTML, C.UTF8_NAME, null);
    }

    public static void start(@NonNull Context context, @Nullable AdReport adreport, @NonNull String htmlData, long broadcastIdentifier) {
        try {
            context.startActivity(createIntent(context, adreport, htmlData, broadcastIdentifier));
        } catch (ActivityNotFoundException e) {
            Log.d("MraidInterstitial", "MraidActivity.class not found. Did you declare MraidActivity in your manifest?");
        }
    }

    @VisibleForTesting
    protected static Intent createIntent(@NonNull Context context, @Nullable AdReport adReport, @NonNull String htmlData, long broadcastIdentifier) {
        Intent intent = new Intent(context, MraidActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, htmlData);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        return intent;
    }

    public View getAdView() {
        String htmlData = getIntent().getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        if (htmlData == null) {
            MoPubLog.w("MraidActivity received a null HTML body. Finishing the activity.");
            finish();
            return new View(this);
        }
        this.mMraidController = new MraidController(this, this.mAdReport, PlacementType.INTERSTITIAL);
        this.mMraidController.setDebugListener(this.mDebugListener);
        this.mMraidController.setMraidListener(new 2());
        this.mMraidController.setUseCustomCloseListener(new 3());
        this.mMraidController.loadContent(htmlData);
        return this.mMraidController.getAdContainer();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_SHOW);
        if (VERSION.SDK_INT >= 14) {
            getWindow().setFlags(ViewCompat.MEASURED_STATE_TOO_SMALL, ViewCompat.MEASURED_STATE_TOO_SMALL);
        }
    }

    protected void onPause() {
        if (this.mMraidController != null) {
            this.mMraidController.pause(isFinishing());
        }
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        if (this.mMraidController != null) {
            this.mMraidController.resume();
        }
    }

    protected void onDestroy() {
        if (this.mMraidController != null) {
            this.mMraidController.destroy();
        }
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_DISMISS);
        super.onDestroy();
    }

    @VisibleForTesting
    public void setDebugListener(@Nullable MraidWebViewDebugListener debugListener) {
        this.mDebugListener = debugListener;
        if (this.mMraidController != null) {
            this.mMraidController.setDebugListener(debugListener);
        }
    }
}
