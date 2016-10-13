package com.mopub.mobileads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.OnCloseListener;
import com.mopub.common.DataKeys;

abstract class BaseInterstitialActivity extends Activity {
    protected AdReport mAdReport;
    private Long mBroadcastIdentifier;
    private CloseableLayout mCloseableLayout;

    class 1 implements OnCloseListener {
        1() {
        }

        public void onClose() {
            BaseInterstitialActivity.this.finish();
        }
    }

    enum JavaScriptWebViewCallbacks {
        WEB_VIEW_DID_APPEAR("webviewDidAppear();"),
        WEB_VIEW_DID_CLOSE("webviewDidClose();");
        
        private String mJavascript;

        private JavaScriptWebViewCallbacks(String javascript) {
            this.mJavascript = javascript;
        }

        protected String getJavascript() {
            return this.mJavascript;
        }

        protected String getUrl() {
            return "javascript:" + this.mJavascript;
        }
    }

    public abstract View getAdView();

    BaseInterstitialActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(intent);
        this.mAdReport = getAdReportFromIntent(intent);
        requestWindowFeature(1);
        getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        View adView = getAdView();
        this.mCloseableLayout = new CloseableLayout(this);
        this.mCloseableLayout.setOnCloseListener(new 1());
        this.mCloseableLayout.addView(adView, new LayoutParams(-1, -1));
        setContentView(this.mCloseableLayout);
    }

    protected void onDestroy() {
        this.mCloseableLayout.removeAllViews();
        super.onDestroy();
    }

    Long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    protected void showInterstitialCloseButton() {
        this.mCloseableLayout.setCloseVisible(true);
    }

    protected void hideInterstitialCloseButton() {
        this.mCloseableLayout.setCloseVisible(false);
    }

    protected static Long getBroadcastIdentifierFromIntent(Intent intent) {
        if (intent.hasExtra(DataKeys.BROADCAST_IDENTIFIER_KEY)) {
            return Long.valueOf(intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1));
        }
        return null;
    }

    @Nullable
    protected static AdReport getAdReportFromIntent(Intent intent) {
        try {
            return (AdReport) intent.getSerializableExtra(DataKeys.AD_REPORT_KEY);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
