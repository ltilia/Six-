package com.mopub.mobileads;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

public class FacebookInterstitial extends CustomEventInterstitial implements InterstitialAdListener {
    public static final String PLACEMENT_ID_KEY = "placement_id";
    private InterstitialAd mFacebookInterstitial;
    private CustomEventInterstitialListener mInterstitialListener;

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> serverExtras) {
        this.mInterstitialListener = customEventInterstitialListener;
        if (extrasAreValid(serverExtras)) {
            this.mFacebookInterstitial = new InterstitialAd(context, (String) serverExtras.get(PLACEMENT_ID_KEY));
            this.mFacebookInterstitial.setAdListener(this);
            this.mFacebookInterstitial.loadAd();
            return;
        }
        this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
    }

    protected void showInterstitial() {
        if (this.mFacebookInterstitial == null || !this.mFacebookInterstitial.isAdLoaded()) {
            Log.d("MoPub", "Tried to show a Facebook interstitial ad before it finished loading. Please try again.");
            if (this.mInterstitialListener != null) {
                onError(this.mFacebookInterstitial, AdError.INTERNAL_ERROR);
                return;
            } else {
                Log.d("MoPub", "Interstitial listener not instantiated. Please load interstitial again.");
                return;
            }
        }
        this.mFacebookInterstitial.show();
    }

    protected void onInvalidate() {
        if (this.mFacebookInterstitial != null) {
            this.mFacebookInterstitial.destroy();
            this.mFacebookInterstitial = null;
        }
    }

    public void onAdLoaded(Ad ad) {
        Log.d("MoPub", "Facebook interstitial ad loaded successfully.");
        this.mInterstitialListener.onInterstitialLoaded();
    }

    public void onError(Ad ad, AdError error) {
        Log.d("MoPub", "Facebook interstitial ad failed to load.");
        if (error == AdError.NO_FILL) {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
        } else if (error == AdError.INTERNAL_ERROR) {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
        } else {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.UNSPECIFIED);
        }
    }

    public void onInterstitialDisplayed(Ad ad) {
        Log.d("MoPub", "Showing Facebook interstitial ad.");
        this.mInterstitialListener.onInterstitialShown();
    }

    public void onAdClicked(Ad ad) {
        Log.d("MoPub", "Facebook interstitial ad clicked.");
        this.mInterstitialListener.onInterstitialClicked();
    }

    public void onInterstitialDismissed(Ad ad) {
        Log.d("MoPub", "Facebook interstitial ad dismissed.");
        this.mInterstitialListener.onInterstitialDismissed();
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        String placementId = (String) serverExtras.get(PLACEMENT_ID_KEY);
        return placementId != null && placementId.length() > 0;
    }

    @Deprecated
    InterstitialAd getInterstitialAd() {
        return this.mFacebookInterstitial;
    }
}
