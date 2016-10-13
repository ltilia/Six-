package com.mopub.mobileads;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.mopub.common.util.Views;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import java.util.Map;

class GooglePlayServicesBanner extends CustomEventBanner {
    public static final String AD_HEIGHT_KEY = "adHeight";
    public static final String AD_UNIT_ID_KEY = "adUnitID";
    public static final String AD_WIDTH_KEY = "adWidth";
    public static final String LOCATION_KEY = "location";
    private CustomEventBannerListener mBannerListener;
    private AdView mGoogleAdView;

    private class AdViewListener extends AdListener {
        private AdViewListener() {
        }

        public void onAdClosed() {
        }

        public void onAdFailedToLoad(int errorCode) {
            Log.d("MoPub", "Google Play Services banner ad failed to load.");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_NO_FILL);
            }
        }

        public void onAdLeftApplication() {
        }

        public void onAdLoaded() {
            Log.d("MoPub", "Google Play Services banner ad loaded successfully. Showing ad...");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerLoaded(GooglePlayServicesBanner.this.mGoogleAdView);
            }
        }

        public void onAdOpened() {
            Log.d("MoPub", "Google Play Services banner ad clicked.");
            if (GooglePlayServicesBanner.this.mBannerListener != null) {
                GooglePlayServicesBanner.this.mBannerListener.onBannerClicked();
            }
        }
    }

    GooglePlayServicesBanner() {
    }

    protected void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> serverExtras) {
        this.mBannerListener = customEventBannerListener;
        if (extrasAreValid(serverExtras)) {
            String adUnitId = (String) serverExtras.get(AD_UNIT_ID_KEY);
            int adWidth = Integer.parseInt((String) serverExtras.get(AD_WIDTH_KEY));
            int adHeight = Integer.parseInt((String) serverExtras.get(AD_HEIGHT_KEY));
            this.mGoogleAdView = new AdView(context);
            this.mGoogleAdView.setAdListener(new AdViewListener());
            this.mGoogleAdView.setAdUnitId(adUnitId);
            AdSize adSize = calculateAdSize(adWidth, adHeight);
            if (adSize == null) {
                this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                return;
            }
            this.mGoogleAdView.setAdSize(adSize);
            try {
                this.mGoogleAdView.loadAd(new Builder().setRequestAgent("MoPub").build());
                return;
            } catch (NoClassDefFoundError e) {
                this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_NO_FILL);
                return;
            }
        }
        this.mBannerListener.onBannerFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
    }

    protected void onInvalidate() {
        Views.removeFromParent(this.mGoogleAdView);
        if (this.mGoogleAdView != null) {
            this.mGoogleAdView.setAdListener(null);
            this.mGoogleAdView.destroy();
        }
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        try {
            Integer.parseInt((String) serverExtras.get(AD_WIDTH_KEY));
            Integer.parseInt((String) serverExtras.get(AD_HEIGHT_KEY));
            return serverExtras.containsKey(AD_UNIT_ID_KEY);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private AdSize calculateAdSize(int width, int height) {
        if (width <= AdSize.BANNER.getWidth() && height <= AdSize.BANNER.getHeight()) {
            return AdSize.BANNER;
        }
        if (width <= AdSize.MEDIUM_RECTANGLE.getWidth() && height <= AdSize.MEDIUM_RECTANGLE.getHeight()) {
            return AdSize.MEDIUM_RECTANGLE;
        }
        if (width <= AdSize.FULL_BANNER.getWidth() && height <= AdSize.FULL_BANNER.getHeight()) {
            return AdSize.FULL_BANNER;
        }
        if (width > AdSize.LEADERBOARD.getWidth() || height > AdSize.LEADERBOARD.getHeight()) {
            return null;
        }
        return AdSize.LEADERBOARD;
    }

    @Deprecated
    AdView getGoogleAdView() {
        return this.mGoogleAdView;
    }
}
