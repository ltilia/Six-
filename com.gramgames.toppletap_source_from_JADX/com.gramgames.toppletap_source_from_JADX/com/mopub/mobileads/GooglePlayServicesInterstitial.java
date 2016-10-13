package com.mopub.mobileads;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public class GooglePlayServicesInterstitial extends CustomEventInterstitial {
    public static final String AD_UNIT_ID_KEY = "adUnitID";
    public static final String LOCATION_KEY = "location";
    private InterstitialAd mGoogleInterstitialAd;
    private CustomEventInterstitialListener mInterstitialListener;

    private class InterstitialAdListener extends AdListener {
        private InterstitialAdListener() {
        }

        public void onAdClosed() {
            Log.d("MoPub", "Google Play Services interstitial ad dismissed.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialDismissed();
            }
        }

        public void onAdFailedToLoad(int errorCode) {
            Log.d("MoPub", "Google Play Services interstitial ad failed to load.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
            }
        }

        public void onAdLeftApplication() {
            Log.d("MoPub", "Google Play Services interstitial ad clicked.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialClicked();
            }
        }

        public void onAdLoaded() {
            Log.d("MoPub", "Google Play Services interstitial ad loaded successfully.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialLoaded();
            }
        }

        public void onAdOpened() {
            Log.d("MoPub", "Showing Google Play Services interstitial ad.");
            if (GooglePlayServicesInterstitial.this.mInterstitialListener != null) {
                GooglePlayServicesInterstitial.this.mInterstitialListener.onInterstitialShown();
            }
        }
    }

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> serverExtras) {
        this.mInterstitialListener = customEventInterstitialListener;
        if (extrasAreValid(serverExtras)) {
            String adUnitId = (String) serverExtras.get(AD_UNIT_ID_KEY);
            this.mGoogleInterstitialAd = new InterstitialAd(context);
            this.mGoogleInterstitialAd.setAdListener(new InterstitialAdListener());
            this.mGoogleInterstitialAd.setAdUnitId(adUnitId);
            try {
                this.mGoogleInterstitialAd.loadAd(new Builder().setRequestAgent("MoPub").build());
                return;
            } catch (NoClassDefFoundError e) {
                this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
                return;
            }
        }
        this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
    }

    protected void showInterstitial() {
        if (this.mGoogleInterstitialAd.isLoaded()) {
            this.mGoogleInterstitialAd.show();
        } else {
            Log.d("MoPub", "Tried to show a Google Play Services interstitial ad before it finished loading. Please try again.");
        }
    }

    protected void onInvalidate() {
        if (this.mGoogleInterstitialAd != null) {
            this.mGoogleInterstitialAd.setAdListener(null);
        }
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        return serverExtras.containsKey(AD_UNIT_ID_KEY);
    }

    @Deprecated
    InterstitialAd getGoogleInterstitialAd() {
        return this.mGoogleInterstitialAd;
    }
}
