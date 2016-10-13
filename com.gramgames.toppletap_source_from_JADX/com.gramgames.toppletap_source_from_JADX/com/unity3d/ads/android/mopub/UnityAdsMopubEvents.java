package com.unity3d.ads.android.mopub;

import android.app.Activity;
import android.content.Context;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.MoPubErrorCode;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Map;

public class UnityAdsMopubEvents extends CustomEventInterstitial implements IUnityAdsListener {
    private static UnityAdsMopubEvents currentShowingWrapper;
    private String gameId;
    private CustomEventInterstitialListener listener;
    private Activity nextActivity;
    private Map<String, Object> options;
    private UnityAdsMopubEvents wrapperAfterShow;
    private String zoneId;

    public UnityAdsMopubEvents() {
        this.listener = null;
        this.gameId = null;
        this.zoneId = null;
        this.options = null;
        this.wrapperAfterShow = null;
        this.nextActivity = null;
    }

    static {
        currentShowingWrapper = null;
    }

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        this.listener = customEventInterstitialListener;
        if (serverExtras.get(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY) == null || !(serverExtras.get(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY) instanceof String)) {
            this.listener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        this.gameId = (String) serverExtras.get(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY);
        this.zoneId = (String) serverExtras.get("zoneId");
        this.options = new HashMap();
        this.options.putAll(localExtras);
        this.options.putAll(serverExtras);
        if (currentShowingWrapper == null) {
            UnityAds.setDebugMode(true);
            UnityAds.init((Activity) context, this.gameId, this);
            UnityAds.changeActivity((Activity) context);
            UnityAds.setListener(this);
            if (UnityAds.canShow() && UnityAds.canShowAds()) {
                this.listener.onInterstitialLoaded();
                return;
            }
            return;
        }
        currentShowingWrapper.setNextWrapper(this);
        this.nextActivity = (Activity) context;
    }

    private void setNextWrapper(UnityAdsMopubEvents nextWrapper) {
        this.wrapperAfterShow = nextWrapper;
    }

    private void activateNextWrapper() {
        UnityAds.changeActivity(this.nextActivity);
        UnityAds.setListener(this);
        if (UnityAds.canShow() && UnityAds.canShowAds()) {
            this.listener.onInterstitialLoaded();
        }
    }

    protected void showInterstitial() {
        if (UnityAds.canShow() && UnityAds.canShowAds()) {
            UnityAds.setZone(this.zoneId);
            if (UnityAds.show(this.options)) {
                currentShowingWrapper = this;
                return;
            } else {
                this.listener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
                return;
            }
        }
        this.listener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
    }

    protected void onInvalidate() {
    }

    public void onHide() {
        currentShowingWrapper = null;
        if (this.wrapperAfterShow != null) {
            this.wrapperAfterShow.activateNextWrapper();
            this.wrapperAfterShow = null;
        }
        this.listener.onInterstitialDismissed();
    }

    public void onShow() {
        this.listener.onInterstitialShown();
    }

    public void onVideoStarted() {
    }

    public void onVideoCompleted(String rewardItemKey, boolean skipped) {
    }

    public void onFetchCompleted() {
        this.listener.onInterstitialLoaded();
    }

    public void onFetchFailed() {
        this.listener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
    }
}
