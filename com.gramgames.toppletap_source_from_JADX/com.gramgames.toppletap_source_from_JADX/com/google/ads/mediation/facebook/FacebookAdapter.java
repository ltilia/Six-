package com.google.ads.mediation.facebook;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.games.GamesStatusCodes;

public final class FacebookAdapter implements MediationInterstitialAdapter, MediationBannerAdapter {
    private static final String PLACEMENT_PARAMETER = "pubid";
    private static final String TAG = "FacebookAdapter";
    private AdView mAdView;
    private MediationBannerListener mBannerListener;
    private InterstitialAd mInterstitialAd;
    private MediationInterstitialListener mInterstitialListener;
    private RelativeLayout mWrappedAdView;

    private class BannerListener implements AdListener {
        private BannerListener() {
        }

        public void onAdClicked(Ad ad) {
            FacebookAdapter.this.mBannerListener.onAdClicked(FacebookAdapter.this);
            FacebookAdapter.this.mBannerListener.onAdOpened(FacebookAdapter.this);
        }

        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mBannerListener.onAdLoaded(FacebookAdapter.this);
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mBannerListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }
    }

    private class InterstitialListener implements InterstitialAdListener {
        private InterstitialListener() {
        }

        public void onAdClicked(Ad ad) {
        }

        public void onAdLoaded(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdLoaded(FacebookAdapter.this);
        }

        public void onError(Ad ad, AdError adError) {
            String errorMessage = adError.getErrorMessage();
            if (!TextUtils.isEmpty(errorMessage)) {
                Log.w(FacebookAdapter.TAG, errorMessage);
            }
            FacebookAdapter.this.mInterstitialListener.onAdFailedToLoad(FacebookAdapter.this, FacebookAdapter.this.convertErrorCode(adError));
        }

        public void onInterstitialDismissed(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdClosed(FacebookAdapter.this);
        }

        public void onInterstitialDisplayed(Ad ad) {
            FacebookAdapter.this.mInterstitialListener.onAdOpened(FacebookAdapter.this);
        }
    }

    public void onDestroy() {
        if (this.mAdView != null) {
            this.mAdView.destroy();
        }
        if (this.mInterstitialAd != null) {
            this.mInterstitialAd.destroy();
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public View getBannerView() {
        return this.mWrappedAdView;
    }

    public void requestBannerAd(Context context, MediationBannerListener listener, Bundle serverParameters, AdSize adSize, MediationAdRequest adRequest, Bundle mediationExtras) {
        if (context != null && serverParameters != null && adSize != null) {
            this.mBannerListener = listener;
            String placementId = serverParameters.getString(PLACEMENT_PARAMETER);
            if (placementId == null) {
                Log.w(TAG, "Fail to request banner Ad, placementId is null");
                this.mBannerListener.onAdFailedToLoad(this, 0);
                return;
            }
            com.facebook.ads.AdSize facebookAdSize = getAdSize(context, adSize);
            if (facebookAdSize == null) {
                Log.w(TAG, "The input ad size " + adSize.toString() + " is not supported at this moment.");
                this.mBannerListener.onAdFailedToLoad(this, 3);
                return;
            }
            this.mAdView = new AdView(context, placementId, facebookAdSize);
            this.mAdView.setAdListener(new BannerListener());
            buildAdRequest(adRequest);
            LayoutParams wrappedLayoutParams = new LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context));
            this.mWrappedAdView = new RelativeLayout(context);
            this.mWrappedAdView.setLayoutParams(wrappedLayoutParams);
            this.mWrappedAdView.addView(this.mAdView);
            this.mAdView.loadAd();
        }
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener listener, Bundle serverParameters, MediationAdRequest adRequest, Bundle mediationExtras) {
        if (context != null && serverParameters != null) {
            this.mInterstitialListener = listener;
            String placementId = serverParameters.getString(PLACEMENT_PARAMETER);
            if (placementId == null) {
                Log.w(TAG, "Fail to request interstitial Ad, placementId is null");
                this.mInterstitialListener.onAdFailedToLoad(this, 0);
                return;
            }
            this.mInterstitialAd = new InterstitialAd(context, placementId);
            this.mInterstitialAd.setAdListener(new InterstitialListener());
            buildAdRequest(adRequest);
            this.mInterstitialAd.loadAd();
        }
    }

    public void showInterstitial() {
        if (this.mInterstitialAd.isAdLoaded()) {
            this.mInterstitialAd.show();
        }
    }

    private int convertErrorCode(AdError adError) {
        if (adError == null) {
            return 0;
        }
        int errorCode = adError.getErrorCode();
        if (errorCode == GamesStatusCodes.STATUS_REQUEST_UPDATE_TOTAL_FAILURE || errorCode == GamesStatusCodes.STATUS_REQUEST_UPDATE_PARTIAL_SUCCESS) {
            return 0;
        }
        if (errorCode == AdError.NETWORK_ERROR_CODE) {
            return 2;
        }
        if (errorCode == AdError.LOAD_TOO_FREQUENTLY_ERROR_CODE) {
            return 1;
        }
        return 3;
    }

    private void buildAdRequest(MediationAdRequest adRequest) {
        boolean z = true;
        if (adRequest != null) {
            if (adRequest.taggedForChildDirectedTreatment() != 1) {
                z = false;
            }
            AdSettings.setIsChildDirected(z);
        }
    }

    private com.facebook.ads.AdSize getAdSize(Context context, AdSize adSize) {
        if (adSize.getWidth() == com.facebook.ads.AdSize.BANNER_320_50.getWidth() && adSize.getHeight() == com.facebook.ads.AdSize.BANNER_320_50.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_320_50;
        }
        int heightInDip = pixelToDip(adSize.getHeightInPixels(context));
        if (heightInDip == com.facebook.ads.AdSize.BANNER_HEIGHT_50.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_HEIGHT_50;
        }
        if (heightInDip == com.facebook.ads.AdSize.BANNER_HEIGHT_90.getHeight()) {
            return com.facebook.ads.AdSize.BANNER_HEIGHT_90;
        }
        if (heightInDip == com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250.getHeight()) {
            return com.facebook.ads.AdSize.RECTANGLE_HEIGHT_250;
        }
        return null;
    }

    private int pixelToDip(int pixel) {
        return Math.round(((float) pixel) / Resources.getSystem().getDisplayMetrics().density);
    }
}
