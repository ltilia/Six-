package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.chartboost.sdk.Chartboost;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Map;

class ChartboostInterstitial extends CustomEventInterstitial {
    @NonNull
    private String mLocation;

    ChartboostInterstitial() {
        this.mLocation = ChartboostShared.LOCATION_DEFAULT;
    }

    protected void loadInterstitial(@NonNull Context context, @NonNull CustomEventInterstitialListener interstitialListener, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(interstitialListener);
        Preconditions.checkNotNull(localExtras);
        Preconditions.checkNotNull(serverExtras);
        if (context instanceof Activity) {
            if (serverExtras.containsKey(GooglePlayServicesInterstitial.LOCATION_KEY)) {
                String location = (String) serverExtras.get(GooglePlayServicesInterstitial.LOCATION_KEY);
                if (TextUtils.isEmpty(location)) {
                    location = this.mLocation;
                }
                this.mLocation = location;
            }
            if (!ChartboostShared.getDelegate().hasInterstitialLocation(this.mLocation) || ChartboostShared.getDelegate().getInterstitialListener(this.mLocation) == interstitialListener) {
                Activity activity = (Activity) context;
                try {
                    ChartboostShared.initializeSdk(activity, serverExtras);
                    ChartboostShared.getDelegate().registerInterstitialListener(this.mLocation, interstitialListener);
                    Chartboost.onCreate(activity);
                    Chartboost.onStart(activity);
                    if (Chartboost.hasInterstitial(this.mLocation)) {
                        ChartboostShared.getDelegate().didCacheInterstitial(this.mLocation);
                        return;
                    } else {
                        Chartboost.cacheInterstitial(this.mLocation);
                        return;
                    }
                } catch (NullPointerException e) {
                    interstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                    return;
                } catch (IllegalStateException e2) {
                    interstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                    return;
                }
            }
            interstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        interstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
    }

    protected void showInterstitial() {
        Log.d("MoPub", "Showing Chartboost interstitial ad.");
        Chartboost.showInterstitial(this.mLocation);
    }

    protected void onInvalidate() {
        ChartboostShared.getDelegate().unregisterInterstitialListener(this.mLocation);
    }
}
