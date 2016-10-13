package com.mopub.mobileads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.AdReport;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.DataKeys;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.factories.HtmlInterstitialWebViewFactory;
import java.io.Serializable;

public class MoPubActivity extends BaseInterstitialActivity {
    private HtmlInterstitialWebView mHtmlInterstitialWebView;

    static class 1 extends WebViewClient {
        final /* synthetic */ CustomEventInterstitialListener val$customEventInterstitialListener;

        1(CustomEventInterstitialListener customEventInterstitialListener) {
            this.val$customEventInterstitialListener = customEventInterstitialListener;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if ("mopub://finishLoad".equals(url)) {
                this.val$customEventInterstitialListener.onInterstitialLoaded();
            } else if ("mopub://failLoad".equals(url)) {
                this.val$customEventInterstitialListener.onInterstitialFailed(null);
            }
            return true;
        }
    }

    class BroadcastingInterstitialListener implements CustomEventInterstitialListener {
        BroadcastingInterstitialListener() {
        }

        public void onInterstitialLoaded() {
            MoPubActivity.this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_APPEAR.getUrl());
        }

        public void onInterstitialFailed(MoPubErrorCode errorCode) {
            BaseBroadcastReceiver.broadcastAction(MoPubActivity.this, MoPubActivity.this.getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_FAIL);
            MoPubActivity.this.finish();
        }

        public void onInterstitialShown() {
        }

        public void onInterstitialClicked() {
            BaseBroadcastReceiver.broadcastAction(MoPubActivity.this, MoPubActivity.this.getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_CLICK);
        }

        public void onLeaveApplication() {
        }

        public void onInterstitialDismissed() {
        }
    }

    public static void start(Context context, String htmlData, AdReport adReport, boolean isScrollable, String redirectUrl, String clickthroughUrl, CreativeOrientation creativeOrientation, long broadcastIdentifier) {
        try {
            context.startActivity(createIntent(context, htmlData, adReport, isScrollable, redirectUrl, clickthroughUrl, creativeOrientation, broadcastIdentifier));
        } catch (ActivityNotFoundException e) {
            Log.d("MoPubActivity", "MoPubActivity not found - did you declare it in AndroidManifest.xml?");
        }
    }

    static Intent createIntent(Context context, String htmlData, AdReport adReport, boolean isScrollable, String redirectUrl, String clickthroughUrl, CreativeOrientation orientation, long broadcastIdentifier) {
        Intent intent = new Intent(context, MoPubActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, htmlData);
        intent.putExtra(DataKeys.SCROLLABLE_KEY, isScrollable);
        intent.putExtra(DataKeys.CLICKTHROUGH_URL_KEY, clickthroughUrl);
        intent.putExtra(DataKeys.REDIRECT_URL_KEY, redirectUrl);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.CREATIVE_ORIENTATION_KEY, orientation);
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        return intent;
    }

    static void preRenderHtml(Context context, AdReport adReport, CustomEventInterstitialListener customEventInterstitialListener, String htmlData) {
        HtmlInterstitialWebView dummyWebView = HtmlInterstitialWebViewFactory.create(context, adReport, customEventInterstitialListener, false, null, null);
        dummyWebView.enablePlugins(false);
        dummyWebView.enableJavascriptCaching();
        dummyWebView.setWebViewClient(new 1(customEventInterstitialListener));
        dummyWebView.loadHtmlResponse(htmlData);
    }

    public View getAdView() {
        Intent intent = getIntent();
        boolean isScrollable = intent.getBooleanExtra(DataKeys.SCROLLABLE_KEY, false);
        String redirectUrl = intent.getStringExtra(DataKeys.REDIRECT_URL_KEY);
        String clickthroughUrl = intent.getStringExtra(DataKeys.CLICKTHROUGH_URL_KEY);
        String htmlResponse = intent.getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        this.mHtmlInterstitialWebView = HtmlInterstitialWebViewFactory.create(getApplicationContext(), this.mAdReport, new BroadcastingInterstitialListener(), isScrollable, redirectUrl, clickthroughUrl);
        this.mHtmlInterstitialWebView.loadHtmlResponse(htmlResponse);
        return this.mHtmlInterstitialWebView;
    }

    protected void onCreate(Bundle savedInstanceState) {
        CreativeOrientation requestedOrientation;
        super.onCreate(savedInstanceState);
        Serializable orientationExtra = getIntent().getSerializableExtra(DataKeys.CREATIVE_ORIENTATION_KEY);
        if (orientationExtra == null || !(orientationExtra instanceof CreativeOrientation)) {
            requestedOrientation = CreativeOrientation.UNDEFINED;
        } else {
            requestedOrientation = (CreativeOrientation) orientationExtra;
        }
        DeviceUtils.lockOrientation(this, requestedOrientation);
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_SHOW);
    }

    protected void onDestroy() {
        this.mHtmlInterstitialWebView.loadUrl(JavaScriptWebViewCallbacks.WEB_VIEW_DID_CLOSE.getUrl());
        this.mHtmlInterstitialWebView.destroy();
        BaseBroadcastReceiver.broadcastAction(this, getBroadcastIdentifier().longValue(), EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_DISMISS);
        super.onDestroy();
    }
}
