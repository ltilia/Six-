package com.mopub.mobileads;

import com.mopub.common.CacheService;
import com.mopub.common.DataKeys;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.VastManager.VastManagerListener;
import com.mopub.mobileads.factories.VastManagerFactory;
import java.util.Map;

class VastVideoInterstitial extends ResponseBodyInterstitial implements VastManagerListener {
    private CustomEventInterstitialListener mCustomEventInterstitialListener;
    private VastManager mVastManager;
    private String mVastResponse;
    private VastVideoConfig mVastVideoConfig;

    VastVideoInterstitial() {
    }

    protected void extractExtras(Map<String, String> serverExtras) {
        this.mVastResponse = (String) serverExtras.get(DataKeys.HTML_RESPONSE_BODY_KEY);
    }

    protected void preRenderHtml(CustomEventInterstitialListener customEventInterstitialListener) {
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        if (CacheService.initializeDiskCache(this.mContext)) {
            this.mVastManager = VastManagerFactory.create(this.mContext);
            this.mVastManager.prepareVastVideoConfiguration(this.mVastResponse, this, this.mAdReport.getDspCreativeId(), this.mContext);
            return;
        }
        this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_CACHE_ERROR);
    }

    public void showInterstitial() {
        BaseVideoPlayerActivity.startVast(this.mContext, this.mVastVideoConfig, this.mBroadcastIdentifier);
    }

    public void onInvalidate() {
        if (this.mVastManager != null) {
            this.mVastManager.cancel();
        }
        super.onInvalidate();
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
        if (vastVideoConfig == null) {
            this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.VIDEO_DOWNLOAD_ERROR);
            return;
        }
        this.mVastVideoConfig = vastVideoConfig;
        this.mCustomEventInterstitialListener.onInterstitialLoaded();
    }

    @Deprecated
    String getVastResponse() {
        return this.mVastResponse;
    }

    @Deprecated
    void setVastManager(VastManager vastManager) {
        this.mVastManager = vastManager;
    }
}
