package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.factories.HtmlBannerWebViewFactory;
import java.util.Map;

public class HtmlBanner extends CustomEventBanner {
    private HtmlBannerWebView mHtmlBannerWebView;

    protected void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        if (extrasAreValid(serverExtras)) {
            String htmlData = (String) serverExtras.get(DataKeys.HTML_RESPONSE_BODY_KEY);
            try {
                this.mHtmlBannerWebView = HtmlBannerWebViewFactory.create(context, (AdReport) localExtras.get(DataKeys.AD_REPORT_KEY), customEventBannerListener, Boolean.valueOf((String) serverExtras.get(DataKeys.SCROLLABLE_KEY)).booleanValue(), (String) serverExtras.get(DataKeys.REDIRECT_URL_KEY), (String) serverExtras.get(DataKeys.CLICKTHROUGH_URL_KEY));
                AdViewController.setShouldHonorServerDimensions(this.mHtmlBannerWebView);
                this.mHtmlBannerWebView.loadHtmlResponse(htmlData);
                return;
            } catch (ClassCastException e) {
                MoPubLog.e("LocalExtras contained an incorrect type.");
                customEventBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
                return;
            }
        }
        customEventBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
    }

    protected void onInvalidate() {
        if (this.mHtmlBannerWebView != null) {
            this.mHtmlBannerWebView.destroy();
        }
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        return serverExtras.containsKey(DataKeys.HTML_RESPONSE_BODY_KEY);
    }
}
