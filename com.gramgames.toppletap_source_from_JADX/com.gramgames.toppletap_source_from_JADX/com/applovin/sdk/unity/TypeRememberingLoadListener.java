package com.applovin.sdk.unity;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

public class TypeRememberingLoadListener implements AppLovinAdLoadListener {
    private UnityExtendedLoadListener callback;
    private AppLovinAdSize requestedSize;
    private AppLovinAdType requestedType;

    public TypeRememberingLoadListener(AppLovinAdSize requestedSize, AppLovinAdType requestedType, UnityExtendedLoadListener callback) {
        this.requestedSize = requestedSize;
        this.requestedType = requestedType;
        this.callback = callback;
    }

    public void adReceived(AppLovinAd ad) {
        if (this.callback != null) {
            this.callback.onAdReceived(ad);
        }
    }

    public void failedToReceiveAd(int errorCode) {
        if (this.callback != null) {
            this.callback.onAdLoadFailed(this.requestedSize, this.requestedType, errorCode);
        }
    }
}
