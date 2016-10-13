package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;

public class cm extends cl {
    public cm(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, list, appLovinNativeAdLoadListener);
    }

    public cm(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdVideos", appLovinSdkImpl, list, appLovinNativeAdPrecacheListener);
    }

    private boolean b(NativeAdImpl nativeAdImpl) {
        this.g.w("TaskCacheNativeAdVideos", "Unable to cache video resource " + nativeAdImpl.getSourceVideoUrl());
        a(nativeAdImpl, !q.a(this.h) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES);
        return false;
    }

    protected void a(NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.onNativeAdVideoPreceached(nativeAdImpl);
        }
    }

    protected void a(NativeAdImpl nativeAdImpl, int i) {
        if (this.b != null) {
            this.b.onNativeAdVideoPrecachingFailed(nativeAdImpl, i);
        }
    }

    protected boolean a(NativeAdImpl nativeAdImpl, y yVar) {
        if (AppLovinSdkUtils.isValidString(nativeAdImpl.getSourceVideoUrl())) {
            this.f.getLogger().d("TaskCacheNativeAdVideos", "Beginning slot video caching for ad " + nativeAdImpl.getAdId());
            if (((Boolean) this.f.a(cb.B)).booleanValue()) {
                String a = a(nativeAdImpl.getSourceVideoUrl(), yVar);
                if (a == null) {
                    return b(nativeAdImpl);
                }
                nativeAdImpl.setVideoUrl(a);
            } else {
                this.f.getLogger().d("TaskCacheNativeAdVideos", "Resource caching is disabled, skipping...");
            }
            return true;
        }
        this.f.getLogger().d("TaskCacheNativeAdVideos", "No video attached to ad, nothing to cache...");
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
