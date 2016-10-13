package com.mopub.common;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event.Builder;
import com.mopub.common.event.MoPubEvents;
import com.mopub.common.util.Drawables;
import com.mopub.mobileads.BaseWebView;
import com.mopub.mobileads.util.WebViews;
import com.mopub.volley.DefaultRetryPolicy;

public class MoPubBrowser extends Activity {
    public static final String DESTINATION_URL_KEY = "URL";
    public static final String DSP_CREATIVE_ID = "mopub-dsp-creative-id";
    private static final int INNER_LAYOUT_ID = 1;
    public static final int MOPUB_BROWSER_REQUEST_CODE = 1;
    private DoubleTimeTracker dwellTimeTracker;
    private ImageButton mBackButton;
    private ImageButton mCloseButton;
    private String mDspCreativeId;
    private ImageButton mForwardButton;
    private ImageButton mRefreshButton;
    private WebView mWebView;

    class 1 extends WebChromeClient {
        1() {
        }

        public void onProgressChanged(WebView webView, int progress) {
            MoPubBrowser.this.setTitle("Loading...");
            MoPubBrowser.this.setProgress(progress * 100);
            if (progress == 100) {
                MoPubBrowser.this.setTitle(webView.getUrl());
            }
        }
    }

    class 2 implements OnClickListener {
        2() {
        }

        public void onClick(View v) {
            if (MoPubBrowser.this.mWebView.canGoBack()) {
                MoPubBrowser.this.mWebView.goBack();
            }
        }
    }

    class 3 implements OnClickListener {
        3() {
        }

        public void onClick(View v) {
            if (MoPubBrowser.this.mWebView.canGoForward()) {
                MoPubBrowser.this.mWebView.goForward();
            }
        }
    }

    class 4 implements OnClickListener {
        4() {
        }

        public void onClick(View v) {
            MoPubBrowser.this.mWebView.reload();
        }
    }

    class 5 implements OnClickListener {
        5() {
        }

        public void onClick(View v) {
            MoPubBrowser.this.finish();
        }
    }

    @NonNull
    public ImageButton getBackButton() {
        return this.mBackButton;
    }

    @NonNull
    public ImageButton getCloseButton() {
        return this.mCloseButton;
    }

    @NonNull
    public ImageButton getForwardButton() {
        return this.mForwardButton;
    }

    @NonNull
    public ImageButton getRefreshButton() {
        return this.mRefreshButton;
    }

    @NonNull
    public WebView getWebView() {
        return this.mWebView;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(-1);
        getWindow().requestFeature(2);
        getWindow().setFeatureInt(2, -1);
        setContentView(getMoPubBrowserView());
        this.dwellTimeTracker = new DoubleTimeTracker();
        initializeWebView();
        initializeButtons();
        enableCookies();
    }

    private void initializeWebView() {
        WebSettings webSettings = this.mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        this.mDspCreativeId = getIntent().getStringExtra(DSP_CREATIVE_ID);
        this.mWebView.loadUrl(getIntent().getStringExtra(DESTINATION_URL_KEY));
        this.mWebView.setWebViewClient(new BrowserWebViewClient(this));
        this.mWebView.setWebChromeClient(new 1());
    }

    private void initializeButtons() {
        this.mBackButton.setBackgroundColor(0);
        this.mBackButton.setOnClickListener(new 2());
        this.mForwardButton.setBackgroundColor(0);
        this.mForwardButton.setOnClickListener(new 3());
        this.mRefreshButton.setBackgroundColor(0);
        this.mRefreshButton.setOnClickListener(new 4());
        this.mCloseButton.setBackgroundColor(0);
        this.mCloseButton.setOnClickListener(new 5());
    }

    private void enableCookies() {
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().startSync();
    }

    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().stopSync();
        WebViews.onPause(this.mWebView, isFinishing());
        this.dwellTimeTracker.pause();
    }

    protected void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().startSync();
        WebViews.onResume(this.mWebView);
        this.dwellTimeTracker.start();
    }

    public void finish() {
        ((ViewGroup) getWindow().getDecorView()).removeAllViews();
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
        this.mWebView = null;
        MoPubEvents.log(new Builder(Name.AD_DWELL_TIME, Category.AD_INTERACTIONS, SamplingRate.AD_INTERACTIONS.getSamplingRate()).withDspCreativeId(this.mDspCreativeId).withPerformanceDurationMs(Double.valueOf(this.dwellTimeTracker.getInterval())).build());
    }

    private View getMoPubBrowserView() {
        LinearLayout moPubBrowserView = new LinearLayout(this);
        moPubBrowserView.setLayoutParams(new LayoutParams(-1, -1));
        moPubBrowserView.setOrientation(MOPUB_BROWSER_REQUEST_CODE);
        RelativeLayout outerLayout = new RelativeLayout(this);
        outerLayout.setLayoutParams(new LayoutParams(-1, -2));
        moPubBrowserView.addView(outerLayout);
        LinearLayout innerLayout = new LinearLayout(this);
        innerLayout.setId(MOPUB_BROWSER_REQUEST_CODE);
        RelativeLayout.LayoutParams innerLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
        innerLayoutParams.addRule(12);
        innerLayout.setLayoutParams(innerLayoutParams);
        innerLayout.setBackgroundDrawable(Drawables.BACKGROUND.createDrawable(this));
        outerLayout.addView(innerLayout);
        this.mBackButton = getButton(Drawables.LEFT_ARROW.createDrawable(this));
        this.mForwardButton = getButton(Drawables.RIGHT_ARROW.createDrawable(this));
        this.mRefreshButton = getButton(Drawables.REFRESH.createDrawable(this));
        this.mCloseButton = getButton(Drawables.CLOSE.createDrawable(this));
        innerLayout.addView(this.mBackButton);
        innerLayout.addView(this.mForwardButton);
        innerLayout.addView(this.mRefreshButton);
        innerLayout.addView(this.mCloseButton);
        this.mWebView = new BaseWebView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(2, MOPUB_BROWSER_REQUEST_CODE);
        this.mWebView.setLayoutParams(layoutParams);
        outerLayout.addView(this.mWebView);
        return moPubBrowserView;
    }

    private ImageButton getButton(Drawable drawable) {
        ImageButton imageButton = new ImageButton(this);
        LayoutParams layoutParams = new LayoutParams(-2, -2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        layoutParams.gravity = 16;
        imageButton.setLayoutParams(layoutParams);
        imageButton.setImageDrawable(drawable);
        return imageButton;
    }

    @Deprecated
    @VisibleForTesting
    void setWebView(WebView webView) {
        this.mWebView = webView;
    }
}
