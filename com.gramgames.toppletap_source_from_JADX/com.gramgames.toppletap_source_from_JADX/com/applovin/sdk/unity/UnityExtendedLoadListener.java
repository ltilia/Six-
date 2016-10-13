package com.applovin.sdk.unity;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;

public interface UnityExtendedLoadListener {
    void onAdLoadFailed(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, int i);

    void onAdReceived(AppLovinAd appLovinAd);
}
