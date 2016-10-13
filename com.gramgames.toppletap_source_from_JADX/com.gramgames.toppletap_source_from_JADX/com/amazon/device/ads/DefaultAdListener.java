package com.amazon.device.ads;

import android.graphics.Rect;

public class DefaultAdListener implements ExtendedAdListener {
    private static final String LOGTAG;
    private final MobileAdsLogger logger;

    static {
        LOGTAG = DefaultAdListener.class.getSimpleName();
    }

    public DefaultAdListener() {
        this(LOGTAG);
    }

    DefaultAdListener(String logtag) {
        this(new MobileAdsLoggerFactory(), logtag);
    }

    DefaultAdListener(MobileAdsLoggerFactory loggerFactory, String logtag) {
        this.logger = loggerFactory.createMobileAdsLogger(logtag);
    }

    MobileAdsLogger getLogger() {
        return this.logger;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        this.logger.d("Default ad listener called - AdLoaded.");
    }

    public void onAdFailedToLoad(Ad ad, AdError error) {
        this.logger.d("Default ad listener called - Ad Failed to Load. Error code: %s, Error Message: %s", error.getCode(), error.getMessage());
    }

    public void onAdExpanded(Ad ad) {
        this.logger.d("Default ad listener called - Ad Will Expand.");
    }

    public void onAdCollapsed(Ad ad) {
        this.logger.d("Default ad listener called - Ad Collapsed.");
    }

    public void onAdDismissed(Ad ad) {
        this.logger.d("Default ad listener called - Ad Dismissed.");
    }

    public void onAdResized(Ad ad, Rect positionOnScreen) {
        this.logger.d("Default ad listener called - Ad Resized.");
    }

    public void onAdExpired(Ad ad) {
        this.logger.d("Default ad listener called - Ad Expired.");
    }
}
